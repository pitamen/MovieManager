package com.taregan.moviemanager.fragments;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.taregan.moviemanager.R;
import com.taregan.moviemanager.adapters.MovieRecyclerViewAdapter;
import com.taregan.moviemanager.constants.APIConstants;
import com.taregan.moviemanager.models.Movie;
import com.taregan.moviemanager.models.MovieResponse;
import com.taregan.moviemanager.rest.ApiClient;
import com.taregan.moviemanager.rest.ApiInterface;
import com.taregan.moviemanager.utils.ApiUtils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class NowPlayingFragment extends Fragment {

    @BindView(R.id.rvMovies)
    RecyclerView rvMovies;
    @BindView(R.id.pb_nowPlaying)
    ProgressBar nowPlayingProgressBar;

    private List<Movie> movies;
    private String movieJsonString;


    public NowPlayingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_now_playing, container, false);
        ButterKnife.bind(this,rootView);

        initializeData();

        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        GridLayoutManager glm = new GridLayoutManager(getActivity(),2);

        rvMovies.setHasFixedSize(true);
        rvMovies.setLayoutManager(llm);


        return rootView;
    }

    private void initializeData() {
        requestRetrofit();
    }



    private void requestRetrofit() {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<MovieResponse> call = apiService.getNowPlayingMovies(APIConstants.API_KEY);

        final ProgressDialog pDialog = intializeProgressDialog();

        // show it
        pDialog.show();

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                int statusCode = response.code();
                movies = response.body().getResults();
                MovieRecyclerViewAdapter adapter = new MovieRecyclerViewAdapter(getContext(), movies);
                rvMovies.setAdapter(adapter);
                pDialog.dismiss();
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }

    private ProgressDialog intializeProgressDialog() {

        ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading....");
        progressDialog.setIndeterminate(true);
        progressDialog.setTitle("Fetching Movies");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        return progressDialog;
    }







}
