package com.codepath.apps.simpleTwitt.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.codepath.apps.simpleTwitt.TimeFormatter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Entity(foreignKeys = @ForeignKey(entity=User.class, parentColumns="id", childColumns="userId"))
@Parcel
public class Tweet {
    @ColumnInfo
    public String body;
    @ColumnInfo
    public String createdAt;
    @Ignore
    public User user;
    @ColumnInfo
    @PrimaryKey
    public long id;
    @ColumnInfo
    public long userId;

    // Parcel purpose
    public Tweet(){};

    public static Tweet fromJson(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();
        tweet.body = jsonObject.getString("text");
        tweet.createdAt = jsonObject.getString("created_at");

        tweet.id = jsonObject.getLong("id");
        User user = User.fromJson(jsonObject.getJSONObject("user"));
        tweet.user = user;
        tweet.userId = user.id;
        return tweet;
    }

    public static List<Tweet> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Tweet> tweets = new ArrayList<>();
        for(int i =0; i< jsonArray.length(); i++){
           tweets.add(fromJson(jsonArray.getJSONObject(i)));
        }
        return tweets;
    }

    public String getTimeStamp(){
        String formattedTime = TimeFormatter.getTimeDifference(this.createdAt);
        return  formattedTime;
    }
}
