package Classes;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private static Biblioteca biblioteca;

    public Servidor(Biblioteca biblioteca) throws IOException {
        this.biblioteca = new Biblioteca();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ServerSocket servidor = new ServerSocket(5000);
        System.out.println("Servidor conectado na porta 5000");
        System.out.println("Aguardando clientes de servidor...");


        while (true) {
            try(Socket cliente = servidor.accept();
                PrintWriter out = new PrintWriter(cliente.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                ObjectInputStream objectInput = new ObjectInputStream(cliente.getInputStream())) {
                out.println("Menu de opções:");
                out.println("1. Cadastrar Livro");
                out.println("2. Alugar Livro");
                out.println("3. Devolver Livro");
                out.println("4. Listar Livros");

                String option = in.readLine();
                System.out.println("Opção recebida: " + option);

                Livros livro = null;
                if (!option.equals("4")) {
                    livro = (Livros) objectInput.readObject();
                    System.out.println("Livro recebido: " + livro);
                }

                String resposta = processarOpcao(option, livro);
                out.println(resposta);

            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }


        }


    }
    private static String processarOpcao(String opcao, Livros livro){
        switch (opcao) {
            case "1":
                return "Livro cadastrado com sucesso!";
            case "2":
                return "Livro alugado com sucesso!";
            case "3":
                return "Livro devolvido com sucesso!";
            case "4":
                return "Lista de Livros" + biblioteca.listarLivros();
            default:
                return "Opção invalida";
        }
    }
}


