package com.qf.day23bindservicedemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/*
*绑定服务:绑定它的组件退出，服务也会退出
* bindService()
*  onCreate.....
*  onBind......
*
* unBindService()----组件退出，必须调用,不能解绑多次，解绑之前要判断
* onUnBind。。。。
* onDestroy.....
*
* */

public class MainActivity extends AppCompatActivity
{
    private TaotaoService taotaoService = null;

    //类似于联系经纪人的工具
    private ServiceConnection conn = new ServiceConnection()
    {
        //绑定成功回调
        //参数一：连接成功的组件名，就是服务
        //参数二：服务端返回的IBinder对象：从IBinder对象中获得服务对象
        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
            Log.e("print", "onServiceConnected....." + service.hashCode());

            //从IBinder对象中获得服务对象
            //1.IBinder对象向下转型
            TaotaoService.TaoTaoBinder taoTaoBinder = (TaotaoService.TaoTaoBinder) service;
            //2.获得service对象
            taotaoService = taoTaoBinder.getTaoTaoService();

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

    public void clickBind(View view)
    {
        Intent intent = new Intent(MainActivity.this, TaotaoService.class);

        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    public void clickSing(View view)
    {
        if (taotaoService != null)
            taotaoService.sing("咱们屯里人");
    }


    public void clickUnbind(View view)
    {
        unbindService(conn);

    }


    public void clickStart(View view)
    {
        Intent intent = new Intent(MainActivity.this, TaotaoService.class);
        startService(intent);

    }

    public void clickStop(View view)
    {
        Intent intent = new Intent(MainActivity.this, TaotaoService.class);
        stopService(intent);
    }



}
