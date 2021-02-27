package com.codepath.apps.simpleTwitt;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.codepath.apps.simpleTwitt.models.SampleModel;
import com.codepath.apps.simpleTwitt.models.SampleModelDao;

@Database(entities={SampleModel.class}, version=1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract SampleModelDao sampleModelDao();

    // Database name to be used
    public static final String NAME = "MyDataBase";
}
