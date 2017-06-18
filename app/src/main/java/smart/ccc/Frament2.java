package smart.ccc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.FrameMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/23 0023.
 */

public class Frament2 extends Fragment {

    private Toolbar toolbar1;
    private TextView title;
    private GridView gview;
    private List<Map<String, Object>> data_list;
    private SimpleAdapter sim_adapter;
    private String[] iconName = { "[文字]", "[语录]", "[歌词]", "[电影]", "[诗]" };
    private View view;
    private Button btn1,btn2,btn3,btn4,btn5;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view =inflater.inflate(R.layout.fragment2,container,false);
        toolbar1= (Toolbar) getActivity().findViewById(R.id.toolbar_search);
     /*   toolbar1.setTitle("");
        title= (TextView) getActivity().findViewById(R.id.Title);
        title.setText("发现");*/
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar1);
        initView();

        return view;
    }


    public  void initView(){
        btn1= (Button) view.findViewById(R.id.btn1);
        btn2= (Button) view.findViewById(R.id.btn2);
        btn3= (Button) view.findViewById(R.id.btn3);
        btn4= (Button) view.findViewById(R.id.btn4);
        btn5= (Button) view.findViewById(R.id.btn5);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putInt("classyNum",1);
                Intent intent=new Intent(getActivity(),ClassifyActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putInt("classyNum",2);
                Intent intent=new Intent(getActivity(),ClassifyActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putInt("classyNum",3);
                Intent intent=new Intent(getActivity(),ClassifyActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putInt("classyNum",4);
                Intent intent=new Intent(getActivity(),ClassifyActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putInt("classyNum",5);
                Intent intent=new Intent(getActivity(),ClassifyActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }


   /* private void setGview() {
        gview = (GridView) view.findViewById(R.id.gview1);
        //新建List
        data_list = new ArrayList<Map<String, Object>>();
        //获取数据
        getData();
        //新建适配器
        String[] from = {"btn"};
        int[] to = {R.id.btn};
        sim_adapter = new SimpleAdapter(getContext(), data_list, R.layout.fragment2_item, from, to);
        //配置适配器
        gview.setAdapter(sim_adapter);
        gview.setNumColumns(2);
        gview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getContext(),""+iconName[position]+"被点击了",Toast.LENGTH_SHORT).show();
                Log.d("aa",""+iconName[position]+"被点击了");
            }
        });


    }
    public List<Map<String, Object>> getData(){
        //cion和iconName的长度是相同的，这里任选其一都可以
        for(int i=0;i<iconName.length;i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("btn", iconName[i]);
            data_list.add(map);
        }

        return data_list;
    }*/
}
