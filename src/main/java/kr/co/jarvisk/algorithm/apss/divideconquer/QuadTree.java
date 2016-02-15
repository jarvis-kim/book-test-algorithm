package kr.co.jarvisk.algorithm.apss.divideconquer;

public class QuadTree {

    private static String[][] map = new String[][] {
            { "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "b", "b" },
            { "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "b", "b" },
            { "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "b", "b", "b", "b" },
            { "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "b", "b", "b", "b" },
            { "w", "w", "w", "w", "b", "b", "b", "b", "w", "w", "w", "w", "w", "w", "w", "w" },
            { "w", "w", "w", "w", "b", "b", "b", "b", "w", "w", "w", "w", "w", "w", "w", "w" },
            { "w", "w", "w", "w", "b", "b", "b", "b", "w", "w", "w", "w", "w", "w", "w", "w" },
            { "w", "w", "w", "w", "b", "b", "b", "b", "w", "w", "w", "w", "w", "w", "w", "w" },
            { "w", "w", "b", "b", "w", "w", "w", "w", "b", "b", "b", "b", "b", "b", "b", "b" },
            { "b", "b", "b", "b", "w", "w", "w", "w", "b", "b", "b", "b", "b", "b", "b", "b" },
            { "w", "w", "w", "w", "w", "w", "w", "w", "b", "b", "b", "b", "b", "b", "b", "b" },
            { "w", "w", "w", "w", "w", "w", "w", "w", "b", "b", "b", "b", "b", "b", "b", "b" },
            { "w", "w", "w", "w", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b" },
            { "w", "w", "w", "w", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b" },
            { "w", "w", "w", "w", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b" },
            { "w", "w", "w", "w", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b" },
    };


    public static void main(String[] args) {
        map = new String[][] {
                { "b", "b", "w", "w" },
                { "b", "b", "w", "w" },
                { "w", "b", "b", "b" },
                { "b", "w", "b", "b" },
        };

        String input = "xxwwwbxwxwbbbwwxxxwwbbbwwwwbb";
//        String input = "xbwxwbbwb";
        int size = getSize(input);
        String[][] map = new String[ size ][ size ];
        decompress(map, input, 0, size, 0, size);
        String decompressData = compress(map, 0, size, 0, size);
        System.out.println(decompressData);
//        printMap(map);
    }

    public static String compress(String[][] quadTreeMap, int left, int right, int top, int bottom) {
        if ( left + 1 >= right ) {
            return quadTreeMap[ top ][ left ];
        }

        /* 왼쪽 아래 */
        String leftBottom = compress(quadTreeMap, left, (right - left) / 2 + left, (bottom - top) / 2 + top, bottom);

        /* 오른쪽 아래 */
        String rightBottom = compress(quadTreeMap, (right - left) / 2 + left, right, (bottom - top) / 2 + top, bottom);

        /* 왼쪽 위 */
        String leftTop = compress(quadTreeMap, left, (right - left) / 2 + left, top, (bottom - top) / 2 + top);

        /* 오른쪽 위 */
        String rightTop = compress(quadTreeMap, (right - left) / 2 + left, right, top, (bottom - top) / 2 + top);

        if ( leftTop.equals(rightTop) && leftTop.equals(leftBottom) && leftTop.equals(rightBottom) ) {
            return leftTop;
        }

        return "x" + leftBottom + rightBottom + leftTop + rightTop;
    }

    private static int decompressPosition = 0;
    public static void decompress(String[][] quadTreeMap, String compress, int left, int right, int top, int bottom) {
        if ( compress.length() <= decompressPosition ) {
            return;
        }

        if ( compress.charAt(decompressPosition) == 'x' ) {
            decompressPosition++;
            decompress(quadTreeMap, compress, left, (right - left) / 2 + left, top, (bottom - top) / 2 + top);
            decompressPosition++;
            decompress(quadTreeMap, compress, (right - left) / 2 + left, right, top, (bottom - top) / 2 + top);
            decompressPosition++;
            decompress(quadTreeMap, compress, left, (right - left) / 2 + left, (bottom - top) / 2 + top, bottom);
            decompressPosition++;
            decompress(quadTreeMap, compress, (right - left) / 2 + left, right, (bottom - top) / 2 + top, bottom);
            return;
        }

        String value = compress.substring(decompressPosition, decompressPosition + 1);
        for ( int i = top; i < bottom; i++ ) {
            for ( int j = left; j < right; j++ ) {
                quadTreeMap[ i ][ j ] = value;
            }
        }
    }

    public static final void printMap(String[][] map) {
        for ( int i = 0; i < map.length; i++ ) {
            for ( int j = 0; j < map[ i ].length; j++ ) {
                System.out.print(map[ i ][ j ]);
            }
            System.out.println();
        }
    }

    public static int getSize(String compress) {
        return ( int ) Math.pow(2, getLevel(compress, 0));
    }

    public static int levelPosition = 0;

    public static int getLevel(String compress, int currentLevel) {
        if ( compress.length() <= levelPosition ) {
            return 0;
        }

        char character = compress.charAt(levelPosition);
        if ( character == 'w' || character == 'b' ) {
            return 0;
        }

        int level = 1 + currentLevel;
        int maxLevel = 0;

        if ( character == 'x' ) {
            levelPosition++;
            maxLevel = Math.max(maxLevel, getLevel(compress, level));
        }

        maxLevel = Math.max(maxLevel, getLevel(compress, level));
        levelPosition++;
        maxLevel = Math.max(maxLevel, getLevel(compress, level));
        levelPosition++;
        maxLevel = Math.max(maxLevel, getLevel(compress, level));
        levelPosition++;
        maxLevel = Math.max(maxLevel, getLevel(compress, level));
        levelPosition++;

        return maxLevel + 1;
    }

}
