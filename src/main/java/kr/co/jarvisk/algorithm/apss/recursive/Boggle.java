package kr.co.jarvisk.algorithm.apss.recursive;

import java.util.List;

public class Boggle {

    private static String MAP[][] = new String[][] {
            { "U", "R", "L", "P", "M" },
            { "X", "P", "R", "E", "T" },
            { "G", "I", "A", "E", "T" },
            { "X", "T", "N", "Z", "Y" },
            { "X", "O", "Q", "R", "S" }
//            {"N", "N", "N", "N", "S"},
//            {"N", "E", "E", "E", "N"},
//            {"N", "E", "Y", "E", "N"},
//            {"N", "E", "E", "E", "N"},
//            {"N", "N", "N", "N", "N"}
    };

    private static int DIRECTION_Y[] = new int[] { -1, -1, -1, 0, 0, 1, 1, 1 };
    private static int DIRECTION_X[] = new int[] { -1, 0, 1, -1, 1, -1, 0, 1 };

    public static final void main(String[] args) {

        System.out.println(hasWord(1, 1, "PRETTY"));
        printCharter(1,1);
//        System.out.println(hasWord(2, 0, "GIRL"));
//        printCharter(2,0);
//        System.out.println(hasWord(1, 2, "REPEAT"));
//        printCharter(1,2);
//        System.out.println(hasWord(0, 1, "REPEAT"));
//        System.out.println(hasWord(2, 2, "YES"));
//        printCharter(2,2);

    }

    public static boolean hasWord(int y, int x, String word) {
        String str = word.substring(0, 1);

        // 기저사레 : 글자가 하나이면 y, x 좌표를 검사하여 맞으면 찾은것이다.
        // EX) 글자가 하나만 넘어왔고, y, x 좌표가 그 글자이면 더이상 탐색할필요가 없음.
        if ( word.length() == 1 && MAP[ y ][ x ].equals(str)) {
            return true;
        }

        int yy, xx;
        String next = word.substring(1, 2);
        for ( int index = 0; index < DIRECTION_X.length; index++ ) {
            yy = y + DIRECTION_Y[ index ];
            xx = x + DIRECTION_X[ index ];

            if ( (0 <= yy && yy < MAP.length) &&
                    (0 <= xx && xx < MAP[ yy ].length) &&
                    MAP[ yy ][ xx ].equals(next) &&
                    hasWord(yy, xx, word.substring(1, word.length())) ) {
                printCharter(yy, xx);
                return true;
            }
        }

        return false;
    }


    private static void printCharter(int y, int x) {
        System.out.println("--------------------------------");
        for ( int yy = 0; yy < MAP.length; yy++ ) {
            for ( int xx = 0; xx < MAP.length; xx++ ) {
                if ( y == yy && x == xx ) {
                    System.out.print(MAP[ yy ][ xx ]);
                } else {
                    System.out.print("*");
                }
            }

            System.out.println();
        }
        System.out.println("--------------------------------");
    }
}
