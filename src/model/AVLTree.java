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
        if (root.getElement().compareTo(element) != 0) {
            if (element.compareTo(root.getElement()) == 1) {
                if (root.getRight() == null) {
                    Node<T> right = new Node<T>(element, root);
                    root.setRight(right);
                } else {
                    this.auxAdd(root.getRight(), element);
                    this.balancFactor(root);
                    if (root.getBf() == -2) {
                        if (element.compareTo(root.getRight().getElement()) == 1) {
                            leftRotation(root);
                        } else {
                            rightRotation(root.getRight());
                            leftRotation(root);
                        }
                    }
                }
            } else {
                if (root.getLeft() == null) {
                    Node<T> left = new Node<T>(element, root);
                    root.setLeft(left);
                } else {
                    this.auxAdd(root.getLeft(), element);
                    this.balancFactor(root);
                    if (root.getBf() == 2) {
                        if (element.compareTo(root.getLeft().getElement()) == -1) {
                            rightRotation(root);
                        } else {
                            leftRotation(root.getLeft());
                            rightRotation(root);
                        }
                    }
                }
            }
        }
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
        if (node != null) {
            Node<T> right = node.getRight();
            if (right != null) {
                Node<T> left = right.getLeft();
                Node<T> father = node.getFather();
                if (father == null) {
                    this.root = right;
                } else {
                    if (node == father.getLeft()) {
                        father.setLeft(right);
                    } else {
                        father.setRight(right);
                    }
                }
                node.setFather(right);
                right.setLeft(node);
                node.setRight(left);
                right.setFather(father);
                if (left != null) {
                    left.setFather(node);
                }
            }
        }
    }

    private void rightRotation(Node<T> node) {
        if (node != null) {
            Node<T> left = node.getLeft();
            if (left != null) {
                Node<T> right = left.getRight();
                Node<T> father = node.getFather();
                if (father == null) {
                    this.root = left;
                } else {
                    if (node == father.getLeft()) {
                        father.setLeft(left);
                    } else {
                        father.setRight(left);
                    }
                }
                node.setFather(left);
                left.setRight(node);
                node.setLeft(right);
                left.setFather(father);
                if (right != null) {
                    right.setFather(node);
                }
            }
        }
    }
}
