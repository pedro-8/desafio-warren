import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Desafio3 {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Integer> elementos = new ArrayList<Integer>();
    static ArrayList<Integer> adicionando = new ArrayList<Integer>();
    static ArrayList<Integer> primeira = new ArrayList<Integer>();
    static int numeroN;
    static String simOuNao;
    static int maiorElemento = 0;
    static boolean primeiraIteracao = true;
    static int menorPossivel = 0;
    static boolean parar = false;

    /* 
    remove do vetor os caractéres desnecessários, transforma os números em formato de string para int
    e os adiciona na arraylist 
    */

    private static void umaVez() throws IOException {
        String stringNumeros = "";
        System.out.println("Insira o vetor:");
        String dados = reader.readLine();

        for (int i = 0; i < dados.length(); i++) {
            if (dados.charAt(i) == '[' || dados.charAt(i) == ']') {
                continue;
            }
            if (dados.charAt(i) == '-' && stringNumeros == "") {
                stringNumeros += "-";
                continue;
            } 
            if (dados.charAt(i) == '-' && stringNumeros != "") {
                elementos.add(Integer.parseInt(stringNumeros));
                stringNumeros = "-";
                continue;
            }   
            if (Character.isDigit(dados.charAt(i))) {
                stringNumeros += dados.charAt(i);
                continue;
            }
            if ((dados.charAt(i) == ',' || dados.charAt(i) == ' ') && stringNumeros != "") {
                elementos.add(Integer.parseInt(stringNumeros));
                stringNumeros = "";
            }
        }   
        if (stringNumeros != "") {
            elementos.add(Integer.parseInt(stringNumeros));
        }
    }

    // adiciona os horários de chegada um por um na arraylist

    private static void maisDeUma() {
        int numeroElemento = 0;
        System.out.println("Digite a quantidade de elementos:");
        int quantidadeElementos = scanner.nextInt();

        for (int i = 0; i < quantidadeElementos; i++) {
            System.out.println("Digite o número do " + (numeroElemento + 1) + "º elemento:"); 
            elementos.add(scanner.nextInt());
            numeroElemento++;
        }
    }

    /* 
    Exemplo:

    N = 10
    Vetor = [2,3,4]
    
    1º passo: adiciona o menor elemento do Vetor em uma arraylist  X 
    até soma da arraylist X for maior ou igual a N
    
    arraylist X = [2,2,2,2,2]

    2º passo: acha o menor elemento da arraylist X 
    
    menorElemento = 2

    3º passo: substituir a primeira ocorrência do menorElemento na arraylist X pelo primeiro elemento
    superior do Vetor, descartar o resto da arraylist X

    X = [3]

    4º passo: executar novamente o 1º passo

    X = [3,2,2,2,2]
       
    Continuar executando todos os passos em ordem até a arraylist X for composta somente do maior
    número do Vetor

    Exemplo do resultado:

    [2,2,2,2,2]
    [3,2,2,2,2]
    [3,3,2,2]
    [3,3,3,2]
    [3,3,3,3]
    [4,2,2,2]
    [4,3,2,2]
    [4,3,3]
    [4,4,2]
    [4,4,3]
    [4,4,4]
    
    A array que será mostrada na tela é a array em que a soma dos seus números seja = N e que
    possua a menor quantidade de elementos, caso mais de uma array atinja essas condições, ela
    também será mostrada 
    */

    private static void resultado() {
        ArrayList<Integer> nova = new ArrayList<Integer>();
        int numeroPrimeira = 0;
        int menorNumero = 0;
        int umMaior = 0;
        int numeroX = 0;
        boolean remover = false;

        // 1º passo, cria a primeira arraylist

        if (adicionando.isEmpty()) {
            while (numeroPrimeira < numeroN) {
                adicionando.add(elementos.get(0));
                primeira.add(elementos.get(0));
                numeroPrimeira += elementos.get(0);
            }
        }

        nova = adicionando;

        // 2º passo, acha o menor elemento

        for (int i = 0; i < nova.size(); i++) {
            if (menorNumero == 0 || nova.get(i) < menorNumero) {
                menorNumero = nova.get(i);
            }
        }

        // acha o primeiro elemento superior do vetor, pelo qual o menor elemento será trocado

        for (int i = 0; i < elementos.size(); i++) {
            if (elementos.get(i) > menorNumero) {
                umMaior = elementos.get(i);
                break;
            }
        }

        // 3º passo, substitui a primeira ocorrência e remove o resto

        for (int i = 0; i < nova.size(); i++) {
            if (remover == true) {
                nova.remove(i);
                continue;
            }
            if (nova.get(i) == menorNumero) {
                nova.set(i, umMaior);
                remover = true;
            }
        }

        // 4º passo, adiciona o menor elemento na arraylist

        for (int i = 0; i < nova.size(); i++) {
            numeroX += nova.get(i);
        }

        while (numeroX < numeroN) {
            nova.add(elementos.get(0));
            numeroX += elementos.get(0);
        }

        // mostra na tela a arraylist se atingida as condições

        if (primeiraIteracao == true && menorPossivel == primeira.size()) {
            System.out.println(primeira);
        }
        primeiraIteracao = false;

        if (numeroX == numeroN && menorPossivel == nova.size()) {
            System.out.println(nova);
            parar = true;
        }

        // executa novamente o método até última arraylist for composta somente do maior número
        
        for (int i = 0; i < nova.size(); i++) {
            if (nova.get(i) < maiorElemento && maiorElemento != elementos.get(0)) {
                resultado();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        
        System.out.println("Digite um número N:");
        numeroN = scanner.nextInt();

        System.out.println("Deseja inserir o dados de uma só vez?");
        simOuNao = scanner.next();

        /* se a resposta enviada pelo usuário começar com "s" ou "d" executará o método umaVez(),
        se não, executará o método maisDeUma() */

        if (simOuNao.toLowerCase().startsWith("s") || simOuNao.toLowerCase().startsWith("d")) {
            umaVez();
        } else {
            maisDeUma();  
        }

        // ordena em ordem crescente a arraylist passada

        Collections.sort(elementos);

        // atribui o valor do maior elemento da arraylist para a variável maiorElemento

        for (int i = 0; i < elementos.size(); i++) {
            if (i + 1 == elementos.size()) {
                maiorElemento = elementos.get(i);
            }
        }

        System.out.println(numeroN);

        /*
        executa o método resultado() enquanto não tiver sido mostrado nenhum valor no console, a cada
        iteração aumenta em 1 a variável menorPossivel, a primeira arraylist que atinge as condições
        da função e tem o número de elementos igual a variável menorPossivel é mostrada ao console e
        parar se torna true, finalizando o loop
        */
         
        while (parar == false) {
            adicionando.clear();
            primeira.clear();
            menorPossivel++;
            resultado();
        }
    }   
}
