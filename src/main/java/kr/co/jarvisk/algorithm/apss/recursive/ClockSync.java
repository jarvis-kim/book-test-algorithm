package kr.co.jarvisk.algorithm.apss.recursive;

import java.util.Iterator;
import java.util.Stack;

public class ClockSync {

    private static int clocks[] = new int[] { 12, 6, 6, 6, 6, 6, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12 };

    private static final int HANDS_OF_CLOCK = 3;

    private static final int SWITCH_MAX_COUNT = 3;

    private static final int TWELVE = 12;

    private static final int[][] SWITCHES = new int[][] {
            new int[] { 0, 1, 2 },
            new int[] { 3, 7, 9, 11 },
            new int[] { 4, 10, 14, 15 },
            new int[] { 0, 4, 5, 6, 7 },
            new int[] { 6, 7, 8, 10, 12 },
            new int[] { 0, 2, 14, 15 },
            new int[] { 3, 14, 15 },
            new int[] { 4, 5, 7, 14, 15 },
            new int[] { 1, 2, 3, 4, 5 },
            new int[] { 3, 4, 5, 9, 13 }
    };

    public static void main(String[] args) {
        int result = -1;
        Stack<Integer> stack;
        for ( int repeat = 1; repeat <= SWITCH_MAX_COUNT; repeat++ ) {
            result = sync(0, repeat, stack = new Stack<Integer>());
            if ( result >= 0 ) {
                printArray(stack);
                break;
            }
        }
        System.out.println(result);

//        for ( int repeat = 1; repeat <= 2; repeat++ ) {
//            test(5, 0, repeat, new Stack<Integer>());
//        }
    }

    public static int sync(int startSwitch, int repeat, Stack<Integer> currentNumbers) {
        if ( isAllTwelve() ) {
            return currentNumbers.size();
        }

        int result;
        for ( int switchNumber = startSwitch; switchNumber < SWITCHES.length; switchNumber++ ) {
            for ( int repeatCount = 0; repeatCount < repeat; repeatCount++ ) {
                currentNumbers.push(switchNumber);
                turnAtRight(switchNumber);
            }
            result = sync(switchNumber + 1, repeat, currentNumbers);
            if ( result > 0 ) {
                return result;
            }
            for ( int repeatCount = 0; repeatCount < repeat; repeatCount++ ) {
                turnAtLeft(currentNumbers.pop());
            }
        }

        return -1;
    }

    private static void turnAtRight(int switchNumber) {
        int length = SWITCHES[ switchNumber ].length;
        for ( int switchClock = 0; switchClock < length; switchClock++ ) {
            int clockNumber = SWITCHES[ switchNumber ][ switchClock ];
            clocks[ clockNumber ] = clocks[ clockNumber ] % TWELVE;
            clocks[ clockNumber ] += HANDS_OF_CLOCK;
        }
    }

    private static void turnAtLeft(int switchNumber) {
        int length = SWITCHES[ switchNumber ].length;
        for ( int switchClock = 0; switchClock < length; switchClock++ ) {
            int clockNumber = SWITCHES[ switchNumber ][ switchClock ];
            clocks[ clockNumber ] -= HANDS_OF_CLOCK;
            if ( clocks[ clockNumber ] == 0 ) {
                clocks[ clockNumber ] = TWELVE;
            }
        }
    }


    private static boolean isAllTwelve() {
        for ( int index = 0; index < clocks.length; index++ ) {
            if ( clocks[ index ] != 12 ) {
                return false;
            }
        }

        return true;
    }

    public static void test(int maxNumber, int startNumber, int repeat, Stack<Integer> stack) {
        for ( int number = startNumber; number < maxNumber; number++ ) {
            for ( int repeatCount = 0; repeatCount < repeat; repeatCount++ ) {
                stack.push(number);
            }
            printArray(stack);
            test(maxNumber, number + 1, repeat, stack);
            for ( int repeatCount = 0; repeatCount < repeat; repeatCount++ ) {
                stack.pop();
            }
        }
    }

    public static void printArray(Iterable<Integer> integers) {
        Iterator<Integer> iterator = integers.iterator();
        while ( iterator.hasNext() ) {
            System.out.print(iterator.next());
            if ( iterator.hasNext() ) {
                System.out.print(" - ");
            }
        }
        System.out.println();
    }
}
