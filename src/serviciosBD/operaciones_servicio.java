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
public class operaciones_servicio {
 
    public final static String tipoTexto="1";
    public final static String tipoArchivo="2";
    public final static String tipoNumero="3";
    public final static String tipoFecha="4";
    public final static String tipoTextoLargo="5";
    public final static String catalogo="6";
    
    
    
    public String[][] listaOperacionesRelacionadasPorTipoOperacion(String idOperacion,String relacion ){
        
         String SQL=    " SELECT  " +
                        "       o.idOperacion,    " + //0
                        "	DATE_FORMAT(o.fechaCreacion, '%d/%m/%Y'),    " +//1
                        "	eo.Estado,    " +//2
                        "	p.apellidoPaterno,    " +//3
                        "	p.apellidoMaterno,    " +//4
                        "	p.nombre,    " +//5
                        "	pv.fila,    " +//6
                        "	c.nombre,    " +//7
                        "	c.idTipoVariable,	    " +//8
                        "	co.valorTexto,    " +//9
                        "	co.valorNumerico,    " +//10
                        "	co.valorFecha,    " +//11
                        "	co.idArchivo,    " +//12
                        "	c.idConcepto,     " +//13
                        "	c.nombreHTML " + //14
                        "	 " +
                        "FROM  " +
                        "	operacion o " +
                        "INNER JOIN relacionoperaciones ro ON (idOperacion2=o.idOperacion) " +
                        "inner join usuarios u on (o.idusuarios=u.idusuarios)   " +
                        "inner join persona p on (p.idPersona = u.idPersona)  " +
                        "inner join pertenenciadevalores pv on (pv.idOperacion=o.idOperacion  )  " +
                        "inner join concepto c on (c.idConcepto = pv.idConcepto )  " +
                        "inner join conceptooperacion co on (pv.idConceptoOperacion=co.idConceptoOperacion)  " +
                        "inner join estadooperacion eo on (eo.idEstadoOperacion=o.idEstadoOperacion)  " +
                        " " +
                        "WHERE ro.idOperacion1="+idOperacion+" AND idTipoRelacion="+relacion+" ";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 15);
        return R;
        
        
    }
    
    
    
    
    public String insertaRelacionOperaciones(String idOperacion1, String idOperacion2,String idRelacionOperacion){
        
        String insertado=null;
        String SQL = "INSERT INTO `soxtecdb`.`relacionoperaciones` (`idOperacion1`, `idOperacion2`, `idTipoRelacion`)    "
                    + "VALUES ('"+idOperacion1+"', '"+idOperacion2+"', '"+idRelacionOperacion+"');";
        utilidadVinculoBD.operacionSQL(SQL);
        
        SQL="SELECT @@identity AS id";
        String R[][] = ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
        
        if (R.length>0){
            insertado=R[0][0];
        } 
        
        return insertado;
        
    } 
    
    
    
    public String[][] operacionCalificada(String idOperacion){
        
        String SQL="SELECT idEstadoOperacion FROM operacion WHERE idOperacion="+idOperacion+"; ";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
        return R;
        
        
    }
    
    
    
    public String insertaOperacionesCuenta(String idOperacion, String idCuenta){
        
        String insertado=null;
        String SQL = " INSERT INTO `operacioncuentas` (`idOperacion`, `idCuentas`) VALUES ('"+idOperacion+"', '"+idCuenta+"');   "   ;
        utilidadVinculoBD.operacionSQL(SQL);
        
        SQL="SELECT @@identity AS id";
        String R[][] = ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
        
        if (R.length>0){
            insertado=R[0][0];
        } 
        
        return insertado;
        
    } 
    
    
    public String[][] listaCuentas(){
        String SQL="  SELECT idCuentas,nombre FROM cuentas ";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 2);
        return R;
    }
    
    public String[][] listaOperacionesPorTipo(String tipoOperacion, String idOperacion){
        String SQL=     " select 	" +
                        "	o.idOperacion, " +  //0
                        "	DATE_FORMAT(o.fechaCreacion, '%d/%m/%Y'), " + //1
                        "	eo.Estado, " + //2
                        "	p.apellidoPaterno, " + //3
                        "	p.apellidoMaterno, " + //4
                        "	p.nombre, " + //5
                        "	pv.fila, " + //6
                        "	c.nombre, " + //7
                        "	c.idTipoVariable,	 " +  //8
                        "	co.valorTexto, " + //9
                        "	co.valorNumerico, " + //10
                        "	co.valorFecha, " + //11
                        "	co.idArchivo, " + //12
                        "	c.idConcepto,  " + //13
                        "	c.nombreHTML " + //14
                        "from operacion o  " +
                        "inner join usuarios u on (o.idusuarios=u.idusuarios)  " +
                        "inner join persona p on (p.idPersona = u.idPersona) " +
                        "inner join pertenenciadevalores pv on (pv.idOperacion=o.idOperacion  ) " +
                        "inner join concepto c on (c.idConcepto = pv.idConcepto) " +
                        "inner join conceptooperacion co on (pv.idConceptoOperacion=co.idConceptoOperacion) " +
                        "inner join estadooperacion eo on (eo.idEstadoOperacion=o.idEstadoOperacion) " +
                        "where  " +
                        "o.idCatalogoOperacion = "+tipoOperacion+"     AND   o.idOperacion="+idOperacion+"    " +
                        "order by idOperacion desc, u.idusuarios asc  ,fechaCreacion desc  ; ";

         
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 15);
        return R;
        
    }
    
    
    
    public String[][] listaOperecionesPorTipoOperacionPorUsuario(ArrayList<String> tipoOperacion,String Concepto,ArrayList<String> estadosOperacion,String idUsuario ){
 
        String tipoOperacionLista = listToString.listaEntreComas(tipoOperacion);
        String estadosLista = listToString.listaEntreComas(estadosOperacion);
        

        
        String SQL=    "SELECT 	" +
                        "	o.idOperacion, 	" +  //0
                        "	eo.Estado, 	" +  //1
                        "	DATE_FORMAT(o.fechaCreacion, '%d/%m/%Y'), 	" +//2
                        "	co.valorTexto, " +//3
                        "       p.apellidoPaterno, " +//4
                        "	p.apellidoMaterno, " +//5
                        "	p.nombre, "+//6
                        "	co.valorNumerico, "+//7
                        "	co.valorFecha,  "+//8
                        "	eo.idEstadoOperacion  "+//9
                        "from operacion o " +
                        "inner join usuarios u on (o.idusuarios=u.idusuarios) " +
                        " inner join persona p on (p.idPersona = u.idPersona) "+
                        "inner join pertenenciadevalores pv on (pv.idOperacion=o.idOperacion  ) " +
                        "inner join concepto c on (c.idConcepto = pv.idConcepto   AND c.idConcepto="+Concepto+") " +
                        "inner join conceptooperacion co on (pv.idConceptoOperacion=co.idConceptoOperacion) " +
                        "inner join estadooperacion eo on (eo.idEstadoOperacion=o.idEstadoOperacion) " +
                        "where  "+
                        "u.idusuarios="+idUsuario+" and " +
                        " o.idCatalogoOperacion  in (  "+tipoOperacionLista+"  )   AND  " +
                        " eo.idEstadoOperacion in ("+estadosLista+")  "+
                        "order by idOperacion desc,fechaCreacion desc  ; ";
         
         
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 10);
        return R;
        
    }
    
    
        
    public String[][] listaOperecionesPorTipoOperacionPorUsuario(ArrayList<String> tipoOperacion,ArrayList<String> Concepto,ArrayList<String> estadosOperacion,String idUsuario ){
 
        String tipoOperacionLista = listToString.listaEntreComas(tipoOperacion);
        String estadosLista = listToString.listaEntreComas(estadosOperacion);
        String concepto = listToString.listaEntreComas(Concepto);
        
       
        
        String SQL=    "SELECT 	" +
                        "	o.idOperacion, 	" +  //0
                        "	eo.Estado, 	" +  //1
                        "	DATE_FORMAT(o.fechaCreacion, '%d/%m/%Y'), 	" +//2
                        "	co.valorTexto, " +//3
                        "       p.apellidoPaterno, " +//4
                        "	p.apellidoMaterno, " +//5
                        "	p.nombre, "+//6
                        "	co.valorNumerico, "+//7
                        "	co.valorFecha,  "+//8
                        "	eo.idEstadoOperacion  "+//9
                        "from operacion o " +
                        "inner join usuarios u on (o.idusuarios=u.idusuarios) " +
                        " inner join persona p on (p.idPersona = u.idPersona) "+
                        "inner join pertenenciadevalores pv on (pv.idOperacion=o.idOperacion  ) " +
                        "inner join concepto c on (c.idConcepto = pv.idConcepto   AND c.idConcepto in ("+concepto+")      ) " +
                        "inner join conceptooperacion co on (pv.idConceptoOperacion=co.idConceptoOperacion) " +
                        "inner join estadooperacion eo on (eo.idEstadoOperacion=o.idEstadoOperacion) " +
                        "where  "+
                        "u.idusuarios="+idUsuario+" and " +
                        " o.idCatalogoOperacion  in (  "+tipoOperacionLista+"  )   AND  " +
                        " eo.idEstadoOperacion in ("+estadosLista+")  "+
                        "order by idOperacion desc,fechaCreacion desc  ; ";
         
         
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 10);
        return R;
        
    }
    
    
    public String[][] listaOperecionesPorTipoOperacion(ArrayList<String> tipoOperacion,String Concepto,ArrayList<String> estadosOperacion ){
 
        String tipoOperacionLista = listToString.listaEntreComas(tipoOperacion);
        String estadosLista = listToString.listaEntreComas(estadosOperacion);
        

        
        String SQL=    "SELECT 	" +
                        "	o.idOperacion, 	" +  //0
                        "	eo.Estado, 	" +  //1
                        "	DATE_FORMAT(o.fechaCreacion,'%d/%m/%Y'), 	" +//2
                        "	co.valorTexto, " +//3
                        "       p.apellidoPaterno, " +//4
                        "	p.apellidoMaterno, " +//5
                        "	p.nombre, "+//6
                        "	co.valorNumerico, "+//7
                        "	co.valorFecha,  "+//8
                        "	eo.idEstadoOperacion  "+//9
                        "from operacion o " +
                        "inner join usuarios u on (o.idusuarios=u.idusuarios) " +
                        " inner join persona p on (p.idPersona = u.idPersona) "+
                        "inner join pertenenciadevalores pv on (pv.idOperacion=o.idOperacion  ) " +
                        "inner join concepto c on (c.idConcepto = pv.idConcepto   AND c.idConcepto="+Concepto+") " +
                        "inner join conceptooperacion co on (pv.idConceptoOperacion=co.idConceptoOperacion) " +
                        "inner join estadooperacion eo on (eo.idEstadoOperacion=o.idEstadoOperacion) " +
                        "where  " +
                        " o.idCatalogoOperacion  in (  "+tipoOperacionLista+"  )   AND  " +
                        " eo.idEstadoOperacion in ("+estadosLista+")  "+
                        "order by idOperacion desc,fechaCreacion desc  ; ";
         
         
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 10);
        return R;
        
    }   
    
    
    
    public String[][] listaOperecionesPorTipoOperacion(ArrayList<String> tipoOperacion,ArrayList<String> Concepto,ArrayList<String> estadosOperacion ){
 
        String tipoOperacionLista = listToString.listaEntreComas(tipoOperacion);
        String estadosLista = listToString.listaEntreComas(estadosOperacion);
        String concepto = listToString.listaEntreComas(Concepto);
        

        
        String SQL=    "SELECT 	" +
                        "	o.idOperacion, 	" +  //0
                        "	eo.Estado, 	" +  //1
                        "	DATE_FORMAT(o.fechaCreacion,'%d/%m/%Y'), 	" +//2
                        "	co.valorTexto, " +//3
                        "       p.apellidoPaterno, " +//4
                        "	p.apellidoMaterno, " +//5
                        "	p.nombre, "+//6
                        "	co.valorNumerico, "+//7
                        "	co.valorFecha,  "+//8
                        "	eo.idEstadoOperacion  "+//9
                        "from operacion o " +
                        "inner join usuarios u on (o.idusuarios=u.idusuarios) " +
                        " inner join persona p on (p.idPersona = u.idPersona) "+
                        "inner join pertenenciadevalores pv on (pv.idOperacion=o.idOperacion  ) " +
                        "inner join concepto c on (c.idConcepto = pv.idConcepto   AND c.idConcepto  in ("+concepto+")    ) " +
                        "inner join conceptooperacion co on (pv.idConceptoOperacion=co.idConceptoOperacion) " +
                        "inner join estadooperacion eo on (eo.idEstadoOperacion=o.idEstadoOperacion) " +
                        "where  " +
                        " o.idCatalogoOperacion  in (  "+tipoOperacionLista+"  )   AND  " +
                        " eo.idEstadoOperacion in ("+estadosLista+")  "+
                        "order by idOperacion desc,fechaCreacion desc  ; ";
         
         
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 10);
        return R;
        
    }
    
    
    
    public String[][] listaOperecionesPorUsuarioYTipoOperacion(String idUsuario,String tipoOperacion,String Concepto){
         
         String SQL=    "SELECT 	" +
                        "	o.idOperacion, 	" +
                        "	eo.Estado, 	" +
                        "	DATE_FORMAT(o.fechaCreacion,'%d/%m/%Y'), 	" +
                        "	co.valorTexto " +
                        "from operacion o " +
                        "inner join usuarios u on (o.idusuarios=u.idusuarios) " +
                        "inner join pertenenciadevalores pv on (pv.idOperacion=o.idOperacion  ) " +
                        "inner join concepto c on (c.idConcepto = pv.idConcepto   AND c.idConcepto="+Concepto+") " +
                        "inner join conceptooperacion co on (pv.idConceptoOperacion=co.idConceptoOperacion) " +
                        "inner join estadooperacion eo on (eo.idEstadoOperacion=o.idEstadoOperacion) " +
                        "where  " +
                        "u.idusuarios="+idUsuario+" and o.idCatalogoOperacion="+tipoOperacion+"   " +
                        "order by idOperacion desc,fechaCreacion desc  ; ";
         
         
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 4);
        return R;
        
    }
    
     
     
    public String[][] listaOperacionesBasePorTipo(String tipoOperacion){
        
         String SQL=    " SELECT "
                                + " bvo.idBaseValoresDeOperacion, "
                                + " c.nombre ,"
                                + " l.idLista ," 
                                + " c.idTipoVariable," 
                                + " c.nombreHTML, " 
                                + " c.idConcepto " +
                        " FROM basevaloresdeoperacion bvo " +
                        " LEFT JOIN lista l on (bvo.idLista = l.idLista) " +
                        " INNER JOIN concepto c on (bvo.idConcepto=c.idConcepto) " +
                        " where bvo.idCatalogoOperacion="+tipoOperacion+"     " +
                        " ORDER BY orden ; ";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 6);
        return R;
        
        
    }
    
    
    
    public String actualizaEstado(String idOperacion, String idEstado){
        
        String insertado=null;
        String SQL = "  UPDATE `operacion` SET `idEstadoOperacion`='"+idEstado+"' WHERE `idOperacion`='"+idOperacion+"';   "   ;
        utilidadVinculoBD.operacionSQL(SQL);
        
        SQL="SELECT @@identity AS id";
        String R[][] = ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
        
        if (R.length>0){
            insertado=R[0][0];
        } 
        
        return insertado;
        
        
        
    }
    
    
    
    public String insertaOperacion(String idEstadoOperacion,String FechaCreacion,String FechaModificacion,String idUsuarios,String idCatalogoOperacion){
        
        String insertado=null;
        String SQL = "  INSERT INTO `operacion` (`idEstadoOperacion`, `fechaCreacion`, `fechaModificacion`, `idusuarios`, `idCatalogoOperacion`)    "+
                     "  VALUES ('"+idEstadoOperacion+"', '"+FechaCreacion+"', '"+FechaModificacion+" ', '"+idUsuarios+"', '"+idCatalogoOperacion+"');   "   ;
        utilidadVinculoBD.operacionSQL(SQL);
        
        SQL="SELECT @@identity AS id";
        String R[][] = ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
        
        if (R.length>0){
            insertado=R[0][0];
        } 
        
        return insertado;
       
    }

    
    public String insertaInformacion(String tipo,String valor){
        
        
        String insertado=null;
        
        String SQL="";
        
        if (tipo.equals(tipoTexto)  ||  tipo.equals(tipoTextoLargo) ||  tipo.equals(catalogo) ){
            
            SQL = "INSERT INTO conceptooperacion (`valorTexto`)  "
                    + "  VALUES (  '"+valor+"');";
            
        }
        if (tipo.equals(tipoNumero)){
            
            SQL = "INSERT INTO conceptooperacion ( `valorNumerico`)  "
                    + "  VALUES (  '"+valor+"');";
            
        }
        if (tipo.equals(tipoFecha)){
            
            SQL = "INSERT INTO conceptooperacion ( `valorFecha` )  "
                    + "  VALUES (  '"+valor+"');";
            
        }
        
        if (tipo.equals(tipoArchivo)){
            
            SQL = "INSERT INTO conceptooperacion ( `idArchivo` )  "
                    + "  VALUES (  '"+valor+"');";
            
        }
        
        utilidadVinculoBD.operacionSQL(SQL);
        
        SQL="SELECT @@identity AS id";
        String R[][] = ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
        
        if (R.length>0){
            insertado=R[0][0];
        } 
        
        return insertado;
        
        
        
        
    }
    
    
    public String insertaPertenenciaDeValores(String idConcepto,String idConceptoOperacion,String idOperacion, String fila){
        
        String insertado=null;
        
        String SQL="";
        SQL = "    INSERT INTO `pertenenciadevalores` (`idConcepto`, `idConceptoOperacion`, `idOperacion`, `fila`)  "
                + "   VALUES ('"+idConcepto+"', '"+idConceptoOperacion+"', '"+idOperacion+"', "+fila+");   "   ;

        utilidadVinculoBD.operacionSQL(SQL);
        
        SQL="SELECT @@identity AS id";
        String R[][] = ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
        
        if (R.length>0){
            insertado=R[0][0];
        } 
        
        return insertado;
        
        
    }
    
    
    
}
