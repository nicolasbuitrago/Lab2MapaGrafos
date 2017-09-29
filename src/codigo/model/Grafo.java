/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo.model;

import codigo.controller.ControlGraphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author Estudiante
 */
public class Grafo {
    ArrayList<Nodo> nodos;   public Arco a;     // Nodo p;
    ArrayList<Arco> arcos;
    int cantNodos = 0;
    public int TAM_NODOS = 20;
    int matriz[][];

    public Grafo() {
        nodos = new ArrayList();
        arcos = new ArrayList();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Metodo insertar de la clase">   
    /*public void insertar (Graphics g){
        jPanel1.addMouseListener(new MouseListener() {
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
    }*/// </editor-fold>  

    public ArrayList<Nodo> getNodos() {
        return nodos;
    }

    public ArrayList<Arco> getArcos() {
        return arcos;
    }
    
    public int getCantNodos(){
        return nodos.size();
    }
    
    public void addNodo(Nodo nodo){
        nodos.add(nodo);
    }

    public void addArco(Arco arco){
        arcos.add(arco);
    }
    
    public int distancia(int x1, int y1, int x2, int y2){
        return (int) Math.sqrt(Math.pow(x2-x1, 2.0)+Math.pow(y2-y1, 2.0));
    }
    
    public Nodo buscarNodo(int x, int y){
        Nodo nodoR = null;
        for (Nodo nodo : nodos) {
            if (x>=nodo.x && y>= nodo.y && x<=nodo.x+TAM_NODOS && y <=nodo.y+TAM_NODOS) {
                nodoR= nodo;
                break;
            }
        }
        return nodoR;
    }//https://luisrey.wordpress.com/2008/07/06/distancia-punto-1/
    
    public Nodo calcularRuta(Nodo ni, Nodo nf){
        Arco a = arcoMasCercano(ni);
        this.a = a;
        Nodo nodo = puntoP(a,factorU(ni,a));System.out.println("nodo = "+nodo);
////        Nodo nodop = p;
        return nodo;
    }
    
    private Arco arcoMasCercano(Nodo ni){
        Arco a = null;
        int min = Integer.MAX_VALUE, d;
        for (Arco arco : arcos) {
            d = this.distanciaRectaPunto(ni, arco);
            if(d<min){
                min = d;
                a= arco;
            }
        }System.out.println("min = "+min);
        return a;
    }
    
    private double factorU(Nodo c, Arco ar){
        Nodo a = ar.getNodoInicial(), b = ar.getNodoFinal();
        double u;
        u = ((c.getX() - a.getX()) * (b.getX() - a.getX()) + (c.getY() - a.getY()) * (b.getY() - a.getY()));
        u = u / (Math.pow((b.getX() - a.getX()), 2) + Math.pow((b.getY() - a.getY()), 2));
        return u;
    }
    
    private Nodo puntoP(Arco ar,double u){
        double x,y;
        Nodo p = null, a = ar.getNodoInicial(), b = ar.getNodoFinal();
//        if (u >= 0 && u <= 1) {
            x = a.getX()+u*(b.getX()-a.getX());
            y = a.getY()+u*(b.getY()-a.getY());
            p = new Nodo((int)x,(int)y);System.out.println("p = "+p+"\n ar= "+ar);
//        }
        return p;
    }
    
    private int distanciaRectaPunto(Nodo c, Arco ar){
        double u = factorU(c, ar),x,y;//   System.out.println("u = "+u);
        double d = Integer.MAX_VALUE;
        Nodo p, a = ar.getNodoInicial(), b = ar.getNodoFinal();
        if (u >= 0 && u <= 1) {
            x = a.getX()+u*(b.getX()-a.getX());
            y = a.getY()+u*(b.getY()-a.getY());
//            p = new Nodo((int)x,(int)y); System.out.println("pd = "+p); this.p = p;
            y = Math.pow(b.getX()-a.getX(),2)+Math.pow(b.getY()-a.getY(),2);
            d = ((b.getX()-a.getX())*(c.getY()-a.getY())-(b.getY()-a.getY())*(c.getX()-a.getX()))/Math.sqrt(y);
        }
        return (int)Math.abs(d);
    }
    
     private void calcularMatriz(){
        matriz = new int [nodos.size()][nodos.size()];
        for (Arco arco : arcos) {
//            matriz[arco.nodoInicial][arco.nodoFinal] = arco.dist;
//            matriz[arco.nodoFinal][arco.nodoInicial] = arco.dist;
        }
        prim();
    }
     
     private void prim(){
         boolean vector[] = new boolean[nodos.size()];
         vector[0]=true;
         while (todosSeleccionados(vector)) {             
             int min = menor(matriz,vector);
             vector[min] = true;
         }
     }
     
     private int menor(int[][] matriz, boolean[] vector){
         int menor = Integer.MAX_VALUE;
         int fila = -1, col = -1;
         for (int i = 0; i < matriz.length; i++) {
             if (vector[i]) {
                 for (int j = 0; j < matriz.length; j++) {
                     if (matriz[j][i]!=0 && vector[j]==false && matriz[j][i]<=menor) {
                         menor = matriz[j][i];
                         fila = j;
                         col = i;
                     }
                 }
             }
         }
         System.out.println(""+fila+" - "+col);
         return fila;
     }
     
     private boolean todosSeleccionados(boolean[] vector) {
         for (int i = 0; i < vector.length; i++) {
             if(!vector[i]){
                 return true;
             }
         }
         return false;
    }
     
    private int nearestarc (Nodo p, Arco arc){
        int n = 0;
        
        Nodo a=arc.nodoInicial,b=arc.nodoFinal;
        
        double d1=Math.sqrt(Math.pow(p.x-a.x,2)+Math.pow(p.y-a.y,2));
        
        
        return n;
    }
     
     
    
}

