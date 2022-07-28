package com.essoft.unittest.junit.basic.courseRecord.application;

import com.essoft.unittest.junit.basic.courseRecord.model.Course;
import com.essoft.unittest.junit.basic.courseRecord.model.Department;

import java.util.Optional;

public interface CourseService {
    Optional<Course> findCourse(Course course);

    Optional<Course> findCourse(Department department, String code, String name);
}
