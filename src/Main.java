import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) {
        conexion();
    }

    public static void conexion() {
        try {
            System.out.println("Obteniendo factoria de sockets cliente");

            SSLSocketFactory SocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();

            System.out.println("Creando socket cliente");


            SSLSocket clientSocket = (SSLSocket) SocketFactory.createSocket();
            clientSocket.setSoLinger(true, 0);
            clientSocket.setReuseAddress(true);

            System.out.println("Estableciendo la conexion");

            InetSocketAddress addr = new InetSocketAddress("localhost", 5555);

            clientSocket.bind(addr);
            clientSocket.startHandshake();
            OutputStream os = clientSocket.getOutputStream();

            System.out.println("Enviando mensaje");

            String mensaje = "mensaje desde el cliente transmitido por SSL";
            os.write(mensaje.getBytes());


            System.out.println("Mensaje enviado");

            System.out.println("Cerrando el socket cliente");

            clientSocket.close();

            System.out.println("Terminado");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}