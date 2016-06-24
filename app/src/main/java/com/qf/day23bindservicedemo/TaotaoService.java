package com.qf.day23bindservicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/*
*绑定服务的步骤：
* 服务端：
* 1.定义类继承Service,注册
* 2.重写onBind方法，参考重写onBind方法的步骤
*
*
* 重写onBind方法的步骤：
* 1.定义类继承Binder类，在其中加入返回服务对象的方法
*     Binder是IBinder的子类
*
* 2.实例化IBinder,通过onBind返回
*
*
* 客户端：
* 1.创建intent对象
* 2.创建类继承ServiceConnection，重写onServiceConnected方法，实例化
* 3.通过调用bindService，将intent对象和ServiceConnection对象传参进去，启动服务即可
*
* 如果绑定成功，服务端onBind方法中返回的IBInder对象会被客户端的ServiceConnection对象的
* onServiceConnected方法接收
*
* 可以从IBInder对象中获得服务端传回的服务对象。就可以调用服务中的方法了
* */
public class TaotaoService extends Service
{

    public  void sing(String name){
        Log.e("print","taotao 在 唱歌："+name);
    }


    public class TaoTaoBinder extends Binder
    {
        public TaotaoService getTaoTaoService(){
            return TaotaoService.this;
        }
    }
    private TaoTaoBinder binder = new TaoTaoBinder();

    //绑定服务的时候回调，返回的是用于服务和客户端进行通信的对象（类似于经纪人）
    //会返回到客户端的connection对象的onServiceConnected方法中
    //可以把服务对象封装到Ibinder对象中，客户端就可以获得服务对象
    @Override
    public IBinder onBind(Intent intent)
    {
        Log.e("print", "onBind......"+binder.hashCode());
       return  binder;
    }

    @Override
    public void onCreate()
    {
        Log.e("print", "onCreate......");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.e("print", "onStartCommand......");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent)
    {
        Log.e("print", "onUnbind......");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy()
    {
        Log.e("print", "onDestroy......");
        super.onDestroy();
    }
}
