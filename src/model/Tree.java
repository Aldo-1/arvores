package model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Tree<T extends Comparable<T>> implements ITree<T> {

    private Node<T> root;

    public Tree() {
        this.root = null;
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

    private Node<T> getNode(Node<T> actualNode, T element) {
        Node<T> esquerda, direita;
        // verificando se o no atual que ele recebe e nulo
        if (actualNode != null) {
            // verificando se o elemento do no atual é igual ao elemento
            if (actualNode.getElement().equals(element)) {
                return actualNode;
            }

            // recursividade in-ordem

            esquerda = getNode(actualNode.getLeft(), element);
            if (esquerda != null) {
                return esquerda;
            }
            direita = getNode(actualNode.getRight(), element);
            if (direita != null) {
                return direita;
            }
        }
        return null;
    }

    @Override
    public boolean contains(T element) {
        // Se a raiz for igual ao null ele retornar falso pois nao tem arvore.
        if (this.root == null) {
            return false;
        }

        // pegando o no raiz
        Node<T> actualNode = this.root;
        // retornando o resultado do no do elemento
        Node<T> noElemento = getNode(actualNode, element);

        // ternario para retornar true ou false
        return noElemento != null ? true : false;
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
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
        if (this.root == null) {
            return false;
        }
        // pegando o no raiz
        Node<T> actualNode = this.root;
        // retornando o resultado do no do elemento
        Node<T> noElemento = getNode(actualNode, element);

        if (noElemento.getFather() == null) {
            System.out.println("Nao e possivel remover raiz");
            return false;
        }
        if (noElemento.getLeft() == null && noElemento.getRight() == null) {
            Node<T> noPai = noElemento.getFather();
            if (noPai.getLeft() == noElemento) {
                noPai.setLeft(null);
                noElemento = null;
                return true;
            } else if (noPai.getRight() == noElemento) {
                noPai.setRight(null);
                noElemento = null;
                return true;
            }
        } else if (noElemento.getLeft() == null || noElemento.getRight() == null) {
            Node<T> noPai = noElemento.getFather();
            if (noElemento.getLeft() != null) {
                Node<T> noFilho = noElemento.getLeft();
                noElemento = null;
                noPai.setLeft(noFilho);
                return true;
            } else if (noElemento.getRight() != null) {
                Node<T> noFilho = noElemento.getRight();
                noElemento = null;
                noPai.setRight(noFilho);
                return true;
            }
        }
        return false;
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

    public Tree<T> mirror() {
        Tree<T> mirrorTree = new Tree<>();
        mirrorTree.add(null, root.getElement(), 'c');
        this.auxMirror(mirrorTree, this.root);
        return mirrorTree;
    }

    private void auxMirror(Tree<T> tree, Node<T> root) {
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

    public BinaryTree<T> toBinaryTree() {
        List<Node<T>> nodes = new ArrayList<>();
        BinaryTree<T> binaryTree = new BinaryTree<>();
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
}
