import model.BinaryTreeSearch;
import model.BinaryTree;

public class App {
    public static void main(String[] args) throws Exception {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        binaryTree.add(null, 45, 'r');
        binaryTree.add(45, 30, 'l');
        binaryTree.add(45, 60, 'r');
        binaryTree.add(30, 20, 'l');
        binaryTree.add(30, 40, 'r');
        binaryTree.add(60, 50, 'l');
        binaryTree.add(60, 75, 'r');
        binaryTree.add(20, 10, 'l');
        binaryTree.add(20, 25, 'r');
        binaryTree.add(40, 35, 'l');
        binaryTree.add(40, 42, 'r');
        binaryTree.add(50, 49, 'l');
        binaryTree.add(50, 52, 'r');
        binaryTree.add(75, 70, 'l');
        binaryTree.add(75, 80, 'r');
        binaryTree.add(25, 27, 'r');

        System.out.println("In Order Search: " + binaryTree.inOrder());
        System.out.println("Pre Order Search: " + binaryTree.preOrder());
        System.out.println("Post Order Search: " + binaryTree.postOrder());

        BinaryTree<Integer> mirrorTree = binaryTree.mirror();
        System.out.println("\n\nIn Order Search: " + mirrorTree.inOrder());
        System.out.println("Pre Order Search: " + mirrorTree.preOrder());
        System.out.println("Post Order Search: " + mirrorTree.postOrder());

        System.out.println("\n\nMenor: " + binaryTree.less());
        System.out.println("Média: " + binaryTree.mean());
        System.out.println("Soma dos nós pares: " + binaryTree.sum());

        BinaryTreeSearch<Integer> binaryTreeSearch = new BinaryTreeSearch<>();
        binaryTreeSearch.add(45);
        binaryTreeSearch.add(30);
        binaryTreeSearch.add(60);
        binaryTreeSearch.add(20);
        binaryTreeSearch.add(40);
        binaryTreeSearch.add(50);
        binaryTreeSearch.add(75);
        binaryTreeSearch.add(10);
        binaryTreeSearch.add(25);
        binaryTreeSearch.add(35);
        binaryTreeSearch.add(42);
        binaryTreeSearch.add(49);
        binaryTreeSearch.add(52);
        binaryTreeSearch.add(70);
        binaryTreeSearch.add(80);
        binaryTreeSearch.add(27);

        System.out.println("\n\nIn Order Search: " + binaryTreeSearch.inOrder());
        System.out.println("Pre Order Search: " + binaryTreeSearch.preOrder());
        System.out.println("Post Order Search: " + binaryTreeSearch.postOrder());

        binaryTreeSearch.remove(45);
        binaryTreeSearch.remove(30);

        System.out.println("\n\nIn Order Search: " + binaryTreeSearch.inOrder());
        System.out.println("Pre Order Search: " + binaryTreeSearch.preOrder());
        System.out.println("Post Order Search: " + binaryTreeSearch.postOrder());

        System.out.println("\n\nSimilar: " + binaryTree.isEqual(binaryTree));

        BinaryTree<Integer> binaryTree2 = new BinaryTree<>();
        binaryTree2.add(null, 45, 'r');
        binaryTree2.add(45, 30, 'l');
        binaryTree2.add(45, 60, 'r');
        binaryTree2.add(30, 20, 'l');
        binaryTree2.add(30, 40, 'r');
        binaryTree2.add(60, 50, 'l');
        binaryTree2.add(60, 75, 'r');
        binaryTree2.add(20, 10, 'l');

        System.out.println("\n\nSimilar: " + binaryTree.isEqual(binaryTree2));
    }
}
