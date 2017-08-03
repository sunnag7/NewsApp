package com.joseadilson.news_app.domain;

/**
 * Created by jose on 05/03/2017.
 */

public class News {

    private String categoryNews;
    private String timeNews;
    private String summaryNews;
    private String publisherNews;
    private String urlNews;
    private String imgUrlNews;

    public String getPublisherNews() {
        return publisherNews;
    }

    public void setPublisherNews(String publisherNews) {
        this.publisherNews = publisherNews;
    }

    public String getUrlNews() {
        return urlNews;
    }

    public void setUrlNews(String urlNews) {
        this.urlNews = urlNews;
    }

    public News(String categoryNews, String timeNews, String summaryNews){
        this.categoryNews = categoryNews;
        this.timeNews     = timeNews;
        this.summaryNews  = summaryNews;
    }

    public News(){

    }

    public String getCategoryNews() {
        return categoryNews;
    }

    public void setCategoryNews(String categoryNews) {
        this.categoryNews = categoryNews;
    }

    public String getTimeNews() {
        return timeNews;
    }

    public void setTimeNews(String timeNews) {
        this.timeNews = timeNews;
    }

    public String getSummaryNews() {
        return summaryNews;
    }

    public void setSummaryNews(String summaryNews) {
        this.summaryNews = summaryNews;
    }

    public String getImgUrlNews() {
        return imgUrlNews;
    }

    public void setImgUrlNews(String imgUrlNews) {
        this.imgUrlNews = imgUrlNews;
    }
}
