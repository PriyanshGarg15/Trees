package leetcode;
import java.util.*;

import javax.swing.tree.TreeNode;
public class a1_257_binary_tree_paths {
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

    public static class Pair {
        Node node;
        int state;

        Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public static void main(String[] args) {
        Integer[] arr = {50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null, null};
        Node root = new Node(arr[0], null, null);
        Pair rp = new Pair(root, 1);
        Stack<Pair> st = new Stack<>();
        st.push(rp);
        int idx = 0;

        while (st.size() > 0) {
            Pair top = st.peek();
            if (top.state == 1) {
                idx++;
                if (arr[idx] != null) {
                    Node ln = new Node(arr[idx], null, null);
                    top.node.left = ln;
                    Pair lp = new Pair(ln, 1);
                    st.push(lp);
                } else {
                    top.node.left = null;
                }
                top.state += 1;
            } else if (top.state == 2) {
                idx++;
                if (arr[idx] != null) {
                    Node rn = new Node(arr[idx], null, null);
                    top.node.right = rn;
                    Pair rpRight = new Pair(rn, 1);
                    st.push(rpRight);
                } else {
                    top.node.right = null;
                }
                top.state += 1;
            } else {
                st.pop();
            }
        }
        List<String> paths = binaryTreePaths(root);
        System.out.println(paths);
    }
    
    public static List<String> binaryTreePaths(Node root) {
        List<String> paths = new ArrayList<>();
        if (root == null) {
            return paths;
        }
        findPaths(root, "", paths);
        return paths;
    }

    public static void findPaths(Node node, String path, List<String> paths) {
        if (node == null) {
            return;
        }

        path += node.data;

        if (node.left == null && node.right == null) 
        {
            paths.add(path);
        } 
        else 
        {
            path += "->";
            findPaths(node.left, path, paths);
            findPaths(node.right, path, paths);
        }
    }
}
