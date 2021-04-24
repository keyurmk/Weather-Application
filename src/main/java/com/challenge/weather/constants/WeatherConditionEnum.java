package com.challenge.weather.constants;

public enum WeatherConditionEnum {
    Thunderstorm, Drizzle, Rain;

    public static boolean needsUmbrella(String type) {
        WeatherConditionEnum[] types = WeatherConditionEnum.values();
        for (WeatherConditionEnum t : types) {
            if (t.name().equalsIgnoreCase(type)) {
                return true;
            }
        }
        return false;
    }
}
