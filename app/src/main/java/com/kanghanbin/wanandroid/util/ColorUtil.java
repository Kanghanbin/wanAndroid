package com.kanghanbin.wanandroid.util;

import java.util.Random;

/**
 * 创建时间：2018/11/9
 * 编写人：kanghb
 * 功能描述：
 */
public class ColorUtil {
    /**
     * 产生随机颜色
     * @return
     */
    public static int randomTagColor() {
        int randomNum = new Random().nextInt();
        int position = randomNum % Constant.TAB_COLORS.length;
        if (position < 0) {
            position = -position;
        }
        return Constant.TAB_COLORS[position];
    }

}
