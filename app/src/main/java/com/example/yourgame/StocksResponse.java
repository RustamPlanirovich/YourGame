package com.example.yourgame;

public class StocksResponse {
    private String Name;


    public StocksResponse() {
    }

    public StocksResponse(String name) {
        this.Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

}
