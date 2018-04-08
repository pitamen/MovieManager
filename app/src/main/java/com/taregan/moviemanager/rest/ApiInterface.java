package com.taregan.moviemanager.rest;

import com.taregan.moviemanager.models.Movie;
import com.taregan.moviemanager.models.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by pitambar on 4/5/18.
 */

public interface ApiInterface {

    @GET("movie/upcoming")
    Call<MovieResponse> getUpcomingMovies(@Query("api_key") String apiKey);

    //https://api.themoviedb.org/3/movie/upcoming?api_key=254cc109494a63789e322d1ac3e31d79

    @GET("movie/now_playing")
    Call<MovieResponse> getNowPlayingMovies(@Query("api_key") String apiKey);
    //https://api.themoviedb.org/3/movie/now_playing?api_key=254cc109494a63789e322d1ac3e31d79

}
