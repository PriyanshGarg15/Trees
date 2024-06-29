import java.util.*;

public class a25_burning_tree {
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
        int time = burningTree(root, 12);
        System.out.println(time);
    }

    public static int burningTree(Node root, int target) {
        if (root == null) {
            return -1;
        }

        Map<Node, Node> parentMap = new HashMap<>();
        Node targetNode = getParentMap(root, parentMap, target);
        if (targetNode == null) {
            return -1;
        }

        Set<Node> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(targetNode);
        visited.add(targetNode);
        int time = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean burned = false;

            for (int i = 0; i < size; i++) {
                Node currentNode = queue.poll();

                if (currentNode.left != null && !visited.contains(currentNode.left)) {
                    queue.add(currentNode.left);
                    visited.add(currentNode.left);
                    burned = true;
                }

                if (currentNode.right != null && !visited.contains(currentNode.right)) {
                    queue.add(currentNode.right);
                    visited.add(currentNode.right);
                    burned = true;
                }

                Node parent = parentMap.get(currentNode);
                if (parent != null && !visited.contains(parent)) {
                    queue.add(parent);
                    visited.add(parent);
                    burned = true;
                }
            }

            if (burned) {
                time++;
            }
        }

        return time;
    }

    public static Node getParentMap(Node root, Map<Node, Node> parentMap, int target) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node targetNode = null;

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            if (currentNode.data == target) {
                targetNode = currentNode;
            }

            if (currentNode.left != null) {
                parentMap.put(currentNode.left, currentNode);
                queue.add(currentNode.left);
            }

            if (currentNode.right != null) {
                parentMap.put(currentNode.right, currentNode);
                queue.add(currentNode.right);
            }
        }

        return targetNode;
    }
}
