package com.bcreagh.mpspark.mp.domain;

public class Readme {
    private String data = "";

    public Readme() {
    }

    public Readme(Readme readme) {
        this.data = readme.data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
