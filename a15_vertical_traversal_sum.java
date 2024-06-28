import java.util.*;
public class a15_vertical_traversal_sum {
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
        int hd;

        Pair(Node node, int hd) {
            this.node = node;
            this.hd = hd;
        }
    }

    public static void main(String[] args) {
        Integer[] arr = {50, 25, null, 37, null, 30, null, 55, null, null, 75, 62, null, null, 87};
        Node root = constructTree(arr);
        List<Integer> l = verticalSum(root);
        System.out.println(l);
    }

    public static Node constructTree(Integer[] arr) {
        if (arr.length == 0 || arr[0] == null) {
            return null;
        }
        Node root = new Node(arr[0], null, null);
        Pair rp = new Pair(root, 1);
        Stack<Pair> st = new Stack<>();
        st.push(rp);
        int idx = 0;

        while (!st.isEmpty()) {
            Pair top = st.peek();
            if (top.hd == 1) {
                idx++;
                if (idx < arr.length && arr[idx] != null) {
                    Node ln = new Node(arr[idx], null, null);
                    top.node.left = ln;
                    Pair lp = new Pair(ln, 1);
                    st.push(lp);
                } else {
                    top.node.left = null;
                }
                top.hd += 1;
            } else if (top.hd == 2) {
                idx++;
                if (idx < arr.length && arr[idx] != null) {
                    Node rn = new Node(arr[idx], null, null);
                    top.node.right = rn;
                    Pair rpRight = new Pair(rn, 1);
                    st.push(rpRight);
                } else {
                    top.node.right = null;
                }
                top.hd += 1;
            } else {
                st.pop();
            }
        }

        return root;
    }

    public static List<Integer> verticalSum(Node node) {
        if (node == null) return new ArrayList<>();

        Queue<Pair> queue = new LinkedList<>();
        Map<Integer, Integer> map = new HashMap<>();
        int minhd = 0, maxhd = 0;

        queue.add(new Pair(node, 0));

        while (!queue.isEmpty()) {
            Pair qp = queue.poll();
            int hd = qp.hd;
            Node n = qp.node;

            map.put(hd, map.getOrDefault(hd, 0) + n.data);

            if (n.left != null)
                queue.add(new Pair(n.left, hd - 1));
            if (n.right != null)
                queue.add(new Pair(n.right, hd + 1));

            minhd = Math.min(minhd, hd);
            maxhd = Math.max(maxhd, hd);
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = minhd; i <= maxhd; i++) 
        {
                ans.add(map.get(i));
        }

        return ans;
    }
}
