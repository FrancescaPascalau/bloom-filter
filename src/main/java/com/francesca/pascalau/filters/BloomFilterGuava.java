package com.francesca.pascalau.filters;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

public class BloomFilterGuava {

    private final BloomFilter<Integer> filter;

    /**
     * FPP - the desired false positive probability (must be positive and less than 1.0)
     */
    public BloomFilterGuava(int size, double fpp) {
        this.filter = BloomFilter.create(Funnels.integerFunnel(), size, fpp);
    }

    public void add(Integer integer) {
        filter.put(integer);
    }

    /**
     * When mightContain() returns true in our example, we can be 99% certain that the element is in the filter
     * and there is a one-percent probability that the result is a false positive.
     * When the filter returns false, we can be 100% certain that the element is not present.
     */
    public boolean mightContain(Integer integer) {
        return filter.mightContain(integer);
    }
}
