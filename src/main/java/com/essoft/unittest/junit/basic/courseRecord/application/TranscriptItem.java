package com.essoft.unittest.junit.basic.courseRecord.application;

import com.essoft.unittest.junit.basic.courseRecord.model.Course;
import com.essoft.unittest.junit.basic.courseRecord.model.Semester;
import com.essoft.unittest.junit.basic.courseRecord.model.StudentCourseRecord;

public class TranscriptItem {

    private Course course;
    private int credit;
    private Semester semester;
    private StudentCourseRecord.Grade grade;

    public static TranscriptItemBuilder newTranscriptItem() {
        return new TranscriptItemBuilder(new TranscriptItem());
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public StudentCourseRecord.Grade getGrade() {
        return grade;
    }

    public void setGrade(StudentCourseRecord.Grade grade) {
        this.grade = grade;
    }

    static class TranscriptItemBuilder {
        private TranscriptItem transcriptItem;

        public TranscriptItemBuilder(TranscriptItem transcriptItem) {
            this.transcriptItem = transcriptItem;
        }

        public TranscriptItemBuilder withCourse(Course course) {
            this.transcriptItem.setCourse(course);
            return this;
        }

        public TranscriptItemBuilder withCredit(int credit) {
            this.transcriptItem.setCredit(credit);
            return this;
        }

        public TranscriptItemBuilder withSemester(Semester semester) {
            this.transcriptItem.setSemester(semester);
            return this;
        }

        public TranscriptItemBuilder withGrade(StudentCourseRecord.Grade grade) {
            this.transcriptItem.setGrade(grade);
            return this;
        }

        public TranscriptItem transcriptItem() {
            return this.transcriptItem;
        }
    }

}
