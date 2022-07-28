package com.essoft.unittest.courseRecord.application;

import com.essoft.unittest.courseRecord.model.Course;
import com.essoft.unittest.courseRecord.model.Department;

import java.util.Optional;

public interface CourseService {
    Optional<Course> findCourse(Course course);

    Optional<Course> findCourse(Department department, String code, String name);
}
