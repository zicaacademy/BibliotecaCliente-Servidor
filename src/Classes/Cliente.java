package Classes;

import java.io.*;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        Socket socket = new Socket("127.0.0.1", 5000);

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream());

        try{
            for (int i = 0; i < 4; i++) {
                System.out.println(in.readLine());
            }
        }catch(IOException e){
            e.printStackTrace();
        }



    }

}
