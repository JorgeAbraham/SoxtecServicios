/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviciosBD;

import javax.swing.table.DefaultTableModel;
import utilidadesbasicas.utilidadVinculoBD;

/**
 *
 * @author Soxtec Desarrollo
 */
public class gqrs_servicio {
    
    
    public String[][] gqrsPorID(String idGQRS ){
        
        String SQL= " SELECT  " +
                    "	g.idgqrs,  " + //0
                    "	g.gqrs,  " + //1
                    "	g.fechaApertura,  " + //2
                    "	g.fechaCierre,  " + //3
                    "	g.estado  ,  " + //4
                    "	c.valor as Estado ,  " + //5
                    "	f.claveFactura,  " + //6
                    "	g.descripcion,  " + //7
                    "	g.familia,  " + //8
                    "	c2.valor as Familia,  " + //9
                    "	g.cs_ccr,  " + //10
                    "	g.idLugar ,  " + //11
                    "	l.nombre as Lugar,  " + //12
                    "	g.sorteado,  " + //13
                    "	g.rechazado,  " + //14
                    "	g.retrabajado,  " + //15
                    "	g.idPersona,  " + //16
                    "	CONCAT(p.apellidoPaterno,' ', p.apellidoMaterno,' ', p.nombre) AS Persona  " + //17
                    "  FROM gqrs g     " +
                    " LEFT JOIN catalogo c on (g.estado = c.idCatalogo) " +
                    " LEFT JOIN Factura f on (g.idFactura = f.idFactura)  "+
                    " LEFT JOIN catalogo c2 on (g.familia = c2.idCatalogo) "+
                    " LEFT JOIN lugar l on (l.idLugar = g.idLugar)  "+
                    " LEFT JOIN persona p on (p.idPersona = g.idPersona)  "+
                    " WHERE  g.idgqrs='"+idGQRS+"'  "+
                    "   ;  ";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 18);
       
