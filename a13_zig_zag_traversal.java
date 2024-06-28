import java.util.*;

public class a13_zig_zag_traversal {
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

        //           50
        //         /    \
        //       25      75
        //      /  \    /  \
        //    12   37  62   87
        //           /    \
        //          30    70
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
        zigzagTraversal(root);
    }

    public static void zigzagTraversal(Node node) {
        if (node == null) {
            return;
        }

        Deque<Node> deque = new LinkedList<>();
        deque.addLast(node);
        boolean leftToRight = true;

        while (!deque.isEmpty()) {
            int size = deque.size(); // Number of nodes at the current level

            // Use a list to store the current level's nodes
            List<Integer> currentLevel = new ArrayList<>();

            // Iterate through all nodes at the current level
            for (int i = 0; i < size; i++) {
                if (leftToRight) {
                    Node currentNode = deque.pollFirst();
                    currentLevel.add(currentNode.data);

                    if (currentNode.left != null) {
                        deque.addLast(currentNode.left);
                    }

                    if (currentNode.right != null) {
                        deque.addLast(currentNode.right);
                    }
                } else {
                    Node currentNode = deque.pollLast();
                    currentLevel.add(currentNode.data);

                    if (currentNode.right != null) {
                        deque.addFirst(currentNode.right);
                    }

                    if (currentNode.left != null) {
                        deque.addFirst(currentNode.left);
                    }
                }
            }
            // Print the current level's nodes
            for (int val : currentLevel) {
                System.out.print(val + " ");
            }
            System.out.println();

            // Switch the direction for the next level
            leftToRight = !leftToRight;
        }
    }
}
