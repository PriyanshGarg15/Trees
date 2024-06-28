//  as this test case fails in case of tree traversal in such a way
//  ex:-root =
// [1,2,3,4,6,5,7]
// Output
// [[4],[2],[1,6,5],[3],[7]]
// Expected
// [[4],[2],[1,5,6],[3],[7]]
// so we will not use below method
// There may be multiple nodes in the same row and same column. In such a case, sort these nodes by their values.
//see leetcode 987 example 3

import java.util.*;

public class a14_vertical_traversal {
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
        Integer[] arr = {50, 25, null, 37,null, 30,null, 55, null, null, 75, 62, null, null, 87};

        Node root = constructTree(arr);
        List<List<Integer>> l = function(root);
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


    public static List<List<Integer>> function(Node node) {
        Queue<Pair> queue = new LinkedList<>();
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        int minhl = 0, maxhl = 0;
        List<List<Integer>> ans = new ArrayList<>();

        queue.add(new Pair(node, 0));

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Pair qp = queue.poll();
                int hl = qp.hd;
                Node n = qp.node;

                map.putIfAbsent(hl, new ArrayList<>());
                map.get(hl).add(n.data);

                if (n.left != null)
                    queue.add(new Pair(n.left, hl - 1));
                if (n.right != null)
                    queue.add(new Pair(n.right, hl + 1));

                minhl = Math.min(minhl, hl);
                maxhl = Math.max(maxhl, hl);
            }
        }

        for (int i = minhl; i <= maxhl; i++) {
            if (map.containsKey(i)) {
                ans.add(map.get(i));
            }
        }

        return ans;
    }
}
