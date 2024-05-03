package com.example.our_trpp_project.Data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {TutorEntity.class}, version = 1)
public abstract class AppDatabaseTutor extends RoomDatabase {
    private static volatile AppDatabaseTutor INSTANCE;

    public abstract TutorDAO tutorDao();

    public static synchronized AppDatabaseTutor getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabaseTutor.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabaseTutor.class, "tutor_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
