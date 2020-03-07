package com.github.febner.rateyourcourse;

import lombok.Builder;
import lombok.Value;

import java.time.ZonedDateTime;

@Value
@Builder
public class DateTimeValue {
    ZonedDateTime dateTime;
    Integer value;
}
