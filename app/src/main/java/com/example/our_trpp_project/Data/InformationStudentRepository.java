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

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public InformationStudentRepository(String number, String password, String repeatPassword, String name, String grade, String city) {
        Number = number;
        Password = password;
        RepeatPassword = repeatPassword;
        Name = name;
        Grade = grade;
        City = city;
    }



    private String Password;
    private String RepeatPassword;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    private String Name;



    private String Grade;
    private String City;
    public InformationStudentRepository() {}

}