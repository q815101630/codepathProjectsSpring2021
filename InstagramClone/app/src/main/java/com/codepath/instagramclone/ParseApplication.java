package com.codepath.instagramclone;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //Register
        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("MdjBSEvUx6T5VOJxsEqANSEoK7tuMoUtW2rJxA3n")
                .clientKey("lYZRfvBY6C4ir9xbi3sfUbf5kwkrM33hjZlRcmiq")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
