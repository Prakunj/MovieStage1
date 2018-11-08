package com.example.dell.moviestage1;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;

public class SectionLoader extends AsyncTaskLoader<ArrayList<Movie>> {

    String mUrl;

    public SectionLoader(Context context, String url){
        super(context);
        this.mUrl = url;

    }
    protected void onStartLoading(){
        forceLoad();
    }
    @Nullable
    @Override


    public ArrayList<Movie> loadInBackground() {

        try{
            Thread.sleep(2000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(mUrl == null){
            return null;
        }
        ArrayList<Movie> movies = QueryUtils.fetchData(mUrl);
        return movies;
    }
}
