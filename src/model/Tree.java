package model;

public class Tree<T extends Comparable<T>> implements ITree<T> {

    private Node<T> root;

    @Override
    public void add(T element) {
        // TODO Auto-generated method stub
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
        Boolean resultado = search(actualNode, element);
        // ternario para retornar true ou false
        return resultado == true ? true : false;
    }

    private boolean search(Node<T> actualNode, T element) {
        // verificando se o no atual que ele recebe e nulo
        if (actualNode != null) {
            // verificando se o elemento do no atual é igual ao elemento
            if (actualNode.getElement().equals(element))
                return true;
            // recursividade pre-ordem
            search(actualNode.getLeft(), element);
            search(actualNode.getRight(), element);
        }
        return false;
    }

    @Override
    public int degree(T element) {
        // TODO Auto-generated method stub
        return 0;
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
        // TODO Auto-generated method stub
        return 0;
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
        // TODO Auto-generated method stub
        return null;
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
