/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviciosBD;

import utilidadesbasicas.utilidadVinculoBD;

/**
 *
 * @author usuario
 */
public class catalogo_servicio {

    public catalogo_servicio() {
    
    }
    
    public String[][] listaCuentas( ){
        
        String SQL= " SELECT idCuentas, nombre, descripcion " +
                    " FROM `cuentas`; ";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 3);
        return R;
        
        
    }
    
    
    public String insertaCuenta(String nombre, String descripcion){
        
        String insertado=null;
        String SQL = "  INSERT INTO `cuentas` (`nombre`, `descripcion`) "
                + "  VALUES ('"+nombre+"', '"+descripcion+"');   "; 
        utilidadVinculoBD.operacionSQL(SQL);
        
        SQL="SELECT @@identity AS id";
        String R[][] = ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
        
        if (R.length>0){
            insertado=R[0][0];
        } 
        
        return insertado;
        
    } 
    
}
