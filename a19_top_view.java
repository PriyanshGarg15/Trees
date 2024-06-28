
import java.util.*;
public class a20_top_view {
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
        Node root = new Node(arr[0], null, null);
        Pair rp = new Pair(root, 1);
        Stack<Pair> st = new Stack<>();
        st.push(rp);
        int idx = 0;  // idx should start at 0

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
        
        Map<Integer,Integer> mp=new TreeMap<>();
        topview(root,mp);
        ArrayList<Integer> ar=new ArrayList<>();
        for(Integer i:mp.keySet())
        {
            ar.add(mp.get(i));
        }

        System.out.println(ar);
        
    }
    

    public static void topview(Node node,Map<Integer,Integer> mp) {
        Queue<Pair> qt = new LinkedList<>();
        if(node==null)
        {
            return;
        }

        qt.add(new Pair(node,0));

        while(!qt.isEmpty())
        {
            int size=qt.size();
            for(int i=0;i<size;i++)
            {
                Pair pg=qt.poll();
                if(mp.get(pg.state)==null)
                {
                    mp.put(pg.state,pg.node.data);
                }
                if(pg.node.left!=null)
                qt.add(new Pair (pg.node.left,pg.state-1));
                if(pg.node.right!=null)
                qt.add(new Pair(pg.node.right,pg.state+1));
            }
        }


        
    }
}
