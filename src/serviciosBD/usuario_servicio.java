/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviciosBD;


/**
 *
 * @author Abraham
 */
public class usuario_servicio {
    
    
    
    public usuario_servicio() {
        
        
    }
    
    public String acceso(String Usuario,String password ){
        String usuario;
        
        String SQL="SELECT idusuarios, usuario, password, idPersona " +
                    "FROM `usuarios`  "
                + "WHERE usuario='"+Usuario+"' AND  password = '"+password+"' "
                + ";";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 4);
        
        
        if (R.length>0){
            usuario=R[0][0];
        }
        else{
            usuario="";
        }
        return usuario;
    }
    
    
    public String[][] getUsuarios( ){
        
        
        String SQL="SELECT idusuarios, usuario, password, idPersona,email " +
                    "FROM `usuarios`  ;";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 5);
        
        
        return R;
    }
            
    
      
      
      
    
}
