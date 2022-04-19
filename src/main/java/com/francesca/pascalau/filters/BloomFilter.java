package com.francesca.pascalau.filters;

public interface BloomFilter<T> {
    void add(T value);
    boolean mightContain(T value);
}