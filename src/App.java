import java.util.Scanner;

import model.AVLTree;

public class App {
    public static void main(String[] args) throws Exception {
        AVLTree<Integer> tree = new AVLTree<>();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Qual a quantidade de nós que irá ter na arvore? ");
        int tamanho = scanner.nextInt();
        int contador = 0;
        boolean start = true;
        int elemento;
        while (contador < tamanho) {
            System.out.print("Digite o elemento que deseja colocar na arvore: ");
            elemento = scanner.nextInt();
            tree.add(elemento);
            contador++;
        }
        while (start) {
            System.out.println("\n1- Pre-Order");
            System.out.println("2- In-Order");
            System.out.println("3- Post-Order");
            System.out.println("4- Remover elemento");
            System.out.println("5- Buscar elemento");
            System.out.print("Parar o programa - Nenhuma das opções acima.\nOpção: ");
            int escolha = scanner.nextInt();
            switch (escolha) {
                case 1:
                    System.out.println("\nPre-Order: " + tree.preOrder());
                    break;
                case 2:
                    System.out.println("\nIn-Order: " + tree.inOrder());
                    break;
                case 3:
                    System.out.println("\nPost-Order: " + tree.postOrder());
                    break;
                case 4:
                    System.out.print("\nElemento: ");
                    elemento = scanner.nextInt();
                    tree.remove(elemento);
                    break;
                case 5:
                    System.out.print("\nElemento: ");
                    elemento = scanner.nextInt();
                    System.out.println("Contains " + elemento + ": " + tree.contains(elemento));
                    break;
                default:
                    start = false;
                    break;
            }
        }
        scanner.close();
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
