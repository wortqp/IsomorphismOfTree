package isomorphismoftrees;

import java.io.File;

public class Application {

    public static void main(String[] args) {
        Tree tree = new Tree(new File("src/resources/Sample1_6nodes_structure1"));
        Tree otherTree = new Tree(new File("src/resources/Sample2_6nodes_structure1"));
        System.out.println(tree);
        System.out.println(otherTree);
        System.out.println("Trees are isomorphic - " + tree.isomorphic(otherTree));
    }
}
