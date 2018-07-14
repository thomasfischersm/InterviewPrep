package com.playposse.interviewprep.crackingthecodeinterview.treesandgraphs;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * In a directed graph, find out if there is a route between two nodes.
 */
public class RouteBetweenNodes {

    @Test
    public void hasRoute() {
//        for (int i = 0; i < 101; i++) {
//            System.out.println(i + " -> " + (int) (Math.log10(i) + 1));
//        }

        Node rootNode = createGraph(
                "1", "2,3",
                "2", "4,5");

        Node endNote = rootNode.getChildren().get(0).getChildren().get(0);
        assertTrue(hasRoute(rootNode, endNote));

        System.out.println(endNote.value);
        System.out.println(rootNode.getChildren().get(1).value);
        assertFalse(hasRoute(rootNode.getChildren().get(1), endNote));

        print(rootNode);
    }

    @Test
    public void findDepth() {
        assertEquals(0, findDepth(null));
        assertEquals(1, findDepth(createGraph("1", "")));
        assertEquals(2, findDepth(createGraph("1", "2")));
        assertEquals(2, findDepth(createGraph("1", "2,3")));
        assertEquals(2, findDepth(createGraph("1", "2,3,4")));
        assertEquals(3, findDepth(createGraph(
                "1", "2,3",
                "2", "4")));
        assertEquals(4, findDepth(createGraph(
                "1", "2,3",
                "2", "4",
                "3", "5, 6",
                "5", "7")));
    }

    @Test
    public void computeWidth() {
        assertEquals(0, computeWidth(null));
        assertEquals(1, computeWidth(createGraph("1", "")));
        assertEquals(2, computeWidth(createGraph("11", "")));
        assertEquals(1, computeWidth(createGraph("1", "2")));
        assertEquals(2, computeWidth(createGraph("11", "2")));
        assertEquals(2, computeWidth(createGraph("1", "22")));
        assertEquals(3, computeWidth(createGraph("1", "2,3")));
        assertEquals(4, computeWidth(createGraph("1", "22,3")));
        assertEquals(4, computeWidth(createGraph("1", "2,33")));
        assertEquals(5, computeWidth(createGraph("1", "22,33")));
        assertEquals(5, computeWidth(createGraph(
                "1", "2,3",
                "2", "4,5")));
    }

    @Test
    public void prepareOutput() {
        print(prepareOutput(createGraph("1", "")));
        System.out.println("----------------");

        print(prepareOutput(createGraph("1", "2, 3")));
        System.out.println("----------------");

        print(prepareOutput(createGraph(
                "1", "2, 3",
                "2", "4,5")));
        System.out.println("----------------");

        print(prepareOutput(createGraph(
                "1", "2, 3",
                "2", "4,5",
                "4", "6,7")));
        System.out.println("----------------");

        print(prepareOutput(createGraph(
                "1", "2, 3",
                "2", "4,5",
                "4", "6,7",
                "5", "8,9")));
        System.out.println("----------------");

        print(prepareOutput(createGraph(
                "1", "2, 3",
                "2", "4,5",
                "4", "6,7",
                "5", "8,9",
                "3", "10,11")));
        System.out.println("----------------");
    }

    private static boolean hasRoute(Node startNode, Node endNode) {
        if ((startNode == null) || (endNode == null)) {
            return false;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(startNode);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            if (currentNode == endNode) {
                return true;
            }

            queue.addAll(currentNode.getChildren());
        }

        return false;
    }

    private static int computeWidth(Node node) {
        if (node == null) {
            return 0;
        }

        if (node.getChildren().size() == 0) {
            return getDigitCount(node);
        }

        boolean isChildCountEven = (node.getChildren().size() % 2 == 0);
        int width = 0;
        for (int i = 0; i < node.getChildren().size(); i++) {
            Node childNode = node.getChildren().get(i);

            int childWidth = computeWidth(childNode);
            if (isChildCountEven && (i == node.getChildren().size() / 2)) {
                // This node fills the space between two child nodes.
                width += getDigitCount(node);
            } else if (!isChildCountEven && (i == node.getChildren().size() / 2)) {
                // This node is right on top of the middle child node.
                childWidth = Math.max(childWidth, getDigitCount(node));
            } else if (width > 0) {
                width++;
            }
            width += childWidth;
        }
        return width;
    }

    private static void print(Node node) {
        boolean isChildCountEven = (node.getChildren().size() % 2 == 0);
        int nodeWidth = getDigitCount(node);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < node.getChildren().size(); i++) {
            Node childNode = node.getChildren().get(i);
            int childWidth = computeWidth(childNode);
            if (isChildCountEven && (i == node.getChildren().size() / 2)) {
                // This node fills the space between two child nodes.
                sb.append(node.value);
            } else if (!isChildCountEven && (i == node.getChildren().size() / 2)) {
                // This node is right on top of the middle child node.
                if (sb.length() > 0) {
                    sb.append(" ");
                }

                if (childWidth <= nodeWidth) {
                    sb.append(node.value);
                } else {
                    sb.append(concat(' ', (int) Math.ceil((childWidth - nodeWidth) / 2)));
                    sb.append(node.value);
                    sb.append(concat(' ', (int) Math.floor((childWidth - nodeWidth) / 2)));
                }
                continue;
            }

            if (sb.length() > 0) {
                sb.append(" ");
            }

            sb.append(concat(' ', childWidth));
        }

