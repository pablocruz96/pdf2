package pdf_1;

/*
Pablo Antonio Cruz Meza
Amado Ramos Zuñiga}
Maria Josefina olvera rivera

*/

import javax.swing.JFrame;
import javax.swing.UIManager;

public class TareaDemo {

    public static void main(String[] args) {
        try {
            JFrame.setDefaultLookAndFeelDecorated(true);
            UIManager.setLookAndFeel(new com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel());
        } catch (Exception e) {
        }
        new Tarea3();
        Tarea3 desktopFrame = new Tarea3();
        desktopFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        desktopFrame.setSize(600, 480);
        desktopFrame.setVisible(true);
    }
}
