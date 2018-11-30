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
    
    
    
    public DefaultComboBoxModel LISTAlugaresEspacioEnBlanco() {
        
        DefaultComboBoxModel combo;
        String SQL="SELECT '' UNION SELECT nombre FROM lugar; ";
        combo=ManejadorDeDatos.BD.consultaCombo(SQL);
        return combo;
        
    }
    
    public DefaultComboBoxModel LISTAlugares() {
        
        DefaultComboBoxModel combo;
        String SQL="SELECT nombre FROM lugar; ";
        combo=ManejadorDeDatos.BD.consultaCombo(SQL);
        return combo;
        
    }
    
    public String idLugarPorNombre( String nombre ){
        
        String SQL= " SELECT idLugar " +
                    " FROM lugar " +
                    " WHERE nombre='"+nombre+"' ; ";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
        return R[0][0];
        
        
    }
    
    
    
    public String[][] listaLugares( ){
        
        String SQL= " SELECT idLugar, nombre " +
                    " FROM lugar; ";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 2);
        return R;
        
        
    }
    
    public String[][] lugaresPorId( String id ){
        
        String SQL= " SELECT idLugar, nombre " +
                    " FROM lugar where idLugar="+id+"; ";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 2);
        return R;
        
        
    }
    
    public String[][] listaLugaresPorIDPersona( String idPersona ){
        
        String SQL= " select idLugar, l.nombre from lugar l " +
                    " inner join personalugar pl on (l.idLugar=pl.Lugar_idLugar) " +
                    " inner join persona p on (p.idPersona=l.idLugar) " +
                    " where Persona_idPersona='"+idPersona+"' ;  ";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 2);
        return R;
        
        
    }
    
    
    
    
    
    
}
