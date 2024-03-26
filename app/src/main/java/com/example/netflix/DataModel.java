package com.example.netflix;

public class DataModel {
    private String Ttitle;
    private String Turl;
    private String Tvideo;

    public DataModel() {
    }

    public DataModel(String ttitle, String turl, String tvideo) {
        Ttitle = ttitle;
        Turl = turl;
        Tvideo = tvideo;
    }

    public String getTtitle() {
        return Ttitle;
    }

    public void setTtitle(String ttitle) {
        Ttitle = ttitle;
    }

    public String getTurl() {
        return Turl;
    }

    public void setTurl(String turl) {
        Turl = turl;
    }

    public String getTvideo() {
        return Tvideo;
    }

    public void setTvideo(String tvideo) {
        Tvideo = tvideo;
    }
}
