package com.essoft.unittest.junit.basic.courseRecord.model;

import java.util.Optional;

public interface LecturerRepository {

    Optional<Lecturer> findByCourseAndSemester(Course course, Semester semester);
}
