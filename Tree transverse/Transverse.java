/* A tree has:
    A) DFS transverse
        a) preorder: (root, left, right)    // before the recursions
        b) postorder: (left, right, root)   // after the recursions
        c) inorder: (left, root, right)     // in the middle of recursions
    B) BFS transverse
*/


/* TreeNode
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode (int x) {
        val = x;
        left = null;
        right = null;
    }
*/

class Transverse {
    public List<TreeNode> transerveDFS(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        preorder(root, res);
        // postorder(root, res);
        // inorder(root, res);
        return res;
    }
    
    public List<TreeNode> transerveBFS(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        if (root == null)
            return res;
            
        List<TreeNode> layer = new ArrayList<>();
        layer.add(root);
        
        while (!layer.isEmpty()) {
            res.addAll(layer);
            
            List<TreeNode> temp = new ArrayList<>();
            for (TreeNode node : layer) {
                if (node.left != null)
                    temp.add(left);
                if (node.right != null)
                    temp.add(right);
            }
            
            layer = temp;
        }
        
        return res;
    }
    
    private void preoder(TreeNode root, List<TreeNode> res) {
        if (root == null) 
            return;
        
        res.add(root);
        preorder(root.left, res);
        preorder(root.right, res);
    }
    
    private void postorder(TreeNode root, List<TreeNode> res) {
        if (root == null)
            return;
            
        postorder(root.left, res);
        postorder(root.right, res);
        res.add(root);
    }
    
    private void inorder(TreeNode root, List<TreeNode> res) {
        if (root == null)
            return;
           
        inorder(root.left, res);
        res.add(root);
        inorder(root.right, res);
    }
}
        
    
