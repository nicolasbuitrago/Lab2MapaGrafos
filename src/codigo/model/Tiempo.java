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
 * Es un hilo que se encaraga de mantener actualizadolas horas en la ventana
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
    
    /**
     * Metodo sobreescrito de su clase padre Thread usado para ir aumentando el tiempo en un minuto
     */
    @Override
    public void run() {
        while(true){
            time = time.plusSeconds(1);
            label.setText(fromHoraMilitar());
            esperar();
        }
    }
    
    /**
     * Metodo que se usa para iniciar el hilo y ademas sumarle cierta cantidad de miinutos al tiempo
     * @param minutos los minutos que se le van a sumar al tiempo para despues empezar el hilo y comenzar a contar.
     */
    public void start(double minutos){
        this.time = LocalTime.now().plusMinutes((long)minutos); System.out.println("Tiempo = "+minutos);
        label.setText(fromHoraMilitar());
        if (this.isAlive()) {
            this.resume();
        } else {
            this.start();
        }
//        this.label.setVisible(true);

        
    }
    
    /**
     * Metodo que transforma el atributo LocalTime de su formato original a un string que indica 
     * el tiempo en un sistema de horario de 12 horas
     * @return devuelve el string que sera mostrado en los labels horaActual y hhoraLlegada
     */
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
    
    /**
     * Metodo que se encarga de hacer que el hilo detenga su ejecucion por 60 segundos
     */
    private void esperar() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    
}
