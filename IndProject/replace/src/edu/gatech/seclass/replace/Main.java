package edu.gatech.seclass.replace;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {

 /*
Do not alter this class or implement it.
 */

    public static void main(String[] args){
        //  Skeleton Method

        boolean isFirst = false;
        boolean isSpace = false;
        boolean isCase = false;
        boolean isWild = false;
        String fromString;
        String inputString;
        String toString;
        StringBuilder outputString;
        String inputFilePath;
        String stringToLook;
        String patternToLook;
        boolean legalArgs = true;
        char patDelimiter = ' ';
        char wildCard = 'x';
        int match;

        if(args == null) {
            usage();
        }

        else {
            if (args.length < 3) {
                usage();
            } else {
                Charset charset = StandardCharsets.UTF_8;
                Path filePath = null;
                List<String> listArgs = new LinkedList<>(Arrays.asList(args));
                inputFilePath = listArgs.get(listArgs.size() - 1);
                try {
                    filePath = Paths.get(inputFilePath);
                    inputString = new String(Files.readAllBytes(filePath), charset);
                } catch (Exception e) {
                    inputString = null;
                }

                if (filePath == null || inputString == null) {
                    System.err.println("File Not Found");
                } else {

                    stringToLook = inputString;
                    outputString = new StringBuilder(inputString);
                    listArgs.remove(listArgs.size() - 1);

                    toString = listArgs.get(listArgs.size() - 1);
                    listArgs.remove(listArgs.size() - 1);

                    fromString = listArgs.get(listArgs.size() - 1);
                    patternToLook = fromString;
                    listArgs.remove(listArgs.size() - 1);

                    while (listArgs.contains("-f")) {
                        isFirst = true;
                        listArgs.remove("-f");
                    }

                    while (listArgs.contains("-i")) {
                        listArgs.remove("-i");
                        isCase = true;
                        stringToLook = stringToLook.toLowerCase();
                        patternToLook = patternToLook.toLowerCase();
                    }

                    while (listArgs.contains("-w")) {
                        isSpace = true;
                        int wLocation = listArgs.indexOf("-w");
                        if (listArgs.size() > 1) {
                            String nextArgsW = listArgs.get(wLocation + 1);
                            if (nextArgsW.length() == 1) {
                                patDelimiter = nextArgsW.charAt(0);
                                listArgs.remove(nextArgsW);
                            }
                        }
                        listArgs.remove("-w");
                    }

                    while (listArgs.contains("-x")) {
                        isWild = true;
                        int xLocation = listArgs.indexOf("-x");
                        if (listArgs.size() > 1) {
                            String nextArgsX = listArgs.get(xLocation + 1);
                            if (nextArgsX.length() == 1) {
                                wildCard = nextArgsX.charAt(0);
                                listArgs.remove(nextArgsX);
                            }
                        }
                        listArgs.remove("-x");
                    }

                    if (!listArgs.isEmpty()) {
                        usage();
                        legalArgs = false;
                    }

                    if (fromString.length() > 0) {
                        int start = 0;
                        boolean found = false;
                        int patternLength = patternToLook.length();
                        int toStringLength = toString.length();
                        while (start < stringToLook.length() && (!isFirst || !found)) {
                            if (!isWild) {
                                match = stringToLook.indexOf(patternToLook, start);
                            } else {
                                match = indexOf2(stringToLook, patternToLook, start, wildCard);
                            }
                            if (match != -1) {
                                if (!isSpace || (patDelimiter == ' ' && (match == 0 || stringToLook.charAt(match - 1) == patDelimiter || stringToLook.charAt(match - 1) == '\n') && (match + patternLength == stringToLook.length() ||
                                        stringToLook.charAt(match + patternLength) == patDelimiter || stringToLook.charAt(match + patternLength) == '\n')) ||
                                        (patDelimiter != ' ' && (match == 0 || stringToLook.charAt(match - 1) == patDelimiter) && (match + patternLength == stringToLook.length() ||
                                                stringToLook.charAt(match + patternLength) == patDelimiter))) {

                                    found = true;
//                            System.out.println(stringToLook.charAt(match + patternLength));
                                    outputString.replace(match, match + patternLength, toString);
                                    stringToLook = outputString.toString();
                                    if (isCase) {
                                        stringToLook = stringToLook.toLowerCase();
                                    }
                                    start = match + toStringLength;
                                } else {
                                    start = match + patternLength;
                                }

                            } else {
                                start++;
                            }
                        }
                    }
                    if (legalArgs) {
                        try {
                            editFile(inputFilePath, outputString.toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }


    private static void usage() {
        System.err.println("Usage: Replace [-f] [-i] [-w[char]] [-x[char]] <from> <to> <filename>" );
    }

    private static void editFile(String inputFilePath, String outputString) throws Exception {

        //Create some files here
        File myFile = new File(inputFilePath);
        FileOutputStream fileStream = new FileOutputStream(myFile, false); // true to append, false to overwrite.
        byte[] myBytes = outputString.getBytes();
        fileStream.write(myBytes);
        fileStream.close();
    }

    private static  int indexOf4(String string, String pattern, int start, char wildCard){
        int sLength = string.length();
        int pLength = pattern.length();
        for (int i = start; i <= sLength - pLength; i++){
            int j = 0;
            int k = i;
            while ( j < pLength && k < sLength && (string.charAt(k) == pattern.charAt(j) || pattern.charAt(j) == wildCard)){
                k++;
                j++;
            }
            if (j == pLength){
                return k-j;
            }
        }
        return -1;
    }

    private static  int indexOf2(String string, String pattern, int start, char wildCard){
        String regex = pattern.replace(Character.toString(wildCard), ".");
        Pattern patternRegEx = Pattern.compile(regex);
        Matcher matcher = patternRegEx.matcher(string);
        if (matcher.find(start)){
            return matcher.start();
        }
        else {
            return -1;
        }
    }

    private static  int indexOf3(String string, String pattern, int start, char wildCard) {
        int i = start;
        int j = 0;
        int starIndex = -1;
        int iIndex = -1;

        while (i < string.length()) {
            if (j < pattern.length() && (pattern.charAt(j) == wildCard || pattern.charAt(j) == string.charAt(i))) {
                ++i;
                ++j;
            } else if (starIndex != -1) {
                j = starIndex + 1;
                i = iIndex+1;
                iIndex++;
            } else {
                return -1;
            }
        }

        return i-j;
    }
}




// -------------------------------------------------------------------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------------------------------------------------------------------------------



//old trial
//package edu.gatech.seclass.replace;
//
//import java.io.*;
//import java.nio.charset.Charset;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.Arrays;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.regex.Pattern;
//
//public class Main {
//
// /*
//Do not alter this class or implement it.
// */
//
//    public static void main(String[] args){
//        //  Skeleton Method
//
//        boolean isFirst = false;
//        boolean isCase = false;
//        boolean isSpace = false;
//        String fromString;
//        String inputString;
//        String toString;
//        String outputString;
//        String inputFilePath;
//        boolean legalArgs = true;
//
//        if(args == null) {
//            usage();
//        }
//
//        else if(args.length < 3) {
//            usage();
//        }
//
//        else{
//            List<String> listArgs = new LinkedList<>(Arrays.asList(args));
//            inputFilePath = listArgs.get(listArgs.size() - 1);
//            inputString = getFileContent(inputFilePath);
//            listArgs.remove(listArgs.size() - 1);
//
//            toString = listArgs.get(listArgs.size()-1);
//            listArgs.remove(listArgs.size()-1);
//
//            fromString = listArgs.get(listArgs.size()-1);
//            listArgs.remove(listArgs.size()-1);
//
//            if (listArgs.contains("-f")){
//                isFirst = true;
//                listArgs.remove("-f");
//            }
//
//            if (listArgs.contains("-i")){
//                isCase = true;
//                listArgs.remove("-i");
//            }
//
//            if (listArgs.contains("-w")){
//                isSpace = true;
//                listArgs.remove("-w");
//            }
//
//            if(!listArgs.isEmpty()) {
//                usage();
//                legalArgs = false;
//            }
//
////            if (isSpace){
////                fromString = " " + fromString + " ";
////                toString = " " + toString + " ";
////            }
//
//            if (fromString == ""){
//                outputString = inputString;
//            }
//
//            else if (!isSpace){
//                if (isCase && !isFirst){
//                    outputString = Pattern.compile(fromString, Pattern.CASE_INSENSITIVE).matcher(inputString).replaceAll(toString);
//                }
//
//                else if (isCase && isFirst){
//                    outputString = Pattern.compile(fromString, Pattern.CASE_INSENSITIVE).matcher(inputString).replaceFirst(toString);
//                }
//
//                else if (isFirst){
//                    outputString = Pattern.compile(fromString).matcher(inputString).replaceFirst(toString);
//                }
//
//                else {
//                    outputString = Pattern.compile(fromString).matcher(inputString).replaceAll(toString);
//                }
//            }
//
//            else{
//                if (isCase && !isFirst){
//                    outputString = Pattern.compile("\\s"+fromString+"\\s", Pattern.CASE_INSENSITIVE).matcher(inputString).replaceAll(" "+toString+" ");
//                    outputString = Pattern.compile("\\^"+fromString+"\\s", Pattern.CASE_INSENSITIVE).matcher(outputString).replaceAll("\n"+toString+" ");
//                    outputString = Pattern.compile("\\s"+fromString+"\\$", Pattern.CASE_INSENSITIVE).matcher(outputString).replaceAll(" "+toString+"\\n");
//
//                }
//
//                else if (isCase && isFirst){
//                    outputString = Pattern.compile("\\s"+fromString+"\\s", Pattern.CASE_INSENSITIVE).matcher(inputString).replaceFirst(" "+toString+" ");
//                    outputString = Pattern.compile("\\^"+fromString+"\\s", Pattern.CASE_INSENSITIVE).matcher(outputString).replaceFirst("\n"+toString+" ");
//                    outputString = Pattern.compile("\\s"+fromString+"\\$", Pattern.CASE_INSENSITIVE).matcher(outputString).replaceFirst(" "+toString+"\n");
//                }
//
////                else if (isFirst){
//
//                else {
//                    outputString = Pattern.compile("\\s"+fromString+"\\s").matcher(inputString).replaceFirst(" "+toString+" ");
//                    outputString = Pattern.compile("\\^"+fromString+"\\s").matcher(outputString).replaceFirst("\\^"+toString+" ");
//                    outputString = Pattern.compile("\\s"+fromString+"\\$").matcher(outputString).replaceFirst(" "+toString+"\\$");
//                }
//
////                else {
////                    outputString = Pattern.compile("\\s"+fromString+"\\s").matcher(inputString).replaceAll(" "+toString+" ");
////                    outputString = Pattern.compile("\\^"+fromString+"\\s").matcher(outputString).replaceAll("\\^"+toString+" ");
////                    outputString = Pattern.compile("\\s"+fromString+"\\$").matcher(outputString).replaceAll(" "+toString+"\\$");
////                }
//
//            }
//
//
//
////            System.out.println(outputString);
////            System.out.println(legalArgs);
//
//
//            if (legalArgs){
//                try {
//                    editFile(inputFilePath,outputString);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }
//    }
//
//
//    private static void usage() {
//        System.err.println("Usage: Replace [-f] [-i] [-w] <from> <to> <filename>" );
//    }
//
//    private static String getFileContent(String filename) {
//        Charset charset = StandardCharsets.UTF_8;
//        String content = null;
//        try {
//            content = new String(Files.readAllBytes(Paths.get(filename)), charset);
//        } catch (IOException e) {
//            usage();
//            e.printStackTrace();
//        }
//        return content;
//    }
//
//    private static void editFile(String inputFilePath, String outputString) throws Exception {
//
//        //Create some files here
//        File myFile = new File(inputFilePath);
//        FileOutputStream fileStream = new FileOutputStream(myFile, false); // true to append, false to overwrite.
//        byte[] myBytes = outputString.getBytes();
//        fileStream.write(myBytes);
//        fileStream.close();
//    }
//
//}
//
//



// -------------------------------------------------------------------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------------------------------------------------------------------------------


//
//package edu.gatech.seclass.replace;
//
//        import java.io.*;
//        import java.nio.charset.Charset;
//        import java.nio.charset.StandardCharsets;
//        import java.nio.file.Files;
//        import java.nio.file.InvalidPathException;
//        import java.nio.file.Path;
//        import java.nio.file.Paths;
//        import java.util.Arrays;
//        import java.util.LinkedList;
//        import java.util.List;
//
//public class Main {
//
// /*
//Do not alter this class or implement it.
// */
//
//    public static void main(String[] args){
//        //  Skeleton Method
//
//        boolean isFirst = false;
//        boolean isSpace = false;
//        boolean isCase = false;
//        boolean isWild = false;
//        String fromString;
//        String inputString;
//        String toString;
//        StringBuilder outputString;
//        String inputFilePath;
//        String stringToLook;
//        String patternToLook;
//        boolean legalArgs = true;
//        char patDelimiter = ' ';
//        char wildCard = 'x';
//
//        if(args == null) {
//            usage();
//        }
//
//        else if(args.length < 3) {
//            usage();
//        }
//
//        else{
//            List<String> listArgs = new LinkedList<>(Arrays.asList(args));
//            inputFilePath = listArgs.get(listArgs.size() - 1);
//            inputString = getFileContent(inputFilePath);
//            stringToLook = inputString;
//            outputString = new StringBuilder(inputString);
//            listArgs.remove(listArgs.size() - 1);
//
//            toString = listArgs.get(listArgs.size()-1);
//            listArgs.remove(listArgs.size()-1);
//
//            fromString = listArgs.get(listArgs.size()-1);
//            patternToLook = fromString;
//            listArgs.remove(listArgs.size()-1);
//
//            if (listArgs.contains("-f")){
//                isFirst = true;
//                listArgs.remove("-f");
//            }
//
//            if (listArgs.contains("-i")){
//                listArgs.remove("-i");
//                isCase = true;
//                stringToLook = stringToLook.toLowerCase();
//                patternToLook = patternToLook.toLowerCase();
//            }
//
//            if (listArgs.contains("-w")){
//                isSpace = true;
//                int wLocation = listArgs.indexOf("-w");
//                if(listArgs.size()>2) {
//                    String nextArgsW = listArgs.get(wLocation + 1);
//                    if (nextArgsW.length() == 1) {
//                        patDelimiter = nextArgsW.charAt(0);
//                        listArgs.remove(nextArgsW);
//                    }
//                }
//                listArgs.remove("-w");
//            }
//
//
//            System.out.println(patDelimiter);
//
//
//            if (listArgs.contains("-x")){
//                isWild = true;
//                int xLocation = listArgs.indexOf("-x");
//                if(listArgs.size()>2) {
//                    String nextArgsX = listArgs.get(xLocation + 1);
//                    if (nextArgsX.length() == 1) {
//                        patDelimiter = nextArgsX.charAt(0);
//                        listArgs.remove(nextArgsX);
//                    }
//                }
//                listArgs.remove("-x");
//            }
//
//            if(!listArgs.isEmpty()) {
//                usage();
//                legalArgs = false;
//            }
//
//            if (fromString.length() > 0){
//                int start = 0;
//                boolean found = false;
//                int patternLength = patternToLook.length();
//                int toStringLength = toString.length();
//                while (start < stringToLook.length() && (!isFirst || !found)) {
//                    int match = stringToLook.indexOf(patternToLook, start);
//                    if (match != -1) {
//                        if (!isSpace || (match == 0 || stringToLook.charAt(match - 1) == patDelimiter || stringToLook.charAt(match - 1) == '\n') && (match + patternLength == stringToLook.length() ||
//                                stringToLook.charAt(match + patternLength) == patDelimiter || stringToLook.charAt(match + patternLength) == '\n')) {
//                            found = true;
////                            System.out.println(stringToLook.charAt(match + patternLength));
//                            outputString.replace(match, match + patternLength, toString);
//                            stringToLook = outputString.toString();
//                            if (isCase) {
//                                stringToLook = stringToLook.toLowerCase();
//                            }
//                            start = match + toStringLength;
//                        }
//                        else {
//                            start = match + patternLength;
//                        }
//
//                    } else {
//                        start++;
//                    }
//                }
//            }
//            if (legalArgs){
//                try {
//                    editFile(inputFilePath,outputString.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }
//    }
//
//
//    private static void usage() {
//        System.err.println("Usage: Replace [-f] [-i] [-w[char]] [-x[char]] <from> <to> <filename>" );
//    }
//
//    private static String getFileContent(String filename) {
//        Charset charset = StandardCharsets.UTF_8;
//        String content = null;
//        Path filePath = null;
//
//        try {
//            filePath = Paths.get(filename);
//        } catch (InvalidPathException ex) {
//            System.err.println("File Not Found");
//        }
//
//        try {
//            content = new String(Files.readAllBytes(filePath), charset);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return content;
//    }
//
//    private static void editFile(String inputFilePath, String outputString) throws Exception {
//
//        //Create some files here
//        File myFile = new File(inputFilePath);
//        FileOutputStream fileStream = new FileOutputStream(myFile, false); // true to append, false to overwrite.
//        byte[] myBytes = outputString.getBytes();
//        fileStream.write(myBytes);
//        fileStream.close();
//    }
//}