package com.qf.day24aidlservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
/*
* aidl服务端的步骤：
* 1.在应用中创建一个aidl文件，在其中声明接口，在接口中声明服务需要提供给客户端使用的方法
*    注意:a)aidl文件的名字和接口的名字必须相同
*         b)接口前面和方法的前面不能加任何修饰符（public fina static 等都不能加）
*         c)aidl文件中声明的方法的参数和返回值只能是基本类型的数据（String, int, float , boolean,）
*         以及实现过Parcelable接口的对象
*
*
*         如果要传递自定义对象到服务端：
*         1.定义Mobile类实现Parcelable接口
*         2.在服务端项目中增加aidl文件，用于声明自定义的类
*            Mobile.aidl   其中加入 parcelable Mobile;
*         3.在接口的aidl文件中导入类，在接口声明前增加 import com.qf.day24aidlservice.Mobile;
*           在用到自定义的方法声明前面加入in  例如：void sendMobile(in Mobile mo);
*         4.重构项目，在服务中的binder类的定义处实现stub类的所有的方法
*
*
*         在其他应用的客户端接收自定义对象：
*         1.拷贝java类和aidl文件到客户端项目
*         2.重构项目
*         3.通过接口调用方法即可
*
*
* 2.重构项目，编译器就会自动生成对应的java接口文件，
*    并且在这个接口文件中会自动生成一个内部类stub,
* 3.在service类中定义内部类继承stub类，实现抽象方法
* 4.在service的onBind方法中返回stub的子类对象即可
*
* 其他应用作为客户端：
* 1.复制服务端的Aidl文件到客户端的应用：将aidl目录整个复制到...day24client\src\main目录下
*    如果服务端提供的方法中传递了自定类的对象的话，还需要拷贝相应的java类，保持包名一致
* 2.重构项目
* 3.在客户端绑定服务，在ServiceConnection对象的onServiceConnected方法中，使用stub的asInterface方法将binder对象转换成接口对象
* 4.用接口对象调用方法即可
*
*
*
*
* 本应用的客户端:
* 1.在onServiceConnected方法中，使用stub的asInterface方法将binder对象转换成接口对象
* 2.用接口对象调用方法即可
* */
public class JingJingService extends Service
{
    public JingJingService()
    {
    }

    public void  sing(String name){
        Log.e("print","静静在唱歌："+name);
    }
    //Stub是编译工具自动根据aidl文件生成的java接口中的一个内部类
    //原型是：class Stub extends android.os.Binder implements IJingJingAidlInterface
    private class JingJingBinder extends IJingJingAidlInterface.Stub {

        @Override
        public void sing(String name) throws RemoteException
        {
            //调用服务中的唱歌的方法
            JingJingService.this.sing(name);
        }

        @Override
        public void sendMobile(Mobile mo) throws RemoteException
        {
            Log.e("print","静静收到了礼物："+mo.toString());
        }
    }

    private Binder binder = new JingJingBinder();
    @Override
    public IBinder onBind(Intent intent)
    {
        Log.e("print","静静onBind。。。。。");
        return binder;
    }

    @Override
    public void onCreate()
    {
        Log.e("print","静静onCreate。。。。。");
        super.onCreate();
    }

    @Override
    public void onDestroy()
    {
        Log.e("print","静静onDestroy。。。。。");
        super.onDestroy();
    }
}
