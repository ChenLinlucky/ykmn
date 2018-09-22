package com.example.dell.ykmn.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.example.dell.ykmn.R;
import com.example.dell.ykmn.bean.news;

import java.util.List;

public class outAdapter extends RecyclerView.Adapter<outAdapter.oneHolder>{
    private Context context;
    private List<news.DataBean> list;
    public outAdapter(Context context, List<news.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public oneHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.include1, null);
        oneHolder holder = new oneHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull oneHolder holder, int position) {
        holder.cb_02.setChecked(list.get(position).isOutchecked());
        holder.cb_02.setText(list.get(position).getSellerName());

        LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        holder.recy_view01.setLayoutManager(manager);
        innerAdapter innerAdapter = new innerAdapter(context,list.get(position).getList());
        holder.recy_view01.setAdapter(innerAdapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //创建视图
    class oneHolder extends RecyclerView.ViewHolder{

        private final RecyclerView recy_view01;
        private final CheckBox cb_02;

        public oneHolder(View itemView) {
            super(itemView);
            cb_02 = itemView.findViewById(R.id.cb_02);
            recy_view01 = itemView.findViewById(R.id.recy_view01);
        }
    }
}
