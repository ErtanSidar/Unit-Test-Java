package com.essoft.unittest.courseRecord.application;

import com.essoft.unittest.courseRecord.model.Course;
import com.essoft.unittest.courseRecord.model.Lecturer;
import com.essoft.unittest.courseRecord.model.Semester;

import java.util.Optional;

public interface LecturerService {

    Optional<Lecturer> findLecturer(Course course, Semester semester);
}
