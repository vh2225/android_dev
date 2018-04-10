package edu.gatech.seclass.replace;

/**
 * Created by Chuanxi on 4/20/2017.
 */
public class testMain {
    public static void main(String[] args) {
        String a = "There was a man with a bad reflex\n" +
                "He made up tests that were too complex\n" +
                "googolplexmetroplexmultiplex";
        String b = "llex";
        System.out.println(indexOf4(a, b, 0, 'l'));
    }

    private static int indexOf4(String string, String pattern, int start, char wildCard) {
        int sLength = string.length();
        int pLength = pattern.length();
        for (int i = start; i <= sLength - pLength; i++) {
            int j = 0;
            int k = i;
            while (j < pLength && k < sLength && (string.charAt(k) == pattern.charAt(j) || pattern.charAt(j) == wildCard)) {
                k++;
                j++;
            }
            if (j == pLength) {
                return k - j;
            }
        }
        return -1;
    }
}
