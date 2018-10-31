package kanghb.com.wanandroid.http;

import io.reactivex.Flowable;
import kanghb.com.wanandroid.model.bean.UserBean;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 创建时间：2018/10/19
 * 编写人：kanghb
 * 功能描述：
 */
public interface ApiService {
    @POST("/user/login")
    @FormUrlEncoded
    Flowable<BaseResponse<UserBean>> login(@Field("username") String username, @Field("password") String password);

    @POST("/user/register")
    @FormUrlEncoded
    Flowable<BaseResponse<UserBean>> register(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);
}
