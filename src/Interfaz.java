import javax.swing.*;
import java.awt.*;

public class Interfaz extends JFrame {
    public static JButton sendButton;
    public static JTextField editor;
    public static JTextArea messageArea;
    JFrame frameMenu = new JFrame();

    public Interfaz() {

        messageArea = new JTextArea();
        messageArea.setBounds(10, 10, 390, 265);
        messageArea.setToolTipText("Aqui va el resultado");
        messageArea.setVisible(true);
        messageArea.setEnabled(false); // El area de mensajes del chat no se debe de poder editar
        messageArea.setLineWrap(true); // Las lineas se parten al llegar al ancho del textArea
        messageArea.setWrapStyleWord(true); // Las lineas se parten entre palabras (por los espacios blancos)
        messageArea.setBackground(Color.PINK);
        JScrollPane scrollMensajesChat = new JScrollPane(messageArea);


        editor = new JTextField(""); //Incilaizamos el editorPane
        editor.setBounds(10, 300, 390, 30);
        editor.setEditable(true); //Habilitamos la edicion
        /*editor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });*/


        sendButton = new JButton("Enviar");
        sendButton.setBounds(405, 300, 75, 30);
        sendButton.getName();
        sendButton.setToolTipText("Envia el mensaje");
       /* btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });*/

        /*
         * Asignamos titulo a la ventana
         * */
        frameMenu.setTitle("ClientChat");
        /*
         * creamos un panel y le añadimos los botones
         * */
        JPanel panelMenu = new JPanel();
        panelMenu.setOpaque(false);
        panelMenu.setLayout(null);
        panelMenu.setBounds(0, 0, 495, 400);
        panelMenu.add(sendButton);
        panelMenu.add(editor);
        panelMenu.add(messageArea);
        panelMenu.add(scrollMensajesChat);

        /*
         * Dentro de la venatana de inicio de menu colocamos los botones
         * a final de pagina.
         * */
        frameMenu.add(panelMenu);
        /*
         *  Asignamos tamaño de la ventana
         * */
        frameMenu.setSize(495, 400);
        /*
         *  Centra la ventana al medio de la pantalla
         * */
        frameMenu.setLocationRelativeTo(null);
        frameMenu.setVisible(true);
        frameMenu.setResizable(false);
        frameMenu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

}