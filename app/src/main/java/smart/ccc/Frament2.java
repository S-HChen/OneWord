package smart.ccc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.FrameMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view =inflater.inflate(R.layout.fragment2,container,false);
        toolbar1= (Toolbar) getActivity().findViewById(R.id.toolbar1);
        toolbar1.setTitle("");
        title= (TextView) getActivity().findViewById(R.id.Title);
        title.setText("发现");
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar1);

        setGview();
        return view;
    }


    private void setGview(){
        gview = (GridView) view.findViewById(R.id.gview1);
        //新建List
        data_list = new ArrayList<Map<String, Object>>();
        //获取数据
        getData();
        //新建适配器
        String [] from ={"btn"};
        int [] to = {R.id.btn};
        sim_adapter = new SimpleAdapter(getContext(), data_list, R.layout.fragment2_item, from, to);
        //配置适配器
        gview.setAdapter(sim_adapter);
        gview.setNumColumns(2);
    }



    public List<Map<String, Object>> getData(){
        //cion和iconName的长度是相同的，这里任选其一都可以
        for(int i=0;i<iconName.length;i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("btn", iconName[i]);
            data_list.add(map);
        }

        return data_list;
    }
}
