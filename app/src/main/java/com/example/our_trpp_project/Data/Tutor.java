package com.example.our_trpp_project.Data;

import java.io.Serializable;

public class Tutor implements Serializable {
    private String name;

    public Tutor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
