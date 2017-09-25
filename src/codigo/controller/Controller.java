/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo.controller;

import codigo.view.Ventana;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JFrame;

/**
 *
 * @author Estudiante
 */
public class Controller {
    
    public void iniciarVentana(JFrame frame){
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
    public void main(){
        Ventana ventana = new Ventana(this);
        iniciarVentana(ventana);

        
    }
    
    public String horaActual(){
        LocalTime horaActual = LocalTime.now();
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
}
