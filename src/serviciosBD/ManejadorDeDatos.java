/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviciosBD;

import conexion.datosBD;
import utilidadesbasicas.utilidadVinculoBD;

/**
 *
 * @author Abraham
 */
public class ManejadorDeDatos {
    
    
    
    public static utilidadVinculoBD BD;

    public ManejadorDeDatos() {
        
        //BD=new utilidadVinculoBD(utilidadVinculoBD.datosDeBdDesdeArchivo("datosGenrales.txt"),"soxtecDB");
     
        datosBD DATOS=new datosBD();
        DATOS.setDbUserName("AdminUser2602018");
        DATOS.setDbPassword("anA.?zahuAn0721");
        DATOS.setDbURL("jdbc:mysql://localhost:3306/");
        DATOS.setDriverBD("com.mysql.jdbc.Driver");
        BD=new utilidadVinculoBD(DATOS, "soxtecDB");
    }

    
    
}
