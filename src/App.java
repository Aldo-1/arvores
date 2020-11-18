import model.AVLTree;

public class App {
    public static void main(String[] args) throws Exception {
        AVLTree<Integer> tree = new AVLTree<>();
        tree.add(10);
        tree.add(20);
        tree.add(35);
        tree.add(50);
        tree.add(30);
        tree.add(40);
        tree.add(41);
        tree.add(42);
        tree.add(45);
        tree.add(60);
        tree.add(70);
        tree.add(46);
        tree.add(80);
        tree.remove(60);
        tree.remove(70);
        tree.remove(50);
        tree.remove(46);
        System.out.println("Pre-Order: " + tree.preOrder());
        System.out.println("Post-Order: " + tree.postOrder());
        System.out.println("In-Order: " + tree.inOrder());
    }
}
