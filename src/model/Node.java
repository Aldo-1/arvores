package model;

public class Node<T extends Comparable<T>> {
    
    private T element;
    private Node<T> father;
    private Node<T> left;
    private Node<T> right;

    public Node(T element, Node<T> father) {
        this.element = element;
        this.father = father;
    }

    public T getElement() {
        return element;
    }

    public Node<T> getFather() {
        return father;
    }

    public void setFather(Node<T> father) {
        this.father = father;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }
}
