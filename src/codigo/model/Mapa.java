/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo.model;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Es la clase que hereda de panel en la cual  se dibuja la imagen del mapa y sobre la 
 * cual se trabajan todos los graficos que dibujan el grafo y la ruta
 * @author nicolasbuitrago
 */
public class Mapa extends JPanel {
    
    private BufferedImage imagen = null;

    public Mapa(JPanel padre) {
        try {
            imagen = ImageIO.read(getClass().getResource("/Imagenes/mapa.png")); // la carga en una BufferedReader
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        this.setSize(padre.getWidth(), padre.getHeight()); //se selecciona el tamaño del panel
    }

    /**
     * Metodo que pinta el panel que contiene el mapa
     * @param grafico 
     */
    @Override
    public void paint(Graphics grafico) {
//        Dimension height = getSize();
//Se selecciona la imagen que tenemos en el paquete de la //ruta del programa
//        ImageIcon Img = new ImageIcon(getClass().getResource("/Imagenes/mapa.png"));

//se dibuja la imagen que tenemos en el paquete Images //dentro de un panel
//        grafico.drawImage(Img.getImage(), 0, 0, null);

//        Graphics2D pint = img.createGraphics();
//        pint.setColor(Color.GREEN);
//        pint.fillRect(200, 200, 100, 100);
//        pint.dispose();
        
//        grafico.drawImage(imagen, 0, 0, height.width, height.height, null);
        grafico.drawImage(imagen, 0, 0, null);

        setOpaque(false);
        super.paintComponent(grafico);
    }
    
    /**
     * Metodo que se encarga de dibujar el mapa de nuevo sobre el panel sin todos los nodos y sin rutas en el
     */
    public void reset(){
        try {
            imagen = ImageIO.read(getClass().getResource("/Imagenes/mapa.png")); // la carga en una BufferedReader
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        this.repaint();
    }
     
    public BufferedImage getImagen() {
        return imagen;
    }
    
    public Graphics2D getGraphics2D() {
        return imagen.createGraphics();
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
//        setOpaque(false);
//        super.paintComponent(g);
//        g.drawImage(img, 0, 0, null); // dibuja la imagen al iniciar la aplicacion
//    }

    
}
