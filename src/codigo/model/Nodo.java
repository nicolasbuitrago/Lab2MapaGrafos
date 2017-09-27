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
    int x,y;
//    Color color;

    public Nodo(int x, int y) {
        this.x = x;
        this.y = y;
//        this.color = color;
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
        return "Nodo{" + "x=" + x + ", y=" + y + '}';
    }
    
}
