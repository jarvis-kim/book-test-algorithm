package kr.co.jarvisk.algorithm;

import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int total = scanner.nextInt();
        String str;
        for ( int index = 0; index < total; index++ ) {
            str = scanner.next();
            System.out.println("Hello, " + str + "!");
        }
    }
}
