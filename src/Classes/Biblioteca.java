package Classes;
import com.fasterxml.jackson.databind.JsonNode;
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
            System.out.println("Carregando livros...");
            JsonNode rootNode = objectMapper.readTree(file);
            JsonNode livrosNode = rootNode.path("livros");
            livros = objectMapper.convertValue(livrosNode, new TypeReference<List<Livros>>() {});
            System.out.println("Livros carregados com sucesso!" + livros);
        } else {
            System.out.println("Não foi possível carregar livros");
            livros = new ArrayList<>();
        }
    }

    private void salvarLivros() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(json), new BibliotecaData(livros));
    }

    public void cadastrarLivro(Livros livro) throws IOException {
        livros.add(livro);
        System.out.println("Livros castrados: " + livros);
        salvarLivros();
    }

    public void alugarLivro(String titulo) throws IOException {
        for (Livros livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                livros.remove(livro);
                livro.setNumeroDeExemplares(livro.getNumeroDeExemplares() - 1);
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

    static class BibliotecaData {
        public List<Livros> livros;

        public BibliotecaData(List<Livros> livros) {
            this.livros = livros;
        }
    }
}
