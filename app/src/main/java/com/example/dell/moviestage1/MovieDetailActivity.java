package com.example.dell.moviestage1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    private TextView vote_average_view, release_date_view, synopsis_view;
    private ImageView poster;
    private Context context;
    private String release_date, synopsis, vote_average;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        vote_average_view = (TextView)findViewById(R.id.detailAverage);
        release_date_view = findViewById(R.id.detailRelease);
        synopsis_view = findViewById(R.id.detailOverview);
        poster = findViewById(R.id.detailImage);

        Intent intent = getIntent();
        Movie movie = intent.getParcelableExtra("result");

        getSupportActionBar().setTitle(movie.getTitle());

        String url = "http://image.tmdb.org/t/p/w185/";
        Picasso.with(context).load(url+movie.getMovie_poster()).into(poster);

        if(movie.getRelease_date() == null) {
            release_date_view.setText(R.string.not_known);
        }else{
            release_date = movie.getRelease_date();
            release_date_view.setText(release_date);
        }
        if(movie.getSynopsis() == null) {
            synopsis_view.setText(R.string.not_known);

        }else{
            synopsis = movie.getSynopsis();
            synopsis_view.setText(synopsis);
        }
            vote_average = String.valueOf(movie.getVote_average());
            vote_average_view.setText(vote_average);
    }
}
