import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Controllers control = new Controllers();
        Scanner scan = new Scanner(System.in);

        int opcao;


        do {
            System.out.println("====MENU====");
            System.out.println("1.Jogar\n2.Adicionar\n0.Sair");
            opcao = scan.nextInt();

            switch (opcao) {
                case 1:
                    control.jogarForca();
                    break;
                case 2:
                    control.addPalavra();
                    break;
                case 0:
                    System.out.println("Ok! Desligando...");;
                    break;
            }
        } while (opcao != 0);


    }
}