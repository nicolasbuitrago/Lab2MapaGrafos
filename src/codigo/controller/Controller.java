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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author nicolasbuitrago
 */
public class Controller {
    
    private LocalTime horaActual, horaLlegada;
    private Grafo grafo;
    private Ventana ventana;
    private Mapa mapa;
    private ControlGraphics graphics;

    public Controller() {
        this.grafo= new Grafo();
        getGrafo(); //Obtiene el grafo del archivo Grafo.txt
        this.ventana = new Ventana(this);
        this.graphics = new ControlGraphics(this.grafo,this.mapa);
//        this.graphics.paintMapa(); //Dibuja el grafo sobre el mapa
//        grafo.adyacencia();
    }

    public Mapa getMapa() {
        return mapa;
    }

    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
        listener();
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
                if(c.length==3){
                    grafo.addNodo(new Nodo(Integer.parseInt(c[0]),Integer.parseInt(c[1]),Integer.parseInt(c[2])));
                }else{
                    Nodo ni = grafo.buscarNodo(toInt(c[0]),toInt(c[1])), nf = grafo.buscarNodo(toInt(c[2]),toInt(c[3]));
                    grafo.addArco(new Arco(ni,nf,toInt(c[4])));
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
    
    public void nodo(){graphics.isNodo=!graphics.isNodo;};   //VER  QUE LA CANTIDAD DE NODOS COINCIDA CON EL NAME DEL ULTIMO NODO
    
    private void listener(){
        this.mapa.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                graphics.crearGrafoMapa(e.getX(),e.getY());    //Metodo para crear un grafo a partir de los clicks en el panel
//                graphics.getPoints(e.getX(),e.getY());  //             System.out.println("x = "+e.getX()+", "+e.getY());
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

    public void setGrafo() {
        FileWriter fw = null;
        try {
            fw = new FileWriter("Grafo.txt", false);
            PrintWriter pw = new PrintWriter(fw);
            String linea = "Vertice = x,y         Arco = x1,y1,x2,y2,distancia";
            pw.print(linea);
            for (Nodo nodo : grafo.getNodos()) {
                linea = nodo.toString();
                pw.println();
                pw.print(linea);
            }
            for (Arco arco : grafo.getArcos()) {
                linea = arco.toString();
                pw.println();
                pw.print(linea);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error al guardar Grafo.txt ");
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    public void restablecer(){
        this.graphics.restablecer();
//        this.graphics.paintMapa(); //Pinta el grafo sobre el mapa
    }

}

