import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {

        int numExecucoes = 50;
        int tamanhoVetor = 100000;
        long[] compBubble = new long[numExecucoes];
        long[] trocasBubble = new long[numExecucoes];
        long[] compSelection = new long[numExecucoes];
        long[] trocasSelection = new long[numExecucoes];

        try {
            FileWriter fw = new FileWriter("resultados.txt");
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("Resultados da ordenação de vetores de " + tamanhoVetor + " elementos\n\n");


            for (int i = 0; i < numExecucoes; i++) {
            System.out.println(i);
            int[] vetor = geraVetor(tamanhoVetor);
            int[] copia = vetor.clone();

            // Ordena o vetor com o algoritmo Bubble Sort e registra o número de comparações e trocas
            long[] resultBubble = bubbleSort(vetor);
            compBubble[i] = resultBubble[0];
            trocasBubble[i] = resultBubble[1];

            // Ordena a cópia do vetor com o algoritmo Selection Sort e registra o número de comparações e trocas
            long[] resultSelection = selectionSort(copia);
            compSelection[i] = resultSelection[0];
            trocasSelection[i] = resultSelection[1];
        }

        // Calcula as estatísticas das execuções
        long maxCompBubble = calcularMax(compBubble);
        long minCompBubble = calcularMin(compBubble);
        long mediaCompBubble = calcularMedia(compBubble);
        long maxTrocasBubble = calcularMax(trocasBubble);
        long minTrocasBubble = calcularMin(trocasBubble);
        long mediaTrocasBubble = calcularMedia(trocasBubble);
        long maxCompSelection = calcularMax(compSelection);
        long minCompSelection = calcularMin(compSelection);
        long mediaCompSelection = calcularMedia(compSelection);
        long maxTrocasSelection = calcularMax(trocasSelection);
        long minTrocasSelection = calcularMin(trocasSelection);
        long mediaTrocasSelection = calcularMedia(trocasSelection);

        // Exibe os resultados
        bw.write("Estatísticas do Bubble Sort:");
        bw.write("\nMaior quantidade de comparações: " + maxCompBubble);
        bw.write("\nMenor quantidade de comparações: " + minCompBubble);
        bw.write("\nMédia de comparações: " + mediaCompBubble);
        bw.write("\nMaior quantidade de trocas: " + maxTrocasBubble);
        bw.write("\nMenor quantidade de trocas: " + minTrocasBubble);
        bw.write("\nMédia de trocas: " + mediaTrocasBubble +"\n");

        bw.write("\nEstatísticas do Selection Sort:");
        bw.write("\nMaior quantidade de comparações: " + maxCompSelection);
        bw.write("\nMenor quantidade de comparações: " + minCompSelection);
        bw.write("\nMédia de comparações: " + mediaCompSelection);
        bw.write("\nMaior quantidade de trocas: " + maxTrocasSelection);
        bw.write("\nMenor quantidade de trocas: " + minTrocasSelection);
        bw.write("\nMédia de trocas: " + mediaTrocasSelection);

            bw.close();
            fw.close();
            System.out.println("Resultados salvos no arquivo resultados.txt");
    } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
        }        }



    // Arrays.stream(geraVetor(100000)).forEach(z-> System.out.println(z));


    private static int[] geraVetor(int qtdValores) {

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

    public static long[] bubbleSort(int[] arr) {
        int n = arr.length;
        long comparacoes = 0;
        long trocas = 0;

        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                comparacoes++;
                if (arr[j] > arr[j+1]) {
                    trocas++;
                    // swap arr[j+1] and arr[i]
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }

        return new long[] {comparacoes, trocas};
    }

    public static long[] selectionSort(int[] arr) {
        int n = arr.length;
        long comparacoes = 0;
        long trocas = 0;

        for (int i = 0; i < n-1; i++) {
            int min_idx = i;
            for (int j = i+1; j < n; j++) {
                comparacoes++;
                if (arr[j] < arr[min_idx]) {
                    min_idx = j;
                }
            }
            trocas++;
            // swap arr[min_idx] and arr[i]
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }

        return new long[] {comparacoes, trocas};
    }

    private static long calcularMax(long[] valores) {
        long max = Long.MIN_VALUE;
        for (long valor : valores) {
            if (valor > max) {
                max = valor;
            }
        }
        return max;
    }

    private static long calcularMin(long[] valores) {
        long min = Long.MAX_VALUE;
        for (long valor : valores) {
            if (valor < min) {
                min = valor;
            }
        }
        return min;
    }

    private static long calcularMedia(long[] valores) {
        long soma = 0;
        for (long valor : valores) {
            soma += valor;
        }
        return soma / valores.length;
    }
}