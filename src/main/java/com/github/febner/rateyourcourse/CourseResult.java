package com.github.febner.rateyourcourse;

import lombok.Builder;
import lombok.Value;

import java.time.ZonedDateTime;

@Value
@Builder
public class CourseResult {
    ZonedDateTime start;
    ZonedDateTime end;
    Integer averageValue;
    Integer maxValue;
    Integer medianValue;
}
