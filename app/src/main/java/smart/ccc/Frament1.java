package smart.ccc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonArray;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import smart.ccc.Bean.ArticleBean;
import smart.ccc.Bean.DataBean;
import smart.ccc.Bean.ResultData;
import smart.ccc.Bean.UserBean;
import smart.ccc.Interface.GetAllArticleService;
import smart.ccc.Interface.SendArticleService;

/**
 * Created by Administrator on 2017/5/23 0023.
 */

public class Frament1 extends android.support.v4.app.Fragment{

    private Toolbar toolbar1;
    private RecyclerView recycler_view;
    private TextView tv1,tv2;
    private View view;
    private List<ArticleBean> lists;
    private TextView title;


    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
         view = inflater.inflate(R.layout.fragment1, container, false);

        toolbar1= (Toolbar) getActivity().findViewById(R.id.toolbar_main);
      /*  title= (TextView) getActivity().findViewById(R.id.Title);
        title.setText("主页");
        toolbar1.setTitle("");*/

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar1);
       /* ((AppCompatActivity)getActivity()).setSupportActionBar((Toolbar)(view.findViewById(R.id.toolbar1)));
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("主页");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
      /*   toolbar1= (Toolbar) view.findViewById(R.id.toolbar1);

        toolbar1.setTitle("主页");
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar1);


*/
        initView();
        LinearLayoutManager m=new LinearLayoutManager(getContext());
        m.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler_view.setLayoutManager(m);
        initData();




        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void initData() {
        lists=new ArrayList<>();
      /*  lists.add(new DataBean("Smart","青青原上草，一岁一枯荣","http://pic11.nipic.com/20101214/213291_155243023914_2.jpg"));
        lists.add(new DataBean("Smart","青青原上草，一岁一枯荣","http://www.quanjing.com/Freeimgbuy/pm0131-3586kn.html"));
        lists.add(new DataBean("Smart","青青原上草，一岁一枯荣","http://www.quanjing.com/Freeimgbuy/pm0131-3586kn.html"));
        lists.add(new DataBean("Smart","青青原上草，一岁一枯荣","http://www.quanjing.com/Freeimgbuy/pm0131-3586kn.html"));
        lists.add(new DataBean("Smart","青青原上草，一岁一枯荣","http://www.quanjing.com/Freeimgbuy/pm0131-3586kn.html"));
        lists.add(new DataBean("Smart","青青原上草，一岁一枯荣","http://www.quanjing.com/Freeimgbuy/pm0131-3586kn.html"));
*/
  /*    String test="[{\"title\":\"lalala\",\"content\":\"nihaonihao\",\"author\":\"2222\",\"isMe\":true,\"classify\":\"1\",\"user\":\"555\"},{\"title\":\"诗词\",\"content\":\"白日依山尽\",\"author\":\"啊哈\",\"isMe\":false,\"classify\":\"1\",\"user\":\"555\"},{\"title\":\"诗词\",\"content\":\"举头望明月\",\"author\":\"试试\",\"isMe\":false,\"classify\":\"1\",\"user\":\"555\"},{\"title\":\"语录\",\"content\":\"哈哈哈哈\",\"author\":\"的\",\"isMe\":true,\"classify\":\"2\",\"user\":\"555\"}]";
        lists=JSONObject.parseArray(test,ArticleBean.class);
        Log.d("TAG", "initData: "+lists.get(0).getAuthor());
*/
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://121.42.150.20:8080/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build();

        final GetAllArticleService getAllArticleService=retrofit.create(GetAllArticleService.class);

            getAllArticleService.GetAllArticleService("articleAll")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(new Func1<List<ArticleBean>, Object>() {

                        @Override
                        public Object call(List<ArticleBean> articleBeen) {
                            lists.clear();
                            lists.addAll(articleBeen);
                            return null;
                        }
                    })
                    .subscribe(new Subscriber() {
                        @Override
                        public void onCompleted() {


                            Log.d("TAG", "onCompleted: success");
                            Log.d("TAG", "onCompleted: "+lists);
                            recyclerViewadapter adapter=new recyclerViewadapter(lists,getContext());
                            recycler_view.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    getActivity().startActivity(new Intent(getActivity(),DetailActivity.class));

                                }
                            }));
                            recycler_view.setAdapter(adapter);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d("TAG", "onError:"+e);
                            Log.d("TAG", "onError: "+lists);

                        }

                        @Override
                        public void onNext(Object o) {

                        }
                    });



    }

    private void initView() {
        recycler_view= (RecyclerView) view.findViewById(R.id.recycler_View);
        tv1= (TextView) view.findViewById(R.id.tv1);
        tv2= (TextView) view.findViewById(R.id.tv2);


    }
}
