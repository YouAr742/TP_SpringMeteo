package tp.ensim.tp3INFO2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties

public class WeatherResponseData {
    private int  weather, tmin, tmax, probarain,wind10m;

    public WeatherResponseData(){}
    public int getWeather() {
        return weather;
    }

    public void setWeather(int weather) {
        this.weather = weather;
    }

    public int getTmin() {
        return tmin;
    }

    public void setTmin(int tmin) {
        this.tmin = tmin;
    }

    public int getTmax() {
        return tmax;
    }

    public void setTmax(int tmax) {
        this.tmax = tmax;
    }

    public int getProbarain() {
        return probarain;
    }

    public void setProbarain(int probarain) {
        this.probarain = probarain;
    }

    public int getWind10m() {
        return wind10m;
    }

    public void setWind10m(int wind10m) {
        this.wind10m = wind10m;
    }

}