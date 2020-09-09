import model.ITree;
import model.Tree;

public class App {
    public static void main(String[] args) throws Exception {
        ITree<Integer> tree = new Tree<>();

        tree.add(null, 10, 'r');
        tree.add(10, 9, 'l');
        tree.add(10, 11, 'r');
        tree.add(9, 7, 'l');
        tree.add(11, 12, 'r');

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
    }
}
