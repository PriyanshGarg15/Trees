//approach
// in pre state state bhadega and left ko jayenge
//in inorder statebhadega right ko jayeneg
//in post order pop ho jayega bas khai jayeg anahi !


import java.util.*;
public class a5_preorder_inorder_postorder_traversal_itteratively {
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
        iiteartivetraversal(root);
    }
    public static void iiteartivetraversal(Node node)
    {
        Stack<Pair> st=new Stack<>();
        Pair rtp=new Pair(node,1);
        st.push(rtp);

        String preorder="";
        String postorder="";
        String inorder="";
        while(st.size()>0)
        {
            Pair top=st.peek();
            if(top.state==1)
            {
                //state 1 tells that if we will push in out data structurer than we wil get preorder traversal
                preorder+=top.node.data+" ";
                top.state++;

                if(top.node.left!=null)
                {
                    Pair lp=new Pair(top.node.left,1);
                    st.push(lp);
                }


            }
            else if(top.state==2)
            {
                //state 2 tells that if we will push in out data structurer than we wil get inorder traversal
                inorder+=top.node.data+" ";
                top.state++;

                
                if(top.node.right!=null)
                {
                    Pair lp=new Pair(top.node.right,1);
                    st.push(lp);
                }
                
            }
            else //i.e top.state==3
            {
                //state =3 tells that if we will push in out data structurer than we wil get postorder traversal
                postorder+=top.node.data+" ";
                st.pop();
            }
        }

        System.out.println(preorder);
        System.out.println(inorder);
        System.out.println(postorder);
    }

}
