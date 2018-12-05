package com.kanghanbin.wanandroid.model.local;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

/**
 * 创建时间：2018/11/12
 * 编写人：kanghb
 * 功能描述：
 */
public class LocalHotKey extends LitePalSupport implements Serializable {

    private int id;
    private String data;
    private long date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data == null ? "" : data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
