package com.essoft.unittest.courseRecord.application;

import com.essoft.unittest.courseRecord.model.Course;
import com.essoft.unittest.courseRecord.model.Lecturer;
import com.essoft.unittest.courseRecord.model.LecturerRepository;
import com.essoft.unittest.courseRecord.model.Semester;

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
