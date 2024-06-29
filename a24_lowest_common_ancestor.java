import java.util.*;

public class a24_lowest_common_ancestor {
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
        //         /    \
        //       30      70
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

        LCA(root, 12, 37); 
        System.out.println(lca.data);
    }

    public static Node lca = null;

    public static boolean LCA(Node node, int p, int q) {
        if (node == null) {
            return false;
        }

        boolean self = (node.data == p || node.data == q);
        boolean left = LCA(node.left, p, q);
        boolean right = LCA(node.right, p, q);

        if ((left && right) || (left && self) || (right && self)) {
            lca = node;
        }

        return self || left || right;
    }
}
