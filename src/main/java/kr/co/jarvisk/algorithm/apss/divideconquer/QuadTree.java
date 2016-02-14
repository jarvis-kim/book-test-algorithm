package kr.co.jarvisk.algorithm.apss.divideconquer;

public class QuadTree {

    public static void main(String[] args) {
        int size = quadTreeSize("xbwwb".split("(?!(^|$))"), 0);
        System.out.println(size * 2);
        size = quadTreeSize("xbwxwbbwb".split("(?!(^|$))"), 0);
        System.out.println(size * 2);
        size = quadTreeSize("xxwbxwwxbbwwbwbxwbwwxwwwxbbwb".split("(?!(^|$))"), 0);
        System.out.println(size * 2);
    }

    public static int quadTreeSize(String[] compressData, int startPosition) {
        if ( startPosition >= compressData.length ) {
            return 0;
        }

        if ( compressData.length == 1 ) {
            return 1;
        }

        int size = 0;
        int blockCount = 0;

        int position = startPosition;
        while ( blockCount < 4 ) {
            if ( compressData[ position ].equals("x") ) {
                size += quadTreeSize(compressData, position + 1);
            } else if ( compressData[ position ].equals("w") || compressData[ position ].equals("b") ) {
                blockCount++;
            } else {
                throw new RuntimeException("not contain x,w,b");
            }

            position++;
        }

        return size;
    }
}
