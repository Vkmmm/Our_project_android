package com.example.our_trpp_project.Data;

import static java.time.chrono.ThaiBuddhistChronology.INSTANCE;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {StudentEntity.class}, version = 1)
public abstract class AppDatabaseStudent extends RoomDatabase {
    private static volatile AppDatabaseStudent INSTANCE;

    public abstract StudentDAO studentDAO();

    public static synchronized AppDatabaseStudent getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabaseStudent.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabaseStudent.class, "student_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

