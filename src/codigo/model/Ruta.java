/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo.model;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author nicolasbuitrago
 */
public class Ruta implements Comparable{
    private ArrayList ruta;
    private int distancia;

    public Ruta() {
        ruta = new ArrayList();
    }
    
    public void add(Object o){
        ruta.add(o);
        if(o instanceof Arco){
            this.distancia += ((Arco)o).getDist();
        }
    }
    
    public int getDistancia(){
        return this.distancia;
    }

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
