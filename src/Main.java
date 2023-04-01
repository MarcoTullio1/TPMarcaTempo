import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        int[] vetorPrincipal =  geraVetor(100000);
        int[] vetorCopia =  vetorPrincipal.clone();



       // Arrays.stream(geraVetor(100000)).forEach(z-> System.out.println(z));
    }

    static void trocar(int[] dados, int pos1, int pos2) {
        int aux = dados[pos1];
        dados[pos1] = dados[pos2];
        dados[pos2] = aux;
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

}