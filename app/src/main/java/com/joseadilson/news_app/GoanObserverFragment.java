package com.joseadilson.news_app;

import android.content.Context;
import android.net.Uri;
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
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GoanObserverFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GoanObserverFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GoanObserverFragment extends Fragment {
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
    private OnFragmentInteractionListener mListener;

    public GoanObserverFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GoanObserverFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GoanObserverFragment newInstance(String param1, String param2) {
        GoanObserverFragment fragment = new GoanObserverFragment();
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
        View view = inflater.inflate(R.layout.fragment_goan_observer, container, false);

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
        new  NewsRequest(getActivity()).execute();
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
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
                Document html = Jsoup.connect("http://englishnews.thegoan.net/topstory.php").followRedirects(true)
                        /*.ignoreContentType(true).ignoreHttpErrors(true).*/.timeout(8000)
                        .userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36").get();

                Elements category = html.select("div.listing-news-sections div.news-col-right a");
                Elements time     = html.select("div.content-area div.entry-meta span.posted-on time");
                Elements summary  = html.select("div.listing-news-sections div.news-col-right p");
                Elements images   = html.select("div.content-area div.entry-content div.post-image a img") ;
                /* ListIterator<Element> bundleList = html.select("div.post-listing p.post-meta")
                    .get(0)
                    .select("span")
                    .listIterator();*/

                //assert Iterators.size(bundleList) == category.size();
                for (int i = 0; i < category.size(); i++) {
                    // int i = 0;
                    //for (Element element:category){
                    News news = new News();
                    news.setCategoryNews(category.get(i).text());
                    news.setTimeNews(time.get(i).text());
                    news.setSummaryNews(summary.get(i).text());
                    news.setUrlNews(category.get(i).attr("href"));
                    news.setImgUrlNews("http://englishnews.thegoan.net/images/thegoan-logo.png");
                    newses.add(news);
                    //i++;
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
    public void updateLista(List<News> n){
        newses.clear();
        newses.addAll(n);
        adapter.notifyDataSetChanged();
    }
}
