package Classes;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Livros> livros;
    private static final String FILE_PATH = "livros.json";
    private ObjectMapper objectMapper;

    public Biblioteca() throws IOException {
        objectMapper = new ObjectMapper();
        carregarLivros();
    }

    private void carregarLivros() throws IOException {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            livros = objectMapper.readValue(file, new TypeReference<List<Livros>>() {});
        } else {
            livros = new ArrayList<>();
        }
    }

    private void salvarLivros() throws IOException {
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), livros);
    }

    public void cadastrarLivro(Livros livro) throws IOException {
        livros.add(livro);
        salvarLivros();
    }

    public void alugarLivro(String titulo) throws IOException {
        // Lógica para alugar um livro (remover da lista, por exemplo)
        for (Livros livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                livros.remove(livro);
                break;
            }
        }
        salvarLivros();
    }

    public void devolverLivro(Livros livro) throws IOException {
        // Lógica para devolver um livro (adicionar de volta à lista, por exemplo)
        livros.add(livro);
        salvarLivros();
    }

    public List<Livros> listarLivros() {
        return livros;
    }
}
