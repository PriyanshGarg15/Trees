import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class BurningTree {
    public static void main(String[] args) {
        Integer[] arr = {50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null, null};
        TreeNode root = buildTree(arr);

        int targetNode = 14;
        burnTree(root, targetNode);
    }

    public static TreeNode buildTree(Integer[] arr) {
        // Construct the binary tree from the array
        // (similar to your existing code)
        // ...

        return root;
    }

    public static void burnTree(TreeNode root, int target) {
        Map<Integer, Integer> parentMap = new HashMap<>();
        getParentMap(root, parentMap);

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode currentNode = queue.poll();
                System.out.print(currentNode.val + " ");

                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }

                int parent = parentMap.get(currentNode.val);
                if (parent != 0 && !queue.contains(parent)) {
                    queue.add(new TreeNode(parent));
                }
            }
            System.out.println();
        }
    }

    public static void getParentMap(TreeNode root, Map<Integer, Integer> parentMap) {
        if (root == null) {
            return;
        }

        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        while (!nodeQueue.isEmpty()) {
            TreeNode currentNode = nodeQueue.poll();

            if (currentNode.left != null) {
                parentMap.put(currentNode.left.val, currentNode.val);
                nodeQueue.add(currentNode.left);
            }

            if (currentNode.right != null) {
                parentMap.put(currentNode.right.val, currentNode.val);
                nodeQueue.add(currentNode.right);
            }
        }
    }
}
