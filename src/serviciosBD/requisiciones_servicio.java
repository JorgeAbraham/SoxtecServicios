/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviciosBD;

/**
 *
 * @author Soxtec Desarrollo
 */
public class requisiciones_servicio {
    
    public String[][] ultimaClaveCentrodeCosto( String idCentroDeCosto ){
        
        String SQL= " SELECT co.valorTexto " +
                    " FROM operacion  o " +
                    " INNER JOIN pertenenciadevalores pv on pv.idOperacion = o.idOperacion  AND pv.idConcepto=143 "+ // Clave Centro de Costo 
                    " INNER JOIN conceptooperacion co on co.idConceptoOperacion = pv.idConceptoOperacion " +
                    " INNER JOIN pertenenciadevalores pv2 on pv2.idOperacion = o.idOperacion  AND pv2.idConcepto=139 " + // 139 Centro de Costo 
                    " INNER JOIN conceptooperacion co2 on co2.idConceptoOperacion = pv2.idConceptoOperacion AND  co2.valorTexto='"+idCentroDeCosto+"'  "+ //   Parametro de ID Centro de Costo" +
                    " WHERE o.idCatalogoOperacion=1 " +
                    " ORDER BY o.idOperacion DESC  LIMIT 1; ";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
        
        return R;
        
    }
    
}
