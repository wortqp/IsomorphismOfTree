package isomorphismoftrees;

import java.io.*;

public class TreeTest {

    private PrintStream out = System.out;
    private int successCount;
    private int failureCount;

    public void testIsomorphicStruct1() {
        Tree tree = new Tree(new File("src/resources/Sample1_6nodes_structure1"));
        Tree otherTree = new Tree(new File("src/resources/Sample2_6nodes_structure1"));

        if (tree.isomorphic(otherTree)) {
            successCount++;
            out.println("Success: testIsomorphicStruct1 - are isomorphic, same quantity nodes and structure");
        } else {
            failureCount++;
            System.err.println("Failure: testIsomorphicStruct1 - should be isomorphic, same quantity nodes and structure");
        }
    }

    public void testIsomorphicStruct2() {
        Tree tree = new Tree(new File("src/resources/Sample3_10nodes_structure2"));
        Tree otherTree = new Tree(new File("src/resources/Sample4_10nodes_structure2"));

        if (tree.isomorphic(otherTree)) {
            successCount++;
            out.println("Success: testIsomorphicStruct2 - are isomorphic, same quantity nodes and structure");
        } else {
            failureCount++;
            System.err.println("Failure: testIsomorphicStruct2 - should be isomorphic, same quantity nodes and structure");
        }
    }

    public void testNonIsomorphicStruct1Struct3() {
        Tree tree = new Tree(new File("src/resources/Sample1_6nodes_structure1"));
        Tree otherTree = new Tree(new File("src/resources/Sample5_6nodes_structure3"));

        if (!tree.isomorphic(otherTree)) {
            successCount++;
            out.println("Success: testNonIsomorphicStruct1Struct3 - aren't isomorphic, different structure");
        } else {
            failureCount++;
            System.err.println("Failure: testNonIsomorphicStruct1Struct3 - shouldn't be isomorphic, different structure");
        }
    }

    public void testNonIsomorphicDifferentQuantityNodes() {
        Tree tree = new Tree(new File("src/resources/Sample7_5nodes_structure5"));
        Tree otherTree = new Tree(new File("src/resources/Sample8_8nodes_structure6"));

        if (!tree.isomorphic(otherTree)) {
            successCount++;
            out.println("Success: testNonIsomorphicDifferentQuantityNodes - aren't isomorphic, different quantity nodes");
        } else {
            failureCount++;
            System.err.println("Failure: testNonIsomorphicDifferentQuantityNodes - shouldn't be isomorphic, different quantity nodes");
        }
    }

    public void testIsomorphicItself() {
        Tree tree = new Tree(new File("src/resources/Sample8_8nodes_structure6"));

        if (tree.isomorphic(tree)) {
            successCount++;
            out.println("Success: testIsomorphicItself - is isomorphic, check itself");
        } else {
            failureCount++;
            System.err.println("Failure: testIsomorphicItself - should be isomorphic, check itself");
        }
    }

    public void testIsomorphicEmpty() {
        Tree tree = new Tree(new File("src/resources/Sample9_Empty"));
        Tree otherTree = new Tree(new File("src/resources/Sample9_Empty"));

        if (tree.isomorphic(otherTree)) {
            successCount++;
            out.println("Success: testIsomorphicEmpty - are isomorphic, both empty");
        } else {
            failureCount++;
            System.err.println("Failure: testIsomorphicEmpty - should be isomorphic, both empty");
        }
    }

    public void testNonExistentFile() {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintStream err = System.err;
        System.setErr(new PrintStream(buffer));
        Tree tree = new Tree(new File("src/resources/not_exist"));
        System.setErr(err);
        if (buffer.toString().equals("src/resources/not_exist (Нет такого файла или каталога)\n")) {
            successCount++;
            out.println("Success: testNonExistentFile - print to error stream not exist file");
        } else {
            failureCount++;
            System.err.println("Failure: testNonExistentFile - don't print to error stream not exist file");
        }
    }

    public void testInvalidData() {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintStream err = System.err;
        System.setErr(new PrintStream(buffer));
        Tree tree = new Tree(new File("src/resources/Sample10_invalid_data"));
        System.setErr(err);
        if (buffer.toString().equals("Invalid data tree have loop. File: src/resources/Sample10_invalid_data\n")) {
            successCount++;
            out.println("Success: testInvalidData - print to error stream file contains invalid data");
        } else {
            failureCount++;
            System.err.println("Failure: testInvalidData - don't print to error file contains invalid data");
        }
    }


    public static void main(String[] args) {
        TreeTest tt = new TreeTest();
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        tt.out.println("\nTesting class {isomorphismoftrees.Tree}");
        tt.out.println("---------------------------------------");
        tt.testIsomorphicStruct1();
        tt.testIsomorphicStruct2();
        tt.testNonIsomorphicStruct1Struct3();
        tt.testNonIsomorphicDifferentQuantityNodes();
        tt.testIsomorphicItself();
        tt.testIsomorphicEmpty();
        tt.testNonExistentFile();
        tt.testInvalidData();
        tt.out.println("---------------------------------------");
        tt.out.println("Successful tests: " + tt.successCount);
        tt.out.println("Failed tests: " + tt.failureCount);
    }
}
