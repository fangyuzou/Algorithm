/* Stack 的作用， 就是记录此前的访问路径。 当走到头了， 需要返回上一个节点。 stack中存的就是当前位置的上一个节点。 */

public List<Integer> inOrder(TreeNode root) {
    ArrayList<Integer> res = new ArrayList<>();
    if (root == null)
        return res;
    Stack<TreeNode> stack = new Stack<>();
    TreeNode p = root;
    while (p != null || !stack.isEmpty()) {
        if (p != null) {
            stack.push(p);
            p = p.left;
        }
        else {
            res.add(p.val);
            p = stack.pop().right;
        }
    }
    return res;
}


public List<Integer> preOrder(TreeNode root) {
    ArrayList<Integer> res = new ArrayList<>();
    if (root == null)
        return res;
    Stack<TreeNode> stack = new Stack<>();
    TreeNode p = root;
    while (p != null || !stack.isEmpty()) {
        if (p != null) {
            res.push(p.val);
            stack.push(p);
            p = p.left;
        }
        else {
            p = stack.pop().right;
        }
    }
    return res;
}

public List<Integer> postOrder(TreeNode root) {
    LinkedList<Integer> res = new LinkedList<>();
    if (root == null) 
        return res;
    Stack<TreeNode> stack = new Stack<>();
    TreeNode p = root;
    while (p != null || !stack.isEmpty()) {
        if (p != null) {
            res.addFirst(p.val);
            stack.push(p);
            p = p.right;
        }
        else {
            p = stack.pop().left;
        }
    }
    return res;
}
