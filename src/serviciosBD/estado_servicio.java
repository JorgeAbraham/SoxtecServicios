/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviciosBD;

import java.util.ArrayList;
import utilidadesbasicas.listToString;

/**
 *
 * @author Soxtec Desarrollo
 */
public class estado_servicio {
    
    
    
    public String[][] estadoOperacion(ArrayList<String> estados  ){
 
        String Estados = listToString.listaEntreComas(estados);
        
        
        String SQL= " SELECT idEstadoOperacion,Estado FROM estadooperacion eo " +
                    " WHERE eo.idEstadoOperacion in ("+Estados+");  "; 
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 2);
        return R;
        
        
    }
    
}
