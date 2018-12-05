package com.kanghanbin.wanandroid.contract;

import com.kanghanbin.wanandroid.base.BasePresenter;
import com.kanghanbin.wanandroid.base.BaseView;
import com.kanghanbin.wanandroid.model.bean.ToDoBean;

/**
 * 创建时间：2018/11/16
 * 编写人：kanghb
 * 功能描述：
 */
public interface TodoDetailContract {
    interface View extends BaseView {
        void showUpdateResult(ToDoBean toDoBean);

        void showAddResult(ToDoBean toDoBean);


    }

    interface Presenter extends BasePresenter<View> {

        void updateToDo(int id,String title, String content, String date, int type, int status);

        void addToDo(String title, String content, String date, int type);

    }
}
