import java.util.*;

public class a17_maximum_width_leetcode_662 {
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
        int index;

        Pair(Node node, int index) {
            this.node = node;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        Integer[] arr = {50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null, null};
        Node root = new Node(arr[0], null, null);
        Pair rp = new Pair(root, 1);
        Stack<Pair> st = new Stack<>();
        st.push(rp);
        int index = 0;

        while (!st.isEmpty()) {
            Pair top = st.peek();
            if (top.index == 1) {
                index++;
                if (index < arr.length && arr[index] != null) {
                    Node ln = new Node(arr[index], null, null);
                    top.node.left = ln;
                    Pair lp = new Pair(ln, 1);
                    st.push(lp);
                } else {
                    top.node.left = null;
                }
                top.index += 1;
            } else if (top.index == 2) {
                index++;
                if (index < arr.length && arr[index] != null) {
                    Node rn = new Node(arr[index], null, null);
                    top.node.right = rn;
                    Pair rpRight = new Pair(rn, 1);
                    st.push(rpRight);
                } else {
                    top.node.right = null;
                }
                top.index += 1;
            } else {
                st.pop();
            }
        }

        int maxWidth = widthOfBinaryTree(root);
        System.out.println(maxWidth);
    }

    public static int widthOfBinaryTree(Node root) {

        if (root == null) {
            return 0;
        }

        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 0));
        int maxWidth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int firstHd = 0, lastHd = 0;
            for (int i = 0; i < size; i++) {
                Pair rp = queue.poll();
                if (i == 0) firstHd = rp.index; // The index of the first node in the level
                if (i == size - 1) lastHd = rp.index; // The index of the last node in the level
                if (rp.node.left != null) {
                    queue.add(new Pair(rp.node.left, 2 * rp.index + 1));
                }
                if (rp.node.right != null) {
                    queue.add(new Pair(rp.node.right, 2 * rp.index + 2));
                }
            }

            int currentWidth = lastHd - firstHd + 1;
            maxWidth = Math.max(maxWidth, currentWidth);
        }

        return maxWidth;
    }
}
