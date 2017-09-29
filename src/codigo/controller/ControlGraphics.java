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
    
    public void crearGrafoMapa(int x, int y) {
        Graphics2D g = this.mapa.getGraphics2D();
        if (isNodo) {
            g.setColor(Color.BLACK);
            g.fillOval( x - TAM_NODOS / 2, y - TAM_NODOS / 2, TAM_NODOS, TAM_NODOS);
            grafo.addNodo(new Nodo( x - TAM_NODOS / 2, y - TAM_NODOS / 2));
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
                    int dist = grafo.distancia(nodoInicial.getX() + TAM_NODOS / 2, nodoInicial.getY() + TAM_NODOS / 2, nodoFinal.getX() + TAM_NODOS / 2, nodoFinal.getY() + TAM_NODOS / 2);
                    grafo.addArco(new Arco(nodoInicial, nodoFinal, TAM_NODOS,dist));
                    g.drawString(Integer.toString(grafo.distancia(nodoInicial.getX() + TAM_NODOS / 2, nodoInicial.getY() + TAM_NODOS / 2, nodoFinal.getX() + TAM_NODOS / 2, nodoFinal.getY() + TAM_NODOS / 2)), nodoInicial.getX() + dist / 2, nodoInicial.getY() + 5);
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
    
    private void drawNodo(Graphics2D g,Nodo nodo, String s){
        g.setColor(Color.red);
        g.fillOval(nodo.getX(), nodo.getY(), TAM_NODOS, TAM_NODOS);
        g.setColor(Color.white);
        g.drawString(s, nodo.getX()+TAM_NODOS/2.5f, nodo.getY()+TAM_NODOS/1.4f);
//        g.drawString(s, nodo.getX(), nodo.getY());
    }
    
    private void drawArco(Graphics2D g,Arco arco){
        g.drawLine(arco.getX1(), arco.getY1(), arco.getX2(), arco.getY2());
    }
    
    public void paintMapa() {
        
        // creamos una instancia graphics desde la imagen para pintar sobre ella
        Graphics2D g = mapa.getGraphics2D();
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(5));
        for (Nodo nodo : grafo.getNodos()) {
            drawNodo(g,nodo);
        }
        
        for (Arco arco : grafo.getArcos()) {
            drawArco(g,arco);
        }
        
        g.dispose();
        mapa.repaint();
//        Graphics g = panel.getGraphics();
////        panel.paintComponent(g);
//        g.drawImage(img, 0, 0, null);
//        panel.repaint();
    }

    public void getPoints(int x, int y) {
        Graphics2D g = this.mapa.getGraphics2D();
//        g.setColor(Color.red);
        if(nodoInicial == null){
            nodoInicial = new Nodo(x- TAM_NODOS / 2,y- TAM_NODOS / 2);
            drawNodo(g,nodoInicial,"P");
        }else if(nodoFinal == null){
            nodoFinal = new Nodo(x- TAM_NODOS / 2,y- TAM_NODOS / 2);
            drawNodo(g,nodoFinal,"L");
        }
        if(!ruta&&nodoFinal!=null){
            ruta = true;
            g.setColor(Color.blue);
            g.setStroke(new BasicStroke(5));
////            drawNodo(g,grafo.calcularRuta(nodoInicial,nodoFinal));
            paintRuta(grafo.calcularRuta(nodoInicial,nodoFinal));
            drawArco(g,grafo.ai);
        }
        g.dispose();
        mapa.repaint();
    }

    private void paintRuta(ArrayList ruta) {
        Graphics2D g = this.mapa.getGraphics2D();
        g.setColor(Color.blue);
        g.setStroke(new BasicStroke(5));
        for (Object obj : ruta){ 
            if(obj instanceof Nodo){
                drawNodo(g,(Nodo)obj);
            }else{
                drawArco(g,(Arco)obj);
            }
            
            
        }
        g.dispose();
        mapa.repaint();
    }
    
    public void restablecer(){
        nodoInicial = null;
        nodoFinal = null;
        ruta = false;
        mapa.reset();
    }
    
}
