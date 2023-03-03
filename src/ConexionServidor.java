
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Esta clase gestiona el envio de datos entre el cliente y el servidor.
 */
public class ConexionServidor implements ActionListener {

    private Socket socket; 
    private JTextField mensaje;
    private String usuario;
    private DataOutputStream salidaDatos;
    
    public ConexionServidor(Socket socket, JTextField mensaje, String usuario) {
        this.socket = socket;
        this.mensaje = mensaje;
        this.usuario = usuario;
        try {
            this.salidaDatos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            ex.getMessage();
        } catch (NullPointerException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            salidaDatos.writeUTF(usuario + ": " + mensaje.getText() );
            mensaje.setText("");
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

}