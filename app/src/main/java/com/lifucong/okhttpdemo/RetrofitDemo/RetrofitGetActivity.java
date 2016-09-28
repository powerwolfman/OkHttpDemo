package com.lifucong.okhttpdemo.RetrofitDemo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.lifucong.okhttpdemo.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitGetActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_get);
        NetRetrofitClient.getInstance().getData("language:" + "java", 1).enqueue(new Callback<RepoResult>() {
            @Override
            public void onResponse(Call<RepoResult> call, Response<RepoResult> response) {
                if (response.isSuccessful()) {
                    RepoResult repoResult = response.body();
                    List<Repo> list = repoResult.getRepoList();
                    for (Repo repo : list) {
                        Log.e("TAG", repo.getFullName());
                    }
                }
            }

            @Override
            public void onFailure(Call<RepoResult> call, Throwable t) {

            }
        });
    }
}
