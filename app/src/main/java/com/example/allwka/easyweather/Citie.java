package com.example.allwka.easyweather;

public class Citie {
    private String name, temp, wind, rain, sea;

    public Citie(String name, String temp, String wind, String rain, String sea) {
        this.name = name;
        this.temp = temp;
        this.wind = wind;
        this.rain = rain;
        this.sea = sea;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getRain() {
        return rain;
    }

    public void setRain(String rain) {
        this.rain = rain;
    }

    public String getSea() {
        return sea;
    }

    public void setSea(String sea) {
        this.sea = sea;
    }


}
