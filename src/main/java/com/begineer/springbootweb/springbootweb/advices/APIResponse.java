package com.begineer.springbootweb.springbootweb.advices;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class APIResponse<T> {

    @JsonFormat(pattern="hh:mm:ss MM:DD:YYYY")
    private LocalDateTime timeStamp;

    private T data;

    private APIError apiError;

    public APIResponse()
    {
        this.timeStamp=LocalDateTime.now();
    }
    public APIResponse(T data){
        this();
        this.data=data;
    }

    public APIResponse(APIError apiError)
    {
        this.apiError=apiError;
    }
}
