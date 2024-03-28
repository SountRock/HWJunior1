package org.example;

import java.util.ArrayList;
import java.util.List;

public class Task1 {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>(List.of(1,2,3,4,5,6));

        double middle = numbers.stream().
                filter(elem -> (elem % 2 == 0)).
                mapToInt(Integer::intValue).
                summaryStatistics().
                getAverage();

        System.out.println(middle);
    }
}
