package kr.co.jarvisk.algorithm.apss.dynamic;

public class JumpGameByDynamic {

    static int[][] map;
    static int[][] cache;
    static int n;
    public static void main(String[] args) {
        map = new int[ ][ ] {
                { 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 2 },
                { 1, 1, 1, 1, 1, 2, 0 },
        };
        n = map.length;
        cache = new int[ n ][ n ];
        for ( int i = 0; i < n; i++ ) {
            for ( int j = 0; j < n; j++ ) {
                cache[ i ][ j ] = -1;
            }
        }

        System.out.println(jump(0, 0));

    }

    static int jump(int y, int x) {
        if ( y >= n || x >= n ) {
            return 0;
        }
        if ( n - 1 == y && n - 1 == x ) {
            return 1;
        }

        int ret = cache[ y ][ x ];
        if ( ret != -1 ) {
            return ret;
        }

        int size = map[ y ][ x ];
        if ( (cache[ y ][ x ] = jump(y, x + size)) != 1 ) {
            cache[ y ][ x ] = jump(y + size, x);
        }
        return cache[ y ][ x ];
    }
}
