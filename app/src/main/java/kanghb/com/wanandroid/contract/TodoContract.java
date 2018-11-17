package kanghb.com.wanandroid.contract;

import kanghb.com.wanandroid.base.BasePresenter;
import kanghb.com.wanandroid.base.BaseView;
import kanghb.com.wanandroid.model.bean.DoneListBean;
import kanghb.com.wanandroid.model.bean.ToDoBean;

/**
 * 创建时间：2018/11/16
 * 编写人：kanghb
 * 功能描述：
 */
public interface TodoContract {
    interface View extends BaseView {
        void showNotDoneListBean(DoneListBean notDoListBean);

        void showMoreNotDoneListBean(DoneListBean notDoListBean);

        void showListDoneBean(DoneListBean doneListBean);

        void showMoreListDoneBean(DoneListBean doneListBean);

        void showDeleteToDoBean(int pos);

        void showDoneResult(int pos, ToDoBean toDoBean);
    }

    interface Presenter extends BasePresenter<View> {
        void getListNotDoneList(int type, int page);

        void getListDoneList(int type, int page);

        void getMoreListNotDoneList(int type, int page);

        void getMoreListDoneList(int type, int page);

        void deleteToDoBean(int id, int pos);

        void doneToDo(int id, int status, int pos);

    }
}
