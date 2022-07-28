package com.essoft.unittest.junit.basic.courseRecord.model;

import com.essoft.unittest.courseRecord.model.LecturerCourseRecord;
import com.essoft.unittest.courseRecord.model.Student;
import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

@Tag("student")
public class StudentTest {

    @Test
    @DisplayName("Test every student must have an id name and surname")
    void shouldCreateStudentWithIdNameAndSurname() {
        /**
         * Assertions.*
         * assertEquals
         * assertEquals with message
         * assertNotEquals
         * assertTrue with lazily evaluated message
         * assertFalse with boolean supplier
         * assertNull
         * assertNotNull
         * assertArrayEquals
         * assertSame
         */

        Student ertan = new Student("1","Ertan","Sidar");
        assertEquals("Ertan",ertan.getName());
        assertEquals("Ertan",ertan.getName(),"Student's name");
        assertNotEquals("Ertann",ertan.getName(),"Student's name");

        assertTrue(ertan.getName().startsWith("E"));
        assertTrue(ertan.getName().startsWith("E"), ()->"Student's name "+" starts with E");
        assertFalse(()-> {
            Student mehmet = new Student("id1","Mehmet","Can");
            return mehmet.getName().endsWith("M");
        },()->"Student's name"+"ends with M");

        final Student ahmet = new Student("2","Ahmet","Yılmaz");
        assertArrayEquals(new String []{"Ertan","Ahmet"}, Stream.of(ertan,ahmet).map(Student::getName).toArray());
        Student student=ertan;
        assertSame(ertan,student);
        assertNotSame(ahmet,student);
    }

    @Test
    @DisplayName("Test every student must have an id, name and surname with grouped assertions")
    @Tag("createStudent")
    void shouldCreateStudentWithIdNameAndSurnameWithGroupedAssertions() {

        /**
         * grouped assertions
         * failed grouped assertions
         * dependent assertions
         */

        // In a grouped assertion all assertions are executed,
        Student ertan = new Student("1", "Ertan", "Sidar");

        assertAll("Student's name check",
                () -> assertEquals("Ertan", ertan.getName()),
                () -> assertEquals("Ertan", ertan.getName(), "Student's name"),
                () -> assertNotEquals("Ertann", ertan.getName(), "Student's name")
        );

        // and any failures will be reported together.
        assertAll("Student's name character check",
                () -> assertTrue(ertan.getName().startsWith("E")),
                () -> assertTrue(ertan.getName().startsWith("E"), () -> "Student's name " + "starts with  E"),
                () -> assertFalse(() -> {
                    Student mehmet = new Student("id1", "Mehmet", "Can");
                    return mehmet.getName().endsWith("M");
                }, () -> "Student's name " + "ends with M")
        );

        //dependent assertions
        assertAll(() -> {
                    final Student ahmet = new Student("2", "Ahmet", "Yilmaz");

                    assertArrayEquals(new String[]{"Ertan", "Ahmet"}, Stream.of(ertan, ahmet).map(Student::getName).toArray());
                },
                () -> {
                    Student student = ertan;
                    final Student ahmet = new Student("2", "Ahmet", "Yilmaz");

                    assertSame(ertan, student); // ertan == student
                    assertNotSame(student, ahmet);
                });
    }

    @Test
    @DisplayName("Got an exception when add a null lecturer course record to student")
    @Tags({@Tag("exceptional"), @Tag("addCourse")})
    void throwsExceptionWhenAddToNullCourseToStudent() {

        final Student ahmet = new Student("1", "Ahmet", "Can");
        assertThrows(IllegalArgumentException.class, () -> ahmet.addCourse(null));
        assertThrows(IllegalArgumentException.class, () -> ahmet.addCourse(null), "Throws IllegalArgumentException");
        final IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> ahmet.addCourse(null));
        assertEquals("Can't add course with null lecturer course record", illegalArgumentException.getMessage());
    }

    @Test
    @DisplayName("Add course to a student less than 10ms")
    @Tag("addCourse")
    void addCourseToStudentWithATimeConstraint() {
        /**
         * timeoutNotExceeded
         * timeoutNotExceededWithResult
         * timeoutNotExceededWithMethod
         * timeoutExceeded
         * timeoutExceededWithPreemptiveTermination
         */

        assertTimeout(Duration.ofMillis(10), () -> {
            //nothing will be done and this code run under 10ms
        });

        final String result = assertTimeout(Duration.ofMillis(10), () -> {
            //return a string and this code run under 10ms
            return "some string result";
        });
        assertEquals("some string result", result);

        final Student student = new Student("1", "Ahmet", "Can");
        LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(null, null);
        assertTimeout(Duration.ofMillis(10), () -> student.addCourse(lecturerCourseRecord));

        assertTimeoutPreemptively(Duration.ofMillis(10), () -> student.addCourse(lecturerCourseRecord));
    }

    @Test
    @DisplayName("Test student creation at only development machine")
    @Tag("createStudent")
    void shouldCreateStudentWithNameAndSurnameAtDevelopmentMachine() {

        assumeTrue(System.getProperty("ENV") != null, "Aborting Test: System property ENV doesn't exist!");
        assumeTrue(System.getProperty("ENV").equals("dev"), "Aborting Test: Not on a developer machine!");

        final Student ahmet = new Student("1", "Ahmet", "Can");
        assertAll("Student Information",
                () -> assertEquals("Ahmet", ahmet.getName()),
                () -> assertEquals("Can", ahmet.getSurname()),
                () -> assertEquals("1", ahmet.getId())
        );
    }

    @Test
    @DisplayName("Test student creation at different environments")
    @Tag("createStudent")
    void shouldCreateStudentWithNameAndSurnameWithSpecificEnvironment() {

        final Student ahmet = new Student("1", "Ahmet", "Can");

        final String env = System.getProperty("ENV");
        assumingThat(env != null && env.equals("dev"), () -> {
            LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(null, null);
            ahmet.addCourse(lecturerCourseRecord);
            assertEquals(1, ahmet.getStudentCourseRecords().size());
        });

        assertAll("Student Information",
                () -> assertEquals("Ahmet", ahmet.getName()),
                () -> assertEquals("Can", ahmet.getSurname()),
                () -> assertEquals("1", ahmet.getId())
        );
    }

    @Disabled("No more valid scenario")
    @Test
    @DisplayName("Test that student must have only number id")
    @Tag("createStudent")
    void shouldCreateStudentWithNumberId() {
        assertThrows(IllegalArgumentException.class, () -> new Student("id", "Ahmet", "Can"));
    }
}
