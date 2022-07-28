package com.essoft.unittest.courseRecord.model;

import java.util.Optional;

public interface CourseRepository {

    Optional<Course> findByExample(Course course);

    Optional<Course> findByDepartmentAndCodeAndName(Department department, String code, String name);
}
