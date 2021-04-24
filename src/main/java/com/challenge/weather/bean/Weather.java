package com.challenge.weather.bean;

import com.challenge.weather.constants.WeatherConditionEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather implements Serializable {
    private boolean umbrella;
    private double temp;
    private int pressure;

    @JsonProperty("umbrella")
    public boolean isUmbrella() {
        return umbrella;
    }

    @JsonProperty("umbrella")
    public void setUmbrella(boolean umbrella) {
        this.umbrella = umbrella;
    }

    @JsonProperty("temp")
    public double getTemp() {
        return temp;
    }

    @JsonProperty("temp")
    public void setTemp(double temp) {
        this.temp = temp;
    }

    @JsonProperty("pressure")
    public int getPressure() {
        return pressure;
    }

    @JsonProperty("pressure")
    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    @JsonProperty("weather")
    public void setWeather(List<Map<String, Object>> weatherObjects) {
        Map<String, Object> weather = weatherObjects.get(0);
        this.umbrella = WeatherConditionEnum.needsUmbrella(weather.get("main").toString());
    }

    @JsonProperty("main")
    public void setPressureTemperature(Map<String, Object> main) {
        setTemp((double) main.get("temp"));
        setPressure((int) main.get("pressure"));
    }
}
