package kanghb.com.wanandroid.presenter;

import kanghb.com.wanandroid.R;
import kanghb.com.wanandroid.base.RxPresenter;
import kanghb.com.wanandroid.contract.TodoDetailContract;
import kanghb.com.wanandroid.http.BaseResponse;
import kanghb.com.wanandroid.http.BaseSubscriber;
import kanghb.com.wanandroid.http.RxUtil;
import kanghb.com.wanandroid.model.bean.ToDoBean;
import kanghb.com.wanandroid.util.Constant;

/**
 * 创建时间：2018/11/16
 * 编写人：kanghb
 * 功能描述：
 */
public class TodoDetailPresenter extends RxPresenter<TodoDetailContract.View> implements TodoDetailContract.Presenter {
    @Override
    public void updateToDo(int id, String title, String content, String date, int type, int status) {
        addSubscribe(apiService.updateToDo(id, title, content, date, type, status)
                .compose(RxUtil.<BaseResponse<ToDoBean>>rxFlowableSchedulerHelper())
                .compose(RxUtil.<ToDoBean>handleResult())
                .subscribeWith(new BaseSubscriber<ToDoBean>(mView) {
                    @Override
                    public void onNext(ToDoBean toDoBean) {
                        mView.showUpdateResult(toDoBean);
                    }
                }));
    }

    @Override
    public void addToDo(String title, String content, String date, int type) {
        addSubscribe(apiService.addToDo(title, content, date, type)
                .compose(RxUtil.<BaseResponse<ToDoBean>>rxFlowableSchedulerHelper())
                .compose(RxUtil.<ToDoBean>handleResult())
                .subscribeWith(new BaseSubscriber<ToDoBean>(mView) {
                    @Override
                    public void onNext(ToDoBean toDoBean) {
                        mView.showAddResult(toDoBean);
                    }
                }));
    }

}
