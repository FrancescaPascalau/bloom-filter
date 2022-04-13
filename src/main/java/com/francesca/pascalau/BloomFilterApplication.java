package com.francesca.pascalau;

import com.francesca.pascalau.filters.BloomFilterGuava;
import com.francesca.pascalau.filters.CustomBloomFilter;

public class BloomFilterApplication {

    public static void main(String[] args) {
        BloomFilterGuava bloomFilterGuava = new BloomFilterGuava(5, 0.01);

        bloomFilterGuava.add(1);
        bloomFilterGuava.add(2);
        bloomFilterGuava.add(3);

        System.out.println("\n=== Starting Bloom Filter from Guava ===");
        System.out.println(bloomFilterGuava.mightContain(1)); //true
        System.out.println(bloomFilterGuava.mightContain(2)); //true
        System.out.println(bloomFilterGuava.mightContain(3)); //true
        System.out.println(bloomFilterGuava.mightContain(4)); //false
        System.out.println(bloomFilterGuava.mightContain(10)); //false
        System.out.println(bloomFilterGuava.mightContain(22)); //false

        CustomBloomFilter customBloomFilter = new CustomBloomFilter(100);

        customBloomFilter.add(1);
        customBloomFilter.add("Francesca");
        customBloomFilter.add(Boolean.TRUE);
        customBloomFilter.add(1111);

        System.out.println(customBloomFilter.mightContain(1));
        System.out.println(customBloomFilter.mightContain("France"));
        System.out.println(customBloomFilter.mightContain("Hi"));
        System.out.println(customBloomFilter.mightContain(2));
    }
}
