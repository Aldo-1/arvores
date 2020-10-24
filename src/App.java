import model.BinaryTreeSearch;
import model.BinaryTree;

public class App {
    public static void main(String[] args) throws Exception {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.add(null, 45, 'r');
        tree.add(45, 30, 'l');
        tree.add(45, 60, 'r');
        tree.add(30, 20, 'l');
        tree.add(30, 40, 'r');
        tree.add(60, 50, 'l');
        tree.add(60, 75, 'r');
        tree.add(20, 10, 'l');
        tree.add(20, 25, 'r');
        tree.add(40, 35, 'l');
        tree.add(40, 42, 'r');
        tree.add(50, 49, 'l');
        tree.add(50, 52, 'r');
        tree.add(75, 70, 'l');
        tree.add(75, 80, 'r');
        tree.add(25, 27, 'r');
        System.out.println("Contains(25) = " + tree.contains(25));
        System.out.println("Grau(25) = " + tree.degree(25));
        System.out.println("Profundidade(27) = " + tree.depth(27));
        System.out.println("Altura(45) = " + tree.height(45));
        System.out.println("Nível(27) = " + tree.level(27));
        System.out.println("Quantidade de nós = " + tree.countNodes());

        BinaryTree<Integer> mirrorTree = tree.mirror();
        System.out.println("In Order Search: " + mirrorTree.inOrder());
        System.out.println("Pre Order Search: " + mirrorTree.preOrder());
        System.out.println("Post Order Search: " + mirrorTree.postOrder());
        System.out.println("Count Node's: " + mirrorTree.countNodes());
        System.out.println("Height Root: " + mirrorTree.height(45));

        BinaryTreeSearch<Integer> binaryTree = new BinaryTreeSearch<>();
        binaryTree.add(45);
        binaryTree.add(30);
        binaryTree.add(60);
        binaryTree.add(20);
        binaryTree.add(40);
        binaryTree.add(50);
        binaryTree.add(75);
        binaryTree.add(10);
        binaryTree.add(25);
        binaryTree.add(35);
        binaryTree.add(42);
        binaryTree.add(49);
        binaryTree.add(52);
        binaryTree.add(70);
        binaryTree.add(80);
        binaryTree.add(27);
        System.out.println("In Order Search: " + binaryTree.inOrder());
        System.out.println("Pre Order Search: " + binaryTree.preOrder());
        System.out.println("Post Order Search: " + binaryTree.postOrder());
        System.out.println("Count Node's: " + binaryTree.countNodes());
        System.out.println("Height: " + binaryTree.height(45));
        binaryTree.remove(45);
        System.out.println("In Order Search: " + binaryTree.inOrder());
        System.out.println("Pre Order Search: " + binaryTree.preOrder());
        System.out.println("Post Order Search: " + binaryTree.postOrder());
    }
}
