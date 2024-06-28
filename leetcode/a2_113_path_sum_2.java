package leetcode;

import java.util.*;

public class a2_113_path_sum_2 {
    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        Integer[] arr = {50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null, null};
        Node root = new Node(arr[0], null, null);
        Stack<Pair> st = new Stack<>();
        st.push(new Pair(root, 1));
        int idx = 0;

        while (!st.isEmpty()) {
            Pair top = st.peek();
            if (top.state == 1) {
                idx++;
                if (idx < arr.length && arr[idx] != null) {
                    Node ln = new Node(arr[idx], null, null);
                    top.node.left = ln;
                    st.push(new Pair(ln, 1));
                }
                top.state++;
            } else if (top.state == 2) {
                idx++;
                if (idx < arr.length && arr[idx] != null) {
                    Node rn = new Node(arr[idx], null, null);
                    top.node.right = rn;
                    st.push(new Pair(rn, 1));
                }
                top.state++;
            } else {
                st.pop();
            }
        }

        int targetSum = 87; // Example target sum
        List<List<Integer>> paths = binaryTreePaths(root, targetSum);
        System.out.println(paths);
    }

    public static List<List<Integer>> binaryTreePaths(Node root, int targetSum) {
        List<List<Integer>> paths = new ArrayList<>();
        if (root == null) {
            return paths;
        }
        findPaths(root, targetSum, new ArrayList<>(), 0, paths);
        return paths;
    }

    private static void findPaths(Node node, int targetSum, List<Integer> currentPath, int currentSum, List<List<Integer>> paths) {
        if (node == null) {
            return;
        }

        currentPath.add(node.data);
        currentSum += node.data;

        // Check if it's a leaf node and the path sum equals targetSum
        if (node.left == null && node.right == null && currentSum == targetSum) {
            paths.add(new ArrayList<>(currentPath));
        } else {
            // Continue the search on left and right children
            findPaths(node.left, targetSum, currentPath, currentSum, paths);
            findPaths(node.right, targetSum, currentPath, currentSum, paths);
        }

        // Backtrack: remove the current node from the path
        currentPath.remove(currentPath.size() - 1);
    }

    public static class Pair {
        Node node;
        int state;

        Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }
}
