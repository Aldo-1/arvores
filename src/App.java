import java.util.Scanner;

import model.AVLTree;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        AVLTree<Integer> tree = new AVLTree<>();
        System.out.println("Qual o tamanho que voce deseja para sua arvore?");
        int tamanho = input.nextInt();
        int contador = 0;
        boolean start = true;
        while (contador < tamanho) {
            System.out.println("Digite o numero que deseja colocar na arvore");
            int numero = input.nextInt();
            tree.add(numero);
            contador = contador + 1;
        }
        while (start) {
            System.out.println("Escolha como voce quer ver a arvore!");
            System.out.println("1- Pre ordem!");
            System.out.println("2- In ordem!");
            System.out.println("3- Pos ordem!");
            System.out.println("-1- Parar o programa!");
            int escolha = input.nextInt();
            switch (escolha) {
                case 1:
                    System.out.println("Pre-Order: " + tree.preOrder());
                    break;
                case 2:
                    System.out.println("In-Order: " + tree.inOrder());
                    break;

                case 3:
                    System.out.println("Post-Order: " + tree.postOrder());
                    break;

                case -1:
                    start = false;
                    break;
            }

        }
        // tree.add(10);
        // tree.add(20);
        // tree.add(35);
        // tree.add(50);
        // tree.add(30);
        // tree.add(40);
        // tree.add(41);
        // tree.add(42);
        // tree.add(45);
        // tree.add(60);
        // tree.add(70);
        // tree.add(46);
        // tree.add(80);
        // tree.remove(60);
        // tree.remove(70);
        // tree.remove(50);
        // tree.remove(46);
        // System.out.println("Pre-Order: " + tree.preOrder());
        // System.out.println("Post-Order: " + tree.postOrder());
        // System.out.println("In-Order: " + tree.inOrder());
    }
}
