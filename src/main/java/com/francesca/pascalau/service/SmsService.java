package com.francesca.pascalau.service;

import com.francesca.pascalau.filters.CustomBloomFilter;

public class SmsService {

    private final CustomBloomFilter phoneBook;

    public SmsService(int size) {
        this.phoneBook = new CustomBloomFilter(size);
    }

    public void sendSms(String phoneNumber) {
        System.out.println("Was SMS already sent to: " + phoneNumber + "?\n- " + phoneBook.mightContain(phoneNumber));

        if (!phoneBook.mightContain(phoneNumber)) {
            System.out.println("Sending SMS to phone number: " + phoneNumber);
            phoneBook.add(phoneNumber);
            System.out.println("After sending SMS: " + "\n- " + phoneBook.mightContain(phoneNumber));
        } else
            System.out.println("Not sending SMS again to phone number: " + phoneNumber);
    }
}
