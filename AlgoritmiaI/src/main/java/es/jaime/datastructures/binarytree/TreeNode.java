package es.jaime.datastructures.binarytree;

public class TreeNode<T> {
    private T data;
    private TreeNode<T> left;
    private TreeNode<T> right;

    public TreeNode(T data){
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }
}
