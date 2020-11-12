package model;

public class AVLTree<T extends Comparable<T>> extends Tree<T> {

    public AVLTree() {
        super();
    }

    public void add(T element) {
        if (this.root == null) {
            this.root = new Node<T>(element, null);
        } else {
            this.auxAdd(this.root, element);
        }
    }

    private void auxAdd(Node<T> root, T element) {

    }

    @Override
    public void remove(T element) {

    }

    private int balancFactor(Node<T> root) {
        int heightLeft = 0;
        int heightRight = 0;
        if (root != null) {
            if (root.getLeft() != null) {
                heightLeft = 1 + balancFactor(root.getLeft());
            }
            if (root.getRight() != null) {
                heightRight = 1 + balancFactor(root.getRight());
            }
            root.setHeightLeft(heightLeft);
            root.setHeightRight(heightRight);
        }
        return heightLeft > heightRight ? heightLeft : heightRight;
    }

    private void leftRotation(Node<T> node) {

    }

    private void rightRotation(Node<T> node) {

    }
}
