package kanghb.com.wanandroid.http;

import org.reactivestreams.Publisher;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * 创建时间：2018/10/26
 * 编写人：kanghb
 * 功能描述：
 */
public class RxUtil {
    /**
     * 统一线程处理
     * @param <T>
     * @return
     */
    public static <T>FlowableTransformer<T,T> rxFlowableSchedulerHelper(){
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 返回结果统一处理
     * @param <T>
     * @return
     */
    public static <T>FlowableTransformer<BaseResponse<T>,T> handleResult(){
        return new FlowableTransformer<BaseResponse<T>, T>() {
            @Override
            public Publisher<T> apply(Flowable<BaseResponse<T>> upstream) {
                return upstream.flatMap(new Function<BaseResponse<T>, Flowable<T>>() {
                    @Override
                    public Flowable<T> apply(BaseResponse<T> tBaseResponse) throws Exception {
                        if(tBaseResponse.getErrorCode() == 0){
                            return createData(tBaseResponse.getData());
                        }else {
                            return Flowable.error(new ApiException(tBaseResponse.getErrorMsg(),tBaseResponse.getErrorCode()));
                        }
                    }
                });
            }
        };
    }

    /**
     * 生成Flowabele
     * @param data
     * @param <T>
     * @return
     */
    private static <T> Flowable<T> createData(final T data) {
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(FlowableEmitter<T> emitter) throws Exception {
                try{
                    emitter.onNext(data);
                    emitter.onComplete();
                }catch (Exception e){
                    emitter.onError(e);
                }

            }
        }, BackpressureStrategy.BUFFER);
    }


}
