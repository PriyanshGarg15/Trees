import java.util.*;

public class a8_root_to_node_multiple_paths {
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
        Integer[] arr = {50, 25, 12, null, null, 37, 30, null, null, null, 87, 62, null, 70, null, null, 87, null, null};
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

        ArrayList<ArrayList<Integer>> allPaths = new ArrayList<>();
        ArrayList<Integer> path = new ArrayList<>();
        findAllPaths(root, 87, path, allPaths);
        
        if (!allPaths.isEmpty()) {
            System.out.println(allPaths);
        } else {
            System.out.println("Node not found");
        }
    }

    public static void findAllPaths(Node node, int data, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> allPaths) {
        if (node == null) {
            return;
        }

        path.add(node.data);

        if (node.data == data) {
            allPaths.add(new ArrayList<>(path));
        }

        findAllPaths(node.left, data, path, allPaths);
        findAllPaths(node.right, data, path, allPaths);

        path.remove(path.size() - 1);
    }
}
