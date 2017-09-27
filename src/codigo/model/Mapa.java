/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo.model;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Estudiante
 */
public class Mapa extends JPanel {

    public Mapa() {
        this.setSize(300, 400); //se selecciona el tamaño del panel
    }

//Se crea un método cuyo parámetro debe ser un objeto Graphics
    public void paint(Graphics grafico) {
        Dimension height = getSize();

//Se selecciona la imagen que tenemos en el paquete de la //ruta del programa
        ImageIcon Img = new ImageIcon(getClass().getResource("/Imagenes/mapa.png"));

//se dibuja la imagen que tenemos en el paquete Images //dentro de un panel
        grafico.drawImage(Img.getImage(), 0, 0, null);

        setOpaque(false);
        super.paintComponent(grafico);
    }
    
    
//    private BufferedImage img = null;
//
//    public Mapa() {
//        try {
//            img = ImageIO.read(getClass().getResource("/Imagenes/mapa.png")); // la carga en una BufferedReader
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
////        this.setPreferredSize(new Dimension(484, 409));
//
//        // creamos una instancia graphics desde la imagen para pintar sobre ella
//        Graphics2D pint = img.createGraphics();
//        pint.setColor(Color.GREEN);
//        pint.fillRect(200, 200, 100, 100);
//        pint.dispose();
//    }
//
//    @Override
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        g.drawImage(img, 0, 0, null); // dibuja la imagen al iniciar la aplicacion
//    }
}
