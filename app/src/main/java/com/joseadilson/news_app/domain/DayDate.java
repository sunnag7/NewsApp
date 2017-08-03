package com.joseadilson.news_app.domain;

/**
 * Created by jose on 09/03/2017.
 */

public class DayDate {

    private String dayDateNews;

    public DayDate(String dayDateNews){
        this.dayDateNews = dayDateNews;
    }

    public DayDate(){

    }

    public String getDayDateNews() {
        return dayDateNews;
    }

    public void setDayDateNews(String dayDateNews) {
        this.dayDateNews = dayDateNews;
    }
}
