package smart.ccc;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import smart.ccc.Bean.ArticleBean;
import smart.ccc.Bean.CommentBean;
import smart.ccc.Interface.GetArticleByUserService;
import smart.ccc.Interface.SendCommentService;

/**
 * Created by Administrator on 2017/6/19 0019.
 */

public class CommentActivity extends AppCompatActivity{

    private ArticleBean articleBean;
    private List<CommentBean> lists;
    private List<Map<String, Object>> data_list;
    private SimpleAdapter sim_adapter;
    private Button send_comment;
    private EditText comment_content;
    private TextView comnum;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment);
        articleBean= (ArticleBean) getIntent().getExtras().getSerializable("article");
        lists=new ArrayList<>();
        data_list=new ArrayList<Map<String, Object>>();
        send_comment= (Button) findViewById(R.id.send_comment);
        comment_content= (EditText) findViewById(R.id.comment_content);
        comnum= (TextView) findViewById(R.id.comnum);
        initData();
        send_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendData();
            }
        });

    }

    public void initData(){
        SharedPreferences sharedPreferences=getSharedPreferences("OneWord",MODE_PRIVATE);
        SharedPreferences.Editor edit=sharedPreferences.edit();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://121.42.150.20:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(new OkHttpClient())
                .build();


        final SendCommentService sendCommentService=retrofit.create(SendCommentService.class);

        sendCommentService.getbyid("commentByid",articleBean.getArticleid())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<List<CommentBean>, Object>() {
                    @Override
                    public Object call(List<CommentBean> commentBeen) {
                        lists.clear();
                        if (commentBeen != null) {
                            lists.addAll(commentBeen);
                        }
                        return null;
                    }
                })
                .subscribe(new Subscriber() {
                    @Override
                    public void onCompleted() {
                        data_list.clear();
                        //获取数据
                        //新建适配器
                        for(int i=0;i<lists.size();i++){
                            Map<String, Object> map = new HashMap<String, Object>();
                            map.put("name",lists.get(i).getUser());
                            map.put("comment",lists.get(i).getContent());
                            data_list.add(map);
                        }
                        sim_adapter=new SimpleAdapter(CommentActivity.this, data_list,R.layout.comitem,
                                new String[] { "name","comment"},
                                new int[] { R.id.comname, R.id.mycomment});
                        ListView list=(ListView)findViewById(R.id.comlist);
                        list.setAdapter(sim_adapter);
                        sim_adapter.notifyDataSetChanged();
                        comnum.setText(String.valueOf(sim_adapter.getCount()));
                        Log.d("TAG", "onCompleted: "+lists);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("TAG", "onError22:"+e);
                        Log.d("TAG", "onError22: "+lists);

                    }

                    @Override
                    public void onNext(Object o) {

                    }
                });

    }


    public void SendData(){
        SharedPreferences sharedPreferences=getSharedPreferences("OneWord",MODE_PRIVATE);
        SharedPreferences.Editor edit=sharedPreferences.edit();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://121.42.150.20:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(new OkHttpClient())
                .build();


        final SendCommentService sendCommentService=retrofit.create(SendCommentService.class);

        sendCommentService.send("comment",articleBean.getArticleid(),sharedPreferences.getString("user","Smart"),comment_content.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<List<CommentBean>, Object>() {
                    @Override
                    public Object call(List<CommentBean> commentBeen) {
                        lists.clear();
                        lists.addAll(commentBeen);
                        return null;
                    }
                })
                .subscribe(new Subscriber() {
                    @Override
                    public void onCompleted() {
                        data_list.clear();
                        //获取数据
                        //新建适配器
                        for(int i=0;i<lists.size();i++){
                            Map<String, Object> map = new HashMap<String, Object>();
                            map.put("name",lists.get(i).getUser());
                            map.put("comment",lists.get(i).getContent());
                            data_list.add(map);
                        }
                        sim_adapter=new SimpleAdapter(CommentActivity.this, data_list,R.layout.comitem,
                                new String[] { "name","comment"},
                                new int[] { R.id.comname, R.id.mycomment});
                        ListView list=(ListView)findViewById(R.id.comlist);
                        list.setAdapter(sim_adapter);
                        sim_adapter.notifyDataSetChanged();
                        comnum.setText(String.valueOf(sim_adapter.getCount()));

                        Log.d("TAG", "onCompleted: "+lists);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("TAG", "onError22:"+e);
                        Log.d("TAG", "onError22: "+lists);

                    }

                    @Override
                    public void onNext(Object o) {

                    }
                });

    }
}
