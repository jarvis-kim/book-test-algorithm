package kr.co.jarvisk.algorithm.apss.recursive;

import java.io.*;

/**
 * ID : BOARDCOVER
 * LEVEL : LOW
 * Page 159
 */
public class BoardCover {

    // 각 타입의 블럭을 정의한다.
    private final static int[][] blocks = new int[][] {
            { 0, 0, 1, 0, 1, 1 }, // ㄴ
            { 0, 0, 0, 1, 1, 1 }, // ㄱ
            { 0, 0, 0, 1, 1, 0 }, // ┌
            { 0, 0, 1, 0, 1, -1 } // ┙
    };

    public static void main(String[] args) {
/*
        int map[][] = new int[][] {
                { 1, 0, 0, 0, 0, 0, 1 },
                { 1, 0, 0, 0, 0, 0, 1 },
                { 1, 1, 0, 0, 1, 1, 1 }
        };
*/

        int maps[][][] = null;
        try ( InputStream is = BoardCover.class.getClassLoader().getResourceAsStream("./input/recursive/input_board_cover.txt");
              BufferedInputStream bis = new BufferedInputStream(is);
              BufferedReader reader = new BufferedReader(new InputStreamReader(bis)); ) {

            int caseCount = Integer.parseInt(reader.readLine());
            maps = new int[ caseCount ][][];

            for ( int count = 0; count < caseCount; count++ ) {
                String line = reader.readLine();
                int row = Integer.parseInt(line.split(" ")[ 0 ]);
                int col = Integer.parseInt(line.split(" ")[ 1 ]);
                maps[ count ] = new int[ row ][];

                for ( int rowCount = 0; rowCount < row; rowCount++ ) {
                    maps[ count ][ rowCount ] = new int[ col ];
                    String blockString = reader.readLine();
                    String[] blocks = blockString.split("(?!(^|$))");
                    for ( int colCount = 0; colCount < col; colCount++ ) {
                        if ( blocks[ colCount ].equals("#") ) {
                            maps[ count ][ rowCount ][ colCount ] = 1;
                        } else if ( blocks[ colCount ].equals(".") ) {
                            maps[ count ][ rowCount ][ colCount ] = 0;
                        }
                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        for ( int index = 0; index < maps.length; index++ ) {
            int count = countOfCoverBlock(maps[index]);
            System.out.println("count : " + count);
        }

    }


    public static int countOfCoverBlock(int[][] map) {
        int count = 0;


        // 순서대로 비어있는 칸을 찾는다.
        int y = 0, x = 0;
        boolean findPosition = false;
        for ( y = 0; y < map.length && !findPosition; y++ ) {
            for ( x = 0; x < map[ y ].length && !findPosition; x++ ) {
                if ( map[ y ][ x ] == 0 ) {
                    findPosition = true;
                }
            }
        }

        // 기저사례 : 비어있는 칸이 없다면 모든 블럭이 채워짐
        if ( !findPosition ) {
            printMap(map);
            return 1;
        }

        x--;
        y--;

        for ( int index = 0; index < blocks.length; index++ ) {
            // 블럭을 덮는다. 블럭을 덮었다면 true 반환한다.
            if ( coverBlock(map, y, x, blocks[ index ], index + 2) ) {
                // 블럭이 덮혔으니 블럭이 덮힌 사례로 Recursive 한다.
                count += countOfCoverBlock(map);
                // 덮었던 블럭을 다시 원상복귀 시킨다.
                clearBlock(map, y, x, blocks[ index ]);
            }
        }


        return count;
    }

    private static boolean availableBlock(int[][] map, int y, int x, int[] block) {
        if ( map[ y ][ x ] > 0 ) {
            return false;
        }

        int yy, xx;
        for ( int index = 0; index < block.length; index += 2 ) {
            yy = y + block[ index ];
            xx = x + block[ index + 1 ];
            if ( !(0 <= yy && yy < map.length) || !(0 <= xx && xx < map[ y ].length) || map[ yy ][ xx ] > 0 ) {
                return false;
            }
        }

        return true;
    }

    private static boolean coverBlock(int[][] map, int y, int x, int[] block, int blockType) {
        if ( availableBlock(map, y, x, block) ) {
            int yy, xx;
            for ( int index = 0; index < block.length; index += 2 ) {
                yy = y + block[ index ];
                xx = x + block[ index + 1 ];
                map[ yy ][ xx ] = blockType;
            }
//            printMap(map);
            return true;
        }

        return false;
    }

    private static void clearBlock(int[][] map, int y, int x, int[] block) {
        int yy, xx;
        for ( int index = 0; index < block.length; index += 2 ) {
            yy = y + block[ index ];
            xx = x + block[ index + 1 ];
            map[ yy ][ xx ] = 0;
        }
//        printMap(map);
    }

    private static void printMap(int[][] map) {
        System.out.println("-----------------------------------------");
        for ( int y = 0; y < map.length; y++ ) {
            for ( int x = 0; x < map[ y ].length; x++ ) {
                System.out.print(map[ y ][ x ] + "  ");
            }
            System.out.println();
        }
        System.out.println("-----------------------------------------");
    }
}
