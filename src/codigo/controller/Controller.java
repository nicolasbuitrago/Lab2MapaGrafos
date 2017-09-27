/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo.controller;

import codigo.model.Arco;
import codigo.model.Grafo;
import codigo.model.Nodo;
import codigo.view.Ventana;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JFrame;

/**
 *
 * @author Estudiante
 */
public class Controller {
    
    LocalTime horaActual, horaLlegada;
    Grafo grafo;
    
    public void iniciarVentana(JFrame frame){
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
    public void main(){
        grafo= new Grafo();
        Ventana ventana = new Ventana(this);
        iniciarVentana(ventana);
        
    }
    
    public String horaActual(){
        horaActual = LocalTime.now();
        String hora = fromHoraMilitar(horaActual.format(DateTimeFormatter.ofPattern("HH:mm")));
        return hora;
    }
    
    public String fromHoraMilitar(String hora){
        String[] time = hora.split(":");
        int h = Integer.parseInt(time[0]);
        String m = " a.m.";
        if(h>12){
            h-=12;
            m = " p.m.";
        }
        return Integer.toString(h)+":"+time[1]+m;
    }

    private int toInt(String s){
        return Integer.parseInt(s);
    }
    
    public void getGrafo(){
        FileReader frg =null;
        try {
            File Grafo = new File("Grafo.txt");
            frg = new FileReader(Grafo);
            BufferedReader gra = new BufferedReader(frg);
            String v = gra.readLine();
            v = gra.readLine();
            while (v != null) {                
                String[] c = v.split(",");//System.out.println("c.length = "+c.length);
                if(c[0].equals("n")){
                    grafo.newNodo(new Nodo(Integer.parseInt(c[1]),Integer.parseInt(c[2])));
                }else{
                    grafo.newArco(new Arco(toInt(c[1]),toInt(c[2]),toInt(c[3]),toInt(c[4]),toInt(c[5]),toInt(c[6]),toInt(c[7])));
                }
                v = gra.readLine();
            }
        } catch (Exception e) {
            System.out.println("Error Controller getGrafo =    "+e.getMessage());
        }finally{
            try {
                if (frg != null) {
                    frg.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
