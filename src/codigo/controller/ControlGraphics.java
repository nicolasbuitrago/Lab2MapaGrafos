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
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author nicolasbuitrago
 */
public class ControlGraphics {
    
    private Grafo grafo;
    private Mapa mapa;
    private final int TAM_NODOS = 20;
    Nodo nodoInicial = null, nodoFinal = null;
    public boolean isNodo = true, ruta = false;

    public ControlGraphics(Grafo grafo,Mapa mapa) {
        this.grafo = grafo;
        this.mapa = mapa;
    }
    
    /**
     * Metodo usado para crear el Grafo sbre el mapa
     * @param x valor de la coordenada en x
     * @param y valor de la coordenada en y
     */
    public void crearGrafoMapa(int x, int y) {
        Graphics2D g = this.mapa.getGraphics2D();
        g.setStroke(new BasicStroke(2));
        if (isNodo) {
            g.setColor(Color.BLACK);
            g.fillOval( x - TAM_NODOS / 2, y - TAM_NODOS / 2, TAM_NODOS, TAM_NODOS);
            grafo.addNodo(new Nodo(grafo.getCantNodos(), x - TAM_NODOS / 2, y - TAM_NODOS / 2));
            g.setColor(Color.white);
//            g.drawString(Integer.toString(grafo.getCantNodos()),  x, y);
            g.drawString(Integer.toString(grafo.getCantNodos()), (x - TAM_NODOS / 2)+TAM_NODOS/2.5f, (y - TAM_NODOS / 2)+TAM_NODOS/1.4f);
        } else if (nodoInicial == null) {
            nodoInicial = grafo.buscarNodo( x, y);
            if (nodoInicial != null) {
                seleccionarNodo(nodoInicial, g, Color.YELLOW);
            }
        } else {
            nodoFinal = grafo.buscarNodo( x, y);
            if (nodoFinal != null) {
                seleccionarNodo(nodoFinal, g, Color.YELLOW);
                if (!nodoInicial.equals(nodoFinal)) {
                    g.setColor(Color.BLACK);
                    g.drawLine(nodoInicial.getX() + TAM_NODOS / 2, nodoInicial.getY() + TAM_NODOS / 2, nodoFinal.getX() + TAM_NODOS / 2, nodoFinal.getY() + TAM_NODOS / 2);
//                    int dist = grafo.distancia(nodoInicial.getX() + TAM_NODOS / 2, nodoInicial.getY() + TAM_NODOS / 2, nodoFinal.getX() + TAM_NODOS / 2, nodoFinal.getY() + TAM_NODOS / 2);
                    grafo.addArco(new Arco(nodoInicial, nodoFinal));
//                    g.drawString(Integer.toString(grafo.distancia(nodoInicial.getX() + TAM_NODOS / 2, nodoInicial.getY() + TAM_NODOS / 2, nodoFinal.getX() + TAM_NODOS / 2, nodoFinal.getY() + TAM_NODOS / 2)), nodoInicial.getX() + dist / 2, nodoInicial.getY() + 5);
                } else {
                    seleccionarNodo(nodoInicial, g, Color.BLACK);
                }
                seleccionarNodo(nodoInicial, g, Color.BLACK);
                seleccionarNodo(nodoFinal, g, Color.BLACK);
                nodoInicial = null;
            } else {
                seleccionarNodo(nodoInicial, g, Color.BLACK);
                nodoInicial = null;
            }
        }
        g.dispose();
        mapa.repaint();
    }
    
    private void seleccionarNodo(Nodo nodo, Graphics2D g, Color color){
        g.setColor(color);
        g.drawOval(nodo.getX(), nodo.getY(), TAM_NODOS-1, TAM_NODOS -1);
    }
    
    private void drawNodo(Graphics2D g,Nodo nodo){
        g.fillOval(nodo.getX(), nodo.getY(), TAM_NODOS, TAM_NODOS);
    }
    
