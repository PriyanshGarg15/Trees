
import java.util.*;
public class a12_symmetric_tree 
{

    public static class Node 
    {
        int data;
        Node left;
        Node right;

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public static class Pair 
    {
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
        boolean ans=isSymmetric(root);
        System.out.println(ans);
        
    }
 
    
    public static  boolean isSymmetric(Node root) {
        // A tree is symmetric if the left subtree is a mirror reflection of the right subtree
        return root == null || isMirror(root.left, root.right);
    }

    public  static boolean isMirror(Node left, Node right) {
        // If both subtrees are null, they are mirrors of each other
        if (left == null && right == null) {
            return true;
        }
        
        // If only one of the subtrees is null, they are not mirrors of each other
        if (left == null || right == null) {
            return false;
        }
        
        // Check if the current nodes are midatarrors of each other
        boolean currentMirror = (left.data == right.data);
        
        // Check if the left subtree of the left node is a mirror of the right subtree of the right node
        boolean leftMirror = isMirror(left.left, right.right);
        
        // Check if the right subtree of the left node is a mirror of the left subtree of the right node
        boolean rightMirror = isMirror(left.right, right.left);
        
        // The current nodes are mirrors if they match in value and their subtrees are mirrors
        return currentMirror && leftMirror && rightMirror;
    }
}