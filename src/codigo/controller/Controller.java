/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo.controller;

import codigo.model.Arco;
import codigo.model.Grafo;
import codigo.model.Mapa;
import codigo.model.Nodo;
import codigo.view.Ventana;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
    
    private LocalTime horaActual, horaLlegada;
    private Grafo grafo;
    private Ventana ventana;
    private Mapa mapa;

    public Controller() {
        grafo= new Grafo();
        getGrafo();
        ventana = new Ventana(this);
    }

    public Mapa getMapa() {
        return mapa;
    }

    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
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

    public void paintMapa(int x, int y) {
        
        // creamos una instancia graphics desde la imagen para pintar sobre ella
        Graphics2D paint = mapa.getGraphics2D();
        paint.setColor(Color.RED);
        paint.drawString("Hola Mundo", x, y);
        paint.fillRect(200, 200, 100, 100);
        paint.dispose();
        
//        Graphics g = panel.getGraphics();
////        panel.paintComponent(g);
//        g.drawImage(img, 0, 0, null);
//        panel.repaint();
    }
    
    public void crearGrafoMapa(JPanel panel){
        panel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(rbNodo.isSelected()){
                    g.setColor(Color.BLACK);
                    g.fillOval(e.getX()-tamNodos/2, e.getY()-tamNodos/2, tamNodos, tamNodos);
                    nodos.add(new Nodo(cantNodos,e.getX()-tamNodos/2,e.getY()-tamNodos/2,Color.red));
                    g.setColor(Color.white);
                    g.drawString(Integer.toString(cantNodos),e.getX(), e.getY());
                    cantNodos++;
                }else{
                    if(nodoInicial==null){
                        nodoInicial = buscarNodo(e.getX(),e.getY());
                        if(nodoInicial!=null){
                            seleccionarNodo(nodoInicial,g,Color.YELLOW);
                        }
                    }else{
                        nodoFinal = buscarNodo(e.getX(),e.getY());
                        if(nodoFinal!=null){
                            seleccionarNodo(nodoFinal,g,Color.YELLOW);
                            if(nodoFinal.getName()!=nodoInicial.getName()){
                                g.setColor(Color.BLACK);
                                g.drawLine(nodoInicial.x+tamNodos/2, nodoInicial.y+tamNodos/2, nodoFinal.x+tamNodos/2, nodoFinal.y+tamNodos/2);
                                int dist = distancia(nodoInicial.x+tamNodos/2, nodoInicial.y+tamNodos/2, nodoFinal.x+tamNodos/2, nodoFinal.y+tamNodos/2);
                                arcos.add(new Arco(nodoInicial.getName(),nodoFinal.name,nodoInicial.x+tamNodos/2, nodoInicial.y+tamNodos/2, nodoFinal.x+tamNodos/2, nodoFinal.y+tamNodos/2,
                                dist));
                                 g.drawString(Integer.toString(distancia(nodoInicial.x+tamNodos/2, nodoInicial.y+tamNodos/2, nodoFinal.x+tamNodos/2, nodoFinal.y+tamNodos/2)),nodoInicial.x+dist/2,nodoInicial.y+5);
                            }else{
                                seleccionarNodo(nodoInicial,g,Color.BLACK);
                            }
                            seleccionarNodo(nodoInicial,g,Color.BLACK);
                            seleccionarNodo(nodoFinal,g,Color.BLACK);
                            nodoInicial = null;
                        }else{
                            seleccionarNodo(nodoInicial,g,Color.BLACK);
                            nodoInicial = null;
                        }
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                 
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                 
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                 
            }

            @Override
            public void mouseExited(MouseEvent e) {
                 
            }
        });
    }
    
}
