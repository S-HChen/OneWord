package smart.ccc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
    private String[] iconName = { "通讯录", "日历", "照相机", "时钟", "游戏", "短信", "铃声",
            "设置", "语音", "天气", "浏览器", "视频" };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view=inflater.inflate(R.layout.tab4,container,false);

        toolbar1= (Toolbar) getActivity().findViewById(R.id.toolbar1);
        toolbar1.setTitle("");
        title= (TextView) getActivity().findViewById(R.id.Title);
        title.setText("个人");
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar1);
        touxiang= (ImageView) view.findViewById(R.id.touxiang);
        touxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),LoginActivity.class));
            }
        });
        initView();
        return view;
    }

  public void  initView(){
      gview = (GridView) view.findViewById(R.id.gview);
      //新建List
      data_list = new ArrayList<Map<String, Object>>();
      //获取数据
      getData();
      //新建适配器
      String [] from ={"text"};
      int [] to = {R.id.text};
      sim_adapter = new SimpleAdapter(getContext(), data_list, R.layout.item, from, to);
      //配置适配器
      gview.setAdapter(sim_adapter);
      gview.setNumColumns(2);
  }


    public List<Map<String, Object>> getData(){
        //cion和iconName的长度是相同的，这里任选其一都可以
        for(int i=0;i<iconName.length;i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("text", iconName[i]);
            data_list.add(map);
        }

        return data_list;
    }
}
