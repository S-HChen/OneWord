package smart.ccc;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import smart.ccc.Bean.ArticleBean;
import smart.ccc.Bean.DataBean;

/**
 * Created by Administrator on 2017/6/2 0002.
 */

public class recyclerViewadapter extends RecyclerView.Adapter {
    private List<ArticleBean> lists;
    private Context context;

    public recyclerViewadapter(List<ArticleBean> lists, Context context) {
        this.lists = lists;
        this.context = context;
    }

    class myholder extends RecyclerView.ViewHolder{

        private TextView tv1,tv2,title,classify,isme;
        private ImageView imageView;
        public myholder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.imageView);
            tv1= (TextView) itemView.findViewById(R.id.tv1);
            tv2= (TextView) itemView.findViewById(R.id.tv2);
            title= (TextView) itemView.findViewById(R.id.title);
            classify= (TextView) itemView.findViewById(R.id.classify);
            isme= (TextView) itemView.findViewById(R.id.isme);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        myholder holder =new myholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d("TAG", "onBindViewHolder: "+lists.get(position).getAuthor());
        List list=new ArrayList();
        list.add(R.drawable.bg1);
        list.add(R.drawable.bg2);
        list.add(R.drawable.bg3);
        list.add(R.drawable.bg4);
        int i= (int) (Math.random()*4);
        ((myholder)holder).tv1.setText("-by "+lists.get(position).getAuthor()+"-");
        Glide.with(context).load(list.get(i)).centerCrop().into( ((myholder)holder).imageView);
//        ((myholder)holder).imageView.setBackgroundResource(Integer.parseInt(list.get(i).toString()));
        ((myholder)holder).tv2.setText("『 "+lists.get(position).getContent()+" 』");

        /*((myholder) holder).title.setText(lists.get(position).getTitle()+"");*/

        if (lists.get(position).isMe()){
            ((myholder) holder).isme.setText("#原创");
        }
        else {
            ((myholder) holder).isme.setText("#非原创");
        }
        switch (lists.get(position).getClassify()) {
            case "1":
                ((myholder) holder).classify.setText("#文字");
                break;
            case "2":
                ((myholder) holder).classify.setText("#语录");
                break;
            case "3":
                ((myholder) holder).classify.setText("#歌词");
                break;
            case "4":
                ((myholder) holder).classify.setText("#电影");
                break;
            case "5":
                ((myholder) holder).classify.setText("#诗");
                break;

        }


/*
        Glide.with(context).load(lists.get(position).getUrl()).into( ((myholder)holder).imageView);
*/

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }
}
