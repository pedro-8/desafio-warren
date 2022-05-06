public class Desafio1 {
    
    static int numero = 1;
    static int totalNumeros = 0;

    // retorna o reverso do número passado como parâmetro, ex: parâmetro = 1234 retorno = 4321

    private static int reverterNumero(int numero) {         
        int numeroReverso = 0;                              
        while (numero != 0) {
            int digito = numero % 10;
            numeroReverso = numeroReverso * 10 + digito;
            numero /= 10;
        }
        return numeroReverso;
    }

    // retorna true se o último digito do número passado como parâmetro seja diferente de 0

    private static boolean verificarZero(int numero) {      
        int ultimoDigito = numero % 10;                     
        if (ultimoDigito != 0) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        /* 
        mostrará na tela um número N inteiro positivo se: N + N reverso < 1.000.000, 
        N + N reverso = ímpar, e se N não começar ou terminar com 0 
        */

        while (numero < 1000000) {
            if(numero + reverterNumero(numero) < 1000000) {
                if ((numero + reverterNumero(numero)) % 2 != 0 && verificarZero(numero) == true && verificarZero(reverterNumero(numero)) == true) {
                    System.out.println(numero);
                    totalNumeros++;
                }           
            }
        numero++;
        }
        System.out.println("Total de números: " + totalNumeros);
    }
}
