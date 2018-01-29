package com.ygsoft.twsystem.activitys;

import android.Manifest;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.GsonBuilder;
import com.ygsoft.twsystem.R;
import com.ygsoft.twsystem.activitys.base.BaseActivity;
import com.ygsoft.twsystem.beans.Book;
import com.ygsoft.twsystem.interfaces.RetrofitService;
import com.ygsoft.twsystem.utils.PermissionUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 2017/11/20.
 */

public class ActivityLogin extends BaseActivity{


    private Button email_sign_in_button;
    private String[] requestPermissions = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.INTERNET
    };
    private PermissionUtil.PermissionTool permissionTool;
    @Override
    protected void onStart() {
        setContentView(R.layout.activity_login);
        super.onStart();
        if (Build.VERSION.SDK_INT >= 23) { //针对6.0以后的版本加权限判断
            permissionTool = new PermissionUtil.PermissionTool(new PermissionUtil.PermissionListener() {
                @Override
                public void allGranted() {
//                    initView();
                }
            });
            permissionTool.checkAndRequestPermission(ActivityLogin.this, requestPermissions);
        } else {
//            initView();
        }
        email_sign_in_button = (Button) findViewById(R.id.email_sign_in_button);
        email_sign_in_button.setOnClickListener(btnClick);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionTool.onRequestPermissionResult(this, requestCode, permissions, grantResults);
    }

    private View.OnClickListener btnClick = new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.email_sign_in_button:
                    Log.d("wgw_btnClick","======================================");
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://api.douban.com/v2/")
                            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                            .build();
                    RetrofitService service = retrofit.create(RetrofitService.class);
                    Call<Book> call = service.getSearchBook("金瓶梅",null,0,1);
                    call.enqueue(new Callback<Book>() {
                        @Override
                        public void onResponse(Call<Book> call, Response<Book> response) {
                            Log.d("wgw_onResponse",""+response.body().getBooks().get(0).getTitle());
                        }

                        @Override
                        public void onFailure(Call<Book> call, Throwable t) {
                            Log.e("wgw-eee","3333333333333333333333333333");
                        }
                    });
                    break;
            }
        }
    };
}
