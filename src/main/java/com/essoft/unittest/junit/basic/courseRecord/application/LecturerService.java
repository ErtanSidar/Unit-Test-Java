package com.essoft.unittest.junit.basic.courseRecord.application;

import com.essoft.unittest.junit.basic.courseRecord.model.Course;
import com.essoft.unittest.junit.basic.courseRecord.model.Lecturer;
import com.essoft.unittest.junit.basic.courseRecord.model.Semester;

import java.util.Optional;

public interface LecturerService {

    Optional<Lecturer> findLecturer(Course course, Semester semester);
}
