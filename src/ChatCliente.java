import javax.swing.*;
import java.io.*;
import java.net.*;

public class ChatCliente extends JFrame {
    private int puerto;
    private String host;
    private String usuario;
    private Socket socket;

    public ChatCliente() {
        // Ventana de configuracion inicial
        VentanaConfiguracion vc = new VentanaConfiguracion(this);
        host = vc.getHost();
        puerto = vc.getPuerto();
        usuario = vc.getUsuario();
        // Accion para el boton enviar
        Interfaz.btnEnviar.addActionListener(new ConexionServidor(socket, Interfaz.editor, usuario));

    }

    /**
     * Recibe los mensajes del chat reenviados por el servidor
     */
    public void recibirMensajesServidor() throws IOException {
        // Crear un socket y conectar al servidor
        socket = new Socket(host, puerto);
        // Obtiene el flujo de entrada del socket
        DataInputStream entradaDatos = null;
        String mensaje;
        try {
            entradaDatos = new DataInputStream(socket.getInputStream());
        } catch (IOException | NullPointerException ex) {
            throw new RuntimeException(ex);
        }

        // Bucle infinito que recibe mensajes del servidor
        boolean conectado = true;
        while (conectado) {
            try {
                mensaje = entradaDatos.readUTF();
                Interfaz.editor.setText(mensaje + System.lineSeparator());

            } catch (IOException ex) {
                ex.getMessage();
                conectado = false;
            } catch (NullPointerException ex) {
                conectado = false;
            }
        }
    }

    public static void main(String[] args) {
        try {
            ChatCliente c = new ChatCliente();
            new Interfaz();
            c.recibirMensajesServidor();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}