package com.kanghanbin.wanandroid.presenter;

import com.kanghanbin.wanandroid.MyApplication;
import com.kanghanbin.wanandroid.R;
import com.kanghanbin.wanandroid.base.RxPresenter;
import com.kanghanbin.wanandroid.contract.TodoContract;
import com.kanghanbin.wanandroid.http.BaseResponse;
import com.kanghanbin.wanandroid.http.BaseSubscriber;
import com.kanghanbin.wanandroid.http.RxUtil;
import com.kanghanbin.wanandroid.model.bean.DoneListBean;
import com.kanghanbin.wanandroid.model.bean.ToDoBean;

/**
 * 创建时间：2018/11/16
 * 编写人：kanghb
 * 功能描述：
 */
public class TodoPresenter extends RxPresenter<TodoContract.View> implements TodoContract.Presenter {
    @Override
    public void getListNotDoneList(int type, int page) {
        addSubscribe(apiService.listNotDoneList(type, page)
                .compose(RxUtil.<BaseResponse<DoneListBean>>rxFlowableSchedulerHelper())
                .compose(RxUtil.<DoneListBean>handleResult()).subscribeWith(new BaseSubscriber<DoneListBean>(mView) {
                    @Override
                    public void onNext(DoneListBean notDoListBean) {
                        mView.showNotDoneListBean(notDoListBean);
                    }
                }));
    }

    @Override
    public void getListDoneList(int type, int page) {
        addSubscribe(apiService.listDoneList(type, page)
                .compose(RxUtil.<BaseResponse<DoneListBean>>rxFlowableSchedulerHelper())
                .compose(RxUtil.<DoneListBean>handleResult()).subscribeWith(new BaseSubscriber<DoneListBean>(mView) {
                    @Override
                    public void onNext(DoneListBean doneListBean) {
                        mView.showListDoneBean(doneListBean);
                    }
                }));
    }

    @Override
    public void getMoreListNotDoneList(int type, int page) {
        addSubscribe(apiService.listNotDoneList(type, page)
                .compose(RxUtil.<BaseResponse<DoneListBean>>rxFlowableSchedulerHelper())
                .compose(RxUtil.<DoneListBean>handleResult()).subscribeWith(new BaseSubscriber<DoneListBean>(mView) {
                    @Override
                    public void onNext(DoneListBean notDoListBean) {
                        mView.showMoreNotDoneListBean(notDoListBean);
                    }
                }));
    }

    @Override
    public void getMoreListDoneList(int type, int page) {
        addSubscribe(apiService.listDoneList(type, page)
                .compose(RxUtil.<BaseResponse<DoneListBean>>rxFlowableSchedulerHelper())
                .compose(RxUtil.<DoneListBean>handleResult()).subscribeWith(new BaseSubscriber<DoneListBean>(mView) {
                    @Override
                    public void onNext(DoneListBean doneListBean) {
                        mView.showMoreListDoneBean(doneListBean);
                    }
                }));

    }

    @Override
    public void deleteToDoBean(int id, final int pos) {
        addSubscribe(apiService.deleteTodo(id)
                .compose(RxUtil.<BaseResponse<String>>rxFlowableSchedulerHelper())
                .compose(RxUtil.<String>handleCollectResult(MyApplication.getInstance().getString(R.string.delete_success)))
                .subscribeWith(new BaseSubscriber<String>(mView) {
                    @Override
                    public void onNext(String s) {
                        mView.showToast(s);
                        mView.showDeleteToDoBean(pos);
                    }
                }));
    }

    @Override
    public void doneToDo(int id, int status, final int pos) {
        addSubscribe(apiService.doneToDo(id, status)
                .compose(RxUtil.<BaseResponse<ToDoBean>>rxFlowableSchedulerHelper())
                .compose(RxUtil.<ToDoBean>handleResult())
                .subscribeWith(new BaseSubscriber<ToDoBean>(mView) {
                    @Override
                    public void onNext(ToDoBean toDoBean) {
                        mView.showToast("切换状态成功");
                        mView.showDoneResult(pos, toDoBean);
                    }
                }));

    }
}
