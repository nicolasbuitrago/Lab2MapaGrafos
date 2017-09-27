/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Estudiante
 */
public class Mapa extends JPanel{

    private BufferedImage img = null;

    public Mapa() {
        try {
            img = ImageIO.read(getClass().getResource("/Imagenes/mapa.png")); // la carga en una BufferedReader
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setPreferredSize(new Dimension(484, 409));

        // creamos una instancia graphics desde la imagen para pintar sobre ella
        Graphics2D pint = img.createGraphics();
        pint.setColor(Color.GREEN);
        pint.fillRect(200, 200, 100, 100);
        pint.dispose();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, null); // dibuja la imagen al iniciar la aplicacion
    }
}
