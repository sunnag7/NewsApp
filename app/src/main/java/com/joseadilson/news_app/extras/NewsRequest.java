package com.joseadilson.news_app.extras;

import android.os.AsyncTask;
import android.support.test.espresso.core.deps.guava.collect.Iterators;

import com.joseadilson.news_app.MainActivity;
import com.joseadilson.news_app.domain.News;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by jose on 07/03/2017.
 */

public class NewsRequest extends AsyncTask<Void, Void, List<News>> {

    private WeakReference<MainActivity> activity;

    public NewsRequest(MainActivity activity){
        this.activity = new WeakReference<>(activity);
    }

    @Override
    protected List<News> doInBackground(Void... params) {
        List<News> newses = new ArrayList<>();

        try {
            Document html = Jsoup.connect("http://www.navhindtimes.in/category/goanews/").followRedirects(true)/*.ignoreContentType(true).ignoreHttpErrors(true).*/. timeout(5000)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36").get();

            Elements category = html.select("div.post-listing h2.post-box-title a");
            Elements time     = html.select("div.post-listing p.post-meta span");
            Elements summary  = html.select("div.post-listing div.entry p");
            Elements times    = html.select("div.post-listing").first().select("p.post-meta span").first().children();
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
                news.setTimeNews(times.text());
                news.setSummaryNews(summary.get(i).text());
                news.setUrlNews(element.attr("href"));
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

        if (activity.get() != null) {
            activity.get().updateLista(newsList);
        }
    }
}
