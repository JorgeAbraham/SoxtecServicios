/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviciosBD;

import java.util.ArrayList;
import utilidadesbasicas.listToString;
import utilidadesbasicas.utilidadVinculoBD;

/**
 *
 * @author usuario
 */
public class ticket_servicio {
    
    
     public String actualizaPropietarioTicket( String idTicket,String idPropietario){
        
        String insertado=null;
        String SQL = "   UPDATE `ticket` SET `idPersonaPropietario`='"+idPropietario+"' WHERE `idTicket`='"+idTicket+"';  "   ;
        utilidadVinculoBD.operacionSQL(SQL);
        
        SQL="SELECT idTicket FROM ticket WHERE idTicket='"+idTicket+"' ;   ";
        String R[][] = ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
        
        if (R.length>0){
            insertado=R[0][0];
        } 
        
        return insertado;
        
    } 
    
    public String[][] getTicket(String idTicket){
        String R[][];
         
        String SQL= "  SELECT  " +
                    "	idTicket,  " + //0
                    "	titulo,  " + //1
                    "	descripcion,  " + //2
                    "	fechaCreacion,  " + //3
                    "	fechaVencimiento,  " + //4
                    "	hash,  " + //5
                    "	idEstadoTicket,  " + //6
                    "	idPrioridadTicket,  " + //7
                    "	idPersona,  " + //8
                    "	idPersonaPropietario  " +//9
                    "FROM ticket " +
                    "WHERE idTicket='"+idTicket+"';  ";
                 
        R=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 10);
        
