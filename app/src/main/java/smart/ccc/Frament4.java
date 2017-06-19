package smart.ccc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.bumptech.glide.load.model.ImageVideoWrapper;

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
import smart.ccc.Interface.GetAllArticleService;
import smart.ccc.Interface.GetArticleByUserService;

/**
 * Created by Administrator on 2017/6/15 0015.
 */

public class Frament4 extends Fragment {

    private Toolbar toolbar1;
    private View view;
    private GridView gview;
    private List<Map<String, Object>> data_list;
    private SimpleAdapter sim_adapter;
    private TextView title;
    private ImageView touxiang;
    private String[] iconName;
    private List<ArticleBean> lists;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view=inflater.inflate(R.layout.tab4,container,false);

        toolbar1= (Toolbar) getActivity().findViewById(R.id.toolbar_account);
       /* toolbar1.setTitle("");
        title= (TextView) getActivity().findViewById(R.id.Title);
        title.setText("个人");*/
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar1);
        touxiang= (ImageView) view.findViewById(R.id.touxiang);
        touxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),LoginActivity.class));
            }
        });

        gview = (GridView) view.findViewById(R.id.gview);
        //新建List
        lists=new ArrayList<>();
        data_list=new ArrayList<>();
        initData();
        return view;
    }

  /*public void  initView(){
      gview = (GridView) view.findViewById(R.id.gview);
      //新建List
      data_list = new ArrayList<Map<String, Object>>();
      //获取数据
      initView();
      //新建适配器
      String [] from ={"text"};
      int [] to = {R.id.text};
      sim_adapter = new SimpleAdapter(getContext(), data_list, R.layout.item, from, to);
      //配置适配器
      gview.setAdapter(sim_adapter);
      gview.setNumColumns(2);
  }*/



    public void initData(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://121.42.150.20:8080/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build();

        final GetArticleByUserService getArticleByUserService=retrofit.create(GetArticleByUserService.class);

       getArticleByUserService.get("articleByUser","147")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<List<ArticleBean>, Object>() {

                    @Override
                    public Object call(List<ArticleBean> articleBeen) {
                        lists.clear();
                        if(articleBeen!=null) {
                            lists.addAll(articleBeen);
                            Log.d("TAG", "call: "+lists);

                        }
                        return null;
                    }
                })
                .subscribe(new Subscriber() {
                    @Override
                    public void onCompleted() {
                        //获取数据
                        //新建适配器
                        for(int i=0;i<lists.size();i++){
                            Map<String, Object> map = new HashMap<String, Object>();
                            map.put("text", lists.get(i).getTitle());
                            data_list.add(map);
                        }
                     String [] from ={"text"};
                        int [] to = {R.id.text};
                        sim_adapter = new SimpleAdapter(getContext(), data_list, R.layout.item, from, to);
                        //配置适配器
                        gview.setAdapter(sim_adapter);
                        gview.setNumColumns(2);
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
