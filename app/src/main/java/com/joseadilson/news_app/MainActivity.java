package com.joseadilson.news_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.joseadilson.news_app.adapter.NewsAdapter;
import com.joseadilson.news_app.domain.News;
import com.joseadilson.news_app.extras.NewsRequest;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<News> newses;
    private NewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newses = new ArrayList<>();
        initViews();
        SearchNewsRequest();
    }

    private void initViews(){
        RecyclerView rv = (RecyclerView)findViewById(R.id.rv_news);
        rv.setHasFixedSize(true);

        LinearLayoutManager mLinearManager = new LinearLayoutManager(this);
        rv.setLayoutManager(mLinearManager);

        DividerItemDecoration divider = new DividerItemDecoration(this, mLinearManager.getOrientation());
        rv.addItemDecoration(divider);

        adapter = new NewsAdapter(this, newses);
        rv.setAdapter(adapter);
    }

    public void SearchNewsRequest(){
        new NewsRequest(this).execute();
    }

    public void updateLista(List<News> n){
        newses.clear();
        newses.addAll(n);
        adapter.notifyDataSetChanged();
    }
}
