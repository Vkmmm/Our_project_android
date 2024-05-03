package com.example.our_trpp_project.Student.Data;

import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Dao;

import com.example.our_trpp_project.Student.Data.StudentEntity;

import java.util.List;
@Dao
public interface StudentDAO {
    @Query("SELECT * FROM studententity")
    List<StudentEntity> getAllStudents();
    @Insert
    void insert(StudentEntity studentEntity);
}
