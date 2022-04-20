package com.francesca.pascalau.filters;

public class CustomBloomFilter {

    private final int size;
    private final Boolean[] list;

    public CustomBloomFilter(int size) {
        this.size = size;
        this.list = new Boolean[size];
    }

    public void add(Object element) {
        int hashCode1 = element.hashCode();
        int hashCode2 = (element.hashCode() + 17) / 23;

        list[hashCode1 % size] = Boolean.TRUE;
        list[hashCode2 % size] = Boolean.TRUE;
    }

    public Boolean mightContain(Object element) {
        int hashCode1 = element.hashCode();
        int hashCode2 = (element.hashCode() + 17) / 23;

        return Boolean.TRUE.equals(list[hashCode1 % size]) && Boolean.TRUE.equals(list[hashCode2 % size]);
    }
}
