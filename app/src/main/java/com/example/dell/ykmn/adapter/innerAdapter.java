package com.example.dell.ykmn.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.ykmn.R;
import com.example.dell.ykmn.bean.news;

import java.util.List;

public class innerAdapter extends RecyclerView.Adapter<innerAdapter.twoHolder>{
    private Context context;
    private List<news.DataBean.ListBean> list;
    public innerAdapter(Context context, List<news.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public twoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.include2, null);
        twoHolder holder = new twoHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull twoHolder holder, int position) {
       holder.cb_03.setChecked(list.get(position).isInnerchecked());


        holder.price.setText("单价为"+list.get(position).getPrice());
        holder.shop_name.setText(list.get(position).getTitle());
        String[] split = list.get(position).getImages().split("\\|");
        Glide.with(context).load(split[0]).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //创建视图
    class twoHolder extends RecyclerView.ViewHolder{

        private final CheckBox cb_03;
        private final TextView shop_name;
        private final TextView price;
        private final ImageView img;

        public twoHolder(View itemView) {
            super(itemView);
            cb_03 = itemView.findViewById(R.id.cb_03);
            shop_name = itemView.findViewById(R.id.shop_name);
            price = itemView.findViewById(R.id.price);
            img = itemView.findViewById(R.id.img);
        }
    }
}
