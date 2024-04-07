package com.example.our_trpp_project.Data;

import java.io.Serializable;

public class InformationStudentRepository implements Serializable {
    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }


    private String Number;

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getRepeatPassword() {
        return RepeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        RepeatPassword = repeatPassword;
    }

    public int getGrade() {
        return Grade;
    }

    public void setGrade(int grade) {
        Grade = grade;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public InformationStudentRepository(String number, String password, String repeatPassword, String name, int grade, String city) {
        Number = number;
        Password = password;
        RepeatPassword = repeatPassword;
        Name = name;
        Grade = grade;
        City = city;
    }

    public InformationStudentRepository(String number, String password, String repeatPassword) {
        Number = number;
        Password = password;
        RepeatPassword = repeatPassword;
    }

    private String Password;
    private String RepeatPassword;
    private String Name;

    public InformationStudentRepository(String name, int grade, String city) {
        Name = name;
        Grade = grade;
        City = city;
    }

    private int Grade;
    private String City;
    public InformationStudentRepository() {}

}