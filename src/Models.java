import java.util.*;
import java.io.*;

public class Models {
    ArrayList<String> palavras = new ArrayList<>();
    String palavraSecreta;
    Set<Character> letrasCorretas = new HashSet<>();
    Set<Character> letrasErradas = new HashSet<>();
    Scanner scan = new Scanner(System.in);

    public void addPalavra (){
        System.out.println("Escreva a palavra há ser adicionada: ");

        String palavra = scan.next();

        palavras.add(palavra);
        salvarPalavras(palavra);
    }

    public void salvarPalavras(String palavra){
        try {
            BufferedWriter escritor = new BufferedWriter(new FileWriter("palavras.txt", true));
            escritor.write(palavra + ", ");
            escritor.close();
            System.out.println("Palavra salva com sucesso");
        } catch (IOException e) {
            System.out.println("Erro ao salvar as palavras: " + e.getMessage());
        }
    }

    public void carregarPalavras(){
        try{
            BufferedReader leitor = new BufferedReader(new FileReader("palavras.txt"));
            String linha;
            while ((linha = leitor.readLine()) != null) {
               String[] palavrasSeparadas = linha.split(",");
               for (String p : palavrasSeparadas) {
                   if (!p.trim().isEmpty()) {
                       palavras.add(p.trim().toLowerCase());
                   }
               }
            }
            leitor.close();
        } catch (IOException e) {
            System.out.println("Erro ao carregar as palavras: " + e.getMessage());
        }
    }

    public void escolherPalavra(){
        Random random = new Random();
        palavraSecreta = palavras.get(random.nextInt(palavras.size()));
    }

    public void mostrarPalavra(){
        for(char letra: palavraSecreta.toCharArray()) {
            if (letrasCorretas.contains(letra)){
                System.out.println(letra + " ");
            } else {
                System.out.println("_ ");
            }
        }
        System.out.println();
    }

    public char obterLetra(){
        System.out.println("Escreva uma letra");
        String entrada = scan.next().toLowerCase();

        while(entrada.length() != 1 || !Character.isLetter(entrada.charAt(0))) {
            System.out.println("Entrada inválida, digite apenas uma letra: ");
            entrada = scan.next().toLowerCase();
        }

        return entrada.charAt(0);
    }


    public void processarLetra(char letra){
        if(palavraSecreta.contains(String.valueOf(letra))){
            if(!letrasCorretas.contains(letra)){
                letrasCorretas.add(letra);
                System.out.println("Letra correta!");
            } else {
                System.out.println("Você já acertou essa letra!");
            }
        } else{
            if(!letrasErradas.contains(letra)){
                letrasErradas.add(letra);
                System.out.println("Letra incorreta!");
            } else {
                System.out.println("Você já errou essa letra.");
            }
        }
    }

    public boolean venceu(){
        for (char letra : palavraSecreta.toCharArray()){
            if (!letrasCorretas.contains(letra)){
                return false;
            }
        }
        return true;
    }



    public void jogarForca(){
        carregarPalavras();

        if (palavras.isEmpty()){
            System.out.println("Nenhuma palavra disponível para jogar.");
            return;
        }

        escolherPalavra();
        System.out.println("Palavra escolhida!");
        System.out.println("Tente uma letra: ");

        while(!venceu()){
            mostrarPalavra();
            char tentativa = obterLetra();
            processarLetra(tentativa);
        }

        System.out.println("\nParabéns! Você venceu! A palavra era: " + palavraSecreta);



    }
}
