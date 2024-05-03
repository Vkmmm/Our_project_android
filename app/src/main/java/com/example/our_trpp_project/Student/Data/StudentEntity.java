package com.example.our_trpp_project.Student.Data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity
public class StudentEntity implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int StudentId;

    public void setStudentId(int studentId) {
        StudentId = studentId;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }

    public void setCity(String city) {
        City = city;
    }

    private String Number;

    public int getStudentId() {
        return StudentId;
    }

    public String getNumber() {
        return Number;
    }

    public String getPassword() {
        return Password;
    }

    public String getName() {
        return Name;
    }

    public String getGrade() {
        return Grade;
    }

    public String getCity() {
        return City;
    }

    private String Password;
    private String Name;
    private String Grade;
    private String City;

    public StudentEntity() {
    }

    public StudentEntity(int studentId, String number, String password,
                         String name, String grade, String city) {
        StudentId = studentId;
        Number = number;
        Password = password;
        Name = name;
        Grade = grade;
        City = city;
    }

}