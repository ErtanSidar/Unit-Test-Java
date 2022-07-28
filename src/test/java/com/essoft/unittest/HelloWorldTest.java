package com.essoft.unittest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloWorldTest {
    @Test
    void sayHelloToWorld() {
        HelloWorld helloWorld =new HelloWorld();
        assertEquals("Hello World", helloWorld.sayHello(),"Say Hello To World");
    }
}
