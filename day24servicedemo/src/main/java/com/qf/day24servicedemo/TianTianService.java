package com.qf.day24servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/*
* 服务端：
* 1.定义接口ITianTian将服务中需要提供给客户端使用的方法声明出来
* 2.定义类继承Binder并且实现ITianTian接口，实现接口中的方法
* 3.在onBind中返回自定义的Binder的对象
*
*
* 客户端：
* 1.在onServiceConnected方法中将接收到的服务端传来的Binder对象转换成接口对象
* 2.通过接口调用服务中的方法即可
* */
public class TianTianService extends Service
{
    public TianTianService()
    {
    }

    //服务的方法
    public void sing(String name){
        Log.e("print", "小甜甜唱歌---->"+name);
    }

    public class TianTianBinder extends Binder implements ITiantian{
       /* public TianTianService getServiceInstance(){
            return  TianTianService.this;
        }*/

        //内部类Binder的方法
        @Override
        public void sing(String name)
        {
            //调用服务的方法
            TianTianService.this.sing(name);
        }
    }

    private TianTianBinder binder = new TianTianBinder();
    @Override
    public IBinder onBind(Intent intent)
    {
        Log.e("print", "onBind---->");

        return binder;
    }

    @Override
    public void onCreate()
    {
        Log.e("print", "onCreate---->");
        super.onCreate();
    }
}
