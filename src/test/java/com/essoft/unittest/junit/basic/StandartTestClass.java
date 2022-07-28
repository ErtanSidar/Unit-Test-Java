package com.essoft.unittest.junit.basic;

import org.junit.jupiter.api.*;

import java.util.Random;

public class StandartTestClass {

    private static String oneInstancePerClass;
    private Integer onInstancePerMethod;

    @BeforeAll
    static void initAll() {
        oneInstancePerClass=String.valueOf(new Random().nextInt());
        System.out.println("Init Before All Test Method");
    }
    @AfterAll
    static void tearDownAll() {
        oneInstancePerClass=null;
        System.out.println("Tear Down After All Test Method");
    }
    @BeforeEach
    void init() {
        onInstancePerMethod=new Random().nextInt();
        System.out.println("Init Before Each Test Method");
    }
    @AfterEach
    void tearDown() {
        oneInstancePerClass=null;
        System.out.println("Tear Down After Each Test Method");
    }
    @Test
    void testSomething1() {
        System.out.println("Test: testSomething1 :"+onInstancePerMethod+":"+oneInstancePerClass);
    }
    @Test
    void testSomething2() {
        System.out.println("Test: testSomething2 :"+onInstancePerMethod+":"+oneInstancePerClass);
    }
    @Test
    @Disabled("This test is not in scope for now")
    void testSomething3() {
        System.out.println("Test: testSomething3");
    }
    @Test
    void testSomethingToFail() {
        Assertions.fail("A failing test...");
    }
}
