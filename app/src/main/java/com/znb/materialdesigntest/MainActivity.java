package com.znb.materialdesigntest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rv;
    private Toolbar tb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//
//            Window mWindow = getWindow();
//            mWindow.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            mWindow.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//
//            SystemBarTintManager mSystemBarTintManager = new SystemBarTintManager(this);
//            mSystemBarTintManager.setStatusBarTintEnabled(true);
//            mSystemBarTintManager.setNavigationBarTintEnabled(true);
//            mSystemBarTintManager.setTintColor(Color.parseColor("#E03131"));
//        }


        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        tb = (Toolbar) findViewById(R.id.tb);
        tb.setLogo(R.drawable.ic_launcher);
        tb.setTitle("hello");
        tb.setLogoDescription("logo");
//        setSupportActionBar(tb);
        init();
    }

    private List<String> list = new ArrayList<>();

    private void init() {
        rv = (RecyclerView) findViewById(R.id.rv);
        for (int i = 0; i <= 100; i++) {
            list.add(String.valueOf(i));
        }
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new HomeAdapter());
    }


    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    MainActivity.this).inflate(R.layout.item_content, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.tv.setText(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView tv;

            public MyViewHolder(View view) {
                super(view);
                tv = (TextView) view.findViewById(R.id.id_num);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
