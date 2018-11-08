package kanghb.com.wanandroid.http;

import java.util.List;

import io.reactivex.Flowable;
import kanghb.com.wanandroid.model.bean.ArticleListBean;
import kanghb.com.wanandroid.model.bean.BannerBean;
import kanghb.com.wanandroid.model.bean.HierarchyBean;
import kanghb.com.wanandroid.model.bean.NavigationBean;
import kanghb.com.wanandroid.model.bean.ProjectBean;
import kanghb.com.wanandroid.model.bean.UserBean;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

    @GET("/banner/json")
    Flowable<BaseResponse<List<BannerBean>>> getBannerList();

    @GET("/article/list/{page}/json")
    Flowable<BaseResponse<ArticleListBean>> getArticleList(@Path("page") int page);

    @GET("/tree/json")
    Flowable<BaseResponse<List<HierarchyBean>>> getHierarchyTree();

    @GET("/article/list/{page}/json")
    Flowable<BaseResponse<ArticleListBean>> getHierarchyArticleList(@Path("page") int page, @Query("cid") int cid);

    @GET("/navi/json")
    Flowable<BaseResponse<List<NavigationBean>>> getNavigationList();

    @GET("/project/tree/json")
    Flowable<BaseResponse<List<ProjectBean>>> getProjectList();

    @GET("/project/list/{page}/json")
    Flowable<BaseResponse<ArticleListBean>> getProjectArticleList(@Path("page") int page, @Query("cid") int cid);

    @POST("/lg/collect/{id}/json")
    @FormUrlEncoded
    Flowable<BaseResponse<String>> addCollect(@Path("id") int id);

    @POST("/lg/uncollect_originId/{id}/json")
    @FormUrlEncoded
    Flowable<BaseResponse<String>> unCollect(@Path("id") int id);
}
