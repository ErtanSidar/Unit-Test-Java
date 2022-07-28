package com.essoft.unittest.junit.basic.fibonacci;

import com.essoft.unittest.junit.basic.fibonacci.FibonacciNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FibonacciNumberTest {
    @Test
    @DisplayName("Find Fibonacci Numbers for Specific Order")
    void findFibonacciNumbers() {
        FibonacciNumber fibonacciNumber = new FibonacciNumber();
        assertThrows(IllegalArgumentException.class,()->fibonacciNumber.find(0));
        assertAll("Fibonacci Numbers",
                ()->assertEquals(1,fibonacciNumber.find(1)),
                ()->assertEquals(1,fibonacciNumber.find(2)),
                ()->assertEquals(2,fibonacciNumber.find(3))
        );
    }
}
