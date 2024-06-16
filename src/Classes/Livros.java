package Classes;

import java.io.Serializable;

public class Livros implements Serializable {

    private String titulo;
    private String autor;
    private String genero;
    private int numeroDeExemplares;

    public Livros(String titulo, String autor, String genero, int numeroDeExemplares) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.numeroDeExemplares = numeroDeExemplares;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getNumeroDeExemplares() {
        return numeroDeExemplares;
    }

    public void setNumeroDeExemplares(int numeroDeExemplares) {
        this.numeroDeExemplares = numeroDeExemplares;
    }
}

