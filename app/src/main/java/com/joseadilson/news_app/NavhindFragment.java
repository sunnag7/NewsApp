package com.joseadilson.news_app;

import android.content.Context;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.joseadilson.news_app.adapter.NewsAdapter;
import com.joseadilson.news_app.domain.News;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class NavhindFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<News> newses;
    private NewsAdapter adapter;
    private ProgressBar pBar;
    public NavhindFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NavhindFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NavhindFragment newInstance(String param1, String param2) {
        NavhindFragment fragment = new NavhindFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_navhind, container, false);

        pBar = (ProgressBar) view.findViewById(R.id.progressBar);
        newses = new ArrayList<>();
        initViews(view);
        SearchNewsRequest();
        return view;
    }

    private void initViews(View view){
        RecyclerView rv = (RecyclerView)view.findViewById(R.id.rv_news);
        rv.setHasFixedSize(true);

        LinearLayoutManager mLinearManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(mLinearManager);

        DividerItemDecoration divider = new DividerItemDecoration(getActivity(), mLinearManager.getOrientation());
        rv.addItemDecoration(divider);

        adapter = new NewsAdapter(getActivity(), newses);
        rv.setAdapter(adapter);
    }

    public void SearchNewsRequest(){
        new NewsRequest(getActivity()).execute();
    }

    public void updateLista(List<News> n){
        newses.clear();
        newses.addAll(n);
        adapter.notifyDataSetChanged();
    }

    private class NewsRequest extends AsyncTask<Void, Void, List<News>> {
        private Context activity;
        NewsRequest(Context activity){
            this.activity = activity;
        }
        @Override
        protected List<News> doInBackground(Void... params) {
            List<News> newses = new ArrayList<>();

            try {
                Document html = Jsoup.connect("http://www.navhindtimes.in/category/goanews/").followRedirects(true)
                        /*.ignoreContentType(true).ignoreHttpErrors(true).*/. timeout(5000)
                        .userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36").get();

                Elements category = html.select("div.post-listing h2.post-box-title a");
                Elements time     = html.select("div.post-listing p.post-meta span") ;
                Elements summary  = html.select("div.post-listing div.entry p");
                //Elements times  = html.select("div.post-listing").first().select("p.post-meta span").first().children();
                /* ListIterator<Element> bundleList = html.select("div.post-listing p.post-meta")
                    .get(0)
                    .select("span")
                    .listIterator();*/

                //assert Iterators.size(bundleList) == category.size();
                //for (int i = 0; i < category.size(); i++) {
                int i = 0;
                for (Element element:category){
                    News news = new News();
                    news.setCategoryNews(element.text());
                    if (i==0)
                        news.setTimeNews(time.get(i).text());
                    else
                        news.setTimeNews(time.get(i+4).text());

                    news.setSummaryNews(summary.get(i).text());
                    news.setUrlNews(element.attr("href"));
                    news.setImgUrlNews("http://www.navhindtimes.in/wp-content/uploads/2014/07/Logo_New.jpg");
                    newses.add(news);
                    i++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return newses;
        }

        @Override
        protected void onPostExecute(List<News> newsList) {
            super.onPostExecute(newsList);

            pBar.setVisibility(View.GONE);
            if (activity != null) {
                updateLista(newsList);
            }
        }
    }
}