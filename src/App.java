import model.ITree;
import model.Tree;

public class App {
    public static void main(String[] args) throws Exception {
        ITree<Integer> tree = new Tree<>();
        tree.add(null, 10, 'r');
        tree.add(10, 9, 'l');
        tree.add(10, 11, 'r');
        tree.add(9, 7, 'l');
        System.out.println(tree.inOrder());
        System.out.println(tree.degree(11));
        System.out.println(tree.countNodes());
        System.out.println(tree.level(9));
    }
}
