import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;

public class BuscaAleatoria<T> implements IBuscador<T> {

    private long comparacoes;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private T[] dados;
    private Random gerador;

    public BuscaAleatoria(T[] dados) {
        this.dados = dados;
        this.gerador = new Random();
    }

    @Override
    public long getComparacoes() {
        return comparacoes;
    }

    @Override
    public double getTempo() {
        if (inicio == null)
            throw new IllegalStateException("Não foi feita nenhuma busca.");
        return Duration.between(inicio, fim).toNanos();
    }

    @Override
    public T buscar(T dado) {
        comparacoes = 0;
        inicio = LocalDateTime.now();

        int tamanho = dados.length;
        boolean[] visitados = new boolean[tamanho];
        int testados = 0;

        while (testados < tamanho) {
            int pos = gerador.nextInt(tamanho);
            if (visitados[pos]) {
                continue;
            }
            visitados[pos] = true;
            comparacoes++;
            if (dados[pos].equals(dado)) {
                fim = LocalDateTime.now();
                return dados[pos];
            }
            testados++;
        }

        fim = LocalDateTime.now();
        return null;
    }
}