    /**
     * Se encarga de dibujar un nodo rojo en g con un String de color blanco en el cento
     * @param g es donde se va dibujar el nodo
     * @param nodo es el nodo que se va a dibujar
     * @param s es el String  que se va a dibujar en el centro del nodo
     */
    private void drawNodo(Graphics2D g,Nodo nodo, String s){
        g.setColor(Color.red);
        g.fillOval(nodo.getX(), nodo.getY(), TAM_NODOS, TAM_NODOS);
        g.setColor(Color.white);
        g.drawString(s, nodo.getX()+TAM_NODOS/2.5f, nodo.getY()+TAM_NODOS/1.4f);
//        g.drawString(s, nodo.getX(), nodo.getY());
    }
    
    /**
     * Metodo que se encarga de dibujar un arco sobre g
     * @param g es el Graphics2D sobre donde se dibujara el marco
     * @param arco es el arco que se va a dibujar
     */
    private void drawArco(Graphics2D g,Arco arco){
        g.drawLine(arco.getX1(), arco.getY1(), arco.getX2(), arco.getY2());
    }
    
    /**
     * Funcion que se encarga de pintar el grafo sobre el mapa
     */
    public void paintGrafo() {
        Graphics2D g = mapa.getGraphics2D();
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(5));
        for (Arco arco : grafo.getArcos()) {
            drawArco(g,arco);
        }
        for (Nodo nodo : grafo.getNodos()) {
            drawNodo(g,nodo,Integer.toString(nodo.getName()));
        }
        
        g.dispose();
        mapa.repaint();
//        Graphics g = panel.getGraphics();
////        panel.paintComponent(g);
//        g.drawImage(img, 0, 0, null);
//        panel.repaint();
    }

    /**
     * Metodo que se encarga de obtener el nodoInicial y nodoFinal de los cuales a partir de ellos se empezara a calcular la ruta solucion
     * @param x valor de la coordenda en x
     * @param y valor de la coordenada en y
     * @return un bool que dice si ya los dos puntos fueron seleccionados y la ruta fue calculada
     */
    public boolean getPoints(int x, int y) {
        Graphics2D g = this.mapa.getGraphics2D();
        Nodo nodo = new Nodo(x- TAM_NODOS / 2,y- TAM_NODOS / 2);
//        g.setColor(Color.red);
        if(nodoInicial == null){
            nodoInicial = grafo.buscarNodo(nodo)!= null ? grafo.buscarNodo(nodo) : grafo.puntoP(nodo);
            drawNodo(g,nodoInicial,"P");
        }else if(nodoFinal == null){
            nodoFinal = grafo.buscarNodo(nodo)!= null ? grafo.buscarNodo(nodo) : grafo.puntoP(nodo);
            drawNodo(g,nodoFinal,"L");
        }
        if(!ruta&&nodoFinal!=null){
            ruta = true;
            g.setStroke(new BasicStroke(5));
//            drawNodo(g,grafo.calcularRuta(nodoInicial,nodoFinal));
            paintRuta(g,grafo.calcularRuta(nodoInicial,nodoFinal));
            drawNodo(g,nodoInicial,"P");
            drawNodo(g,nodoFinal,"L");            
        }
        g.dispose();
        mapa.repaint();
        return ruta;
    }

    /**
     * Metodo que se encarga de pintar una ruta obtenida a partir del ArrayList ruta sobre g
     * @param g objeto de la clase Graphics2D que es usado para poder dibujar sobre la imagen en el panel Mapa
     * @param ruta es el ArrayList que contiene todos los arcos que conforman la ruta solucion exceptuando el primer y ultimo arco del recorrido
     */
    private void paintRuta(Graphics2D g,ArrayList<Arco> ruta) {
        g.setColor(Color.red);
        g.setStroke(new BasicStroke(5));
        for (Arco arco : ruta){ 
//            if(arco instanceof Nodo){
//                drawNodo(g,(Nodo)arco);
//            }else{
                drawArco(g,(Arco)arco);
//            }
        }
    }
    
    /**
     * Metodo que se encarga de reestablecer los graficos mostrados en la ventana. Como volver a cargar la imagen
     * deseleccionar el nodoInicial y nodoFinal, etc...
     */
    public void restablecer(){
        nodoInicial = null;
        nodoFinal = null;
        ruta = false;
        mapa.reset();
    }
    
}
