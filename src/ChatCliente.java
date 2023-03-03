import java.io.*;
import java.net.*;

public class ChatCliente {
    public static void main(String[] args) {
        try {
            // Crear un socket y conectar al servidor
            Socket socket = new Socket("10.0.9.13", 5555);

            // Configurar los streams de entrada y salida
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            // Nombre de usuario
            System.out.print("Introduce tu nombre: ");
            String name = stdIn.readLine();
            out.println(name);

            // Envío y recepción de mensajes
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println(in.readLine());
            }

            // Cierre del socket
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}