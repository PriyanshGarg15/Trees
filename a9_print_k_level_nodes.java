import java.util.*;

public class a9_print_k_level_nodes {
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

        int k = 1; 
        printKLevelNodes(root, k);
    }

    public static void printKLevelNodes(Node node, int k) {
        if (node == null) {
            return;
        }

        Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.add(node);
        int level = 0;

        while (!nodeQueue.isEmpty()) {
            int size = nodeQueue.size();
            level++;
            if (level == k) {
                for (Node currentNode : nodeQueue) {
                    System.out.print(currentNode.data + " ");
                }
                return; // Exit after printing the Kth level nodes
            }

            for (int i = 0; i < size; i++) {
                Node currentNode = nodeQueue.poll();
                if (currentNode.left != null) {
                    nodeQueue.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    nodeQueue.add(currentNode.right);
                }
            }
        }

        System.out.println("The tree does not have " + k + " levels.");
    }
}
