package com.example.dell.ykmn.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.ykmn.R;
import com.example.dell.ykmn.adapter.outAdapter;
import com.example.dell.ykmn.bean.news;
import com.example.dell.ykmn.di.Icontract;
import com.example.dell.ykmn.di.Presenterimp;
import com.example.dell.ykmn.view.MyView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Icontract.iview {

    private Presenterimp presenterimp;
    private RecyclerView recy_view;
    private CheckBox cb_01;
//    private CheckBox cb_02;
    private List<news.DataBean> data;
    private news news;
    private news.DataBean.ListBean news1;
    private outAdapter adapter;
    private Button btn_sou;
    private EditText edit_sou;
    private MyView myview;
    private List<String> list = new ArrayList<>();//流式
    private Context context;
    private TextView text_name;
    private TextView text_clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;//初始化
        recy_view = findViewById(R.id.recy_view);
        cb_01 = findViewById(R.id.cb_01);
     //   cb_02 = findViewById(R.id.cb_02);
        btn_sou = findViewById(R.id.btn_sou);
        edit_sou = findViewById(R.id.edit_sou);
        text_name = findViewById(R.id.text_name);
        text_clear = findViewById(R.id.text_clear);
        myview = findViewById(R.id.myview);
        presenterimp = new Presenterimp();
        presenterimp.attchview(this);
        presenterimp.requestinfo();
        text_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myview.removeAllViews();
            }
        });
        btn_sou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String st= edit_sou.getText().toString();
                list.add(st);
                myview.removeAllViews();
                for (int i=0;i<list.size();i++){

                    TextView tv=new TextView(MainActivity.this);
                    tv.setText(list.get(i));
                    myview.addView(tv);
                }
                myview.setPadding(5,5,5,5);
            }
           /* @Override
            public void onClick(View v) {
                String s = edit_sou.getText().toString();
                View view = LayoutInflater.from(context).inflate(R.layout.liushi, null);
                TextView text_name = view.findViewById(R.id.text_name);
                list.add(s);
                for (int i = 0; i < list.size(); i++) {
                    text_name.setText(list.get(i));
                }
                myview.addView(view);
            }*/
        });
        cb_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cb_01.isChecked()){
                    for (int i = 0; i <news.getData().size() ; i++) {
                        news.getData().get(i).setOutchecked(true);
                        for (int j = 0; j <news.getData().get(i).getList().size() ; j++) {
                            news.getData().get(i).getList().get(j).setInnerchecked(true);
                        }
                    }
                }else {
                    for (int i = 0; i <news.getData().size() ; i++) {
                        news.getData().get(i).setOutchecked(false);
                        for (int j = 0; j <news.getData().get(i).getList().size() ; j++) {
                            news.getData().get(i).getList().get(j).setInnerchecked(false);
                        }
                    }

                }
               adapter.notifyDataSetChanged();
            }
        });
/*        cb_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i <news.getData().size() ; i++) {
                    news.getData().get(i).setOutchecked(true);
                    for (int j = 0; j <news.getData().get(i).getList().size() ; j++) {
                        news.getData().get(i).getList().get(j).setInnerchecked(true);
                    }
                }
            }
        });*/


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenterimp.datachview(this);
    }
    @Override
    public void data(final String s) {
        runOnUiThread(new Runnable() {


          //  private com.example.dell.ykmn.bean.news news;

            @Override
            public void run() {
                Gson gson = new Gson();
                news = gson.fromJson(s, news.class);
                data = news.getData();
                Toast.makeText(MainActivity.this, "data:" + data, Toast.LENGTH_SHORT).show();
                LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);
                recy_view.setLayoutManager(manager);
                adapter = new outAdapter(MainActivity.this, data);
                recy_view.setAdapter(adapter);

            }
        });
    }
}
