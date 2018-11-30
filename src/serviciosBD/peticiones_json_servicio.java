/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviciosBD;

import utilidadesbasicas.utilidadVinculoBD;

/**
 *
 * @author Soxtec Desarrollo
 */
public class peticiones_json_servicio {
    
     
    public String insertaJSON(String json){
        
        String insertado=null;
        String SQL =   "   INSERT INTO `informacionllegadajson` (`idcatalogoinformacionllegadaJSON`,`informacion`) VALUES ('1','"+json+"');"   ;
        utilidadVinculoBD.operacionSQL(SQL);
        
        SQL="SELECT @@identity AS id";
        String R[][] = ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
        
        if (R.length>0){
            insertado=R[0][0];
        } 
        
        return insertado;
        
    } 
    
    
    
    public String[][] listaPagosFijos( ){
        
        String SQL= " SELECT * from SE ";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 7);
        return R;
        
    }
    
    
}
