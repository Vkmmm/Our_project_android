package com.example.our_trpp_project.Student.Data;

import java.io.Serializable;

public class StudentEntity implements Serializable {
    private String email;
    private String name;
    private String city;
    private String grade;
    private String imageUri;

    // Конструктор без параметров
    public StudentEntity() {
    }

    // Конструктор с 4 параметрами
    public StudentEntity(String email, String name, String city, String grade) {
        this.email = email;
        this.name = name;
        this.city = city;
        this.grade = grade;
        this.imageUri = "";
    }

    // Конструктор с 5 параметрами
    public StudentEntity(String email, String name, String city, String grade, String imageUri) {
        this.email = email;
        this.name = name;
        this.city = city;
        this.grade = grade;
        this.imageUri = imageUri;
    }

    // Геттеры и сеттеры
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
