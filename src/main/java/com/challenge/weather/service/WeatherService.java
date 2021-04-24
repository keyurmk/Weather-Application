package com.challenge.weather.service;

import com.challenge.weather.bean.ApiResponse;
import com.challenge.weather.bean.Weather;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class WeatherService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.url}")
    private String url;

    @Value("${api.key}")
    private String apikey;

    public ApiResponse GetCurrentWeatherData(Optional<String> location) throws Exception {
        if (location.isEmpty()) {
            return new ApiResponse(false, HttpStatus.BAD_REQUEST, "Please enter location.");
        }

        String uri = url + "?&q=" + location.get() + "&appid=" + apikey;
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.exchange(uri, HttpMethod.GET, null, String.class);
        } catch (Exception e) {
            return new ApiResponse(false, HttpStatus.BAD_REQUEST, "Location not found.");
        }

        final ApiResponse result = new ApiResponse();
        ObjectMapper mapper = new ObjectMapper();
        try {
            Weather weather = mapper.readValue(response.getBody(), Weather.class);
            result.setSuccess(true);
            result.setData(weather);
            result.setStatus(HttpStatus.OK);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            result.setSuccess(false);
            result.setMessage("Error fetching data.");
        }

        return result;
    }
}
