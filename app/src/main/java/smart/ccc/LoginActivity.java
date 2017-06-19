package smart.ccc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import smart.ccc.Bean.UserBean;
import smart.ccc.Interface.LoginService;

/*import com.hebin.mduse.entity.User;
import com.hebin.mduse.uitl.SPUtils;
import com.hebin.mduse.uitl.UserService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;*/

/**
 * Created by Administrator on 2016/11/23 0023.
 */

public class LoginActivity extends AppCompatActivity {

    private EditText login_username,login_password;



       @Override
       protected void onCreate(@Nullable Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
           setContentView(R.layout.content_login);

           Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
           toolbar.setTitle("登录");
           setSupportActionBar(toolbar);
           getSupportActionBar().setDisplayHomeAsUpEnabled(true);
           toolbar.setNavigationOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   onBackPressed();
               }
           });
           Button register= (Button) findViewById(R.id.btn_register);
           login_username= (EditText) findViewById(R.id.login_username);
           login_password= (EditText) findViewById(R.id.login_password);
           register.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                   startActivity(intent);

               }
           });
           final Button login = (Button) findViewById(R.id.btn_login);
           login.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                      login();
                      finish();
               }
           });


       }


    public void login(){
       Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://121.42.150.20:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(new OkHttpClient())
                .build();
        UserBean userBean=new UserBean();
        userBean.setUser(login_username.getText().toString());
        userBean.setPwd(login_password.getText().toString());
        final LoginService loginService = retrofit.create(LoginService.class);
        Log.d("TAG", "login: "+JSONObject.toJSONString(userBean));
        try {
            loginService.login("login", java.net.URLEncoder.encode(JSONObject.toJSONString(userBean),"utf-8"))
                    .map(new Func1<UserBean, Object>() {

                        @Override
                        public Object call(UserBean userBean) {
                            SharedPreferences sharedPreferences=getSharedPreferences("OneWord",MODE_PRIVATE);
                            SharedPreferences.Editor edit=sharedPreferences.edit();
                            edit.putString("name",userBean.getName());
                            edit.putString("user",userBean.getUser());
                            edit.commit();
                           /* SPUtils.put(LoginActivity.this,"name",userBean.getName());
                            SPUtils.put(LoginActivity.this,"user",userBean.getUser());*/
                            /*SPUtils.put(LoginActivity.this,"pwd",userBean.getPwd());*/
                            return null;
                        }

                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Object>() {
                        @Override
                        public void onCompleted() {
                            Toast.makeText(LoginActivity.this,"登录成功", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(LoginActivity.this,main.class);
                            startActivity(intent);

                            Log.e("TAG","success");
                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(LoginActivity.this,"登录失败,请核实用密码或账号", Toast.LENGTH_SHORT).show();
                            Log.e("TAG", "onError: "+e );

                        }

                        @Override
                        public void onNext(Object object) {

                        }
                    });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }


}
