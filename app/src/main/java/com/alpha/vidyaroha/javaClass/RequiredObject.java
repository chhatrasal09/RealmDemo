package com.alpha.vidyaroha.javaClass;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by alpha on 11/8/17.
 */

public class RequiredObject extends RealmObject {

    @PrimaryKey
    private String userName;
    private int count;
    private byte[] picture1;
    private byte[] picture2;
    private String dailyTimeStamp;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public byte[] getPicture1() {
        return picture1;
    }

    public void setPicture1(byte[] picture1) {
        this.picture1 = picture1;
    }

    public byte[] getPicture2() {
        return picture2;
    }

    public void setPicture2(byte[] picture2) {
        this.picture2 = picture2;
    }

    public String getDailyTimeStamp() {
        return dailyTimeStamp;
    }

    public void setDailyTimeStamp(String dailyTimeStamp) {
        this.dailyTimeStamp = dailyTimeStamp;
    }
}
