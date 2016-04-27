package kr.co.jarvisk.algorithm.apss.dynamic;

public class Wildcard {

    public static final void main(String[] args) {

        String pattern;
        String str = null;

//        pattern = "he?p";
//
//        System.out.println(pattern + " : " + (str = "help") + " is " + matchByRecursive(pattern, str));
//        System.out.println(pattern + " : " + (str = "heap") + " is " + matchByRecursive(pattern, str));
//        System.out.println(pattern + " : " + (str = "helpp") + " is " + matchByRecursive(pattern, str));
//
//        pattern = "*p*";
//        System.out.println(pattern + " : " + (str = "help") + " is " + matchByRecursive(pattern, str));
//        System.out.println(pattern + " : " + (str = "paha") + " is " + matchByRecursive(pattern, str));
//        System.out.println(pattern + " : " + (str = "hello") + " is " + matchByRecursive(pattern, str));
//
//        pattern = "*bb*";
//        System.out.println(pattern + " : " + (str = "babbbc") + " is " + matchByRecursive(pattern, str));

        pattern = "******a";
        System.out.println(pattern + " : " + (str = "aaaaaaaaaa") + " is " + matchByRecursive(pattern, str));
    }

    static boolean matchByRecursive(String w, String s) {
        return matchByRecursive(w.getBytes(), s.getBytes(), 0, 0);
    }

    static boolean matchByRecursive(byte[] w, byte[] s, int wPosition, int sPosition) {

        while ( sPosition < s.length && wPosition < w.length &&
                (w[ wPosition ] == '?' || w[ wPosition ] == s[ sPosition ]) ) {
            wPosition++;
            sPosition++;
        }

        if ( wPosition == w.length ) {
            return sPosition == s.length;
        }

        if ( w[ wPosition ] == '*' ) {
            for ( int skip = 0; sPosition + skip <= s.length; skip++ ) {
                if ( matchByRecursive(w, s, wPosition + 1, sPosition + skip) ) {
                    return true;
                }
            }
        }

        return false;
    }
}
