package com.example.dell.moviestage1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.support.v7.widget.Toolbar;


import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Movie>>, SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String LOG_TAG = MainActivity.class.getName();

    private static final String URL_SCHEME = "https";
    private static final String BASE_URL = "api.tmdb.org";
    public static final String PATH = "/3/movie/";

    private String api_key = "";


    private String sortBy;

    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private Context context;
    private Toolbar toolbar;

    private static final int SECTION_LOADER_ID = 1;
    View loadingIndicator;
    @BindView(R.id.imageView)
    ImageView poster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        recyclerView = findViewById(R.id.recyclerView);
        toolbar = findViewById(R.id.toolbarView);
        loadingIndicator = findViewById(R.id.loading_indicator);

        setSupportActionBar(toolbar);

        LoaderManager loaderManager = LoaderManager.getInstance(this);
        loaderManager.initLoader(SECTION_LOADER_ID, null, this);
    }

    private String setUpSharePreference() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

        sortBy = sharedPreferences.getString(getString(R.string.settings_order_by_key), getString(R.string.highest_rated_value));

        return sortBy;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
    }

    @NonNull
    @Override
    public Loader<ArrayList<Movie>> onCreateLoader(int i, @Nullable Bundle bundle) {

        setUpSharePreference();
        Uri.Builder builder = new Uri.Builder();

        builder.scheme(URL_SCHEME).authority(BASE_URL).path(PATH);
        builder.appendPath(sortBy);
        builder.appendQueryParameter("api_key", api_key);


        Log.e(LOG_TAG, builder.toString());

        return new SectionLoader(context, builder.toString());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Movie>> loader, ArrayList<Movie> movieArrayList) {

        loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

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
        int id = item.getItemId();
        if (id == R.id.settings) {
            Intent intent = new Intent(context, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(R.string.settings_order_by_key))) {
            setUpSharePreference();
            getSupportLoaderManager().restartLoader(SECTION_LOADER_ID, null, this);
        }
    }
}