        System.out.println(sb.toString());
    }

    private static List<StringBuilder> prepareOutput(Node node) {
        List<StringBuilder> lines = new ArrayList<>();
        lines.add(new StringBuilder());

        // Handle special case of a leaf node.
        if (node.getChildren().size() == 0) {
            lines.get(0).append(node.getValue());
            return lines;
        }

        boolean isChildCountEven = (node.getChildren().size() % 2 == 0);
        int nodeWidth = getDigitCount(node);

        for (int i = 0; i < node.getChildren().size(); i++) {
            Node childNode = node.getChildren().get(i);
            List<StringBuilder> childLines = prepareOutput(childNode);
            int childWidth = (childLines.size() > 0) ? childLines.get(0).length() : 0;

            // The depth of lines might need to be increased.
            while (childLines.size() + 1 > lines.size()) {
                StringBuilder addedLine = new StringBuilder();
                addedLine.append(concat(' ', lines.get(0).length()));
                lines.add(addedLine);
            }

            // For even child nodes, the current node might need to be added in the middle of
            // children.
            if (isChildCountEven && (i == node.getChildren().size() / 2)) {
                lines.get(0).append(node.getValue());
                appendSpace(lines, 1, nodeWidth);
            } else if (lines.size() > 0) {
                // Add space between children.
                appendSpace(lines, 0, 1);
            }

            // For uneven child nodes, the current node has ot be added in the middle of children.
            if (!isChildCountEven && (i == node.getChildren().size() / 2)) {
                if (nodeWidth >= childWidth) {
                    // Current node larger i than child area.
                    lines.get(0).append(node.getValue());
                    copyLines(lines, childLines, nodeWidth);
                } else {
                    // Child area is larger than the current node.
                    lines.get(0).append(pad(Integer.toString(node.getValue()), childWidth));
                    copyLines(lines, childLines, childWidth);
                }
            } else {
                // For child that is NOT beneath the current node.
                lines.get(0).append(concat(' ', childWidth));

                copyLines(lines, childLines, childLines.get(0).length());
            }
        }

        return lines;
    }

    private static void print(List<StringBuilder> lines) {
        for (StringBuilder sb : lines) {
            System.out.println(sb);
        }
    }

    private static void copyLines(
            List<StringBuilder> targetLines,
            List<StringBuilder> sourceLines,
            int width) {

        for (int j = 1; j < targetLines.size(); j++) {
            if (j - 1 < sourceLines.size()) {
                targetLines.get(j).append(pad(sourceLines.get(j - 1).toString(), width));
            } else {
                targetLines.get(j).append(concat(' ', width));
            }
        }
    }

    private static void appendSpace(List<StringBuilder> lines, int startIndex, int spacesCount) {
        for (int i = startIndex; i < lines.size(); i++) {
            lines.get(i).append(concat(' ', spacesCount));
        }
    }

    private static String pad(String str, int width) {
        double halfPadding = (width - str.length()) / 2.0;
        return concat(' ', (int) Math.ceil(halfPadding))
                + str
                + concat(' ', (int) Math.floor(halfPadding));
    }

    private static int getDigitCount(Node node) {
        return (int) (Math.log10(node.value) + 1);
    }

    private static String concat(char c, int count) {
        count = Math.max(0, count);
        if (count == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(c);
        }
        return sb.toString();
    }

    private static int findDepth(Node node) {
        if (node == null) {
            return 0;
        }

        int depth = 0;
        List<Node> currentLevel = new ArrayList<>();
        List<Node> childLevel = new ArrayList<>();
        currentLevel.add(node);

        while (!currentLevel.isEmpty()) {
            for (Node currentNode : currentLevel) {
                childLevel.addAll(currentNode.getChildren());
            }

            // Rotate levels.
            currentLevel = childLevel;
            childLevel = new ArrayList<>();
            depth++;
        }

        return depth;
    }

    /**
     * Creates a graph.
     *
     * @param data The data is encoded in tuplets. The first string value is the value of the node.
     *             The second string value lists all the child node's values. E.g. "1", "2,3,4" defines
     *             that node 1 has three children: 2, 3, 4.
     * @return
     */
    private static Node createGraph(String... data) {
        Node rootNode = null;
        Map<Integer, Node> valueToNodeMap = new HashMap<>();

        for (int i = 0; i < data.length; i += 2) {
            int currentNodeValue = Integer.parseInt(data[i]);

            Node currentNode = valueToNodeMap.get(currentNodeValue);
            if (currentNode == null) {
                currentNode = new Node(currentNodeValue);
                valueToNodeMap.put(currentNodeValue, currentNode);
            }

            if (data[i + 1].trim().length() > 0) {
                String[] childNodeStrings = data[i + 1].split(",");
                for (int j = 0; j < childNodeStrings.length; j++) {
                    int childNodeValue = Integer.parseInt(childNodeStrings[j].trim());
                    Node childNode = valueToNodeMap.get(childNodeValue);
                    if (childNode == null) {
                        childNode = new Node(childNodeValue);
                        valueToNodeMap.put(childNodeValue, childNode);
                    }

                    currentNode.getChildren().add(childNode);
                }
            }

            if (rootNode == null) {
                rootNode = currentNode;
            }
        }

        return rootNode;
    }

    private static class Node {
        private int value;
        private List<Node> children = new ArrayList<>();

        public Node() {
        }

        public Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public List<Node> getChildren() {
            return children;
        }

        public void setChildren(List<Node> children) {
            this.children = children;
        }
    }
}
