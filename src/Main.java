import java.util.HashSet;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        int[] vetorPrincipal =  geraVetor(100000);
        int[] vetorCopia =  vetorPrincipal.clone();

        System.out.println("Bolha:");
        bubbleSort(vetorCopia);
        System.out.println("Selecaao:");
        selectionSort(vetorCopia);

       // Arrays.stream(geraVetor(100000)).forEach(z-> System.out.println(z));
    }

    public static int[] geraVetor(int qtdValores) {

        int[] vetor = new int[qtdValores];
        int randomNumber ;

        HashSet<Integer> set = new HashSet<Integer>();
        Random random = new Random();

        for (int i = 0; i < vetor.length; i++) {
            do {
                randomNumber = random.nextInt();
            } while (set.contains(randomNumber));
            vetor[i] = randomNumber;
            set.add(randomNumber);
        }

        return vetor;
    }

    public static void bubbleSort(int[] vetor) {
        int n = vetor.length;
        int comparacoes = 0;
        int trocas = 0;

        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                comparacoes++;
                if (vetor[j] > vetor[j+1]) {
                    int temp = vetor[j];
                    vetor[j] = vetor[j+1];
                    vetor[j+1] = temp;
                    trocas++;
                }
            }
        }

        System.out.println("Operações de comparação: " + comparacoes);
        System.out.println("Operações de troca: " + trocas);
    }

    public static void selectionSort(int[] vetor) {
        int n = vetor.length;
        int comparacoes = 0;
        int trocas = 0;

        for (int i = 0; i < n-1; i++) {
            int min_idx = i;
            for (int j = i+1; j < n; j++) {
                comparacoes++;
                if (vetor[j] < vetor[min_idx]) {
                    min_idx = j;
                }
            }

            int temp = vetor[min_idx];
            vetor[min_idx] = vetor[i];
            vetor[i] = temp;
            trocas++;
        }

        System.out.println("Operações de comparação: " + comparacoes);
        System.out.println("Operações de troca: " + trocas);
    }
}