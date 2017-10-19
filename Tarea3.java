package pdf_1;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JInternalFrame;


public class Tarea3 extends JFrame {

    private final JDesktopPane theDesktop;
    int n;

    public Tarea3() {
        super("convertidor de pdf");

        JMenuBar bar = new JMenuBar();
        JMenu addMenu = new JMenu("agregar");
        JMenuItem newFrame = new JMenuItem("nueva ventana");

        addMenu.add(newFrame);
        bar.add(addMenu);
        setJMenuBar(bar);

        theDesktop = new JDesktopPane();
        add(theDesktop);

        newFrame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

                JInternalFrame frame = new JInternalFrame(
                        "nuevo" + "(" + n + ")", true, true, true, true);

                Pdf panel = new Pdf();
                frame.add(panel);
                frame.pack();
                frame.setSize(450, 300);

                theDesktop.add(frame);
                frame.setVisible(true);
                n++;
            }
        });
    }
}

