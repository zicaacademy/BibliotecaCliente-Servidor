package Classes;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    private static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) throws IOException, ClassNotFoundException{
        Socket socket = new Socket("127.0.0.1", 5000);

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream());

        try{
            for (int i = 0; i < 4; i++) {
                System.out.println(in.readLine());
            }
            String opcao = entrada.next();
            out.println(opcao);
            if (opcao == "1") {
                objectOutput.writeObject(cadastroLivro());
            }else if (opcao == "2" || opcao == "3") {
                objectOutput.writeObject(alugarOuDevolverLivro());
            }

            System.out.println("Resposta do servidor: " + in.readLine());

            in.close();
            out.close();
            objectOutput.close();
            socket.close();
        }catch(IOException e){
            e.printStackTrace();
        }



    }

    private static Livros cadastroLivro(){
        System.out.println("Digite o titulo do livro");
        String titulo = entrada.next();
        System.out.println("Digite o autor do livro");
        String autor = entrada.next();
        System.out.println("Digite o genero do livro");
        String genero = entrada.next();
        System.out.println("Digite o numero de exemplares do livro");
        int numExemplares = entrada.nextInt();

        return new Livros(titulo, autor, genero, numExemplares);
    };

    private static Livros alugarOuDevolverLivro(){
        System.out.println("Digite o titulo do livro");
        String titulo = entrada.next();

        return new Livros(titulo,  null, null, 0);
    }


}
