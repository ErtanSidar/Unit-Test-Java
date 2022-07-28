package com.essoft.unittest.junit.basic.courseRecord.model;

public class NoCourseFoundForStudentException extends RuntimeException {

    public NoCourseFoundForStudentException(String message) {
        super(message);
    }
}
