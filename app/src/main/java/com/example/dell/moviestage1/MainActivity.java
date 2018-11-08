package com.example.dell.moviestage1;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Movie>> {

    String api_key = "";

    String REQUEST_URL = "https://api.themoviedb.org/3/discover/movie?api_key="+api_key+"&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1";


    RecyclerView recyclerView;
    MovieAdapter adapter;
    Context context;
    Toolbar toolbar;
    Spinner spinner;

    private static final int SECTION_LOADER_ID = 1;
    private TextView mEmptyStateTextView;
    View loadingIndicator;
    @BindView(R.id.imageView) ImageView poster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        recyclerView = findViewById(R.id.recyclerView);
        toolbar = findViewById(R.id.toolbarView);

        setSupportActionBar(toolbar);



        LoaderManager loaderManager = LoaderManager.getInstance(this);
        loaderManager.initLoader(SECTION_LOADER_ID, null, this);




    }

    @NonNull
    @Override
    public Loader<ArrayList<Movie>> onCreateLoader(int i, @Nullable Bundle bundle) {

        return new SectionLoader(context, REQUEST_URL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Movie>> loader, ArrayList<Movie> movieArrayList) {

        adapter = new MovieAdapter(context, movieArrayList);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<Movie>> loader) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.popular:
                showPopular();
                return true;
            case R.id.rated:
                showRated();
                return true;
            default:
        }


        return super.onOptionsItemSelected(item);
    }

    private void showRated() {
    }

    private void showPopular() {


    }
}
