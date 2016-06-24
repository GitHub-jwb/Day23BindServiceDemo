// IJingJingAidlInterface.aidl
package com.qf.day24aidlservice;

// Declare any non-default types here with import statements
//aidl中接口不能加任何的修饰符
import com.qf.day24aidlservice.Mobile;

interface IJingJingAidlInterface {
    void sing(String name);
    void sendMobile(in Mobile mo);
}
