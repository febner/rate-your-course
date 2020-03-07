package com.github.febner.rateyourcourse;


import java.util.Collection;

interface ResultService {

    public CourseResult calculateCourseResult(Collection<DateTimeValue> values);

}
