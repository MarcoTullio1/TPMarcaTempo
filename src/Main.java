import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {

        boolean parte1 = false;

        if(parte1)
        {
            //region parte 1

            int numExecucoes = 50;
            int tamanhoVetor = 100000;
            long[] compBubble = new long[numExecucoes];
            long[] trocasBubble = new long[numExecucoes];
            long[] compSelection = new long[numExecucoes];
            long[] trocasSelection = new long[numExecucoes];

            try {
                FileWriter fw = new FileWriter("parte 1.txt");
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
                System.out.println("Resultados salvos no arquivo parte 1.txt");
            } catch (IOException e) {
                System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
            }
//endregion
        }
       else {
           //region parte2
            try {
                FileWriter fw = new FileWriter("parte 2.txt");
                BufferedWriter bw = new BufferedWriter(fw);

                bw.write("Resultados da busca sequencial \n\n");

                for (int j = 0; j < 1000000; j++) {
                    System.out.println(j);
                    int[] vetor = geraVetor(10000);
                    int numero = gerarNumeroAleatorio();

                    int posicao = 10001;
                    for (int i = 0; i < vetor.length; i++) {
                        if (vetor[i] == numero) {
                            posicao = i;
                            break;
                        }
                    }

                    bw.write("\nNúmero sorteado: " + numero);
                    bw.write("\nPosição encontrada: " + posicao);
                }

                bw.close();
                fw.close();
                System.out.println("Resultados salvos no arquivo parte 2.txt");

                int[] contadores = new int[11000];

                for (int i = 0; i < 1000000; i++) {
                    int numero = gerarNumeroAleatorio() % contadores.length;
                    contadores[numero]++;
                }

                fw = new FileWriter("parte 3.txt");
                bw = new BufferedWriter(fw);

                bw.write("\nResultados da contagem dos números sorteados\n\n");

                int soma = 0;
                for (int i = 0; i < contadores.length / 500; i++) {
                    for (int j = i * 500; j < Math.min((i + 1) * 500, contadores.length); j++) {
                        soma += contadores[j];
                    }
                    bw.write("\nSoma dos contadores de " + (i * 500 + 1) + " a " + Math.min((i + 1) * 500, contadores.length) + ": " + soma + "\n");
                    soma = 0; // reinicializa a variável soma para a próxima iteração
                }

                bw.close();
                fw.close();
                System.out.println("Resultados salvos no arquivo parte 3.txt");

            } catch (IOException e) {
                System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
            }

            }
    }


    public static int gerarNumeroAleatorio() {
        Random random = new Random();
        return random.nextInt(11000) + 1;
    }

    public static int buscarSequencial(int[] vetor, int numero) {
        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i] == numero) {
                return i;
            }
        }
        return 10001; // caso o número não seja encontrado, retorna a posição 10.001
    }


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