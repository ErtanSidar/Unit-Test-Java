package com.essoft.unittest.junit.basic.courseRecord.application;

import com.essoft.unittest.junit.basic.courseRecord.model.Course;
import com.essoft.unittest.junit.basic.courseRecord.model.Lecturer;
import com.essoft.unittest.junit.basic.courseRecord.model.LecturerRepository;
import com.essoft.unittest.junit.basic.courseRecord.model.Semester;

import java.util.Optional;

public class LecturerServiceImpl implements LecturerService {

    private final LecturerRepository lecturerRepository;

    public LecturerServiceImpl(LecturerRepository lecturerRepository) {
        this.lecturerRepository = lecturerRepository;
    }

    @Override
    public Optional<Lecturer> findLecturer(Course course, Semester semester) {
        if (course == null || semester == null) {
            throw new IllegalArgumentException("Can't find lecturer without course and semester");
        }
        return lecturerRepository.findByCourseAndSemester(course, semester);
    }
}
