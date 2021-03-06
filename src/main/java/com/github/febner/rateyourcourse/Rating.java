package com.github.febner.rateyourcourse;

import lombok.Value;

import java.time.LocalTime;

@Value
public class Rating {
    Integer current;
    LocalTime timeStamp;
}
