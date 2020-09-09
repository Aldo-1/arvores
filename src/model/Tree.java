package model;

public class Tree<T extends Comparable<T>> implements ITree<T> {

    private Node<T> root;

    @Override
    public void add(T elementFather, T elementChildren, char position) {
        Node<T> node;
        if (this.root == null) {
            this.root = new Node<>(elementChildren, null);
        } else {
            Node<T> auxRoot = this.root;
            this.getNodeElement(auxRoot, elementFather);
            if (auxRoot != null && position == 'l' || position == 'r') {
                node = new Node<>(elementChildren, auxRoot);
                if (position == 'l' && auxRoot.getLeft() == null) {
                    auxRoot.setLeft(node);
                } else if (position == 'r' && auxRoot.getRight() == null) {
                    auxRoot.setRight(node);
                }
            }
        }
    }

    private void getNodeElement(Node<T> root, T element) {
        if (root != null) {
            if (root.getElement().compareTo(element) == 0) {
                return;
            }
            this.getNodeElement(root.getLeft(), element);
            this.getNodeElement(root.getRight(), element);
        }
    }

    @Override
    public boolean contains(T element) {
        // Se a raiz for igual ao null ele retornar falso pois nao tem arvore.
        if (root == null) {
            return false;
        }
        // pegando o no raiz
        Node<T> actualNode = root;
        // retorando o resultado da pesquisa
        Boolean resultado = auxContains(actualNode, element);
        // ternario para retornar true ou false
        return resultado == true ? true : false;
    }

    private boolean auxContains(Node<T> actualNode, T element) {
        // verificando se o no atual que ele recebe e nulo
        if (actualNode != null) {
            // verificando se o elemento do no atual é igual ao elemento
            if (actualNode.getElement().equals(element))
                return true;
            // recursividade in-ordem
            search(actualNode.getLeft(), element);
            search(actualNode.getRight(), element);
        }
        return false;
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
        Node<T> currentElement = auxDegree(actualNode, element);

        // vai verificar se o da esquerda o elemento é diferente de nulo
        // e somar no contadorGrau
        if (currentElement.getLeft().getElement() != null) {
            contadorGrau = contadorGrau + 1;
        }
        // vai verificar se o da direita o elemento é diferente de nulo
        // e somar no contadorGrau
        if (currentElement.getRight().getElement() != null) {
            contadorGrau = contadorGrau + 1;
        }

        return contadorGrau;
    }

    private Node<T> auxDegree(Node<T> actualNode, T element) {
        // verificando se o no atual que ele recebe e nulo
        if (actualNode != null) {
            // verificando se o elemento do no atual é igual ao elemento
            if (actualNode.getElement().equals(element))
                return actualNode;
            // recursividade in-ordem
            auxDegree(actualNode.getLeft(), element);
            auxDegree(actualNode.getRight(), element);
        }
        return null;
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
