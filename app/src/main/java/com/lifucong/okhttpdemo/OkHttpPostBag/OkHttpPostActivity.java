package com.lifucong.okhttpdemo.OkHttpPostBag;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.google.gson.Gson;
import com.lifucong.okhttpdemo.R;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpPostActivity extends Activity {


    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    private String BASE_URL = "http://admin.syfeicuiedu.com";
    private String url = BASE_URL + "/Handler/UserHandler.ashx?action=register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http_post);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_register)
    public void onClick() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        User user = new User(etUsername.getText().toString(), etPassword.getText().toString());
        String content = new Gson().toJson(user);
        RequestBody body = RequestBody.create(null, content);
        Request request = new Request.Builder()
                .post(body)
                .url(url)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("HAHAHAHA", "响应失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("HAHAHAHA", "响应成功");
            }
        });
    }
}
