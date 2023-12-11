package tp.ensim.tp3INFO2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class WeatherResponse {
    private WeatherResponseData[] forecast;


    public WeatherResponse() {
    }

    public WeatherResponseData[] getForecast() {
        return forecast;
    }

    public void setForecast(WeatherResponseData[] forecast) {
        this.forecast = forecast;
    }


}
