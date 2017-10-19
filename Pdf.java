/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdf_1;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.StringTokenizer;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

class Pdf extends JPanel implements ActionListener {

    String nombre;
    String contenido;
    String ruta;
//    String[] etiquetas = {"{T}", "{P}", "{I}", "{C}", "{S}", "{b}", "{i}"
//            , "{u}", "{n}", "{#T}", "{#P}", "{#I}", "{#C}", "{#S}", "{#b}", "{#i}", "{#u}", "{#n}"};

    private final JButton abrir, guardar;
    JFileChooser abrirArchivo, save;
    private JTextPane textPane;

    public Pdf() {
        setLayout(new BorderLayout());
        textPane = new JTextPane();
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(textPane);
        add(scroll, BorderLayout.CENTER);

        abrir = new JButton("Abrir");
        abrir.addActionListener(this);
        guardar = new JButton("Guardar");
        guardar.addActionListener(this);

        add(abrir, BorderLayout.NORTH);
        add(guardar, BorderLayout.SOUTH);

    }

    public void actionPerformed(ActionEvent e) {
        

        JButton btn = (JButton) e.getSource();
        if (btn.getText().equals("Abrir")) {
            if (abrirArchivo == null) {
                abrirArchivo = new JFileChooser();
            }
            abrirArchivo.setFileSelectionMode(JFileChooser.FILES_ONLY);

            int seleccion = abrirArchivo.showOpenDialog(this);

            if (seleccion == JFileChooser.APPROVE_OPTION) {
                File f = abrirArchivo.getSelectedFile();
                try {
                    nombre = f.getName();
                    ruta = f.getAbsolutePath();
                    contenido = getArchivo(ruta);
                    textPane.setText(contenido);
                   
                } catch (Exception exp) {
                }
            }
        }
        if (btn.getText().equals("Guardar")) {

            StringBuilder texto = new StringBuilder();
            texto.append(textPane.getText());
            if (texto.length() != 0) {
                ConvertirPDF(texto);
            } else {
                JOptionPane.showMessageDialog(null, "El archivo no tiene contenido");
            }

        }
    }

    public String getArchivo(String ruta) {
        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader(ruta);
            br = new BufferedReader(fr);

            String linea;

            while ((linea = br.readLine()) != null) {
                contenido += linea + "\n";
            }
        } catch (Exception e) {
        } finally {
            try {
                br.close();
            } catch (Exception ex) {
            }
        }
        return contenido;
    }

    private void ConvertirPDF(StringBuilder texto) {

        try {

            FileOutputStream archivo = new FileOutputStream(ruta + ".pdf");
            Document documento = new Document(PageSize.LETTER, 20, 20, 20, 20);
            PdfWriter.getInstance(documento, archivo);

            documento.open();
            //Anexamos el texto a un objeto Pharagraph
            Paragraph parrafo = new Paragraph(texto.toString(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE));
            //Escribimos sobre el
            documento.add(parrafo);
            documento.close();
            JOptionPane.showMessageDialog(null, "PDF Creado con exito.");
            textPane.setText(null);

        } catch (FileNotFoundException | DocumentException fe) {
            System.out.println("Error...");
        }

    }

}
