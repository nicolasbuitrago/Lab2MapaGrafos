/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo.model;

import java.util.ArrayList;

/**
 *
 * @author Estudiante
 */
public class Grafo {
    ArrayList<Nodo> nodos;
    ArrayList<Arco> arcos;
    int cantNodos = 0;
    public int TAM_NODOS = 30;
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
     
     
    
}
