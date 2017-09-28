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
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author nicolasbuitrago
 */
public class ControlGraphics {
    
    private Grafo grafo;
    private Mapa mapa;
    private final int TAM_NODOS = 30;
    Nodo nodoInicial = null, nodoFinal = null;
    public boolean isNodo = true;

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
            g.drawString(Integer.toString(grafo.getCantNodos()),  x, y);
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
    
}
