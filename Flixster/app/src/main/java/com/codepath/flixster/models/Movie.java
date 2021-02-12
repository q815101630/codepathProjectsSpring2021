package com.codepath.flixster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Movie {

    String posterPath;
    String title;
    String overview;
    String backdropPath;

    static String baseURL;
    static String posterSize; // choose the third size
    static String backdropSize; //choose the second size
    public Movie(JSONObject jsonObject) throws JSONException { // automatic throw exception
        backdropPath = jsonObject.getString("backdrop_path");
        posterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");

    }

    public static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < movieJsonArray.length(); i++){
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));
        }
        return  movies;
    }
    public static void addSizes(JSONObject jsonObject) throws JSONException {
        JSONArray posterSizes = jsonObject.getJSONArray("poster_sizes");
        JSONArray backDropSizes = jsonObject.getJSONArray("backdrop_sizes");
        posterSize = posterSizes.getString(2);
        backdropSize = backDropSizes.getString(1);

    }

    public static void addBaseURL(JSONObject jsonObject) throws JSONException {
        baseURL = jsonObject.getString("secure_base_url");
    }


    // generated automatically
    public String getPosterPath() {
        return String.format("%s%s%s",baseURL,posterSize, posterPath);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getBackdropPath() {
        return String.format("%s%s%s",baseURL,backdropSize, backdropPath);
    }
}
