package model;

import java.util.concurrent.atomic.AtomicInteger;

public class BinaryTree<T extends Comparable<T>> implements ITree<T> {

    private Node<T> root;

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

    private Node<T> getNode(Node<T> root, T element) {
        if (root == null) {
            return null;
        }
        if (root.getElement().compareTo(element) == 0) {
            return root;
        }
        if (root.getElement().compareTo(element) == -1) {
            return this.getNode(root.getRight(), element);
        }
        return this.getNode(root.getLeft(), element);
    }

    @Override
    public boolean contains(T element) {
        return this.getNode(this.root, element) == null ? false : true;
    }

    @Override
    public int degree(T element) {
        // contador para o grau
        int contadorGrau = 0;

        // Se a raiz for igual ao null ele retornar falso pois nao tem arvore.
        if (this.root == null) {
            return 0;
        }

        // vai comecar pelo no
        Node<T> actualNode = this.root;

        // vai pegar o elemento da escolha
        Node<T> currentElement = this.getNode(actualNode, element);

        // vai verificar se o da esquerda o elemento é diferente de nulo
        // e somar no contadorGrau
        if (currentElement.getLeft() != null) {
            contadorGrau = contadorGrau + 1;
        }
        // vai verificar se o da direita o elemento é diferente de nulo
        // e somar no contadorGrau
        if (currentElement.getRight() != null) {
            contadorGrau = contadorGrau + 1;
        }
        return contadorGrau;
    }

    @Override
    public int depth(T element) {
        int profundidade = 0;
        if (this.root == null) {
            return 0;
        }
        // pegando o no raiz
        Node<T> actualNode = this.root;
        // retornando o resultado do no do elemento
        Node<T> noElemento = getNode(actualNode, element);

        while (noElemento.getFather() != null) {
            profundidade = +profundidade + 1;
            noElemento = noElemento.getFather();
        }
        return profundidade;
    }

    @Override
    public int height(T element) {
        Node<T> node = this.getNode(this.root, element);
        return node == null ? 0 : this.auxHeight(node);
    }

    private int auxHeight(Node<T> root) {
        int heightLeft;
        int heightRight;
        if (root.getLeft() == null) {
            heightLeft = 0;
        } else {
            heightLeft = this.auxHeight(root.getLeft());
        }
        if (root.getRight() == null) {
            heightRight = 0;
        } else {
            heightRight = this.auxHeight(root.getRight());
        }
        if (root.getRight() == null && root.getLeft() == null) {
            return Math.max(heightLeft, heightRight);
        }
        return 1 + Math.max(heightLeft, heightRight);
    }

    @Override
    public int level(T element) {
        int level = 1;
        if (this.root == null) {
            return 0;
        }
        // pegando o no raiz
        Node<T> actualNode = this.root;
        // retornando o resultado do no do elemento
        Node<T> noElemento = this.getNode(actualNode, element);

        while (noElemento.getFather() != null) {
            level = +level + 1;
            noElemento = noElemento.getFather();
        }
        return level;
    }

    @Override
    public int countNodes() {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        this.auxCountNodes(root, atomicInteger);
        return atomicInteger.get();
    }

    private void auxCountNodes(Node<T> root, AtomicInteger atomicInteger) {
        if (root != null) {
            atomicInteger.set(atomicInteger.get() + 1);
            this.auxCountNodes(root.getLeft(), atomicInteger);
            this.auxCountNodes(root.getRight(), atomicInteger);
        }
    }

    @Override
    public boolean remove(T element) {
        Node<T> node = this.getNode(this.root, element);
        if (node == null) {
            return false;
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
        return true;
    }

    private Node<T> getMin(Node<T> root) {
        return root.getLeft() == null ? root : this.getMin(root.getLeft());
    }

    @Override
    public String preOrder() {
        StringBuilder stringBuilder = new StringBuilder();
        this.auxPreOrder(this.root, stringBuilder);
        return stringBuilder.toString();
    }

    private void auxPreOrder(Node<T> root, StringBuilder stringBuilder) {
        if (root != null) {
            stringBuilder.append(root.getElement().toString()).append("\t");
            this.auxPreOrder(root.getLeft(), stringBuilder);
            this.auxPreOrder(root.getRight(), stringBuilder);
        }
    }

    @Override
    public String inOrder() {
        StringBuilder stringBuilder = new StringBuilder();
        this.auxInOrder(this.root, stringBuilder);
        return stringBuilder.toString();
    }

    private void auxInOrder(Node<T> root, StringBuilder stringBuilder) {
        if (root != null) {
            this.auxInOrder(root.getLeft(), stringBuilder);
            stringBuilder.append(root.getElement().toString()).append("\t");
            this.auxInOrder(root.getRight(), stringBuilder);
        }
    }

    @Override
    public String postOrder() {
        StringBuilder stringBuilder = new StringBuilder();
        this.auxPostOrder(this.root, stringBuilder);
        return stringBuilder.toString();
    }

    private void auxPostOrder(Node<T> root, StringBuilder stringBuilder) {
        if (root != null) {
            this.auxPostOrder(root.getLeft(), stringBuilder);
            this.auxPostOrder(root.getRight(), stringBuilder);
            stringBuilder.append(root.getElement().toString()).append("\t");
        }
    }
}
