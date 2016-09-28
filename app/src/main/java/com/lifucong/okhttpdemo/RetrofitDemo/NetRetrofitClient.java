package com.lifucong.okhttpdemo.RetrofitDemo;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/9/28.
 */
public class NetRetrofitClient implements RetrofitUserApi{

    private static NetRetrofitClient netRetrofitClient;
    private RetrofitUserApi userApi;
    private String BASE_URL="https://api.github.com";

    private NetRetrofitClient(){

        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        userApi = retrofit.create(RetrofitUserApi.class);
    }

    public static NetRetrofitClient getInstance() {
        if (netRetrofitClient==null) {
            netRetrofitClient=new NetRetrofitClient();
        }
        return netRetrofitClient;
    }

    @Override
    public Call<RepoResult> getData(@Query("q") String q, @Query("page") int pageId) {
        return userApi.getData(q, pageId);
    }
}
