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
        String usuario=null;
        
        String SQL="SELECT idusuarios, usuario, password, idPersona " +
                    "FROM `usuarios`  "
                + "WHERE usuario='"+Usuario+"' AND  password = '"+password+"' "
                + ";";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 4);
        
        
        
        if(R!=null){
            if (R.length>0){
                usuario=R[0][0];
            }
            else{
                usuario="";
            }
        }
        return usuario;
    }
    
    
    public String[][] usuarioPorID(String idUsuarios ){
        
        String SQL=" SELECT idusuarios, usuario, password, idPersona, email " +
                    " FROM `usuarios`  where  idPersona="+idUsuarios+"   "
                + ";";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 5);
        
        return R;
        
    }
    
    
      public String[][] usuarioPorIdUsuario(String idUsuarios ){
        
        String SQL=" SELECT idusuarios, usuario, password, idPersona, email " +
                    " FROM `usuarios`  where  idusuarios="+idUsuarios+"   "
                + ";";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 5);
        
        return R;
        
    }
    
    public String[][] emailPorIDPerfil(String idPerfil ){
        String SQL= " SELECT u.email " +
                    " FROM usuarios u " +
                    " inner join permiso p on (u.idusuarios=p.idusuarios) " +
                    " where p.idPerfil='"+idPerfil+"' ; ";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
        
        return R;
    }
    
    
    public String[][] getPerfilByIdUsuario( String idUsuario){
        
        
        String SQL="SELECT p.idPerfil " +
                    "FROM  permiso p  " +
                    "INNER JOIN usuarios u on (u.idusuarios=p.idusuarios) " +
                    "where   u.idusuarios  = '"+idUsuario+"' ";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
        
        
        return R;
    }
    
    
    
    public String[][] getUsuarios( ){
        
        
        String SQL="SELECT idusuarios, usuario, password, idPersona,email " +
                    "FROM `usuarios`  ;";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 5);
        
        
        return R;
    }
            
    
    public String[][] getUsuariosPersonaTickets( ){
        
        
        String SQL= "   SELECT   "
                                + " u.idusuarios , "  //0
                                + " ABIERTOS.idPersona , " //1
                                + " ABIERTOS.apellidoPaterno , " //2
                                + " ABIERTOS.apellidoMaterno , " //3
                                + " ABIERTOS.nombre , " //4
                                + " ABIERTOS.Estado1 , " //5
                                + " CERRADOS.Estado2  " //6
                        + " FROM " +
                    "	( " +
                    "		SELECT  p.idPersona,p.apellidoPaterno,p.apellidoMaterno,p.nombre,  COUNT(t.idEstadoTicket) AS Estado1 " +
                    "		FROM persona p  " +
                    "		inner join ticket t on t.idPersonaPropietario  =p.idPersona  AND (t.idEstadoTicket = 1 OR t.idEstadoTicket = 2) " +
                    "		GROUP BY p.idPersona " +
                    "	) AS ABIERTOS  " +
                    "	LEFT JOIN ( " +
                    "		SELECT p.idPersona, COUNT(t.idEstadoTicket) AS Estado2 " +
                    "		FROM persona p   " +
                    "		inner join ticket t on t.idPersonaPropietario  =p.idPersona  AND (t.idEstadoTicket = 3) " +
                    "		GROUP BY p.idPersona " +
                    "	) AS CERRADOS ON (ABIERTOS.idPersona =  CERRADOS.idPersona ) " +
                    "	inner join usuarios u  on u.idPersona=ABIERTOS.idPersona " +
                    "; ";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 7);
        
        
        return R;
    }
      
      
    
}
