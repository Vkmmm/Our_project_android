package com.example.our_trpp_project.Data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TutorDAO {
    @Query("SELECT * FROM tutorentity")
    List<TutorEntity> getAllTutors();
    @Insert
    void insert(TutorEntity tutorEntity);
}
