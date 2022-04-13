package com.francesca.pascalau.filters;

import java.util.ArrayList;
import java.util.List;

public class CustomBloomFilter {

    private final int size;
    private final List<Boolean> list;

    public CustomBloomFilter(int size) {
        this.size = size;
        this.list = new ArrayList<>(size);
    }

    public void add(Object element) {
        int hashCode = element.hashCode();

        list.set(hashCode % size, Boolean.TRUE);
    }

    public Boolean mightContain(Object element) {
        int hashCode = element.hashCode();

        return Boolean.TRUE.equals(list.get(hashCode % size));
    }
}
