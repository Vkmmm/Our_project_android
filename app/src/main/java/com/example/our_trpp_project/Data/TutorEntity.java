package com.example.our_trpp_project.Data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity
public class TutorEntity implements Serializable {
    @PrimaryKey
    private int TutorId;
    private String Number;
    private String Password;

    public TutorEntity(int tutorId, String number, String password, String name, String surname, String lastName, String city, String education, String workExperience, int cost, String address) {
        TutorId = tutorId;
        Number = number;
        Password = password;
        Name = name;
        Surname = surname;
        LastName = lastName;
        City = city;
        Education = education;
        WorkExperience = workExperience;
        Cost = cost;
        Address = address;
    }

    private String Name;
    private String Surname;
    private String LastName;
    private String City;
    private String Education;
    private String WorkExperience;

    public void setTutorId(int tutorId) {
        TutorId = tutorId;
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

    public void setSurname(String surname) {
        Surname = surname;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setCity(String city) {
        City = city;
    }

    public void setEducation(String education) {
        Education = education;
    }

    public void setWorkExperience(String workExperience) {
        WorkExperience = workExperience;
    }

    public void setCost(int cost) {
        Cost = cost;
    }

    public void setAddress(String address) {
        Address = address;
    }

    private int Cost;

    public int getTutorId() {
        return TutorId;
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

    public String getSurname() {
        return Surname;
    }

    public String getLastName() {
        return LastName;
    }

    public String getCity() {
        return City;
    }

    public String getEducation() {
        return Education;
    }

    public String getWorkExperience() {
        return WorkExperience;
    }

    public int getCost() {
        return Cost;
    }

    public String getAddress() {
        return Address;
    }

    private String Address;
    public TutorEntity() {
    }
}
