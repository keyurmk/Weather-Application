package com.challenge.weather.controller;

import com.challenge.weather.bean.ApiResponse;
import com.challenge.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather")
    public ResponseEntity<ApiResponse> GetCurrentWeather(@RequestParam(required = false) String location) throws Exception {
        final ApiResponse response = weatherService.GetCurrentWeatherData(Optional.ofNullable(location));
        return new ResponseEntity<ApiResponse>(response, response.getStatus());
    }
}