        return R;
    }
    
    
     
     
    
    public String[][] getUltimoOrdenRespuestasTicket(String idTicket){
        String R[][];
         
        String SQL= "  SELECT  " +
                    "	rt.idRespuestaTicket, " + //0
                    " 	rt.Descripcion,   " + //1
                    "	rt.orden,   " + //2
                    "	rt.idTicket,   " + //3
                    "	rt.idPersona,  " + //4
                    "	p.apellidoPaterno,  " + //5
                    "	p.apellidoMaterno,  " + //6
                    "	p.nombre   " + //7
                    " FROM respuestaticket  rt   " +
                    " INNER JOIN persona  p on (p.idPersona=rt.idPersona)   " +
                    " WHERE    " +
                    "	idTicket='"+idTicket+"'  " +
                    "ORDER BY orden DESC  ;  ";
                 
        R=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 5);
        
        return R;
    }
    
    
    
    
    
    public String insertaRespuestaTicket(String numeroTicket,String idPersonaRespuesta,String textoRespuesta,String orden){
        String insertado=null;
        String SQL = " INSERT INTO `respuestaticket` (`Descripcion`, `orden`, `idTicket`, `idPersona`)  "
                + "  VALUES ('"+textoRespuesta+"', '"+orden+"', '"+numeroTicket+"', '"+idPersonaRespuesta+"');    "   ;
        utilidadVinculoBD.operacionSQL(SQL);
        
        SQL="SELECT @@identity AS id";
        String R[][] = ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
        
        if (R.length>0){
            insertado=R[0][0];
        } 
        
        return insertado;
    }
    
    public String[][] getListaRespuestasTicket(String idTicket){
        String R[][];
         
        String SQL= " SELECT  " +
                    "	rt.idRespuestaTicket,  " +  //0
                    "	rt.Descripcion,  " + //1
                    "	rt.orden,  " + //2
                    "	rt.idTicket,  " + //3
                    "	rt.idPersona, " + //4
                    "	p.apellidoPaterno, " + //5
                    "	p.apellidoMaterno, " + //6
                    "	p.nombre " + //7
                    " FROM respuestaticket  rt " +
                    " INNER JOIN persona  p on (p.idPersona=rt.idPersona) " +
                    " WHERE  " +
                    "	idTicket='"+idTicket+"' " +
                    " ORDER BY orden DESC " +
                    " ;";
                    
                 
        R=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 8);
        
        
        
        return R;
    }
    
    
    
    public String actualizaEstadoTicket( String idTicket,String idEstado){
        
        String insertado=null;
        String SQL = "   UPDATE ticket SET `idEstadoTicket`='"+idEstado+"' WHERE `idTicket`='"+idTicket+"';   "   ;
        utilidadVinculoBD.operacionSQL(SQL);
        
        SQL="SELECT idTicket FROM ticket WHERE idTicket='"+idTicket+"' ;   ";
        String R[][] = ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
        
        if (R.length>0){
            insertado=R[0][0];
        } 
        
        return insertado;
        
    } 
    
   
    
    public String[][] getListaTicketPorPersonaTodos(String idPersona,ArrayList estados){
         String estadosLista = listToString.listaEntreComas(estados);
        
        String R[][];
        
        String SQL=" SELECT " +
                    " t.idTicket,  " + //0
                    " t.titulo,  " + //1
                    " t.descripcion,  " + //2
                    " t.fechaCreacion,  " + //3
                    " t.fechaVencimiento,  " + //4
                    " t.hash,  " + //5
                    " t.idEstadoTicket,  " + //6
                    " t.idPrioridadTicket,  " + //7
                    " t.idPersona,  " + //8
                    " t.idPersonaPropietario, " + //9
                    " et.estado, " + //10
                    " pt.Prioridad "+ //11
                    " FROM ticket t " +
                    " inner JOIN estadoticket et on (t.idEstadoTicket = et.idEstadoTicket) " +
                    " inner JOIN prioridadticket pt on (t.idPrioridadTicket= pt.idPrioridadTicket) "+
                    " left join respuestaticket rt on (rt.idTicket = t.idTicket) " +
                    " left join ticketconcopia tcc on (tcc.idTicket = t.idTicket ) "+
                    " left join tickethistorialpersonas thp on (thp.idPersona = t.idPersona) "+
                    " where (t.idPersonaPropietario='"+idPersona+"'  OR t.idPersona='"+idPersona+"'  OR tcc.idPersona='"+idPersona+"'  OR rt.idPersona='"+idPersona+"'   )  AND t.idEstadoTicket IN ("+estadosLista+")  " +
                    " group by t.idTicket "+
                    " order by t.fechaCreacion DESC   ; ";
                    
                 
        R=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 12);
        
        
        
        return R;
    } 
    
    
    public String[][] getListaTicketPorPersona(String idPersona,ArrayList estados){
         String estadosLista = listToString.listaEntreComas(estados);
        
        String R[][];
        
        String SQL=" SELECT " +
                    " t.idTicket,  " + //0
                    " t.titulo,  " + //1
                    " t.descripcion,  " + //2
                    " t.fechaCreacion,  " + //3
                    " t.fechaVencimiento,  " + //4
                    " t.hash,  " + //5
                    " t.idEstadoTicket,  " + //6
                    " t.idPrioridadTicket,  " + //7
                    " t.idPersona,  " + //8
                    " t.idPersonaPropietario, " + //9
                    " et.estado, " + //10
                    " pt.Prioridad, "+ //11
                    " p.apellidoPaterno ," + //12
                    " p.apellidoMaterno ," + //13
                    " p.nombre "+ //14
                    " FROM ticket t " +
                    " inner JOIN estadoticket et on (t.idEstadoTicket = et.idEstadoTicket) " +
                    " inner JOIN prioridadticket pt on (t.idPrioridadTicket= pt.idPrioridadTicket) "+
                    " left join persona p on (t.idPersona = p.idPersona) "+
                    " where (t.idPersonaPropietario='"+idPersona+"'   )  AND t.idEstadoTicket IN ("+estadosLista+")  " +
                    " order by t.fechaCreacion DESC   ; ";
                    
                 
        R=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 15);
        
        
        
        return R;
    }
    
    
    
    public String insertaTicketConCopia( String idTicket,String idPersona){
        
        String insertado=null;
        String SQL = " INSERT INTO `ticketconcopia` (`idTicket`, `idPersona`) "
                + "  VALUES ('"+idTicket+"', '"+idPersona+"'); "   ;
        utilidadVinculoBD.operacionSQL(SQL);
        
        SQL="SELECT @@identity AS id";
        String R[][] = ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
        
        if (R.length>0){
            insertado=R[0][0];
        } 
        
        return insertado;
        
    } 
    
    public String insertaTicketHistorial( String idTicket,String idPersona){
        
        String insertado=null;
        String SQL = "   INSERT INTO `tickethistorialpersonas` (`idTicket`, `idPersona`)  "
                + "  VALUES ('"+idTicket+"', '"+idPersona+"'); "   ;
        utilidadVinculoBD.operacionSQL(SQL);
        
        SQL="SELECT @@identity AS id";
        String R[][] = ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
        
        if (R.length>0){
            insertado=R[0][0];
        } 
        
        return insertado;
        
    } 
    
    
    public String[][] getEstadoTicket(){
        String R[][];
         
        String SQL=" SELECT idEstadoTicket, estado " +
                   " FROM `estadoticket`; ";
                 
        R=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 2);
        
        
        
        return R;
    }
    
    public String[][] getPrioridadTicket(){
        String R[][];
         
        String SQL=" SELECT "
                    + " idPrioridadTicket, "
                    + " Prioridad, "
                    + " Descripcion " +
                    " FROM `prioridadticket` ";
                 
        R=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 3);
        
        
        
        return R;
    }
    
    public String[][] getTicketPorHash(String hash){
        String R[][];
         
        String SQL= " SELECT  " +
                    "	idTicket,  " + //0
                    "	titulo,  " + //1
                    "	descripcion,  " + //2
                    "	fechaCreacion,  " + //3
                    "	fechaVencimiento,  " + //4
                    "	hash,  " + //5
                    "	idEstadoTicket,  " + //6
                    "	idPrioridadTicket,  " + //7
                    "	idPersona,  " + //8
                    "	idPersonaPropietario " + //9
                    "FROM `ticket` " +
                    "WHERE hash='"+hash+"'  ;";
                 
        R=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 10);
        
        
        
        return R;
    }
    
    
    public String insertaTicket( String titulo,String descripcion, String fechaCreacion,String fechaVencimiento, String hash,String idEstado,  String idPrioridad, String idPersona , String idPropietario){
        
        String insertado=null;
        String SQL = " INSERT INTO `ticket` (`titulo`, `descripcion`, `fechaCreacion`, `fechaVencimiento`, `hash`, `idEstadoTicket`, `idPrioridadTicket`,  `idPersona`, `idPersonaPropietario`)  "
                + "   VALUES ('"+titulo+"', '"+descripcion+"', '"+fechaCreacion+"', '"+fechaVencimiento+"', '"+hash+"', '"+idEstado+"', '"+idPrioridad+"', '"+idPersona+"', '"+idPropietario+"'); "   ;
        utilidadVinculoBD.operacionSQL(SQL);
        
        SQL="SELECT @@identity AS id";
        String R[][] = ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
        
        if (R.length>0){
            insertado=R[0][0];
        } 
        
        return insertado;
        
    } 
    
     public String[][] getEstadoById(String idEstado){
        String R[][];
         
        String SQL= " SELECT "
                        + " estado "
                    + " FROM  "
                    + " estadoticket "
                    + " WHERE idEstadoTicket="+idEstado+"  ;";
                 
        R=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
        
        
        
        return R;
    }
    
    
    
}
