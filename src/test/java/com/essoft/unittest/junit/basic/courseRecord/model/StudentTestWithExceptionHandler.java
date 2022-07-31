package com.essoft.unittest.junit.basic.courseRecord.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

public class StudentTestWithExceptionHandler {

    @ExtendWith(IllegalArgumentExceptionHandlerExtension.class)
    @Nested
    @DisplayName("Add Course to Student(Exceptional)")
    @Tag("exceptional")
    class AddCourseToStudentExceptionScenario {
        @Test
        @DisplayName("Got an exception when add a null lecturer course record to student")
        void throwsExceptionWhenAddToNullCourseToStudent() {

            final Student ahmet = new Student("1", "Ahmet", "Can");
            ahmet.addCourse(null);
        }
    }
}