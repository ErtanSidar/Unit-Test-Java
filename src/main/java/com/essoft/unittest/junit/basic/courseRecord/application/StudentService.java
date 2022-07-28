package com.essoft.unittest.junit.basic.courseRecord.application;

import com.essoft.unittest.junit.basic.courseRecord.model.Course;
import com.essoft.unittest.junit.basic.courseRecord.model.Semester;
import com.essoft.unittest.junit.basic.courseRecord.model.Student;
import com.essoft.unittest.junit.basic.courseRecord.model.StudentCourseRecord;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface StudentService {

    void addCourse(String studentId, Course course);

    void addCourse(String studentId, Course course, Semester semester);

    void dropCourse(String studentId, Course course);

    void addGrade(String studentId, Course course, StudentCourseRecord.Grade grade);

    boolean isTakeCourse(String studentId, Course course);

    BigDecimal gpa(String studentId);

    List<TranscriptItem> transcript(String studentId);

    Optional<Student> findStudent(String studentId);

    void deleteStudent(String studentId);
}
