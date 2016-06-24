package com.qf.day24aidlservice;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by asus on 2016/3/24.
 */
public class Mobile implements Parcelable
{
    private String brand;//品牌
    private int memorey;//内存大小

    public Mobile(String brand, int memorey)
    {
        this.brand = brand;
        this.memorey = memorey;
    }

    //用序列化对象创建java对象时使用的构造方法
    //读取顺序需要和写入的顺序一致
    protected Mobile(Parcel in)
    {
        brand = in.readString();
        memorey = in.readInt();
    }

    ////必须实现这个接口，用于取出对象的时候回调
    public static final Creator<Mobile> CREATOR = new Creator<Mobile>()
    {
        ////反序列话对象的时候回调,从序列化对象中得到java对象
        @Override
        public Mobile createFromParcel(Parcel in)
        {
            return new Mobile(in);
        }

        ////反序列话对象的时候回调,得到对象数组
        @Override
        public Mobile[] newArray(int size)
        {
            return new Mobile[size];
        }
    };

    public String getBrand()
    {
        return brand;
    }

    public void setBrand(String brand)
    {
        this.brand = brand;
    }

    public int getMemorey()
    {
        return memorey;
    }

    public void setMemorey(int memorey)
    {
        this.memorey = memorey;
    }

    public Mobile()
    {
    }

    @Override
    public String toString()
    {
        return "Mobile{" +
                "brand='" + brand + '\'' +
                ", memorey=" + memorey +
                '}';
    }

    //内容描述方法，默认返回0即可
    @Override
    public int describeContents()
    {
        return 0;
    }

    //该方法的作用是将对象中的数据写入外部提供的Parcel中，
    // 实现序列化，必须按照成员变量声明的顺序进行
    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(brand);
        dest.writeInt(memorey);
    }
}
