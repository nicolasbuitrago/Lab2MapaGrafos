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
import codigo.model.Tiempo;
import codigo.view.Ventana;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JLabel;

/**
 *
 * @author nicolasbuitrago
 */
public class Controller {
    
    private Tiempo horaActual, horaLlegada;
    private Grafo grafo;
    private Ventana ventana;
    private Mapa mapa;
    private ControlGraphics graphics;

    public Controller() {
        this.grafo= new Grafo();
        getGrafo(); //Obtiene el grafo del archivo Grafo.txt
        this.ventana = new Ventana(this);
        this.graphics = new ControlGraphics(this.grafo,this.mapa);
//        this.graphics.paintGrafo(); //Dibuja el grafo sobre el mapa
//        grafo.adyacencia();
    }

    public Mapa getMapa() {
        return mapa;
    }

    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
        listener();
    }

    private int toInt(String s){
        return Integer.parseInt(s);
    }
    
    /**
     * Metodo que se encarga de crear las instancias de horaActual y horaLlegada y darles sus respectivos labels
     * @param horaActual
     * @param horaLlegada 
     */
    public void setHoras(JLabel horaActual, JLabel horaLlegada){
        this.horaActual = new Tiempo(horaActual);
        this.horaActual.start();
        this.horaLlegada = new Tiempo(horaLlegada);
    }
    
    /**
     * Metodo que se encaraga de darle la hora de llegada al label en ventana y establecerle su hora
     * ademas establece en el label tiempo de la ventana el tiempo que se supone que tardara en llegar asu destino
     * @param minutos 
     */
    public void setHoraLlegada(double minutos){  
        horaLlegada.start(minutos);
        ventana.setTiempo((int)minutos);
    }
    
    /**
     * Metodo de obtener el mapa de Grafo.txt y guardarlo en los diferentes ArrayList que se encuntran en la clase Grafo
     * para asi armar el grafo en si
     */
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
    
    /**
     * Funcion que se encarga de obtener los minutos que se le deben sumar a la hora actual para obtener la hora de llegada
     * @param distancia es la distancia recorrida en la ruat seleccionada por el usuario
     * @return los minutos que se demora en recorrer la ruta escogida
     */
    private double getTiempo(int distancia){//5km/h   5000m/h    83.33m/min
        System.out.println("Tiempo = "+distancia/83.33);
        return Math.ceil(distancia/83.33);
    }
            
    public void nodo(){graphics.isNodo=!graphics.isNodo;};   //VER  QUE LA CANTIDAD DE NODOS COINCIDA CON EL NAME DEL ULTIMO NODO
    
    /**
     * Funcion que se encaga de añadir el MouseListener al panel que contiene el mapa
     */
    private void listener(){
        this.mapa.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
//                graphics.crearGrafoMapa(e.getX(),e.getY());    //Metodo para crear un grafo a partir de los clicks en el panel
                if(graphics.getPoints(e.getX(),e.getY())){//   System.out.println(grafo.buscarNodo(e.getX(),e.getY()));
                    int dist = grafo.getMinDistancia();
                    System.out.println("Distancia = "+dist);
                    ventana.setDistancia(Integer.toString(dist));
                    setHoraLlegada(getTiempo(dist));
                    ventana.visible();
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
    
    /**
     * Funcion que se encarga de guardar el grafo en el txt llamado Grafo.txt
     */
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
    
    /**
     * Es la funcion llamada por ventana que se encarga de reestablecer los valores de la ventana como la imagen del mapa entre otros
     */
    public void restablecer(){
        this.graphics.restablecer();
//        this.graphics.paintMapa(); //Pinta el grafo sobre el mapa
    }

}

