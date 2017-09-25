/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo.controller;

import codigo.view.Ventana;
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
        Ventana ventana = new Ventana();
        iniciarVentana(ventana);

        
    }
}
