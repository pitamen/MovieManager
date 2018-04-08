package com.taregan.moviemanager.fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.taregan.moviemanager.R;
import com.taregan.moviemanager.adapters.MovieRecyclerViewAdapter;
import com.taregan.moviemanager.constants.APIConstants;
import com.taregan.moviemanager.models.Movie;
import com.taregan.moviemanager.utils.ApiUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpComingMoviesFragment extends Fragment {

    @BindView(R.id.rvUpcoming)
    RecyclerView rvUpcoming;
    @BindView(R.id.pb_upcoming)
    ProgressBar progressBarUpcoming;

    List<Movie> movies;


    public UpComingMoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_up_coming_movies, container, false);
        ButterKnife.bind(this,rootView);

        initializeData();

        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        rvUpcoming.setHasFixedSize(true);
        rvUpcoming.setLayoutManager(llm);

        return rootView;
    }

    private void initializeData() {

        RequestMovieData requestMovieData = new RequestMovieData();
        requestMovieData.execute();

    }

    private class RequestMovieData extends AsyncTask<Void,Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBarUpcoming.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Void... voids) {

            //request data from api
            return ApiUtils.getData(APIConstants.UPCOMING_MOVIE_URL);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            movies = new ArrayList<>();

            if(s.length()!=0){
                try{
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");
                    for(int i=0;i<jsonArray.length();i++) {

                        JSONObject singleMovie = jsonArray.getJSONObject(i);
                        String id = singleMovie.getString("id");
                        String title = singleMovie.getString("title");
                        String overView = singleMovie.getString("overview");
                        float voteAverage = singleMovie.getLong("vote_average");
                        float voteCount = singleMovie.getLong("vote_count");
                        String posterPath = singleMovie.getString("poster_path");
                        String backdroppath = singleMovie.getString("backdrop_path");
                        movies.add(new Movie(id, title, overView, voteAverage, voteCount, posterPath, backdroppath));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                MovieRecyclerViewAdapter adapter = new MovieRecyclerViewAdapter(getContext(), movies);
                rvUpcoming.setAdapter(adapter);
            }
            progressBarUpcoming.setVisibility(View.GONE);
        }
    }
}
