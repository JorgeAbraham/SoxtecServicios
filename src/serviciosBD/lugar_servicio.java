/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviciosBD;

import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Abraham
 */
public class lugar_servicio {
    int idLugar;
    String nombre;

    public lugar_servicio() {
    }
    
    
    public DefaultComboBoxModel LISTAlugares() {
        
        DefaultComboBoxModel combo;
        String SQL="SELECT nombre FROM lugar; ";
        combo=ManejadorDeDatos.BD.consultaCombo(SQL);
        return combo;
        
    }
    
    
    public String[][] listaLugares( ){
        
        String SQL= " SELECT idLugar, nombre " +
                    " FROM lugar; ";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 2);
        return R;
        
        
    }
    
    
    
    
    
    
}
