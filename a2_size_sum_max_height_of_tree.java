import java.util.*;

public class a2_size_sum_max_height_of_tree {
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
        display(root);
        int pg=size(root);
        System.out.println(pg);
        int sg=sum(root);
        System.out.println(sg);
        int h=height(root);
        System.out.println(h);
    }

    public static void display(Node node) {
        if (node == null) {
            return;
        }
        String str = "";
        str += node.left == null ? "." : node.left.data + "";
        str += "<-" + node.data + "->";
        str += node.right == null ? "." : node.right.data + "";
        System.out.println(str); 
        display(node.left);
        display(node.right);
    }

    public static int size(Node node) {
        if(node ==null)
        {
            return 1;
        }
        int ls=size(node.left);
        int rs=size(node.right);
        return ls+rs+1;
    }

    public static int sum(Node node)
    {
        if(node ==null)
        {
            return 0;
        }
        int ls=size(node.left);
        int rs=size(node.right);
        return ls+rs+node.data;
    }
    
    public static int max(Node node) {
        if (node == null) {
            return Integer.MIN_VALUE;
        }
    
        int leftMax = max(node.left);
        int rightMax = max(node.right);
    
        return Math.max(node.data, Math.max(leftMax, rightMax));
    }

    public static int height(Node node) 
    {
        if (node == null) {
            return 0;
        }
    
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
    
        return Math.max(leftHeight, rightHeight) + 1;
    }
    
}
