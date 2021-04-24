package com.challenge.weather.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse implements Serializable {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    private Boolean success;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    @JsonIgnore
    private HttpStatus status;

    public ApiResponse(){}

    public ApiResponse(Boolean success, HttpStatus status, String message){
        this.success = success;
        this.status = status;
        this.message = message;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
