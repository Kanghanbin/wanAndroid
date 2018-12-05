package com.kanghanbin.wanandroid.eventtype;

/**
 * 创建时间：2018/11/20
 * 编写人：kanghb
 * 功能描述：
 */
public class EventNightMode {
    private boolean isNight;

    public EventNightMode(boolean isNight) {
        this.isNight = isNight;
    }

    public boolean isNight() {
        return isNight;
    }

    public void setNight(boolean night) {
        isNight = night;
    }
}
