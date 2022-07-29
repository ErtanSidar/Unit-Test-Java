package com.essoft.unittest.junit.basic.romanconverter;

import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class RomanConverter {

    private static Map<Integer, String> romanDigitMap = Map.of(
            50, "L",
            40, "XL",
            10, "X",
            9, "IX",
            5, "V",
            4, "IV",
            1, "I"
    );

    public static String convert(int number) {

        StringBuilder result = new StringBuilder();
        AtomicInteger atomicNumber = new AtomicInteger(number);
        romanDigitMap.keySet().stream().sorted(Comparator.reverseOrder())
                .forEach(numberForRomanDigit -> {
                    while (atomicNumber.get() >= numberForRomanDigit) {
                        result.append(romanDigitMap.get(numberForRomanDigit));
                        atomicNumber.set(atomicNumber.get() - numberForRomanDigit);
                    }
                });
        return result.toString();
    }
}
