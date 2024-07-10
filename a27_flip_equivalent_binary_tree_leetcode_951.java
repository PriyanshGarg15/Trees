import java.util.*;

public class a27_flip_equivalent_binary_tree_leetcode_951 {
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

        // Build the binary tree from the array
        Node root = buildTree(arr);
        
        // Example usage: checking flip equivalence of nodes with values 25 and 75
        boolean result = flipEquiv(root, new Node(25, null, null), new Node(75, null, null));
        System.out.println("Are nodes 25 and 75 flip equivalent? " + result);
    }

    // Method to build the binary tree from the array representation
    public static Node buildTree(Integer[] arr) {
        if (arr == null || arr.length == 0) return null;

        Node root = new Node(arr[0], null, null);
        Pair rp = new Pair(root, 1);
        Stack<Pair> st = new Stack<>();
        st.push(rp);
        int idx = 0;

        while (!st.isEmpty()) {
            Pair top = st.pop();
            if (top.state == 1) {
                idx++;
                if (idx < arr.length && arr[idx] != null) {
                    Node ln = new Node(arr[idx], null, null);
                    top.node.left = ln;
                    Pair lp = new Pair(ln, 1);
                    st.push(lp);
                } else {
                    top.node.left = null;
                }
                top.state++;
                st.push(top);
            } else if (top.state == 2) {
                idx++;
                if (idx < arr.length && arr[idx] != null) {
                    Node rn = new Node(arr[idx], null, null);
                    top.node.right = rn;
                    Pair rpRight = new Pair(rn, 1);
                    st.push(rpRight);
                } else {
                    top.node.right = null;
                }
                top.state++;
                st.push(top);
            }
        }

        return root;
    }

    // Method to check if two nodes are flip equivalent in the binary tree
    public static boolean flipEquiv(Node root, Node node1, Node node2) {
        if (root == null && node1 == null && node2 == null) return true;
        if (root == null || node1 == null || node2 == null) return false;
        if (root.data != node1.data && root.data != node2.data) return false;

        boolean case1 = flipEquiv(root.left, node1.left, node2.right) && flipEquiv(root.right, node1.right, node2.left);
        boolean case2 = flipEquiv(root.left, node1.right, node2.left) && flipEquiv(root.right, node1.left, node2.right);

        return case1 || case2;
    }
}
