package com.qf.day24client;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.qf.day24aidlservice.IJingJingAidlInterface;
import com.qf.day24aidlservice.Mobile;

public class MainActivity extends AppCompatActivity
{
    IJingJingAidlInterface iIntreface = null;
    private ServiceConnection conn = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder)
        {
            iIntreface = IJingJingAidlInterface.Stub.asInterface(binder);
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

    public  void clickStart(View view){
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("com.qf.day24aidlservice",
                "com.qf.day24aidlservice.JingJingService");

        intent.setComponent(componentName);
        startService(intent);

    }

    public  void clickBind(View view){
        Intent intent = new Intent();
        //组件名对象
        ComponentName componentName = new ComponentName("com.qf.day24aidlservice",
                "com.qf.day24aidlservice.JingJingService");

        //设置组件名的目的就是告诉系统要绑定的服务的组件名
        intent.setComponent(componentName);

        bindService(intent, conn, BIND_AUTO_CREATE);

    }

    public  void clickSing(View view){
        if (iIntreface != null)
            try
            {
                iIntreface.sing("小酒窝");
            }
            catch (RemoteException e)
            {
                e.printStackTrace();
            }
    }


    public void clickSend(View view){

        if (iIntreface != null){
            try
            {
                iIntreface.sendMobile(new Mobile("苹果iphone se",1024));
            }
            catch (RemoteException e)
            {
                e.printStackTrace();
            }
        }
    }
}
