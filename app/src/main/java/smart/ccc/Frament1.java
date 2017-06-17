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

import java.util.ArrayList;
import java.util.List;

import smart.ccc.Bean.DataBean;

/**
 * Created by Administrator on 2017/5/23 0023.
 */

public class Frament1 extends android.support.v4.app.Fragment{

    private Toolbar toolbar1;
    private RecyclerView recycler_view;
    private TextView tv1,tv2;
    private View view;
    private List<DataBean> lists;
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

        toolbar1= (Toolbar) getActivity().findViewById(R.id.toolbar1);
        title= (TextView) getActivity().findViewById(R.id.Title);
        title.setText("主页");
        toolbar1.setTitle("");

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar1);
       /* ((AppCompatActivity)getActivity()).setSupportActionBar((Toolbar)(view.findViewById(R.id.toolbar1)));
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("主页");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
      /*   toolbar1= (Toolbar) view.findViewById(R.id.toolbar1);

        toolbar1.setTitle("主页");
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar1);


*/
        initView();
        initData();
        LinearLayoutManager m=new LinearLayoutManager(getContext());
        m.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler_view.setLayoutManager(m);
        recyclerViewadapter adapter=new recyclerViewadapter(lists,getContext());
        recycler_view.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                getActivity().startActivity(new Intent(getActivity(),DetailActivity.class));

            }
        }));
        recycler_view.setAdapter(adapter);
        Log.d("TAG", "onCreateView: ccc"+lists.get(1).getAutor());


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void initData() {
        lists=new ArrayList<>();
        lists.add(new DataBean("Smart","青青原上草，一岁一枯荣","http://pic11.nipic.com/20101214/213291_155243023914_2.jpg"));
        lists.add(new DataBean("Smart","青青原上草，一岁一枯荣","http://www.quanjing.com/Freeimgbuy/pm0131-3586kn.html"));
        lists.add(new DataBean("Smart","青青原上草，一岁一枯荣","http://www.quanjing.com/Freeimgbuy/pm0131-3586kn.html"));
        lists.add(new DataBean("Smart","青青原上草，一岁一枯荣","http://www.quanjing.com/Freeimgbuy/pm0131-3586kn.html"));
        lists.add(new DataBean("Smart","青青原上草，一岁一枯荣","http://www.quanjing.com/Freeimgbuy/pm0131-3586kn.html"));
        lists.add(new DataBean("Smart","青青原上草，一岁一枯荣","http://www.quanjing.com/Freeimgbuy/pm0131-3586kn.html"));

    }

    private void initView() {
        recycler_view= (RecyclerView) view.findViewById(R.id.recycler_View);
        tv1= (TextView) view.findViewById(R.id.tv1);
        tv2= (TextView) view.findViewById(R.id.tv2);


    }
}
