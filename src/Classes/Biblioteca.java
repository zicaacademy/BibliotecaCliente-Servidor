package Classes;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Livros> livros;
    private static final String json = "livros.json";
    private ObjectMapper objectMapper;

    public Biblioteca() throws IOException {
        objectMapper = new ObjectMapper();
        carregarLivros();
    }

    private void carregarLivros() throws IOException {
        File file = new File(json);
        if (file.exists()) {
            livros = objectMapper.readValue(file, new TypeReference<List<Livros>>() {});
        } else {
            livros = new ArrayList<>();
        }
    }

    private void salvarLivros() throws IOException {
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(json), livros);
    }

    public void cadastrarLivro(Livros livro) throws IOException {
        livros.add(livro);
        salvarLivros();
    }

    public void alugarLivro(String titulo) throws IOException {

        for (Livros livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                livros.remove(livro);
                livro.setNumeroDeExemplares(livro.getNumeroDeExemplares() -1);
                livros.add(livro);
                break;
            }
        }
        salvarLivros();
    }

    public void devolverLivro(String titulo) throws IOException {
        for (Livros livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                livros.remove(livro);
                livro.setNumeroDeExemplares(livro.getNumeroDeExemplares() + 1);
                livros.add(livro);
                break;
            }
        }
        salvarLivros();
    }

    public List<Livros> listarLivros() {
        return livros;
    }
}
