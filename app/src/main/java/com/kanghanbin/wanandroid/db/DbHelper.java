package com.kanghanbin.wanandroid.db;

import org.litepal.LitePal;

import java.util.Iterator;
import java.util.List;

import com.kanghanbin.wanandroid.model.local.LocalHotKey;
import com.kanghanbin.wanandroid.util.Constant;

/**
 * 创建时间：2018/11/12
 * 编写人：kanghb
 * 功能描述：
 */
public class DbHelper implements IDb {

    private static DbHelper mDbHelper;
    private List<LocalHotKey> mLocalHotKeys;
    private String data;
    private LocalHotKey mLocalHotKey;

    private DbHelper() {
    }

    public static DbHelper getInstatce() {
        if (mDbHelper == null) {
            synchronized (DbHelper.class) {
                if (mDbHelper == null) {
                    mDbHelper = new DbHelper();
                }
            }
        }
        return mDbHelper;
    }

    @Override
    public List<LocalHotKey> getLoaclHotKeys() {
        return LitePal.findAll(LocalHotKey.class);
    }


    @Override
    public void clearLocalHotKey() {
        LitePal.deleteAll(LocalHotKey.class);
    }

    @Override
    public void addLocalHotKey(String data) {
        mLocalHotKeys = getLoaclHotKeys();
        this.data = data;
        createLocalHotKey(data);
        if (isLocalHotKeyForward()) {
            return;
        } else {
            if (mLocalHotKeys.size() < Constant.HISTORY_LIST_SIZE) {
                mLocalHotKey.save();
            } else {
                mLocalHotKeys.remove(0);
                mLocalHotKeys.add(mLocalHotKey);
                clearLocalHotKey();
                LitePal.saveAll(mLocalHotKeys);
            }
        }

        mLocalHotKey.save();
    }

    private void createLocalHotKey(String data) {
        mLocalHotKey = new LocalHotKey();
        mLocalHotKey.setData(data);
        mLocalHotKey.setDate(System.currentTimeMillis());
    }

    private boolean isLocalHotKeyForward() {
        //重复搜索时进行历史记录前移
        Iterator<LocalHotKey> iterator = mLocalHotKeys.iterator();
        //不要在foreach循环中进行元素的remove、add操作，使用Iterator模式
        while (iterator.hasNext()) {
            LocalHotKey localHotKey = iterator.next();
            if (localHotKey.getData().equals(data)) {
                mLocalHotKeys.remove(localHotKey);
                mLocalHotKeys.add(mLocalHotKey);
                clearLocalHotKey();
                LitePal.saveAll(mLocalHotKeys);
                return true;
            }
        }
        return false;
    }
}
