package com.francesca.pascalau;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SimpleApplication {

    public static final List<Long> list = new ArrayList<>();
    public static final Map<Long, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        collectionsCount();

        mapCount();

        streamCount();
    }

    private static void collectionsCount() {
        for (int i = 0; i <= 10; i++)
            list.add((long) i);
        
        list.add(1L);
        list.add(1L);
        list.add(1L);

        // How many times does a specific number appears in the list
        int occurrences = Collections.frequency(list, 1L);
        System.out.println("Element count with collections by a single element: " + occurrences);
    }

    private static void streamCount() {
        Map<Long, Long> counterMap = list.stream()
                .collect(Collectors.groupingBy(number -> number, Collectors.counting()));

        System.out.println("Element count with streams: " + counterMap.get(1L));
    }

    private static void mapCount() {
        for (int i = 0; i <= 10; i++)
            add((long) i);

        add(1L);
        add(1L);
        add(1L);
        add(1L);
        add(1L);
        add(1L);

        // How many times does a specific number appears in the list
        int occurrences = count(1L);
        System.out.println("Element count with map: " + occurrences);
    }

    private static void add(Long number) {
        if (!map.containsKey(number)) {
            map.put(number, 1);
        } else {
            map.put(number, map.get(number) + 1);
        }
    }

    private static int count(Long number) {
        if (!map.containsKey(number)) {
            return 0;
        }
        return map.get(number);
    }
}