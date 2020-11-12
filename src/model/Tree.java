package model;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class Tree<T extends Comparable<T>> {

    protected Node<T> root;

    public Tree() {
        this.root = null;
    }

    public Node<T> getRoot() {
        return this.root;
    }

    public abstract void remove(T element);

    public boolean contains(T element) {
        // Se a raiz for igual ao null ele retornar falso pois nao tem arvore.
        if (this.root == null) {
            return false;
        }

        // pegando o no raiz
        Node<T> actualNode = this.root;
        // retornando o resultado do no do elemento
        Node<T> noElemento = this.getNode(actualNode, element);

        // ternario para retornar true ou false
        return noElemento != null ? true : false;
    }

    protected Node<T> getNode(Node<T> actualNode, T element) {
        Node<T> esquerda, direita;
        // verificando se o no atual que ele recebe e nulo
        if (actualNode != null) {
            // verificando se o elemento do no atual é igual ao elemento
            if (actualNode.getElement().equals(element)) {
                return actualNode;
            }

            // recursividade in-ordem

            esquerda = this.getNode(actualNode.getLeft(), element);
            if (esquerda != null) {
                return esquerda;
            }
            direita = this.getNode(actualNode.getRight(), element);
            if (direita != null) {
                return direita;
            }
        }
        return null;
    }

    public int degree(T element) {
        // contador para o grau
        int contadorGrau = 0;

        // Se a raiz for igual ao null ele retornar falso pois nao tem arvore.
        if (this.root == null) {
            return -1;
        }

        // vai comecar pelo no
        Node<T> actualNode = this.root;

        // vai pegar o elemento da escolha
        Node<T> currentElement = getNode(actualNode, element);
        if (currentElement != null) {
            // vai verificar se o da esquerda o elemento é diferente de nulo
            // e somar no contadorGrau
            if (currentElement.getLeft() != null) {
                contadorGrau++;
            }
            // vai verificar se o da direita o elemento é diferente de nulo
            // e somar no contadorGrau
            if (currentElement.getRight() != null) {
                contadorGrau++;
            }
            return contadorGrau;
        }
        return -1;
    }

    public int depth(T element) {
        int profundidade = 0;
        if (this.root == null) {
            return -1;
        }
        // pegando o no raiz
        Node<T> actualNode = this.root;
        // retornando o resultado do no do elemento
        Node<T> noElemento = getNode(actualNode, element);
        if (noElemento != null) {
            while (noElemento.getFather() != null) {
                profundidade = +profundidade + 1;
                noElemento = noElemento.getFather();
            }
            return profundidade;
        }
        return -1;
    }

    public int height(T element) {
        Node<T> node = this.getNode(this.root, element);
        return node == null ? -1 : this.auxHeight(node);
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

    public int level(T element) {
        int level = 1;
        if (this.root == null) {
            return -1;
        }
        // pegando o no raiz
        Node<T> actualNode = this.root;
        // retornando o resultado do no do elemento
        Node<T> noElemento = getNode(actualNode, element);
        if (noElemento != null) {
            while (noElemento.getFather() != null) {
                level = +level + 1;
                noElemento = noElemento.getFather();
            }
            return level;
        }
        return -1;
    }

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
