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

    public void addChild(FCNSNode<T> parent, T childData) {
        FCNSNode<T> newChild = new FCNSNode<>(childData);

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

    public void traversePreOrder(FCNSNode<T> node) {
        if (node == null) return;

        System.out.println(node.getData());
        traversePreOrder(node.getFirstChild());
        traversePreOrder(node.getNextSibling());
    }
}