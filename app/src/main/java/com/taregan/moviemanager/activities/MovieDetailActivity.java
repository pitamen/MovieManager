package com.taregan.moviemanager.activities;

import android.drm.DrmStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.squareup.picasso.Picasso;
import com.taregan.moviemanager.R;
import com.taregan.moviemanager.models.Movie;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MovieDetailActivity extends AppCompatActivity {

    Movie movie;

    @BindView(R.id.ivMovieBackdrop)
    ImageView ivMovieBackdrop;
    @BindView(R.id.tvOverview)
    TextView tvOverview;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);

        setToolbar();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        displayMovieDataToView();
    }

    /**
     * display movie data to view
     */

    private void displayMovieDataToView() {

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            movie = (Movie) extras.getSerializable("MOVIE");
            this.setTitle(movie.getTitle());
            tvOverview.setText(movie.getOverview());

            Picasso.get()
                    .load(movie.getBackdropPath())
                    .into(ivMovieBackdrop);

        }
    }

    /**
     * set toolbar
     */
    private void setToolbar() {
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * butterknife click listner
     * @param view
     */

    @OnClick({R.id.fab})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.fab:
                Snackbar.make(view,"Movie Saved as Favorite",Snackbar.LENGTH_LONG).setAction("Action",null).show();
                break;
        }

    }
}
