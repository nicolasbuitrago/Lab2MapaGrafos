/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo.model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JLabel;

/**
 *
 * @author nicolasbuitrago
 */
public class Tiempo extends Thread{
    
    private LocalTime time;
    private JLabel label;

    public Tiempo(JLabel label) {
        this.time = LocalTime.now();
        this.label = label;
    }

    public Tiempo(LocalTime time, JLabel label) {
        this.time = time;
        this.label = label;
    }

    @Override
    public void run() {
        while(true){
            time = time.plusSeconds(1);
            label.setText(fromHoraMilitar());
            esperar();
        }
    }
    
    public void start(int segundos){
        this.time = LocalTime.now();
        this.time.plusSeconds((long)segundos);
        this.label.setVisible(true);
        this.start();
    }

    public String fromHoraMilitar(){
        String hora = time.format(DateTimeFormatter.ofPattern("HH:mm"));
        String[] time = hora.split(":");
        int h = Integer.parseInt(time[0]);
        String m = " a.m.";
        if(h>12){
            h-=12;
            m = " p.m.";
        }
        return Integer.toString(h)+":"+time[1]+m;
    }
    
    private void esperar() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    
}
