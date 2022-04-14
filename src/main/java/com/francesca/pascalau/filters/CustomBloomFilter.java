package com.francesca.pascalau.filters;

public class CustomBloomFilter {

    private final int size;
    private final Boolean[] list;

    public CustomBloomFilter(int size) {
        this.size = size;
        this.list = new Boolean[size];
    }

    public void add(Object element) {
        int code = element.hashCode();

        list[code % size] = Boolean.TRUE;
    }

    public Boolean mightContain(Object element) {
        int code = element.hashCode();

        return Boolean.TRUE.equals(list[code % size]);
    }
}
