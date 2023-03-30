package isomorphismoftrees;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tree {

    private List<Node> nodes = new ArrayList<>();
    private Map<List<Integer>, Integer> numbers;

    public Tree(File file) {
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {

            String str;
            while ((str = in.readLine()) != null) {

                Matcher matcher = Pattern.compile("\\d+").matcher(str);
                if (matcher.find()) {
                    int node = Integer.parseInt(matcher.group());
                    add(node);
                    while (matcher.find()) {
                        int subNode = Integer.parseInt(matcher.group());
                        add(subNode);
                        List<Node> subNodes = get(node).subNodes;
                        subNodes.add(get(subNode));
                    }
                }

            }

            for (int i = 0; i < nodes.size(); i++)
                if (nodes.get(i) == null) {
                    nodes.remove(i);
                    i--;
                }
            try {
                isomorphic(this);
            } catch (StackOverflowError e) {
                nodes.clear();
                System.err.println("Invalid data tree have loop. File: " + file);
            }

        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean isomorphic(Tree other) {
        if (nodes.isEmpty() && other.nodes.isEmpty())
            return true;
        if (nodes.size() != other.nodes.size())
            return false;

        numbers = new HashMap<>();
        Node root = nodes.get(new Random().nextInt(nodes.size()));
        int number = root.isomorphic(null);
        other.numbers = numbers;
        for (Node node: other.nodes) {
            if (number == node.isomorphic(null)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Tree {\n");
        for (Node node: nodes) {
            sb.append("    ");
            sb.append(node);
            sb.append(" -> ");
            sb.append(node.subNodes);
            sb.append("\n");
        }
        sb.append("}");
        return sb.toString();
    }

    private void add(int index) {
        index--;
        for (;;)
            try {
                if (nodes.get(index) == null)
                    nodes.set(index, new Node());
                return;
            } catch (IndexOutOfBoundsException e) {
                nodes.add(null);
            }
    }

    private Node get(int index) {
        index--;
        return nodes.get(index);
    }


    private class Node {

        private List<Node> subNodes = new ArrayList<>();

        private int isomorphic(Node parent) {
            List<Integer> nums = new ArrayList<>();
            for (Node node: subNodes) {
                if (node != parent)
                    nums.add(node.isomorphic(this));
            }

            Collections.sort(nums);
            if (!numbers.containsKey(nums))
                numbers.put(nums, numbers.size()+1);
            return numbers.get(nums);
        }

        @Override
        public String toString() {
            return "@" + hashCode();
        }
    }
}
