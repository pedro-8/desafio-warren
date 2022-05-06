import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Desafio2 {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Integer> tempoChegada = new ArrayList<Integer>();
    static int alunosAtrasados = 0; 
    static int limiteAlunos; 
    static String simOuNao; 

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
                tempoChegada.add(Integer.parseInt(stringNumeros));
                stringNumeros = "-";
                continue;
            }   
            if (Character.isDigit(dados.charAt(i))) {
                stringNumeros += dados.charAt(i);
                continue;
            }
            if ((dados.charAt(i) == ',' || dados.charAt(i) == ' ') && stringNumeros != "") {
                tempoChegada.add(Integer.parseInt(stringNumeros));
                stringNumeros = "";
            }
        }   
        if (stringNumeros != "") {
            tempoChegada.add(Integer.parseInt(stringNumeros));
        }
    }

    // adiciona os horários de chegada um por um na arraylist

    private static void maisDeUma() {                                   
        int numeroAluno = 0;                                            
        System.out.println("Digite a quantidade de alunos:");
        int quantidadeAlunos = scanner.nextInt();

        for (int i = 0; i < quantidadeAlunos; i++) {
            System.out.println("Digite o horário de chegada do " + (numeroAluno + 1) + "º aluno:"); 
            tempoChegada.add(scanner.nextInt());
            numeroAluno++;
        }
    }

    // incrementa em 1 a variável alunosAtrasados se o tempo de chegada do aluno > 0

    private static void calcularAtrasados() {                   
        for (int i = 0; i < tempoChegada.size(); i++) {         
            if (tempoChegada.get(i) > 0) {                      
                alunosAtrasados++;
            }     
        }
    }

    public static void main(String[] args) throws IOException {

        System.out.println("Digite o limite de alunos presentes:");
        limiteAlunos = scanner.nextInt();  

        System.out.println("Deseja inserir os dados de uma só vez?");
        simOuNao = scanner.next();

        /* 
        se a resposta enviada pelo usuário começar com "s" ou "d" executará o método umaVez(),
        se não, executará o método maisDeUma() 
        */

        if (simOuNao.toLowerCase().startsWith("s") || simOuNao.toLowerCase().startsWith("d")) {
            umaVez();
        } else {
            maisDeUma();  
        }

        calcularAtrasados();
        
        if (alunosAtrasados > limiteAlunos) {
            System.out.println("Aula cancelada.");
        }
        else {
            System.out.println("Aula normal.");
        }        
    }
}
