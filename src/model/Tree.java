package model;

public class Tree<T extends Comparable<T>> implements ITree<T> {

    private Node<T> root;

    @Override
    public void add(T elementFather, T elementChildren, char position) {
        if (this.root == null) {
            this.root = new Node<>(elementChildren, null);
        } else {
            this.auxAdd(this.root, elementFather, elementChildren, position);
        }
    }

    private void auxAdd(Node<T> root, T elementFather, T elementChildren, char position) {
        if (root != null) {
            if (root.getElement().compareTo(elementFather) == 0) {
                if (root != null && (position == 'l' || position == 'r')) {
                    Node<T> node = new Node<>(elementChildren, root);
                    if (position == 'l' && root.getLeft() == null) {
                        root.setLeft(node);
                    } else if (position == 'r' && root.getRight() == null) {
                        root.setRight(node);
                    }
                    return;
                }
            }
            this.auxAdd(root.getLeft(), elementFather, elementChildren, position);
            this.auxAdd(root.getRight(), elementFather, elementChildren, position);
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
        if (root == null) {
            return false;
        }

        // pegando o no raiz
        Node<T> actualNode = root;
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
        if (root == null) {
            return 0;
        }

        // vai comecar pelo no
        Node<T> actualNode = root;

        // vai pegar o elemento da escolha
        Node<T> currentElement = getNode(actualNode, element);

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
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int height(T element) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int level(T element) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int countNodes() {
        return this.root == null ? 0 : this.auxCountNodes(this.root, 0);
    }

    private int auxCountNodes(Node<T> root, int countNodes) {
        if (root != null) {
            countNodes++;
            this.auxCountNodes(root.getLeft(), countNodes);
            this.auxCountNodes(root.getRight(), countNodes);
        }
        return countNodes;
    }

    @Override
    public boolean remove(T element) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String preOrder() {
        // TODO Auto-generated method stub
        return null;
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Tree<T> mirror() {
        // TODO Auto-generated method stub
        return null;
    }
}
