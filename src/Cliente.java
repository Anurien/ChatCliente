import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;

public class Cliente extends JFrame implements ActionListener {
    private final JTextField campoMensaje = new JTextField(30);
    private final JTextArea areaChat = new JTextArea(10, 30);
    private final String nombreUsuario;
    private DataInputStream entrada;
    private DataOutputStream salida;
    Socket socket;

    public Cliente(String nombreUsuario) {
        super(nombreUsuario);

        this.nombreUsuario = nombreUsuario;

        // Crear la interfaz gráfica del cliente
        areaChat.setEnabled(false);
        areaChat.setLineWrap(true);
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelPrincipal.add(new JScrollPane(areaChat), BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.add(campoMensaje, BorderLayout.CENTER);
        JButton botonEnviar = new JButton("Enviar");
        panelInferior.add(botonEnviar, BorderLayout.EAST);
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

        add(panelPrincipal);

        botonEnviar.addActionListener(this);
        campoMensaje.addActionListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setVisible(true);
    }

    public void conectar(String host, int puerto) throws IOException {
        // Conectar al servidor
        socket = new Socket(host, puerto);
        entrada = new DataInputStream(socket.getInputStream());
        salida = new DataOutputStream(socket.getOutputStream());

        // Enviar el nombre de usuario al servidor
        salida.writeUTF(nombreUsuario);

        // Crear un nuevo hilo para manejar las entradas del servidor
        Thread hiloServidor = new Thread(() -> {
            try {
                while (true) {
                    String mensaje = entrada.readUTF();
                    areaChat.append(mensaje);
                }
            } catch (IOException e) {
                //e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Conexión rehusada, error de Entrada/Salida,\n"
                        + "puede que haya ingresado una ip o un puerto\n"
                        + "incorrecto, o que el servidor no este corriendo.\n"
                        + "Esta aplicación se cerrará.");
                System.exit(0);
            }
        });

        hiloServidor.start();
    }

    /**
     *Método del evento donde se envia el mensaje del cliente al servidor
     * @param e el evento
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Enviar el mensaje al servidor
            String mensaje = campoMensaje.getText();
            areaChat.append(nombreUsuario + ": " + mensaje + "\n");
            if(mensaje.equals("/bye")){
                salida.writeUTF("/bye");
                socket.close();

            }else{
                salida.writeUTF(mensaje + "\n");
                // Borrar el campo de texto después de enviar el mensaje
                campoMensaje.setText("");
            }

        } catch (IOException ex) {
            //ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Obtener el nombre de usuario del usuario
        String nombreUsuario = JOptionPane.showInputDialog("Introduce tu nombre de usuario:");

        // Crear una nueva instancia de Cliente
        Cliente cliente = new Cliente(nombreUsuario);

        // Conectar al servidor
        try {
            cliente.conectar("10.0.9.14", 5555);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




