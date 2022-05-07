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
    static boolean evitarRepetir = false;
    static int determinaExecucoes = 0;
    static int maxExecucoes = 1;
    static int aumentarScope = 0;

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

    3º passo: adicionar cada elemento da arraylist X em um nova arraylist Y até a primeira ocorrência
    do menorElemento da arraylist X for encontrada, quando for encontrada, adiciona o primeiro
    elemento superior do Vetor na arraylist Y e para a execução, arraylist X se torna arraylist Y

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

    private static void resultadoExato() {
        ArrayList<Integer> nova = new ArrayList<Integer>();
        ArrayList<Integer> miniNova = new ArrayList<Integer>();
        int numeroPrimeira = 0;
        int menorNumero = 0;
        int umMaior = 0;
        int numeroX = 0;

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

        // 3º passo

        for (int i = 0; i < nova.size(); i++) {
            if (nova.get(i) == menorNumero) {
                miniNova.add(umMaior);
                break;
            }
            miniNova.add(nova.get(i));
    
        }

        // 4º passo, adiciona o menor elemento na arraylist

        for (int i = 0; i < miniNova.size(); i++) {
            numeroX += miniNova.get(i);
        }

        while (numeroX < numeroN) {
            miniNova.add(elementos.get(0));
            numeroX += elementos.get(0);
        }

        // mostra na tela a arraylist se atingida as condições

        if (primeiraIteracao == true && menorPossivel == primeira.size() && primeira.get(0) < numeroN) {
            System.out.println(primeira);
            evitarRepetir = true;
        }
        primeiraIteracao = false;

        nova.clear();
        for(int i = 0; i < miniNova.size(); i++) {
            nova.add(miniNova.get(i));
        }

        if (numeroX == numeroN && menorPossivel == nova.size() && evitarRepetir == false) {
            if (elementos.size() == 1) {
                miniNova.remove(0);
            }
            System.out.println(miniNova);
            parar = true;
        }
         
        // executa novamente o método até última arraylist for composta somente do maior número
     
        for (int i = 0; i < nova.size(); i++) {
            if (nova.get(i) < maiorElemento && maiorElemento != elementos.get(0)) {
                resultadoExato();
            }
        }
    }

    /*
    o método resultadoInexato() funciona semelhante ao método resultadoExato(), porém mostra apenas 
    o valor mais próximo a N
    */

    private static void resultadoInexato() {
        ArrayList<Integer> nova = new ArrayList<Integer>();
        ArrayList<Integer> miniNova = new ArrayList<Integer>();
        int numeroPrimeira = 0;
        int menorNumero = 0;
        int umMaior = 0;
        int numeroX = 0;

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

        // 3º passo

        for (int i = 0; i < nova.size(); i++) {
            if (nova.get(i) == menorNumero) {
                miniNova.add(umMaior);
                break;
            }
            miniNova.add(nova.get(i));   
        }

        // 4º passo, adiciona o menor elemento na arraylist

        for (int i = 0; i < miniNova.size(); i++) {
            numeroX += miniNova.get(i);
        }

        while (numeroX < numeroN - aumentarScope) {
            miniNova.add(elementos.get(0));
            numeroX += elementos.get(0);
        }

        // mostra na tela a arraylist se atingida as condições

        nova.clear();
        for(int i = 0; i < miniNova.size(); i++) {
            nova.add(miniNova.get(i));
        }

        if ((numeroX == numeroN + aumentarScope || numeroX == numeroN - aumentarScope) && menorPossivel == nova.size()) {
            if (elementos.size() == 1) {
                miniNova.remove(0);
            }
            System.out.println(miniNova);
            parar = true;
        }
         
        // executa novamente o método até última arraylist for composta somente do maior número
     
        for (int i = 0; i < nova.size(); i++) {
            if (nova.get(i) < maiorElemento && maiorElemento != elementos.get(0)) {
                resultadoInexato();
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

        System.out.println("N: " + numeroN);

        /* 
        determina o maior número possível de execuções, para evitar loop infinito e saber quando
        que se deve desistir de procurar a soma de vetores exata a N e procurar a mais proxima
        */

        while (determinaExecucoes < numeroN) {
            determinaExecucoes += elementos.get(0);
            maxExecucoes++;
        }

        /*
        executa o método resultadoExato() enquanto não tiver sido mostrado nenhum valor no console, a cada
        iteração aumenta em 1 a variável menorPossivel, a primeira arraylist que atinge as condições
        da função e tem o número de elementos igual a variável menorPossivel é mostrada ao console e
        parar se torna true, finalizando o loop
        */

        while (parar == false && menorPossivel < maxExecucoes) {
            adicionando.clear();
            primeira.clear();
            menorPossivel++;
            resultadoExato();
        }

        /*
        caso tenha excedido o número máximo de execuções e não tenha sido encontrado o resultado exato,
        o método resultadoInexato() é executado até a arraylist com menor quantidade de elementos
        mais próxima a N seja mostrada no console
        */
        
        if (parar == false) {
            menorPossivel = 0;
            System.out.println("Vetor perfeito nao encontrado, vetor mais próximo é:");
            while (parar == false) {
                adicionando.clear();
                primeira.clear();
                menorPossivel++;
                if (menorPossivel > maxExecucoes) {
                    menorPossivel = 1;
                    aumentarScope++;
                }
                resultadoInexato();
            }
        }
    }   
}
