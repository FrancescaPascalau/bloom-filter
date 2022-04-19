package com.francesca.pascalau;

import com.francesca.pascalau.filters.BloomFilterGuava;
import com.francesca.pascalau.filters.CustomBloomFilter;
import com.francesca.pascalau.filters.CustomBloomFilterBits;
import com.francesca.pascalau.filters.SimpleBloomFilter;
import com.francesca.pascalau.service.SmsService;

import java.util.List;

public class BloomFilterApplication {

    private static final BloomFilterGuava bloomFilterGuava = new BloomFilterGuava(5, 0.01);
    private static final CustomBloomFilter customBloomFilter = new CustomBloomFilter(100);
    private static final CustomBloomFilterBits customizeBloomJob = new CustomBloomFilterBits();

    private static final SmsService smsService = new SmsService(200);

    public static void main(String[] args) {
        bloomFilter();
        customBloomFilter();
        bits();
        simpleBits();

        smsService.sendSms("600 000 001");
        smsService.sendSms("600 000 002");
        smsService.sendSms("600 000 010");
        smsService.sendSms("600 000 001");
    }

    private static void customBloomFilter() {
        customBloomFilter.add(1);
        customBloomFilter.add("Hi");
        customBloomFilter.add(Boolean.TRUE);
        customBloomFilter.add(1111);

        System.out.println("\n=== Starting Custom Bloom Filter ===");
        System.out.println(customBloomFilter.mightContain(1));
        System.out.println(customBloomFilter.mightContain("What"));
        System.out.println(customBloomFilter.mightContain("Hi"));
        System.out.println(customBloomFilter.mightContain(2));
        System.out.println("\n=== Starting Custom Bloom Filter ===");
    }

    private static void bloomFilter() {
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
        System.out.println("\n=== Stopping Bloom Filter from Guava ===");
    }

    private static void bits() {
        System.out.println("\n=== Starting Custom Bloom Filter on Bits ===");
        customizeBloomJob.init();

        int exitsNums = 0;
        for (int i = 0; i < 800000; i++) {
            customizeBloomJob.put(String.valueOf(i));
        }

        for (int i = 0; i < 1000000; i++) {
            if (customizeBloomJob.isExist(String.valueOf(i))) {
                exitsNums++;
            }
        }
        System.out.println("Existent elements = " + exitsNums);
        System.out.println("\n=== Stopping Custom Bloom Filter on Bits ===");
    }

    private static void simpleBits() {
        System.out.println("\n=== Starting Simple Bits Bloom Filter ===");

        long[] list = new long[0];
        SimpleBloomFilter<Long> simpleBloomFilter = new SimpleBloomFilter<>(list, 32, List.of());

        simpleBloomFilter.add(1L);
        simpleBloomFilter.add(2L);
        simpleBloomFilter.add(6L);
        simpleBloomFilter.add(10L);
        simpleBloomFilter.add(111L);

        System.out.println(simpleBloomFilter.mightContain(1L)); //true
        System.out.println(simpleBloomFilter.mightContain(2L)); //true
        System.out.println(simpleBloomFilter.mightContain(6L)); //true
        System.out.println(simpleBloomFilter.mightContain(10L)); //true
        System.out.println(simpleBloomFilter.mightContain(111L)); //true
        System.out.println(simpleBloomFilter.mightContain(20L)); //false
        System.out.println(simpleBloomFilter.mightContain(11L)); //false
        System.out.println("\n=== Stopping Simple Bits Bloom Filter ===");
    }
}
