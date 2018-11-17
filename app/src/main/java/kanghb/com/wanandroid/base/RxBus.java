package kanghb.com.wanandroid.base;

import io.reactivex.Flowable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;

/**
 * 创建时间：2018/11/8
 * 编写人：kanghb
 * 功能描述：有背压处理（Backpressure）的 Rxbus
 */
public class RxBus {
    private final FlowableProcessor<Object> mBus;

    /**
     *
     * PublishSubject只会把在订阅发生的时间点之后来自原始Flowable的数据发射给观察者
     */

    private RxBus() {
        // toSerialized method made bus thread safe
        mBus = PublishProcessor.create().toSerialized();
    }

    public static RxBus getDefault() {
        return Holder.BUS;
    }

    /**
     * 提供了一个新的事件
     * @param obj
     */
    public void post(Object obj) {
        mBus.onNext(obj);
    }

    /**
     * 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
     * @param eventType
     * @param <T>
     * @return
     */
    public <T> Flowable<T> toFlowable(Class<T> eventType) {
        return mBus.ofType(eventType);
    }

    public Flowable<Object> toFlowable() {
        return mBus;
    }

    public boolean hasSubscribers() {
        return mBus.hasSubscribers();
    }

    private static class Holder {
        private static final RxBus BUS = new RxBus();
    }

}
