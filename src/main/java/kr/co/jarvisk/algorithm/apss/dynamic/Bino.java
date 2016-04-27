package kr.co.jarvisk.algorithm.apss.dynamic;

public class Bino {

    private int[][] map;

    public static void main(String[] args) {

        System.out.println(new Bino().bino(4, 5));

    }

    public int bino(int n, int r) {
        map = new int[ n ][ r ];
        for ( int i = 0; i < n; i++ ) {
            map[ i ][ 0 ] = 1;
        }
        for ( int i = 0; i < r; i++ ) {
            map[ 0 ][ i ] = 1;
        }

        for ( int nIndex = 1; nIndex < n; nIndex++ ) {
            for ( int rIndex = 1; rIndex < r; rIndex++ ) {
                map[ nIndex ][ rIndex ] = map[ nIndex - 1 ][ rIndex - 1 ] + map[ nIndex - 1][ rIndex ];
            }
        }

        return map[ n - 1 ][ r - 1 ];

    }
}
