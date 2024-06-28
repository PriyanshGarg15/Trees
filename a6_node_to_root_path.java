import java.util.*;

public class a6_node_to_root_path {
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

        ArrayList<Integer> path = new ArrayList<>();
        boolean found = find(root, 87, path);
        if (found) {
            System.out.println(path);
        } else {
            System.out.println("Node not found");
        }
    }

    public static boolean find(Node node, int data, ArrayList<Integer> path) {
        if (node == null) {
            return false;
        }
        if (node.data == data) {
            path.add(node.data);
            return true;
        }


        if(find(node.left,data,path) || find(node.right,data,path))
        {
            path.add(node.data);
            return true;
        }

        return false;
    }
}
