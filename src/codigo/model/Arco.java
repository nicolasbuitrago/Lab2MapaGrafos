/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo.model;

/**
 *
 * @author nicolasbuitrago
 */
public class Arco {
    int  x1,y1,x2,y2, dist;
    Nodo nodoInicial, nodoFinal;

    public Arco(Nodo nodoInicial, Nodo nodoFinal, int distancia) {
        this.nodoInicial = nodoInicial;
        this.nodoFinal = nodoFinal;
        this.x1 = nodoInicial.getX() + Grafo.TAM_NODOS / 2;
        this.y1 = nodoInicial.getY() + Grafo.TAM_NODOS / 2;
        this.x2 = nodoFinal.getX() + Grafo.TAM_NODOS / 2;
        this.y2 = nodoFinal.getY() + Grafo.TAM_NODOS / 2;
        this.dist = distancia;
    }
    
    public Arco(Nodo nodoInicial, Nodo nodoFinal) {
        this.nodoInicial = nodoInicial;
        this.nodoFinal = nodoFinal;
        this.x1 = nodoInicial.getX() + Grafo.TAM_NODOS / 2;
        this.y1 = nodoInicial.getY() + Grafo.TAM_NODOS / 2;
        this.x2 = nodoFinal.getX() + Grafo.TAM_NODOS / 2;
        this.y2 = nodoFinal.getY() + Grafo.TAM_NODOS / 2;
        this.dist = Grafo.distancia(nodoInicial, nodoFinal);
    }

    public Nodo getNodoInicial() {
        return nodoInicial;
    }

    public void setNodoInicial(Nodo nodoInicial) {
        this.nodoInicial = nodoInicial;
    }

    public Nodo getNodoFinal() {
        return nodoFinal;
    }

    public void setNodoFinal(Nodo nodoFinal) {
        this.nodoFinal = nodoFinal;
    }

    public int getDist() {
        return dist;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    @Override
    public String toString() {
//        return  + "," + y1 + "," + x2 + "," + y2 + "," + dist;
        return x1 + "," + y1 + "," + x2 + "," + y2 + "," + dist;
    }

}
