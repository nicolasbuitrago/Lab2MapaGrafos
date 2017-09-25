/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo.model;

import java.awt.Color;

/**
 *
 * @author nicolasbuitrago
 */
public class Nodo {
    int name, x,y;
    Color color;

    public Nodo(int name, int x, int y, Color color) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
}
