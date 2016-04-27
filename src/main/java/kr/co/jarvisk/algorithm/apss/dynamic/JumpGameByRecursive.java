package kr.co.jarvisk.algorithm.apss.dynamic;

public class JumpGameByRecursive {

    static int[][] map;
    static int n;
    public static void main(String[] args) {
        map = new int[ ][ ] {
                { 2, 5, 1, 6, 1, 4, 1 },
                { 6, 1, 1, 2, 2, 9, 3 },
                { 7, 2, 3, 2, 1, 3, 1 },
                { 1, 1, 3, 1, 7, 1, 2 },
                { 4, 1, 2, 3, 4, 1, 3 },
                { 3, 3, 1, 2, 3, 4, 1 },
                { 1, 5, 2, 9, 4, 7, 0 },
        };
        n = map.length;

        System.out.println(jump(0, 0));

    }

    static boolean jump(int y, int x) {
        boolean result = false;
        if ( n - 1 == y && n - 1 == x ) {
            return true;
        }

        int value = map[ y ][ x ];
        if ( x + value < n ) {
            result |= jump(y, x + value);
        }
        if ( y + value < n ) {
            result |= jump(y + value, x);
        }

        return result;
    }
}
