package com.example.dell.moviestage1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    TextView vote_average_view, release_date_view, synopsis_view;
    ImageView poster;
    Context context;
    String release_date, synopsis, vote_average;

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


        String url = "http://image.tmdb.org/t/p/w185/";
        Picasso.with(context).load(url+movie.getMovie_poster()).into(poster);

        release_date = movie.getRelease_date();
        release_date_view.setText(release_date);

        synopsis = movie.getSynopsis();
        synopsis_view.setText(synopsis);

        vote_average = String.valueOf(movie.getVote_average());
        vote_average_view.setText(vote_average);

    }
}
