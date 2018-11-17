package kanghb.com.wanandroid.db;

import java.util.List;

import kanghb.com.wanandroid.model.local.LocalHotKey;

/**
 * 创建时间：2018/11/12
 * 编写人：kanghb
 * 功能描述：
 */
public interface IDb {

    List<LocalHotKey> getLoaclHotKeys();

    void addLocalHotKey(String data);

    void clearLocalHotKey();

}
