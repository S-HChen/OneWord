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

import java.util.List;

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

        private TextView tv1,tv2;
        private ImageView imageView;
        public myholder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.imageView);
            tv1= (TextView) itemView.findViewById(R.id.tv1);
            tv2= (TextView) itemView.findViewById(R.id.tv2);

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
        ((myholder)holder).tv1.setText(lists.get(position).getAuthor());
        ((myholder)holder).tv2.setText(lists.get(position).getContent());
/*
        Glide.with(context).load(lists.get(position).getUrl()).into( ((myholder)holder).imageView);
*/

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }
}
