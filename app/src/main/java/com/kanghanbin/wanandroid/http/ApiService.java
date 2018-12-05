package com.kanghanbin.wanandroid.http;

import com.kanghanbin.wanandroid.model.bean.ArticleListBean;
import com.kanghanbin.wanandroid.model.bean.BannerBean;
import com.kanghanbin.wanandroid.model.bean.DoneListBean;
import com.kanghanbin.wanandroid.model.bean.FriendBean;
import com.kanghanbin.wanandroid.model.bean.HierarchyBean;
import com.kanghanbin.wanandroid.model.bean.HotKey;
import com.kanghanbin.wanandroid.model.bean.NavigationBean;
import com.kanghanbin.wanandroid.model.bean.ProjectBean;
import com.kanghanbin.wanandroid.model.bean.ToDoBean;
import com.kanghanbin.wanandroid.model.bean.TodoListBean;
import com.kanghanbin.wanandroid.model.bean.UserBean;
import com.kanghanbin.wanandroid.model.bean.WxarticleBean;

import java.util.List;

import io.reactivex.Flowable;
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
    //登录相关

    @POST("/user/login")
    @FormUrlEncoded
    Flowable<BaseResponse<UserBean>> login(@Field("username") String username, @Field("password") String password);

    @POST("/user/register")
    @FormUrlEncoded
    Flowable<BaseResponse<UserBean>> register(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);

    @GET("/user/logout/json")
    Flowable<BaseResponse<String>> logout();

    //四个模块相关

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

    @GET("/friend/json")
    Flowable<BaseResponse<List<FriendBean>>> getFriend();

    @GET("/hotkey/json")
    Flowable<BaseResponse<List<HotKey>>> getHotKey();

    @POST("/article/query/{page}/json")
    @FormUrlEncoded
    Flowable<BaseResponse<ArticleListBean>> getSearchArticleList(@Path("page") int page, @Field("k") String k);


    //收藏相关

    /**
     * 收藏站内文章
     * 参数： 文章id，拼接在链接中。
     *
     * @param id
     * @return
     */
    @POST("/lg/collect/{id}/json")
    Flowable<BaseResponse<String>> addCollect(@Path("id") int id);

    @POST("/lg/collect/add/json")
    @FormUrlEncoded
    Flowable<BaseResponse<String>> addCollectOutSide(@Field("title") int title, @Field("author") int author, @Field("link") int link);

    /**
     * 文章列表取消收藏
     *
     * @param id
     * @return
     */
    @POST("/lg/uncollect_originId/{id}/json")
    Flowable<BaseResponse<String>> unCollect(@Path("id") int id);

    /**
     * 我的收藏页面取消收藏
     *
     * @param id
     * @return
     */
    @POST("/lg/uncollect/{id}/json")
    @FormUrlEncoded
    Flowable<BaseResponse<String>> unCollectFromCollectPage(@Path("id") int id, @Field("originId") int originId);

    @GET("lg/collect/list/{page}/json")
    Flowable<BaseResponse<ArticleListBean>> getCollectArticleList(@Path("page") int page);


    //TODO相关

    /**
     * @param type todo分为4类，主要为type区分，type取值为0，1，2，3。
     * @return
     */
    @GET("lg/todo/list/{type}/json")
    Flowable<BaseResponse<TodoListBean>> getToDoList(@Path("type") int type);

    @POST("/lg/todo/add/json")
    @FormUrlEncoded
    Flowable<BaseResponse<ToDoBean>> addToDo(@Field("title") String title, @Field("content") String content, @Field("date") String date, @Field("type") int type);

    @POST("/lg/todo/update/{id}/json")
    @FormUrlEncoded
    Flowable<BaseResponse<ToDoBean>> updateToDo(@Path("id") int id, @Field("title") String title, @Field("content") String content,
                                                @Field("date") String date, @Field("type") int type, @Field("status") int status);

    /**
     * @param id 拼接在链接上，为唯一标识
     * @return
     */
    @POST("/lg/todo/delete/{id}/json")
    Flowable<BaseResponse<String>> deleteTodo(@Path("id") int id);

    /**
     * @param id     拼接在链接上，为唯一标识
     * @param status 0或1，传1代表未完成到已完成，反之则反之。
     * @return
     */
    @POST("/lg/todo/done/{id}/json")
    @FormUrlEncoded
    Flowable<BaseResponse<ToDoBean>> doneToDo(@Path("id") int id, @Field("status") int status);

    @POST("/lg/todo/listnotdo/{type}/json/{page}")
    Flowable<BaseResponse<DoneListBean>> listNotDoneList(@Path("type") int type, @Path("page") int page);

    @POST("/lg/todo/listdone/{type}/json/{page}")
    Flowable<BaseResponse<DoneListBean>> listDoneList(@Path("type") int type, @Path("page") int page);


    //公众号相关

    @GET("/wxarticle/chapters/json")
    Flowable<BaseResponse<List<WxarticleBean>>> getWxarticleList();

    @GET("/wxarticle/list/{id}/{page}/json")
    Flowable<BaseResponse<ArticleListBean>> getHistoryWxarticleList(@Path("id") int id, @Path("page") int page);

    @GET("/wxarticle/list/{id}/{page}/json")
    Flowable<BaseResponse<ArticleListBean>> getSearchWxarticleList(@Path("id") int id, @Path("page") int page, @Query("k") String key);
}
