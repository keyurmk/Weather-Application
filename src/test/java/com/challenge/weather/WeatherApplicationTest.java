package com.challenge.weather;

import com.challenge.weather.bean.ApiResponse;
import com.challenge.weather.bean.Weather;
import com.challenge.weather.controller.WeatherController;
import com.challenge.weather.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WeatherController.class)
class WeatherApplicationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private WeatherService service;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    public void whenGetWeather_returnJsonObject() throws Exception {
        ApiResponse response = new ApiResponse();
        Weather weather = new Weather();
        weather.setPressure(1027);
        weather.setTemp(14.45);
        weather.setUmbrella(false);
        response.setData(weather);
        response.setSuccess(true);
        response.setStatus(HttpStatus.OK);
        when(service.GetCurrentWeatherData(Optional.ofNullable("London"))).thenReturn(response);

        RequestBuilder request = MockMvcRequestBuilders.get("/weather?location=London")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"data\":{\"umbrella\":false,\"temp\":14.45,\"pressure\":1027},\"success\":true}"))
                .andReturn();

    }

    @Test
    public void whenCityNotFound_returnError() throws Exception {
        ApiResponse response = new ApiResponse();
        response.setStatus(HttpStatus.BAD_REQUEST);
        response.setMessage("Location not found.");
        response.setSuccess(false);
        when(service.GetCurrentWeatherData(Optional.of("test"))).thenReturn(response);

        RequestBuilder request = MockMvcRequestBuilders.get("/weather?location=test")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"success\":false,\"message\":\"Location not found.\"}"))
                .andReturn();

    }
}