package com.essoft.unittest.courseRecord.model;

public class NoCourseFoundForStudentException extends RuntimeException {

    public NoCourseFoundForStudentException(String message) {
        super(message);
    }
}
