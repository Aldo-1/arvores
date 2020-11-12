package model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BinaryTree<T extends Comparable<T>> extends Tree<T> {

    public BinaryTree() {
        super();
    }

    public void add(T elementFather, T elementChildren, char position) {
        if (this.root == null) {
            this.root = new Node<>(elementChildren, null);
        } else {
            Node<T> father = this.getNode(this.root, elementFather);
            Node<T> node = new Node<>(elementChildren, father);
            if (father != null) {
                if (position == 'l' && father.getLeft() == null) {
                    father.setLeft(node);
                } else if (position == 'r' && father.getRight() == null) {
                    father.setRight(node);
                }
            }
        }
    }

    @Override
    public void remove(T element) {
        if (this.root == null) {
            return;
        }
        // pegando o no raiz
        Node<T> actualNode = this.root;
        // retornando o resultado do no do elemento
        Node<T> noElemento = getNode(actualNode, element);

        if (noElemento.getFather() == null) {
            return;
        }
        if (noElemento.getLeft() == null && noElemento.getRight() == null) {
            Node<T> noPai = noElemento.getFather();
            if (noPai.getLeft() == noElemento) {
                noPai.setLeft(null);
                noElemento = null;
                return;
            } else if (noPai.getRight() == noElemento) {
                noPai.setRight(null);
                noElemento = null;
                return;
            }
        } else if (noElemento.getLeft() == null || noElemento.getRight() == null) {
            Node<T> noPai = noElemento.getFather();
            if (noElemento.getLeft() != null) {
                Node<T> noFilho = noElemento.getLeft();
                noElemento = null;
                noPai.setLeft(noFilho);
                return;
            } else if (noElemento.getRight() != null) {
                Node<T> noFilho = noElemento.getRight();
                noElemento = null;
                noPai.setRight(noFilho);
                return;
            }
        }
    }

    public BinaryTree<T> mirror() {
        BinaryTree<T> mirrorTree = new BinaryTree<>();
        mirrorTree.add(null, root.getElement(), 'c');
        this.auxMirror(mirrorTree, this.root);
        return mirrorTree;
    }

    private void auxMirror(BinaryTree<T> tree, Node<T> root) {
        if (root != null) {
            if (root.getLeft() != null) {
                tree.add(root.getElement(), root.getLeft().getElement(), 'r');
            }
            if (root.getRight() != null) {
                tree.add(root.getElement(), root.getRight().getElement(), 'l');
            }
            this.auxMirror(tree, root.getLeft());
            this.auxMirror(tree, root.getRight());
        }
    }

    public BinaryTreeSearch<T> toBinaryTree() {
        List<Node<T>> nodes = new ArrayList<>();
        BinaryTreeSearch<T> binaryTree = new BinaryTreeSearch<>();
        this.auxToBinaryTree(this.root, nodes);
        for (Node<T> node : nodes) {
            binaryTree.add(node.getElement());
        }
        return binaryTree;
    }

    private void auxToBinaryTree(Node<T> root, List<Node<T>> nodes) {
        if (root != null) {
            nodes.add(root);
            this.auxToBinaryTree(root.getLeft(), nodes);
            this.auxToBinaryTree(root.getRight(), nodes);
        }
    }

    public boolean isEqual(BinaryTree<T> tree) {
        return this.auxIsEqual(this.root, tree.getRoot());
    }

    private boolean auxIsEqual(Node<T> root1, Node<T> root2) {
        if (root1 == null && root2 == null) {
            return true;
        } else {
            if (root1 == null && root2 != null || root1 != null && root2 == null) {
                return false;
            }
        }
        if (!auxIsEqual(root1.getLeft(), root2.getLeft())) {
            return false;
        }
        return this.auxIsEqual(root1.getRight(), root2.getRight());
    }

    public T less() {
        T acctualLess = this.root.getElement();
        this.auxLess(acctualLess, root);
        return acctualLess;
    }

    private void auxLess(T acctualLess, Node<T> root) {
        if (root != null) {
            if (root.getElement().compareTo(acctualLess) == -1) {
                acctualLess = root.getElement();
            }
            this.auxLess(acctualLess, root.getLeft());
            this.auxLess(acctualLess, root.getRight());
        }
    }

    public double mean() {
        int count = this.countNodes();
        AtomicInteger sum = new AtomicInteger(0);
        this.auxMean(sum, root);
        return count > 0 ? sum.get() / this.countNodes() : 0.0;
    }

    public void auxMean(AtomicInteger sum, Node<T> root) {
        if (root != null) {
            sum.set(sum.get() + root.getElement().hashCode());
            this.auxMean(sum, root.getLeft());
            this.auxMean(sum, root.getRight());
        }
    }

    public int sum() {
        AtomicInteger sum = new AtomicInteger(0);
        this.auxSum(sum, root);
        return sum.get();
    }

    private void auxSum(AtomicInteger sum, Node<T> root) {
        if (root != null) {
            if (root.getElement().hashCode() % 2 == 0) {
                sum.set(sum.get() + root.getElement().hashCode());
            }
            this.auxSum(sum, root.getLeft());
            this.auxSum(sum, root.getRight());
        }
    }
}
