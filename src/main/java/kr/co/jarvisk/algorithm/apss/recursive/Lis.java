package kr.co.jarvisk.algorithm.apss.recursive;

public class Lis {

    public static void main(String[] args) {
        System.out.println(new Lis(new int[] { 1, 2, 3, 4 }).lis(0));
        System.out.println(new Lis(new int[] { 5, 4, 3, 2, 1, 6, 7, 8  }).lis(0));
        System.out.println(new Lis(new int[] { 5, 6, 7, 8, 1, 2, 3, 4 }).lis(0));
        System.out.println(new Lis(new int[] { 1, 1, 1 }).lis(0));
    }

    private int sequence[];

    private int cache[];

    public Lis(int[] sequence) {
        this.sequence = sequence;
        cache = new int[ sequence.length ];
    }

    int lis(int i) {
        if ( i >= sequence.length ) {
            return 0;
        }

        if ( cache[ i ] > 0 ) {
            return cache [ i ];
        }

        int max = 1;
        int before = sequence[ i ];
        for ( int next = i + 1; next < sequence.length; next++ ) {
            if ( before < sequence[ next ] ) {
                max++;
                before = sequence[ next ];
            }
        }

        return cache[ i ] = Math.max(max, lis(i + 1));
    }

}
