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
public class Nodo {
    private int name,x,y;

    public Nodo(int name,int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public Nodo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return name + "," + x + "," + y;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.name;
        hash = 79 * hash + this.x;
        hash = 79 * hash + this.y;
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
        final Nodo other = (Nodo) obj;
//        if (this.name != other.name) {
//            return false;
//        }
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }

   
          
}
