package com.francesca.pascalau;

import com.clearspring.analytics.stream.frequency.CountMinSketch;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class CmsApplication {

    private static final CountMinSketch countMinSketch = new CountMinSketch(0.001, 0.99, 1);

    public static void main(String[] args) {
        useMultiset();

        useCountMinSketch();
    }

    private static void useCountMinSketch() {
        countMinSketch.add("75.245.10.1", 1);
        countMinSketch.add("10.125.22.20", 1);
        countMinSketch.add("192.170.0.1", 2);

        System.out.println(countMinSketch.estimateCount("192.170.0.1"));
        System.out.println(countMinSketch.estimateCount("999.999.99.99"));
    }

    private static void useMultiset() {
        /*
         * So a Multiset occupies a sort of grey area between a List and a Set.
         * Duplicates allowed, but no guaranteed order.
         * In multisets the order of elements is irrelevant.
         * For example: The multisets {a, a, b} and {a, b, a} are equal.
         */
        Multiset<String> blackListedIPs = HashMultiset.create();
        blackListedIPs.add("192.170.0.1");
        blackListedIPs.add("75.245.10.1");
        blackListedIPs.add("10.125.22.20");
        blackListedIPs.add("192.170.0.1");

        System.out.println(blackListedIPs.count("192.170.0.1"));
        System.out.println(blackListedIPs.count("10.125.22.20"));
    }
}
