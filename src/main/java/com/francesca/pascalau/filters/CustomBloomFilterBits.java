package com.francesca.pascalau.filters;

public class CustomBloomFilterBits {

    private static final long SIZE = 1000000;
    private static final double FPP = 0.01;

    private int numBits;
    private int numHashFunctions;
    private long[] bitMap;

    public void init() {
        this.numBits = (int) optimalNumOfBits(SIZE, FPP);
        this.numHashFunctions = optimalNumOfHashFunctions(SIZE, numBits);
        initBitMap();
    }

    private void initBitMap() {
        long bitMapLength = this.numBits / 64;
        if ((this.numBits & 63) != 0) {
            bitMapLength++;
        }

        if (bitMapLength > Integer.MAX_VALUE) {
            throw new RuntimeException("Array length is too long");
        }

        this.bitMap = new long[(int) bitMapLength];
    }

    // Calculation hash Number of functions 
    private int optimalNumOfHashFunctions(long n, long m) {
        return Math.max(1, (int) Math.round((double) m / n * Math.log(2)));
    }

    // Calculation bit The length of the array 
    private long optimalNumOfBits(long n, double p) {
        if (p == 0) {
            p = Double.MIN_VALUE;
        }

        return (long) (-n * Math.log(p) / (Math.log(2) * Math.log(2)));
    }

    /**
     * Judge keys Whether it exists in a set
     */
    public boolean isExist(String key) {
        int[] indexs = getIndexs(key);
        for (int index : indexs) {
            int bitMapIndex = index / 64;
            int bitIndex = index & 63;
            long l = (long) 1 << bitIndex;
            long result = this.bitMap[bitMapIndex] & l;
            if (result == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * take key Deposit in redis bitmap
     */
    public void put(String key) {
        int[] indexs = getIndexs(key);
        for (int index : indexs) {
            int bitMapIndex = index / 64;
            int bitIndex = index & 63;
            long l = this.bitMap[bitMapIndex];
            long i = (long) 1 << bitIndex;
            long result = l | i;
            this.bitMap[bitMapIndex] = result;
        }
    }

    /**
     * according to key obtain bitmap Suffix Array
     */
    private int[] getIndexs(String key) {
        int[] indexs = new int[numHashFunctions];
// Negative number not allowed 
        for (int i = 0; i < numHashFunctions; i++) {
            int index = hash1(key) + i * hash2(key);
            if (index < 0) {
                index = ~index;
            }
            indexs[i] = index % numBits;
        }
        return indexs;
    }

    public int hash1(String key) {
        int h;
        return (h = key.hashCode()) ^ (h >>> 16);
    }

    public int hash2(String key) {
        int h;
        return h = key.hashCode();
    }
}
