package com.francesca.pascalau;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import net.agkn.hll.HLL;

import java.util.stream.LongStream;

public class HllApplication {

    private static final HLL hyperLogLog = new HLL(14, 5);

    public static final HashFunction HASH_FUNCTION = Hashing.murmur3_128();
    public static final long NUMBER_OF_ELEMENTS = 100_000_000;
    public static final long TOLERATED_DIFFERENCE = 1_000_000;

    public static void main(String[] args) {
        insertElements();

        long cardinality = hyperLogLog.cardinality();
        System.out.println("\nCardinality is: " + cardinality + " and the tolerated differance was: " + TOLERATED_DIFFERENCE);
    }

    private static void insertElements() {
        LongStream.range(0, HllApplication.NUMBER_OF_ELEMENTS).forEach(element -> {
                    long hashedValue = HllApplication.HASH_FUNCTION.newHasher().putLong(element).hash().asLong();
                    hyperLogLog.addRaw(hashedValue);
                }
        );
    }
}