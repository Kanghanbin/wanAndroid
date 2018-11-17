package kanghb.com.wanandroid.contract;

import kanghb.com.wanandroid.base.BasePresenter;
import kanghb.com.wanandroid.base.BaseView;
import kanghb.com.wanandroid.model.bean.DoneListBean;
import kanghb.com.wanandroid.model.bean.ToDoBean;
import retrofit2.http.Field;
import retrofit2.http.Path;

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
