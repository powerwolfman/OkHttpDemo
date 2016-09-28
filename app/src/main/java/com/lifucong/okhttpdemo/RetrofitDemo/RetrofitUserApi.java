package com.lifucong.okhttpdemo.RetrofitDemo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/9/28.
 */
public interface RetrofitUserApi {

    @GET("/search/repositories")
    Call<RepoResult> getData(@Query("q")String q,@Query("page")int pageId);
}
