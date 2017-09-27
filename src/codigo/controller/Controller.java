/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo.controller;

import codigo.model.Arco;
import codigo.model.Grafo;
import codigo.model.Nodo;
import codigo.view.Ventana;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Estudiante
 */
public class Controller {
    
    LocalTime horaActual, horaLlegada;
    Grafo grafo;
    Ventana ventana;

    public Controller() {
        grafo= new Grafo();
        getGrafo();
        ventana = new Ventana(this);
        
    }
    
    public String horaActual(){
        horaActual = LocalTime.now();
        String hora = fromHoraMilitar(horaActual.format(DateTimeFormatter.ofPattern("HH:mm")));
        return hora;
    }
    
    public String fromHoraMilitar(String hora){
        String[] time = hora.split(":");
        int h = Integer.parseInt(time[0]);
        String m = " a.m.";
        if(h>12){
            h-=12;
            m = " p.m.";
        }
        return Integer.toString(h)+":"+time[1]+m;
    }

    private int toInt(String s){
        return Integer.parseInt(s);
    }
    
    private void getGrafo(){
        FileReader frg =null;
        try {
            File Grafo = new File("Grafo.txt");
            frg = new FileReader(Grafo);
            BufferedReader gra = new BufferedReader(frg);
            String v = gra.readLine();
            v = gra.readLine();
            while (v != null) {                
                String[] c = v.split(",");//System.out.println("c.length = "+c.length);
                if(c.length==2){
                    grafo.newNodo(new Nodo(Integer.parseInt(c[0]),Integer.parseInt(c[1])));
                }else{
                    grafo.newArco(new Arco(toInt(c[0]),toInt(c[1]),toInt(c[2]),toInt(c[3]),toInt(c[4]),toInt(c[5]),toInt(c[6])));
                }
                v = gra.readLine();
            }
        } catch (Exception e) {
            System.out.println("Error Controller getGrafo =    "+e.getMessage());
        }finally{
            try {
                if (frg != null) {
                    frg.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void paintMapa(JPanel panel) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(getClass().getResource("/Imagenes/mapa.png")); // la carga en una BufferedReader
        } catch (IOException e) {
            e.printStackTrace();
        }
        // creamos una instancia graphics desde la imagen para pintar sobre ella
        Graphics2D pint = img.createGraphics();
        pint.setColor(Color.GREEN);
        pint.fillRect(200, 200, 100, 100);
        pint.dispose();
        Graphics g = panel.getGraphics();
//        panel.paintComponent(g);
        g.drawImage(img, 0, 0, null);
//        panel.repaint();
    }
    
    
}
