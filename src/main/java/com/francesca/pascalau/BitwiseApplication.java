package com.francesca.pascalau;

public class BitwiseApplication {

    public static void main(String[] args) {
        int number1 = 123;
        int number2 = 456;

        System.out.format(" Bitwise AND Operator: %d & %d = %d \n", number1, number2, number1 & number2);
        System.out.format(" Bitwise OR Operator: %d | %d = %d \n", number1, number2, number1 | number2);
        System.out.format(" Bitwise EXCLUSIVE OR: Operator %d ^ %d = %d \n", number1, number2, number1 ^ number2);
        System.out.format(" Bitwise NOT Operator: ~%d = %d \n", number1, ~number1);

        int shiftNumber = 1;
        System.out.format(" LEFT SHIFT Operator: %d << 1 = %d \n", number1, number1 << shiftNumber);
        System.out.format(" RIGHT SHIFT Operator: %d >> 1 = %d \n", number2, number2 >> shiftNumber);
    }
}
