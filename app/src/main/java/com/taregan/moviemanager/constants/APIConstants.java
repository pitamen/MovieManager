package com.taregan.moviemanager.constants;

/**
 * Created by pitambar on 4/3/18.
 */

public class APIConstants {

    public static final String API_KEY="254cc109494a63789e322d1ac3e31d79";
    public static final String API_ROOT = "https://api.themoviedb.org/3/";
    public static final String UPCOMING_MOVIE_URL=API_ROOT+"movie/upcoming?api_key="+API_KEY;
    public static final String NOWPLAYING_URL=API_ROOT+"/movie/now_playing?api_key="+API_KEY;

}
