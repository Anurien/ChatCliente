import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interfaz extends JFrame {
    public static JButton btnEnviar;
    public static JTextField editor;
    public static JTextArea etiqueta;
    JFrame frameMenu = new JFrame();

    public Interfaz() {

        etiqueta = new JTextArea();
        etiqueta.setBounds(10, 10, 468, 285);
        etiqueta.setToolTipText("Aqui va el resultado");
        etiqueta.setVisible(true);
        etiqueta.setEnabled(false); // El area de mensajes del chat no se debe de poder editar
        etiqueta.setLineWrap(true); // Las lineas se parten al llegar al ancho del textArea
        etiqueta.setWrapStyleWord(true); // Las lineas se parten entre palabras (por los espacios blancos)
        etiqueta.setBackground(Color.PINK);
        JScrollPane scrollMensajesChat = new JScrollPane(etiqueta);


        editor = new JTextField(""); //Incilaizamos el editorPane
        editor.setBounds(10, 300, 390, 30);
        editor.setEditable(true); //Habilitamos la edicion
        /*editor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });*/


        btnEnviar = new JButton("Enviar");
        btnEnviar.setBounds(405, 300, 75, 30);
        btnEnviar.getName();
        btnEnviar.setToolTipText("Calcula cuantos libros te quedan para alcanzar al maestro Perez Reverte!!");
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
        panelMenu.add(btnEnviar);
        panelMenu.add(editor);
        panelMenu.add(etiqueta);
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