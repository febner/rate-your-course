package com.example.messagingstompwebsocket;

import lombok.Value;

import java.time.LocalTime;

@Value
public class Rating {
    private Integer current;
    private LocalTime timeStamp;
}
