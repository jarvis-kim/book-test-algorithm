package kr.co.jarvisk.algorithm.apss.dynamic;

public class PI {

    public static void main(String[] args) {
        System.out.println(new PI(new int[] { 1, 2, 3, 4, 1, 2, 3, 4 }).pi());
        System.out.println(new PI(new int[] { 1, 1, 1, 1, 1, 2, 2, 2 }).pi());
        System.out.println(new PI(new int[] { 1, 2, 1, 2, 2, 2, 2, 2 }).pi());
        System.out.println(new PI(new int[] { 2, 2, 2, 2, 2, 2, 2, 2 }).pi());
        System.out.println(new PI(new int[] { 1, 2, 6, 7, 3, 9, 3, 9 }).pi());
    }


    private int[] data;

    private int[] cache;

    public PI(int[] data) {
        this.data = data;
        this.cache = new int[ data.length ];
    }

    int pi() {
        for ( int index = 0; index < 5; index++ ) {
            cache[ index ] = score(0, index);
        }

        int min;
        for ( int index = 5; index < data.length; index++ ) {
            min = Integer.MAX_VALUE;
            for ( int j = index - 2; j > index - 4 && j >= 0; j-- ) {
                if ( cache[ j ] > 0 ) {
                    min = Math.min(min, score(j, index) + cache[ j - 1 ]);
                }
            }
            cache[ index ] = min;
        }

        return cache[ data.length - 1 ];
    }

    private int score(int start, int end) {
        int difference = end - start;
        if ( !(2 <= difference && difference <= 4) ) {
            return -1;
        }

        /* 모든 숫자가 같을때 */
        boolean case1 = true;
        for ( int index = start; index < end && case1; index++ ) {
            if ( data[ index ] != data[ index + 1 ] ) {
                case1 = false;
            }
        }
        if ( case1 ) {
            return 1;
        }

        /* 숫자가 1씩 단조 증가하거나 단조 감소 할 때*/
        boolean case2 = true;
        for ( int index = start; index < end && case2; index++ ) {
            if ( data[ index ] - data[ index + 1 ] != data[ start ] - data[ start + 1 ] ) {
                case2 = false;
            }
        }
        if ( case2 && Math.abs(data[ start ] - data[ start + 1 ]) == 1 ) {
            return 2;

        /* 숫자가 등차 수열을 이룰 때 */
        } else if ( case2 ) {
            return 5;
        }

        /* 두개의 숫자가 번갈아가며 나타날 때*/
        boolean case3 = true;
        for ( int index = 2; index + start <= end && case3; index++ ) {
            if ( data[ index + start ] != data[ index % 2 + start ] ) {
                case3 = false;
            }
        }
        if ( case3 ) {
            return 4;
        }

        /* 이 외의 모든 경우 */
        return 10;

    }
}
