
import java.util.*;
public class a19_vertical_traversal_bst_method_two {
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

        //constructor
        Pair(Node node, int hd) {
            this.node = node;
            this.hd = hd;
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
//[[12], [25, 30], [50, 37, 62], [75, 70], [87]]


        Node root = new Node(arr[0], null, null);
        Pair rp = new Pair(root, 1);
        Stack<Pair> st = new Stack<>();
        st.push(rp);
        int idx = 0;  // idx should start at 0

        while (st.size() > 0) {
            Pair top = st.peek();
            if (top.hd == 1) {
                idx++;
                if (arr[idx] != null) {
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
                if (arr[idx] != null) {
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

        List<List<Integer>> l=function(root);
        System.out.println(l);
    }
    

    public static   List<List<Integer>> function(Node node) 
    {
        Queue<Pair> queue = new LinkedList<>();
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        int minhl = 0, maxhl = 0;
        List<List<Integer>> ans = new ArrayList<>();

        queue.add(new Pair(node, 0));

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Pair qp = queue.poll();
                int hl = qp.hd;
                Node n = qp.node;

                if (map.get(hl) == null) {
                    map.put(hl, new ArrayList<>());
                }
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
