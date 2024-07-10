import java.util.*;

public class a26_all_nodes_distance_k_in_binary_tree_leetcode_863 {
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
        //        30    70
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

        List<Integer> ans = distanceK(root, 25, 1);
        System.out.println(ans);
    }

    //store all nodes parents 
    public static List<List<Integer>> parent(Node root) {
        List<List<Integer>> ll = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        ll.add(Arrays.asList(root.data, -1)); // root node has no parent, so -1

        while (!queue.isEmpty()) {
            Node peek = queue.poll(); // Remove the element from the queue

            if (peek.left != null) {
                queue.add(peek.left);
                ll.add(Arrays.asList(peek.left.data, peek.data));
            }
            if (peek.right != null) {
                queue.add(peek.right);
                ll.add(Arrays.asList(peek.right.data, peek.data));
            }
        }

        return ll;
    }

    public static List<Integer> distanceK(Node root, int tar, int k) {
        List<List<Integer>> parents = parent(root);
        List<Integer> ll = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();

        // Apply BFS
        Queue<Integer> queue = new LinkedList<>();
        queue.add(tar);
        visited.add(tar);
        int distance = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            if (distance == k) {
                for (int i = 0; i < size; i++) {
                    ll.add(queue.poll());
                }
                break;
            }

            for (int i = 0; i < size; i++) {
                int current = queue.poll();
                for (List<Integer> pair : parents) {
                    if (pair.get(0) == current && !visited.contains(pair.get(1))) {
                        queue.add(pair.get(1));
                        visited.add(pair.get(1));
                    } else if (pair.get(1) == current && !visited.contains(pair.get(0))) {
                        queue.add(pair.get(0));
                        visited.add(pair.get(0));
                    }
                }
            }
            distance++;
        }

        return ll;
    }
}
