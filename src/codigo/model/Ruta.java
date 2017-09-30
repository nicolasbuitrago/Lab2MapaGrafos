/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author nicolasbuitrago
 */
public class Ruta implements Comparable{
    private ArrayList<Nodo> ruta;
    private int distancia;

    public Ruta() {
        this.ruta = new ArrayList();
        this.distancia = 0;
    }
    
//    private Ruta(Ruta ruta) {
//        this.ruta = new ArrayList();
//        this.ruta.addAll(ruta.ruta);
//        this.distancia = ruta.distancia;
//    }
    
    private Ruta(List sub) {
        this.ruta = new ArrayList();
        this.ruta.addAll(sub);
        this.distancia = 0;
    }
    
    private Ruta(int dist){
        ruta = new ArrayList();
        this.distancia = dist;
    }
    
    public void add(Nodo nodo){
        ruta.add(nodo);
//        if(o instanceof Arco){
//            this.distancia += ((Arco)o).getDist();
//        }
    }
    
//    public static void add(ArrayList<Ruta> rutas,Object o){
//        for (Ruta ruta : rutas) {
//            if (ruta.getLast().equals(o)) {
//                ruta.add(o);
//                if (o instanceof Arco) {
//                   ruta.distancia += ((Arco) o).getDist();
//                }
//            }
//        }
//    }
    
    public Object getLast(){
        return this.ruta.get(ruta.size()-1);
    }
    
    public int getDistancia(){
        return this.distancia;
    }
    
    public void addDistancia(int dist){
        this.distancia += dist;
    }

    public ArrayList getRuta() {
        return ruta;
    }
    
    public static Ruta rutaMasCorta(ArrayList<Ruta> rutas, Nodo nf){
        Ruta min = new Ruta(Integer.MAX_VALUE);
        for (Ruta ruta : rutas) {
            if(min.compareTo(ruta)>0 && ruta.ruta.get(ruta.ruta.size()-1).equals(nf)){
                min = ruta;
            }else if(min.compareTo(ruta)==0) System.out.println("SON IGUALES LAS RUTAS =0");
        }
        return min;
    }
    
    public boolean contains(Nodo nodo){
        return ruta.contains(nodo);
    }
    
    public int indexOf(Nodo nodo){
        return ruta.indexOf(nodo);
    }
    
    public Nodo get(int index){
        return (Nodo) ruta.get(index);
    }
    
    public Ruta subRuta(Nodo nodo){
        Ruta ruta;
        List a = this.ruta.subList(0,this.ruta.indexOf(nodo)+1);
        ruta = new Ruta(a);
        return ruta;
    }
    
//    public void imprimirRuta(){
//        for (Object object : this.ruta) {
//            System.out.print(nodos.indexOf((Nodo)object)+"   ");
//        }
//        System.out.println("\n");
//    }

    @Override
    public int compareTo(Object o) {
        return this.distancia - ((Ruta)o).distancia; //Si this es mayor que o return es positivo >0
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.ruta);
        hash = 79 * hash + this.distancia;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ruta other = (Ruta) obj;
        if (this.distancia != other.distancia) {
            return false;
        }
        if (this.ruta.size() != other.ruta.size()) {
            return false;
        }
        for (int i = 0; i < this.ruta.size(); i++) {
            if (!this.ruta.get(i).equals(other.ruta.get(i))) {
                return false;
            }
        }
        return true;
    }
    
    
    
}
