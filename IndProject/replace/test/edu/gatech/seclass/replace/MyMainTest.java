//package edu.gatech.seclass.replace;
//
//import static org.junit.Assert.*;
//
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.PrintStream;
//import java.nio.charset.Charset;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//
//import edu.gatech.seclass.replace.Main;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.rules.TemporaryFolder;
//
//public class MyMainTest {
//
///*
//Place all  of your tests in this class, possibly using MainTest.java as an example.
//*/
//
//}

package edu.gatech.seclass.replace;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

/*
Do not alter this class.  Use it as an example for MyMainTest.java
 */

public class MyMainTest {

    private ByteArrayOutputStream outStream;
    private ByteArrayOutputStream errStream;
    private PrintStream outOrig;
    private PrintStream errOrig;
    private Charset charset = StandardCharsets.UTF_8;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
        outStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outStream);
        errStream = new ByteArrayOutputStream();
        PrintStream err = new PrintStream(errStream);
        outOrig = System.out;
        errOrig = System.err;
        System.setOut(out);
        System.setErr(err);
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(outOrig);
        System.setErr(errOrig);
    }

    // Some utilities

    private File createTmpFile() throws IOException {
        File tmpfile = temporaryFolder.newFile();
        tmpfile.deleteOnExit();
        return tmpfile;
    }


    private File createInputFile1() throws Exception {
        File file1 = createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Howdy Billy,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy\" to Bill again!");

        fileWriter.close();
        return file1;
    }

    private File createInputFile2() throws Exception {
        File file1 = createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Bill is,\n" +
                "in my opinion,\n" +
                "an easier name to spell than William.\n" +
                "Bill is shorter,\n" +
                "and Bill is\n" +
                "first alphabetically.");

        fileWriter.close();
        return file1;
    }

    private File createInputFile3() throws Exception {
        File file1 = createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Howdy Bill, have you learned your abc and 123?\n" +
                "I know My Abc's.\n" +
                "It is important to know your abc's and 123's,\n" +
                "so repeat with me: abc! 123! Abc and 123!");

        fileWriter.close();
        return file1;
    }

    private File createInputFile4() throws Exception {
        File file1 = createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("I love the ABC alphabeth, so repeat with me: abc! ABC! Abc and abc abc");

        fileWriter.close();
        return file1;
    }

    private File createInputFile5() throws Exception {
        File file1 = createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("abc");

        fileWriter.close();
        return file1;
    }

    private File createInputFile6() throws Exception {
        File file1 = createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Prince Hamlet devotes himself toavenginghis father’s death, " +
                "but, because he is contemplative and thoughtful by nature, he delays, entering into a deep melancholy " +
                "and even apparent madness. Claudius and Gertrude worry about the prince’s " +
                "erratic behavior and attempt to discover its cause");

        fileWriter.close();
        return file1;
    }


    private File createInputFile7() throws Exception {
        File file1 = createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Following the death of Augustus, Tiberius is declared Emperor, " +
                "but the legions of Germany refuse to accept Tiberius and instead declare " +
                "Germanicus as his Emperor. Germanicus, shocked and confused, refuses, instead he " +
                "sends his wife and youngest son Caligula away and asks Claudius for an enormous sum of " +
                "money to pay the soldiers. Claudius agrees and pretends that they are gambling debts.");

        fileWriter.close();
        return file1;
    }


    private File createInputFile8() throws Exception {
        File file1 = createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("The story begins with an apology byClaudiusfor ending his first " +
                "history on a dramatic point, and continues with a brief history of his friend " +
                "Herod Agrippa. Herod Agrippa was a schoolmate ofClaudiusand was liked byClaudius' " +
                "mother Antonia. Herod always finds himself in debts and danger in the East and in Rome. " +
                "He eventually gains the favor of Caligula and is made King of Bashan. Herod is in Rome when " +
                "the assassination of Caligula happens and quickly is able to convinceClaudiusto accept being " +
                "Emperor to avoid Civil War");

        fileWriter.close();
        return file1;
    }

    private File createInputFile9() throws Exception {
        File file1 = createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Howdy Billy,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy \" to Bill again!");

        fileWriter.close();
        return file1;
    }


    private File createInputFile10() throws Exception {
        File file1 = createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("The \"Emperor\" penguin is the tallest and heaviest \n" +
                "of all living penguin species and is endemic to Antarctica. \n" +
                "The \"EmpeRor\" penguin male \n" +
                "and the \"Emperor\" penguin female \n" +
                "are similar in plumage and size, reaching \n" +
                "122 cm in height and weighing from 22 to 45");

        fileWriter.close();
        return file1;
    }


    private File createEmptyFile() throws Exception {
        File file1 = createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("");

        fileWriter.close();
        return file1;
    }

    private String getFileContent(String filename) {
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(filename)), charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    // test cases

    // Purpose: Test with empty file
    // Frame #: 1
    @Test
    public void replaceTest1() throws Exception {
        File inputFile1 = createEmptyFile();

        String args[] = {"-i", "a", "b", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected1, actual1);
    }


    // Purpose: Test case with multiple pattern repeated in 1 line
    // Frame #: 3
    @Test
    public void replaceTest2() throws Exception {
        File inputFile2 = createInputFile4();

        String args[] = {"-i", "abc", "ABC", inputFile2.getPath()};
        Main.main(args);

        String expected2 = "I love the ABC alphabeth, so repeat with me: ABC! ABC! ABC and ABC ABC";

        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files differ!", expected2, actual2);
    }


    // Purpose: Test case with empty OPT input
    // Frame #: 6
    @Test
    public void replaceTest3() throws Exception {
        File inputFile3 = createInputFile4();

        String args[] = {"abc", "ABC", inputFile3.getPath()};
        Main.main(args);

        String expected3 = "I love the ABC alphabeth, so repeat with me: ABC! ABC! Abc and ABC ABC";

        String actual3 = getFileContent(inputFile3.getPath());

        assertEquals("The files differ!", expected3, actual3);
    }


    // Purpose: Test case with all 3 OPT input "-i", "-w", "-f"
    // Frame #: 10
    @Test
    public void replaceTest4() throws Exception {
        File inputFile4 = createInputFile4();

        String args[] = {"-i", "-w", "-f", "abc", "ABC", inputFile4.getPath()};
        Main.main(args);

        String expected4 = "I love the ABC alphabeth, so repeat with me: abc! ABC! Abc and abc abc";

        String actual4 = getFileContent(inputFile4.getPath());

        assertEquals("The files differ!", expected4, actual4);
    }


    // Purpose: Test case with invalid option flag
    // Frame #: 11
    @Test
    public void replaceTest5() throws Exception {
        File inputFile5 = createInputFile4();

        String args[] = {"-b", "abc", "ABC", inputFile5.getPath()};
        Main.main(args);
        assertEquals("Usage: Replace [-f] [-i] [-w] <from> <to> <filename>", errStream.toString().trim());
    }


    // Purpose: Test case with an empty <from> field"
    // Frame #: 12
    @Test
    public void replaceTest6() throws Exception {
        File inputFile6 = createInputFile4();

        String args[] = {"-i", "", "ABC", inputFile6.getPath()};
        Main.main(args);

        String expected6 = "I love the ABC alphabeth, so repeat with me: abc! ABC! Abc and abc abc";

        String actual6 = getFileContent(inputFile6.getPath());

        assertEquals("The files differ!", expected6, actual6);
    }


    // Purpose: Test case with <from> field longer then the file length
    // Frame #: 14
    @Test
    public void replaceTest7() throws Exception {
        File inputFile7 = createInputFile5();

        String args[] = {"-i", "Hollywood", "ABC", inputFile7.getPath()};
        Main.main(args);

        String expected7 = "abc";

        String actual7 = getFileContent(inputFile7.getPath());

        assertEquals("The files differ!", expected7, actual7);
    }


    // Purpose: Test case that replace a phase with multiple word (mutiple blanks in <form> field)
    // Frame #: 17
    @Test
    public void replaceTest8() throws Exception {
        File inputFile8 = createInputFile2();

        String args[] = {"-i", "easier name to spell", "way way better", inputFile8.getPath()};
        Main.main(args);

        String expected8 = "Bill is,\n" +
                "in my opinion,\n" +
                "an way way better than William.\n" +
                "Bill is shorter,\n" +
                "and Bill is\n" +
                "first alphabetically.";

        String actual8 = getFileContent(inputFile8.getPath());

        assertEquals("The files differ!", expected8, actual8);
    }


    // Purpose: Test case with an empty <To> field"
    // Frame #: 19
    @Test
    public void replaceTest9() throws Exception {
        File inputFile9 = createInputFile4();

        String args[] = {"-i", "abc", "", inputFile9.getPath()};
        Main.main(args);

        String expected9 = "I love the  alphabeth, so repeat with me: ! !  and  ";

        String actual9 = getFileContent(inputFile9.getPath());

        assertEquals("The files differ!", expected9, actual9);
    }


    // Purpose: typical test case to confirm the tool work with normal use. This is the case with no whitespace between words.
    // Frame #: 28
    @Test
    public void replaceTest10() throws Exception {
        File inputFile10 = createInputFile6();

        String args[] = {"-i", "avenging", " celebrating ", inputFile10.getPath()};
        Main.main(args);

        String expected10 = "Prince Hamlet devotes himself to celebrating his father’s death, " +
                "but, because he is contemplative and thoughtful by nature, he delays, entering into a deep melancholy " +
                "and even apparent madness. Claudius and Gertrude worry about the prince’s " +
                "erratic behavior and attempt to discover its cause";

        String actual10 = getFileContent(inputFile10.getPath());

        assertEquals("The files differ!", expected10, actual10);
    }


    // Purpose: typical test case to confirm the tool work with normal use. This is the case with no whitespace on both side of the word.
    // Frame #: 35
    @Test
    public void replaceTest11() throws Exception {
        File inputFile11 = createInputFile1();

        String args[] = {"-i", "interesting", "funny", inputFile11.getPath()};
        Main.main(args);

        String expected11 = "Howdy Billy,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some funny test cases...\n" +
                "And let's say \"howdy\" to Bill again!";

        String actual11 = getFileContent(inputFile11.getPath());

        assertEquals("The files differ!", expected11, actual11);
    }


    // Purpose: typical test case to confirm the tool work with normal use. This is the case with quote in the pattern.
    // Frame #: 40
    @Test
    public void replaceTest12() throws Exception {
        File inputFile12 = createInputFile1();

        String args[] = {"-i", "\"howdy\"", "hello", inputFile12.getPath()};
        Main.main(args);

        String expected12 = "Howdy Billy,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say hello to Bill again!";

        String actual12 = getFileContent(inputFile12.getPath());

        assertEquals("The files differ!", expected12, actual12);
    }


    // Purpose: typical test case to confirm the tool work with normal use. This is the case with whitespace only in front of the pattern.
    // Frame #: 45
    @Test
    public void replaceTest13() throws Exception {
        File inputFile13 = createInputFile7();

        String args[] = {"-i", "Emperor", "king", inputFile13.getPath()};
        Main.main(args);

        String expected13 = "Following the death of Augustus, Tiberius is declared king, " +
                "but the legions of Germany refuse to accept Tiberius and instead declare " +
                "Germanicus as his king. Germanicus, shocked and confused, refuses, instead he " +
                "sends his wife and youngest son Caligula away and asks Claudius for an enormous sum of " +
                "money to pay the soldiers. Claudius agrees and pretends that they are gambling debts.";

        String actual13 = getFileContent(inputFile13.getPath());

        assertEquals("The files differ!", expected13, actual13);
    }


    // Purpose: typical test case to confirm the tool work with normal use. With the same input the above case, this test is to replace text between 2 whitespaces.
    // Frame #: 57
    @Test
    public void replaceTest14() throws Exception {
        File inputFile14 = createInputFile7();

        String args[] = {"-i", "his", "the", inputFile14.getPath()};
        Main.main(args);

        String expected14 = "Following the death of Augustus, Tiberius is declared Emperor, " +
                "but the legions of Germany refuse to accept Tiberius and instead declare " +
                "Germanicus as the Emperor. Germanicus, shocked and confused, refuses, instead he " +
                "sends the wife and youngest son Caligula away and asks Claudius for an enormous sum of " +
                "money to pay the soldiers. Claudius agrees and pretends that they are gambling debts.";

        String actual14 = getFileContent(inputFile14.getPath());

        assertEquals("The files differ!", expected14, actual14);
    }



    // Purpose: typical test case to confirm the tool work with normal use. With the same input the above case, this test is to add 2 whitespaces in to field.
    // Frame #: 23
    @Test
    public void replaceTest15() throws Exception {
        File inputFile15 = createInputFile8();

        String args[] = {"-i", "claudius", " Germanicus ", inputFile15.getPath()};
        Main.main(args);

        String expected15 = "The story begins with an apology by Germanicus for ending his first " +
                "history on a dramatic point, and continues with a brief history of his friend " +
                "Herod Agrippa. Herod Agrippa was a schoolmate of Germanicus and was liked by Germanicus ' " +
                "mother Antonia. Herod always finds himself in debts and danger in the East and in Rome. " +
                "He eventually gains the favor of Caligula and is made King of Bashan. Herod is in Rome when " +
                "the assassination of Caligula happens and quickly is able to convince Germanicus to accept being " +
                "Emperor to avoid Civil War";

        String actual15 = getFileContent(inputFile15.getPath());

        assertEquals("The files differ!", expected15, actual15);
    }

    // Purpose: no occurence in the file
    // Frame #: 2
    @Test
    public void replaceTest16() throws Exception {
        File inputFile16 = createInputFile3();

        String args[] = {"-i", "superman", " spiderman ", inputFile16.getPath()};
        Main.main(args);

        String expected16 = "Howdy Bill, have you learned your abc and 123?\n" +
                "I know My Abc's.\n" +
                "It is important to know your abc's and 123's,\n" +
                "so repeat with me: abc! 123! Abc and 123!";

        String actual16 = getFileContent(inputFile16.getPath());

        assertEquals("The files differ!", expected16, actual16);
    }


    // Purpose: pattern in the first line
    // Frame #: 4
    @Test
    public void replaceTest17() throws Exception {
        File inputFile17 = createInputFile3();

        String args[] = {"learned", "mastered", inputFile17.getPath()};
        Main.main(args);

        String expected17 = "Howdy Bill, have you mastered your abc and 123?\n" +
                "I know My Abc's.\n" +
                "It is important to know your abc's and 123's,\n" +
                "so repeat with me: abc! 123! Abc and 123!";

        String actual17 = getFileContent(inputFile17.getPath());

        assertEquals("The files differ!", expected17, actual17);
    }


    // Purpose: pattern in the last line
    // Frame #: 5
    @Test
    public void replaceTest18() throws Exception {
        File inputFile18 = createInputFile3();

        String args[] = {"-i", "repeat", "sing", inputFile18.getPath()};
        Main.main(args);

        String expected18 = "Howdy Bill, have you learned your abc and 123?\n" +
                "I know My Abc's.\n" +
                "It is important to know your abc's and 123's,\n" +
                "so sing with me: abc! 123! Abc and 123!";

        String actual18 = getFileContent(inputFile18.getPath());

        assertEquals("The files differ!", expected18, actual18);
    }


    // Purpose: test both f and i options
    // Frame #: 5
    @Test
    public void replaceTest19() throws Exception {
        File inputFile19 = createInputFile6();

        String args[] = {"-i","-f", "hamlet", "Harry", inputFile19.getPath()};
        Main.main(args);

        String expected19 = "Prince Harry devotes himself toavenginghis father’s death, " +
                "but, because he is contemplative and thoughtful by nature, he delays, entering into a deep melancholy " +
                "and even apparent madness. Claudius and Gertrude worry about the prince’s " +
                "erratic behavior and attempt to discover its cause";

        String actual19 = getFileContent(inputFile19.getPath());

        assertEquals("The files differ!", expected19, actual19);
    }


    // Purpose: normal usage, 1 quote within pattern in form, and this pattern appear in multiple places
    // White space in front of pattern, only replace the first occurance
    // Frame #: 44
    @Test
    public void replaceTest20() throws Exception {
        File inputFile20 = createInputFile10();

        String args[] = {"-f", " \"Emperor", " super", inputFile20.getPath()};
        Main.main(args);

        String expected20 = "The super\" penguin is the tallest and heaviest \n" +
                "of all living penguin species and is endemic to Antarctica. \n" +
                "The \"EmpeRor\" penguin male \n" +
                "and the \"Emperor\" penguin female \n" +
                "are similar in plumage and size, reaching \n" +
                "122 cm in height and weighing from 22 to 45";

        String actual20 = getFileContent(inputFile20.getPath());

        assertEquals("The files differ!", expected20, actual20);
    }

    // Purpose: length of from is 1
    // Frame #: 13
    @Test
    public void replaceTest21() throws Exception {
        File inputFile21 = createInputFile3();

        String args[] = {"w", "ght", inputFile21.getPath()};
        Main.main(args);

        String expected21 = "Hoghtdy Bill, have you learned your abc and 123?\n" +
                "I knoght My Abc's.\n" +
                "It is important to knoght your abc's and 123's,\n" +
                "so repeat ghtith me: abc! 123! Abc and 123!";

        String actual21 = getFileContent(inputFile21.getPath());

        assertEquals("The files differ!", expected21, actual21);
    }


    // Purpose: normal usage, no quote within pattern in from, and this pattern appear in multiple places
    // White space at the of pattern, replace only the first of many occurances
    // Frame #: 49
    @Test
    public void replaceTest22() throws Exception {
        File inputFile22 = createInputFile10();

        String args[] = {"-f", "penguin ", "wingless bird ", inputFile22.getPath()};
        Main.main(args);

        String expected22 = "The \"Emperor\" wingless bird is the tallest and heaviest \n" +
                "of all living penguin species and is endemic to Antarctica. \n" +
                "The \"EmpeRor\" penguin male \n" +
                "and the \"Emperor\" penguin female \n" +
                "are similar in plumage and size, reaching \n" +
                "122 cm in height and weighing from 22 to 45";

        String actual22 = getFileContent(inputFile22.getPath());

        assertEquals("The files differ!", expected22, actual22);
    }


    // Purpose: length of to is 1
    // Frame #: 20
    @Test
    public void replaceTest23() throws Exception {
        File inputFile23 = createInputFile3();

        String args[] = {"important", "e", inputFile23.getPath()};
        Main.main(args);

        String expected23 = "Howdy Bill, have you learned your abc and 123?\n" +
                "I know My Abc's.\n" +
                "It is e to know your abc's and 123's,\n" +
                "so repeat with me: abc! 123! Abc and 123!";

        String actual23 = getFileContent(inputFile23.getPath());

        assertEquals("The files differ!", expected23, actual23);
    }

    // Purpose: Presence of blanks in To :  Many
    // Frame #: 23
    @Test
    public void replaceTest24() throws Exception {
        File inputFile24 = createInputFile6();

        String args[] = {"-i","-f", "hamlet", "   Harry   ", inputFile24.getPath()};
        Main.main(args);

        String expected24 = "Prince    Harry    devotes himself toavenginghis father’s death, " +
                "but, because he is contemplative and thoughtful by nature, he delays, entering into a deep melancholy " +
                "and even apparent madness. Claudius and Gertrude worry about the prince’s " +
                "erratic behavior and attempt to discover its cause";

        String actual24 = getFileContent(inputFile24.getPath());

        assertEquals("The files differ!", expected24, actual24);
    }


    // Purpose: normal usage, no quote within pattern to/from, and this pattern appear in multiple places
    // White space at the of pattern, case insensitive
    // Frame #: 51
    @Test
    public void replaceTest25() throws Exception {
        File inputFile25 = createInputFile10();

        String args[] = {"-i", "PeNgUiN ", "human ", inputFile25.getPath()};
        Main.main(args);

        String expected25 = "The \"Emperor\" human is the tallest and heaviest \n" +
                "of all living human species and is endemic to Antarctica. \n" +
                "The \"EmpeRor\" human male \n" +
                "and the \"Emperor\" human female \n" +
                "are similar in plumage and size, reaching \n" +
                "122 cm in height and weighing from 22 to 45";

        String actual25 = getFileContent(inputFile25.getPath());

        assertEquals("The files differ!", expected25, actual25);
    }


    // Purpose: Presence of a quote in from pattern and a space after pattern
    // Frame #: 32
    @Test
    public void replaceTest26() throws Exception {
        File inputFile26 = createInputFile9();

        String args[] = {"-i", "\"howdy ", "goodbye", inputFile26.getPath()};
        Main.main(args);

        String expected26 = "Howdy Billy,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say goodbye\" to Bill again!";

        String actual26 = getFileContent(inputFile26.getPath());

        assertEquals("The files differ!", expected26, actual26);
    }


    // Purpose: Presence of a quote in from pattern and no space after pattern
    // Frame #: 36
    @Test
    public void replaceTest27() throws Exception {
        File inputFile27 = createInputFile1();

        String args[] = {"-i", "\"howdy", "goodbye", inputFile27.getPath()};
        Main.main(args);

        String expected27 = "Howdy Billy,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say goodbye\" to Bill again!";

        String actual27 = getFileContent(inputFile27.getPath());

        assertEquals("The files differ!", expected27, actual27);
    }


    // Purpose: normal usage, case insensitive option on
    // Frame #: 39
    @Test
    public void replaceTest28() throws Exception {
        File inputFile28 = createInputFile6();

        String args[] = {"-i", "prince", "Peter", inputFile28.getPath()};
        Main.main(args);

        String expected28 = "Peter Hamlet devotes himself toavenginghis father’s death, " +
                "but, because he is contemplative and thoughtful by nature, he delays, entering into a deep melancholy " +
                "and even apparent madness. Claudius and Gertrude worry about the Peter’s " +
                "erratic behavior and attempt to discover its cause";

        String actual28 = getFileContent(inputFile28.getPath());

        assertEquals("The files differ!", expected28, actual28);
    }

    // Purpose: normal usage, many occurances, white space first in pattern, only replace the first occurance
    // Frame #: 43
    @Test
    public void replaceTest29() throws Exception {
        File inputFile29 = createInputFile7();

        String args[] = {"-f", " Tiberius", " Penguin", inputFile29.getPath()};
        Main.main(args);

        String expected29 = "Following the death of Augustus, Penguin is declared Emperor, " +
                "but the legions of Germany refuse to accept Tiberius and instead declare " +
                "Germanicus as his Emperor. Germanicus, shocked and confused, refuses, instead he " +
                "sends his wife and youngest son Caligula away and asks Claudius for an enormous sum of " +
                "money to pay the soldiers. Claudius agrees and pretends that they are gambling debts.";

        String actual29 = getFileContent(inputFile29.getPath());

        assertEquals("The files differ!", expected29, actual29);
    }


    // Purpose: normal usage, 1 quote within pattern in form, and this pattern appear in multiple places
    // White space in front of pattern, case insensitive
    // Frame #: 46
    @Test
    public void replaceTest30() throws Exception {
        File inputFile30 = createInputFile10();

        String args[] = {"-i", " \"emperor", "ir", inputFile30.getPath()};
        Main.main(args);

        String expected30 = "Their\" penguin is the tallest and heaviest \n" +
                "of all living penguin species and is endemic to Antarctica. \n" +
                "Their\" penguin male \n" +
                "and their\" penguin female \n" +
                "are similar in plumage and size, reaching \n" +
                "122 cm in height and weighing from 22 to 45";

        String actual30 = getFileContent(inputFile30.getPath());

        assertEquals("The files differ!", expected30, actual30);
    }

}