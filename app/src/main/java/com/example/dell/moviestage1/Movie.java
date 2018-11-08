package com.example.dell.moviestage1;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {

    private String Movie_poster;
    private String Title;
    private String Release_date;
    private double Vote_average;
    private String synopsis;

    public String getTitle() {
        return Title;
    }

    public  String  getMovie_poster() {
        return Movie_poster;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public Movie(String title, String release_date, String movie_poster, double vote_average, String synopsis) {
        Title = title;
        Release_date = release_date;
        this.synopsis = synopsis;
        this.Movie_poster = movie_poster;
        this.synopsis = synopsis;
        this.Vote_average = vote_average;
    }

    public double getVote_average() {
        return Vote_average;
    }

    public String  getRelease_date() {
        return Release_date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Movie_poster);
        dest.writeString(this.Title);
        dest.writeString(this.Release_date);
        dest.writeDouble(this.Vote_average);
        dest.writeString(this.synopsis);
    }

    protected Movie(Parcel in) {
        this.Movie_poster = in.readString();
        this.Title = in.readString();
        this.Release_date = in.readString();
        this.Vote_average = in.readDouble();
        this.synopsis = in.readString();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
