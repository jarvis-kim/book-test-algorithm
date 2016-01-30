package kr.co.jarvisk.algorithm.apss.recursive;

import java.io.*;

/**
 * ID : PICNIC
 * LEVEL : Low
 * Page 155
 */
public class Picnic {

    private static boolean[][] friends;

    private static boolean[] pairs;

    public static final void main(String[] args) {

        try ( InputStream is = Picnic.class.getClassLoader().getResourceAsStream("input/recursive/input_picnic.txt");
              BufferedInputStream bis = new BufferedInputStream(is);
              BufferedReader reader = new BufferedReader(new InputStreamReader(bis)); ) {

            String line = reader.readLine();
            int testCase = Integer.parseInt(line);
            for ( int index = 0; index < testCase; index++ ) {
                line = reader.readLine();
                String[] firstLine = line.split(" ");
                int friendCount = Integer.parseInt(firstLine[ 0 ]);
                line = reader.readLine();
                String[] secondLine = line.split(" ");

                pairs = new boolean[ friendCount ];
                friends = new boolean[ friendCount ][ friendCount ];
                for ( int secondLineIndex = 0; secondLineIndex < secondLine.length; secondLineIndex += 2) {
                    int one = Integer.parseInt(secondLine[ secondLineIndex ]);
                    int two = Integer.parseInt(secondLine[ secondLineIndex + 1 ]);
                    friends[ one ][ two ] = true;
                    friends[ two ][ one ] = true;
                }
                System.out.println(getCountOfFriend(friendCount));
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static int getCountOfFriend(int friendCount) {
        int firstFriend = -1;
        for ( int index = 0; index < pairs.length; index++ ) {
            if ( !pairs[ index ] ) {
                firstFriend = index;
                break;
            }
        }

        // 기저 사례 : 모든 친구가 찾았다.
        if ( firstFriend == -1 ) {
            return 1;
        }

        int count = 0;
        for ( int nextFriend = firstFriend + 1; nextFriend < friendCount; nextFriend++ ) {
            if ( !pairs[nextFriend] && friends[ firstFriend ][ nextFriend ] ) {
                pairs[ firstFriend ] = pairs[ nextFriend ] = true;
                count += getCountOfFriend(friendCount);
                pairs[ firstFriend ] = pairs[ nextFriend ] = false;
            }
        }

        return count;
    }

}
