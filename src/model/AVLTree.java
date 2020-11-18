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
        this.auxRemove(element, this.root);
    }

    private void auxRemove(T element, Node<T> root) {
        if (root != null) {
            if (root.getElement().compareTo(element) == 0) {
                Node<T> unbalanced = null;
                if (root.getLeft() == null && root.getRight() == null) {
                    if (root == this.root) {
                        this.root = null;
                    } else if (root.getFather().getLeft() == root) {
                        root.getFather().setLeft(null);
                    } else {
                        root.getFather().setRight(null);
                    }
                    unbalanced = root.getFather();
                } else if (root.getLeft() != null && root.getRight() == null) {
                    root.getLeft().setFather(root.getFather());
                    if (root == this.root) {
                        this.root = root.getLeft();
                    } else if (root.getFather().getLeft() == root) {
                        root.getFather().setLeft(root.getLeft());
                    } else {
                        root.getFather().setRight(root.getLeft());
                    }
                    unbalanced = root.getLeft();
                } else if (root.getLeft() == null && root.getRight() != null) {
                    root.getRight().setFather(root.getFather());
                    if (root == this.root) {
                        this.root = root.getRight();
                    } else if (root.getFather().getLeft() == root) {
                        root.getFather().setLeft(root.getRight());
                    } else {
                        root.getFather().setRight(root.getRight());
                    }
                    unbalanced = root.getRight();
                } else {
                    Node<T> successor = this.auxMin(root.getRight());
                    unbalanced = successor;
                    if (successor.getFather() != root) {
                        if (successor.getFather().getLeft() == successor) {
                            successor.getFather().setLeft(successor.getRight());
                        } else {
                            successor.getFather().setRight(successor.getRight());
                        }
                        if (successor.getRight() != null) {
                            successor.getRight().setFather(successor.getFather());
                        }
                        successor.setRight(root.getRight());
                        root.getRight().setFather(successor);
                        unbalanced = successor.getFather();
                    }
                    successor.setFather(root.getFather());
                    successor.setLeft(root.getLeft());
                    root.getLeft().setFather(successor);
                    if (root == this.root) {
                        this.root = successor;
                    } else {
                        if (root.getFather().getLeft() == root) {
                            root.getFather().setLeft(successor);
                        } else {
                            root.getFather().setRight(successor);
                        }
                    }
                }
                this.balancTreeRemove(unbalanced);
            } else if (element.compareTo(root.getElement()) == 1) {
                this.auxRemove(element, root.getRight());
            } else {
                this.auxRemove(element, root.getLeft());
            }
        }
    }

    private void balancTreeRemove(Node<T> root) {
        if (root != null) {
            this.balancFactor(root);
            Node<T> left = root.getLeft();
            Node<T> right = root.getRight();
            if (root.getBf() == 2) {
                if (left != null && left.getBf() == 1) {
                    rightRotation(root);
                } else if (right != null && right.getBf() == -1) {
                    leftRotation(right);
                    rightRotation(root);
                } else if (root.getHeightLeft() > root.getHeightRight()) {
                    rightRotation(root);
                }
            } else if (root.getBf() == -2) {
                if (right != null && right.getBf() == -1) {
                    leftRotation(root);
                } else if (left != null && left.getBf() == 1) {
                    rightRotation(left);
                    leftRotation(root);
                } else if (root.getHeightLeft() < root.getHeightRight()) {
                    leftRotation(root);
                }
            }
            this.balancTreeRemove(root.getFather());
        }
    }

    private Node<T> auxMin(Node<T> root) {
        return root.getLeft() == null ? root : this.auxMin(root.getLeft());
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

    private void doubleLeftRotation(Node<T> pivo) {
        Node<T> left = pivo.getLeft();
        this.leftRotation(left);
        this.rightRotation(pivo);
    }

    private void doubleRightRotation(Node<T> pivo) {
        Node<T> right = pivo.getRight();
        this.rightRotation(right);
        this.leftRotation(pivo);
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
