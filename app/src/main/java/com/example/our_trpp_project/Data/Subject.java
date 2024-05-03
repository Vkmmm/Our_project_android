package com.example.our_trpp_project.Data;

import java.util.List;

public class Subject {
    private String name;
    private List<Tutor> tutors;

    public Subject(String name, List<Tutor> tutors) {
        this.name = name;
        this.tutors = tutors;
    }

    public String getName() {
        return name;
    }

    public List<Tutor> getTutors() {
        return tutors;
    }
}