        return R;
    }
    
    
    
    public DefaultTableModel  LISTAgqrs(String gqrs,String lugar,String estado) {
        
        String gqrsSQL="";
        if (gqrs!=null  && !gqrs.equals("") ){
            gqrsSQL=" AND ( g.gqrs LIKE '%"+gqrs+"%'   )  ";
        }
        
        String lugarSQL="";
        if (lugar!=null   && !lugar.equals("") ){
            lugarSQL=" AND ( l.nombre = '"+lugar+"'   )  ";
        }
        
        String estadoSQL="";
        if (estado!=null   && !estado.equals("") ){
            estadoSQL=" AND ( c.valor='"+estado+"'   )  ";
        }
        
        
        DefaultTableModel Tabla;
        String SQL= " SELECT  " +
                    "	g.idgqrs,  " +
                    "	g.gqrs,  " +
                    "	g.fechaApertura,  " +
                    "	g.fechaCierre,  " +
                    "   DATEDIFF( curdate(),g.fechaApertura ) as diasPasados ,  "+
                    "	c.valor as Estado ,  " +
                    "	f.claveFactura,  " +
                    "	g.descripcion,  " +
                    "	c2.valor as Familia,  " +
                    "	g.cs_ccr,  " +
                    "	l.nombre as Lugar,  " +
                    "	g.sorteado,  " +
                    "	g.rechazado,  " +
                    "	g.retrabajado,  " +
                    "	CONCAT(p.apellidoPaterno,' ', p.apellidoMaterno,' ', p.nombre) AS Persona  " +
                    "  FROM gqrs g     " +
                    " LEFT JOIN catalogo c on (g.estado = c.idCatalogo) " +
                    " LEFT JOIN Factura f on (g.idFactura = f.idFactura)  "+
                    " LEFT JOIN catalogo c2 on (g.familia = c2.idCatalogo) "+
                    " LEFT JOIN lugar l on (l.idLugar = g.idLugar)  "+
                    " LEFT JOIN persona p on (p.idPersona = g.idPersona)  "+
                    " WHERE  "
                    + "   1=1  "
                    + gqrsSQL
                    + lugarSQL
                    + estadoSQL
                    + "   ;  ";
        String columnas[]=new String[15];
        columnas[0]="ID"; 
        columnas[1]="GQRS"; 
        columnas[2]="Date Open"; 
        columnas[3]="Date Close";
        columnas[4]="Days Passed";
        columnas[5]="State";
        columnas[6]="Invoice";
        columnas[7]="Sort";
        columnas[8]="Family";
        columnas[9]="CS / CCR";
        columnas[10]="Plant";
        columnas[11]="Sorted";
        columnas[12]="Rejected";
        columnas[13]="Reworked";
        columnas[14]="Responsable";
        
        Tabla=ManejadorDeDatos.BD.consultaTabla(SQL, columnas);
        return Tabla;
        
    }
    
    
    public DefaultTableModel  listaGQRS() {
        
        DefaultTableModel Tabla;
        String SQL=" SELECT g.gqrs, g.fechaApertura, g.fechaCierre, IF  (g.fechaCierre is null ,DATEDIFF( curdate(),g.fechaApertura ), DATEDIFF( g.fechaCierre,g.fechaApertura ) ) as diasPasados ,c.valor, l.nombre,cs_ccr " +
                   " FROM gqrs g  "
                + "  INNER JOIN catalogo c on (g.estado = c.idCatalogo)   "
                + "  INNER JOIN lugar l on (l.idLugar = g.idLugar) "
                + "  ORDER BY   g.estado ASC ,  DATEDIFF( curdate(),g.fechaApertura ) DESC  ;     ";
        String columnas[]=new String[7];
        columnas[0]="GQRS";
        columnas[1]="Fecha Apertura";
        columnas[2]="Fecha Cierre";
        columnas[3]="Dias Trascorrudos";
        columnas[4]="Estado";
        columnas[5]="Lugar"; 
        columnas[6]="CS / CCR"; 
         
        Tabla=ManejadorDeDatos.BD.consultaTabla(SQL, columnas);
        return Tabla;
        
    }
    
    
    public boolean actualizaGQRS(String id,String gqrs,String fechaApertura,String fechaCierre,String estado,String idFactura,String descripcion,String familia,String cs_ccr,String idLugar,String sorteado,String rechazado,String retrabajado,String idPersona){
        boolean correct=false;
         
        String SQL="UPDATE `gqrs` "
                + "   SET   "
                + "   `gqrs`='"+gqrs+"',  "
                + "   `fechaApertura`='"+fechaApertura+"',  "
                + "   `fechaCierre`="+fechaCierre+",   "
                + "   `estado`='"+estado+"',   "
                + "   `descripcion`='"+descripcion+"',   "
                + "   `familia`='"+familia+"',  "
                + "   `cs_ccr`='"+cs_ccr+"',  "
                + "   `idLugar`='"+idLugar+"',  "
                + "   `sorteado`='"+sorteado+"',  "
                + "   `rechazado`='"+rechazado+"',   "
                + "   `retrabajado`='"+retrabajado+"', "
                + "   `idPersona`='"+idPersona+"'  "
                + "    WHERE `idgqrs`='"+id+"';";
        utilidadVinculoBD.operacionSQL(SQL);
        
        
        
        
        SQL="SELECT @@identity AS id";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
          
        if (R.length>0){
            correct=true;
        }
        
        return correct;
    }
    
    
    public boolean insertaGQRS(String gqrs,String fechaApertura,String fechaCierre,String estado,String idFactura,String descripcion,String familia,String cs_ccr,String idLugar,String sorteado,String rechazado,String retrabajado,String idPersona){
         boolean correct=false;
        
        String SQL=" INSERT INTO `gqrs` (`gqrs`, `fechaApertura`, `fechaCierre`, `estado`, `idFactura`, `descripcion`, `familia`, `cs_ccr`, `idLugar`, `sorteado`, `rechazado`, `retrabajado`, `idPersona`)  "
                + "      VALUES ('"+gqrs+"', '"+fechaApertura+"', "+fechaCierre+", '"+estado+"', "+idFactura+", '"+descripcion+"', '"+familia+"', '"+cs_ccr+"', '"+idLugar+"', '"+sorteado+"', '"+rechazado+"', '"+retrabajado+"', '"+idPersona+"');  ";
        utilidadVinculoBD.operacionSQL(SQL);
        
        SQL="SELECT @@identity AS id";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
          
        if (R.length>0){
            correct=true;
        }
        
        return correct;
        
        
    }
    
}
