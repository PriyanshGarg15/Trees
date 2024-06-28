//preorder inorder postorder trraversals
import java.util.*;

public class a3_traversals {
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
        pretraversal(root);
        System.err.println();
        inorderraversal(root);
        System.out.println();
        postordertraversal(root);
    }

    public static void pretraversal(Node node)
    {
        if(node==null)
        {
            return;
        }

        System.out.print(node.data+"->");
        pretraversal(node.left);
        pretraversal(node.right);
    }

    public static void inorderraversal(Node node)
    {
        if(node==null)
        {
            return;
        }

        inorderraversal(node.left);
        System.out.print(node.data+"->");
        inorderraversal(node.right);
    }

    public static void postordertraversal(Node node)
    {
        if(node==null)
        {
            return;
        }

        postordertraversal(node.left);
        postordertraversal(node.right);
        System.out.print(node.data+"->");
    }
}
