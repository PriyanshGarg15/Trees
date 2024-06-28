import java.util.*;

public class a11_level_order_array_to_binary_tree {
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

    public static Node constructTreeLevelOrder(Integer[] arr) {
        if (arr == null || arr.length == 0) return null;

        Node root = new Node(arr[0], null, null);
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        int idx = 1;
        while (idx < arr.length) {
            Node currentNode = queue.poll();
            
            // Add left child if exists
            if (arr[idx] != null) {
                currentNode.left = new Node(arr[idx], null, null);
                queue.add(currentNode.left);
            } else {
                currentNode.left = null;  // Explicitly set to null
            }
            idx++;
            
            // Add right child if exists
            if (idx < arr.length) {
                if (arr[idx] != null) {
                    currentNode.right = new Node(arr[idx], null, null);
                    queue.add(currentNode.right);
                } else {
                    currentNode.right = null;  // Explicitly set to null
                }
                idx++;
            }
        }

        return root;
    }

    public static void main(String[] args) {
        Integer[] arr = {50, 25, 75, 12, 37, 62, 87, null, null, 30, null, null, 70, null, null};
        Node root = constructTreeLevelOrder(arr);

        // You can add additional code here to print the tree or test it
        printLevelOrder(root);  // For example, a level order print to verify the tree structure
    }

    public static void printLevelOrder(Node node) {
        if (node == null) return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            System.out.print(currentNode.data + " ");

            if (currentNode.left != null) {
                queue.add(currentNode.left);
            }

            if (currentNode.right != null) {
                queue.add(currentNode.right);
            }
        }
    }
}
