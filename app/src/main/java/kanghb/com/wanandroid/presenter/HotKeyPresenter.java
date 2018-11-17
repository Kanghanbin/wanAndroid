package kanghb.com.wanandroid.presenter;

import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.functions.Consumer;
import kanghb.com.wanandroid.base.RxPresenter;
import kanghb.com.wanandroid.contract.HotKeyContract;
import kanghb.com.wanandroid.db.DbHelper;
import kanghb.com.wanandroid.http.ApiService;
import kanghb.com.wanandroid.http.BaseResponse;
import kanghb.com.wanandroid.http.BaseSubscriber;
import kanghb.com.wanandroid.http.RetrofitHelper;
import kanghb.com.wanandroid.http.RxUtil;
import kanghb.com.wanandroid.model.bean.HotKey;
import kanghb.com.wanandroid.model.local.LocalHotKey;

/**
 * 创建时间：2018/11/12
 * 编写人：kanghb
 * 功能描述：
 */
public class HotKeyPresenter extends RxPresenter<HotKeyContract.View> implements HotKeyContract.Presenter {
    private DbHelper dbHelper;

    public HotKeyPresenter() {
        dbHelper = DbHelper.getInstatce();
    }

    @Override

    public void getHotKeys() {
        addSubscribe(apiService.getHotKey()
                .compose(RxUtil.<BaseResponse<List<HotKey>>>rxFlowableSchedulerHelper())
                .compose(RxUtil.<List<HotKey>>handleResult())
                .subscribeWith(new BaseSubscriber<List<HotKey>>(mView) {
                    @Override
                    public void onNext(List<HotKey> hotKeyList) {
                        mView.showHotKeyResults(hotKeyList);
                    }
                }));
    }

    @Override
    public void getLoaclHotKeys() {
        addSubscribe(Flowable.create(new FlowableOnSubscribe<List<LocalHotKey>>() {
            @Override
            public void subscribe(FlowableEmitter<List<LocalHotKey>> emitter) throws Exception {
                List<LocalHotKey> localHotKeyList = dbHelper.getLoaclHotKeys();
                emitter.onNext(localHotKeyList);
            }
        }, BackpressureStrategy.BUFFER)
                .compose(RxUtil.<List<LocalHotKey>>rxFlowableSchedulerHelper())
                .subscribe(new Consumer<List<LocalHotKey>>() {
                    @Override
                    public void accept(List<LocalHotKey> localHotKeyList) throws Exception {
                        mView.showLocalHotKeys(localHotKeyList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }));

    }


    @Override
    public void addLocalHotKey(final String data) {
        addSubscribe(Flowable.create(new FlowableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(FlowableEmitter<Boolean> emitter) throws Exception {
                dbHelper.addLocalHotKey(data);
                emitter.onNext(true);
            }
        }, BackpressureStrategy.BUFFER).compose(RxUtil.<Boolean>rxFlowableSchedulerHelper())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        mView.jumpToSearchListActivity();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }));
    }

    @Override
    public void clearLocalHotKey() {
        addSubscribe(Flowable.create(new FlowableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(FlowableEmitter<Boolean> emitter) throws Exception {
                dbHelper.clearLocalHotKey();
                emitter.onNext(true);
            }
        }, BackpressureStrategy.BUFFER).compose(RxUtil.<Boolean>rxFlowableSchedulerHelper())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        mView.showClearResult();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    }
                }));


    }
}
