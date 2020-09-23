import model.ITree;
import model.Tree;
import model.BinaryTree;

public class App {
    public static void main(String[] args) throws Exception {
        Tree<Integer> tree = new Tree<>();
        tree.add(null, 10, 'r');
        tree.add(10, 9, 'l');
        tree.add(10, 11, 'r');
        tree.add(9, 7, 'l');
        tree.add(11, 12, 'r');

        System.out.println(tree.remove(12));

        System.out.println("In Order Search: " + tree.inOrder());
        System.out.println("Pre Order Search: " + tree.preOrder());
        System.out.println("Post Order Search: " + tree.postOrder());

        System.out.println("Count Node's: " + tree.countNodes());

        System.out.println("Height Root: " + tree.height(10));

        ITree<Integer> mirrorTree = tree.mirror();

        System.out.println("In Order Search: " + mirrorTree.inOrder());
        System.out.println("Pre Order Search: " + mirrorTree.preOrder());
        System.out.println("Post Order Search: " + mirrorTree.postOrder());

        System.out.println("Count Node's: " + mirrorTree.countNodes());

        System.out.println("Height Root: " + mirrorTree.height(10));

        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        binaryTree.add(40);
        binaryTree.add(65);
        binaryTree.add(30);
        binaryTree.add(75);
        binaryTree.add(25);
        binaryTree.add(35);
        binaryTree.add(38);
        binaryTree.add(28);
        binaryTree.add(26);

        System.out.println("In Order Search: " + binaryTree.inOrder());
        System.out.println("Pre Order Search: " + binaryTree.preOrder());
        System.out.println("Post Order Search: " + binaryTree.postOrder());
        System.out.println("Count Node's: " + binaryTree.countNodes());
        System.out.println("Height Root: " + binaryTree.height(40));

        binaryTree.remove(30);
        System.out.println("In Order Search: " + binaryTree.inOrder());
    }
}
