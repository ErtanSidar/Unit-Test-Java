package com.essoft.unittest.whatIsUnitTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FizzBuzzTest {

    private FizzBuzz fizzBuzz;

    @BeforeEach
    void setUp() {
        fizzBuzz = new FizzBuzz();
    }

    @Test
    void returnFizzWhenTheNumberIsDividedByThree() {
        assertEquals("Fizz", fizzBuzz.stringFor(3));
    }
    @Test
    void returnBuzzWhenTheNumberIsDividedByFive() {
        assertEquals("Buzz",fizzBuzz.stringFor(5));
    }
    @Test
    void returnFizzBuzzWhenTheNumberIsDividedBothOfThreeAndFive() {
        assertEquals("FizzBuzz",fizzBuzz.stringFor(15));
    }
    @Test
    void returnTheNumberItselfWhenTheNumberIsNotDividedAnyOfThreeOrFive() {
        assertEquals("7",fizzBuzz.stringFor(7));
    }
    @Test
    void throwsIllegalArgumentExceptionWhenTheNumberIsLessThanOneOrGreaterThan100() {
        assertThrows(IllegalArgumentException.class, ()-> fizzBuzz.stringFor(-1));
        assertThrows(IllegalArgumentException.class, ()-> fizzBuzz.stringFor(101));
    }
}
