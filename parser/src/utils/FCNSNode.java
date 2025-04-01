package utils;

public class FCNSNode<T> {
    private T data;
    private FCNSNode<T> firstChild;
    private FCNSNode<T> nextSibling;

    public FCNSNode(T data) {
        this.data = data;
        this.firstChild = null;
        this.nextSibling = null;
    }

    public T getData() { return data; }
    public void setData(T data) { this.data = data; }

    public FCNSNode<T> getFirstChild() { return firstChild; }
    public void setFirstChild(FCNSNode<T> firstChild) { this.firstChild = firstChild; }

    public FCNSNode<T> getNextSibling() { return nextSibling; }
    public void setNextSibling(FCNSNode<T> nextSibling) { this.nextSibling = nextSibling; }
}