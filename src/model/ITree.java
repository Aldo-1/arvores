package model;

public interface ITree<T extends Comparable<T>> {

    void add(T element);

    boolean contains(T element);

    int degree(T element);

    int depth(T element);

    int height(T element);

    int level(T element);

    int countNodes();

    boolean remove(T element);

    String preOrder();

    String inOrder();

    String postOrder();

    Tree<T> mirror();
}
