package kr.co.jarvisk.algorithm.apss.dynamic;

/**
 * 2 x N 크기의 사각형을 덮는 방법의 수.
 */
public class Tiling {

    public static void main(String[] args) {
        System.out.println(new Tiling().tiling(1));
        System.out.println(new Tiling().tiling(2));
        System.out.println(new Tiling().tiling(3));
        System.out.println(new Tiling().tiling(4));
        System.out.println(new Tiling().tiling(5));
    }


    int cache[] = new int[ 100 ];

    int tiling(int n) {
        if ( n <= 0 ) {
            return 0;
        }

        if ( cache[ n ] > 0 ) {
            return cache[ n ];
        }

        int result = 0;
        if ( n <= 2 ) {
            result += 1;
        }

        return cache[ n ] = result + tiling(n - 1) + tiling(n - 2);
    }
}
