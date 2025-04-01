package utils;

public class FCNSTree<T> {
    private FCNSNode<T> root;

    public FCNSTree() {
        this.root = null;
    }

    public FCNSTree(T rootData) {
        this.root = new FCNSNode<>(rootData);
    }

    public FCNSNode<T> getRoot() { return root; }

    public void setRoot(FCNSNode<T> root) { this.root = root; }

    public boolean isEmpty() { return root == null; }

    public void addChild(T childData) {
        if (root == null) {
            throw new IllegalStateException("Cannot add child to null root");
        }
        addChildNode(root, new FCNSNode<>(childData));
    }

    public void addChild(FCNSTree<T> childTree) {
        if (root == null) {
            throw new IllegalStateException("Cannot add child to null root");
        }
        if (childTree == null || childTree.isEmpty()) {
            throw new IllegalArgumentException("Cannot add null or empty child tree");
        }
        addChildNode(root, childTree.getRoot());
    }

    private void addChildNode(FCNSNode<T> parent, FCNSNode<T> newChild) {
        if (parent.getFirstChild() == null) {
            parent.setFirstChild(newChild);
        } else {
            FCNSNode<T> current = parent.getFirstChild();
            while (current.getNextSibling() != null) {
                current = current.getNextSibling();
            }
            current.setNextSibling(newChild);
        }
    }

    public void traversePreOrder() {
        traversePreOrder(root);
    }

    public void traversePreOrder(FCNSNode<T> node) {
        printTree();
    }

    public void printTree() {
        printTree(root, 0);
    }

    private void printTree(FCNSNode<T> node, int level) {
        if (node == null) return;

        String sb = ".".repeat(Math.max(0, level)) +
                node.getData();
        System.out.println(sb);

        // first child - new level
        printTree(node.getFirstChild(), level + 1);

        // siblings - same level
        printTree(node.getNextSibling(), level);
    }
}