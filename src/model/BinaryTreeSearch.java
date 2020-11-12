package model;

public class BinaryTreeSearch<T extends Comparable<T>> extends Tree<T> {

    public BinaryTreeSearch() {
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
        if (root.getElement().compareTo(element) == -1) {
            if (root.getRight() == null) {
                Node<T> right = new Node<T>(element, root);
                root.setRight(right);
            } else {
                this.auxAdd(root.getRight(), element);
            }
        } else if (root.getElement().compareTo(element) == 1) {
            if (root.getLeft() == null) {
                Node<T> left = new Node<T>(element, root);
                root.setLeft(left);
            } else {
                this.auxAdd(root.getLeft(), element);
            }
        }
    }

    @Override
    public void remove(T element) {
        Node<T> node = this.getNode(this.root, element);
        if (node == null) {
            return;
        }
        Node<T> father = node.getFather();
        if (node.getLeft() == null && node.getRight() == null) {
            if (node != this.root) {
                if (father.getLeft() == node) {
                    father.setLeft(null);
                } else {
                    father.setRight(null);
                }
                node.setFather(null);
                node = null;
            } else {
                this.root = null;
            }
        } else {
            if (node.getLeft() == null && node.getRight() != null) {
                if (node == this.root) {
                    this.root = this.root.getRight();
                    this.root.setFather(null);
                } else {
                    if (father.getRight() == node) {
                        father.setRight(node.getRight());
                    } else {
                        father.setLeft(node.getRight());
                    }
                    node.getRight().setFather(father);
                }
            } else if (node.getLeft() != null && node.getRight() == null) {
                if (node == this.root) {
                    this.root = this.root.getLeft();
                    this.root.setFather(null);
                } else {
                    if (father.getRight() == node) {
                        father.setRight(node.getLeft());
                    } else {
                        father.setLeft(node.getLeft());
                    }
                    node.getLeft().setFather(father);
                }
            } else {
                Node<T> suc = this.getMin(node.getRight());
                if (suc.getFather() == node) {
                    if (father != null && father.getRight() == node) {
                        father.setRight(suc);
                    } else {
                        if (father != null) {
                            father.setLeft(suc);
                        }
                    }
                } else {
                    father = suc.getFather();
                    if (father.getRight() == suc) {
                        father.setRight(suc.getRight());
                    } else {
                        father.setLeft(suc.getRight());
                    }
                    if (suc.getRight() != null) {
                        suc.getRight().setFather(father);
                        if (suc == father.getLeft()) {
                            father.setLeft(suc.getRight());
                        } else {
                            father.setRight(suc.getRight());
                        }
                    }
                }
                if (suc.getFather() != node) {
                    suc.setRight(node.getRight());
                    node.getRight().setFather(suc);
                }
                suc.setFather(node.getFather());
                suc.setLeft(node.getLeft());
                node.getLeft().setFather(suc);
                if (node.getFather() != null && node == node.getFather().getRight()) {
                    father.setRight(suc);
                } else if (node.getFather() != null && node == node.getFather().getLeft()) {
                    node.getFather().setLeft(suc);
                }
                if (node == this.root) {
                    this.root = suc;
                }
            }
        }
    }

    private Node<T> getMin(Node<T> root) {
        return root.getLeft() == null ? root : this.getMin(root.getLeft());
    }
}
