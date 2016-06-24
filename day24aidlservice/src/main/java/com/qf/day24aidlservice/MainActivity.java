package com.qf.day24aidlservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity
{
    private IJingJingAidlInterface iInterface = null;

    private ServiceConnection conn = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder)
        {
            iInterface = IJingJingAidlInterface.Stub.asInterface(binder);
        }

        @Override
        public void onServiceDisconnected(ComponentName name)
        {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickSing(View view){
        if (iInterface != null)
            try
            {
                iInterface.sing("双节棍！");
            }
            catch (RemoteException e)
            {
                e.printStackTrace();
            }
    }
    public void clickBind(View view){
        Intent intent = new Intent(MainActivity.this, JingJingService.class);
        bindService(intent,conn,BIND_AUTO_CREATE);
    }

    public void clickSend(View view)
    {
        if (iInterface != null)
        {
            try
            {
                iInterface.sendMobile(new Mobile("诺基亚",128));
            }
            catch (RemoteException e)
            {
                e.printStackTrace();
            }
        }
    }
}
