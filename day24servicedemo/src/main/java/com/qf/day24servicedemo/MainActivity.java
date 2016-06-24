package com.qf.day24servicedemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
{

    ITiantian iTiantian = null;

    private ServiceConnection conn = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder)
        {

            //1.将Binder对象转换成接口的引用
            iTiantian = (ITiantian) binder;
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

        iTiantian.sing("两只老虎");
    }
    public void clickBind(View view){

        Intent intent = new Intent(MainActivity.this, TianTianService.class);
        bindService(intent, conn, BIND_AUTO_CREATE);
    }


}
