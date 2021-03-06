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
        String SQL = "INSERT INTO `relacionoperaciones` (`idOperacion1`, `idOperacion2`, `idTipoRelacion`)    "
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
    
    
    
    public String[][] getArchivoDeOperacion(String idOperacion,  String idConcepto ){
        
          
        String SQL= " SELECT co.idArchivo,a.nombreArchivo  " +
                    " FROM pertenenciadevalores pv   " +
                    " INNER JOIN concepto c on c.idConcepto=pv.idConcepto   " +
                    " INNER JOIN conceptooperacion co on co.idConceptoOperacion=pv.idConceptoOperacion   " +
                    " INNER JOIN archivo a on a.idArchivo=co.idArchivo " +
                    " WHERE pv.idOperacion = "+idOperacion+" AND c.idConcepto = "+idConcepto+"  ; ";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 2);
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
    
    
    
    public String[][] listaCatalogoPorTipo(String idTipo){
        String SQL="  SELECT idCatalogo,valor FROM catalogo WHERE idCatalogoTipo="+idTipo+"  ; ";
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
                        "	DATE_FORMAT(co.valorFecha, '%d/%m/%Y'), " + //11
                        "	co.idArchivo, " + //12
                        "	c.idConcepto,  " + //13
                        "	c.nombreHTML, " + //14
                        "	pv.idConceptoOperacion " + //15
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

         
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 16);
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
    
    public String[][] listaOperacionesConcatenadas(ArrayList<String> concepto,ArrayList<String> estadosOperacion,ArrayList<String> idCatalogoOperacion,String adicionalCampo,String adicionalCondicion,int numeroAdicionales,String where,ArrayList<Boolean> lista,String ORDERBY){
              String tipoOperacionLista = listToString.listaEntreComas(idCatalogoOperacion);
        String estadosLista = listToString.listaEntreComas(estadosOperacion);
        
        
        
        String SQL;
         
        
        SQL=    "   SELECT " +
                "	o.idOperacion " +
                "	,o.idEstadoOperacion " +
                "	,o.fechaCreacion " +
                "	,o.fechaModificacion " +
                "	,o.idusuarios " ;
                
        
        for (int i=0;i<concepto.size();i++){
            SQL=SQL+"  ,valor"+i;
        }
        
        
        if (!adicionalCampo.equals("")){
            SQL=SQL+"  ,"+adicionalCampo+"  ";
        }
            
        
      

        
        SQL=SQL+" FROM " +
                "   Operacion o ";

        
        for (int i=0;i<concepto.size();i++){
            SQL=SQL+" LEFT JOIN " +
                "	(    "
                    + "  SELECT " ;
            
           
            if (lista!=null){
                if (lista.get(i)){
                    SQL=SQL+ " pv"+i+".fila , ";
                }
            }
                                    
                                    
                                    
            SQL=SQL+    "   o.idOperacion,( " +
                "           IF( " +
                "               c"+i+".idConcepto = "+concepto.get(i)+" , " +
                "		(   " +
                "                   IF ( " +
                "                       co"+i+".valorTexto IS NOT NULL , " +
                "                       co"+i+".valorTexto, " +
                "                       IF ( " +
                "                           co"+i+".valorNumerico IS NOT NULL , " +
                "                           co"+i+".valorNumerico, " +
                "                           IF ( " +
                "                               co"+i+".valorFecha IS NOT NULL , " +
                "                               co"+i+".valorFecha,NULL " +
                "                           ) " +
                "                       ) " +
                "                   ) " +   
                "		), " +
                "		NULL " +
                "              )  	 " +
                "           ) AS valor"+i+"  "+ 
                "       FROM Operacion o " +
                "       INNER JOIN pertenenciadevalores pv"+i+" on (o.idOperacion=pv"+i+".idOperacion)   " +
                "       INNER JOIN concepto c"+i+" on c"+i+".idConcepto = pv"+i+".idConcepto   AND  (c"+i+".idConcepto="+concepto.get(i)+" )   " +
                "       INNER JOIN conceptooperacion co"+i+" on co"+i+".idConceptoOperacion = pv"+i+".idConceptoOperacion   " +                
                "   ) AS valor"+i+"   ON o.idOperacion  =  valor"+i+".idOperacion   " ;
              
        }
        
        if (!adicionalCondicion.equals("")){
            SQL=SQL+"  "+adicionalCondicion+"  ";
        }
        
        
        
        String whereLista="";
        
        for (int i=0;i<concepto.size()-1;i++){
            if (lista!=null){
                if (lista.get(i)){
                    whereLista=whereLista+"   AND      valor"+i+".fila  = valor"+(i+1)+".fila  ";
                }
            }
        }
        
        String ORDER="";
        if (ORDERBY !=null){
            ORDER=ORDERBY;
        }else{
            ORDER=" ORDER BY o.idOperacion DESC, o.fechaCreacion DESC  ";
        }
        
        SQL=SQL+" WHERE o.idCatalogoOperacion  IN (  "+tipoOperacionLista+"  )   AND  " +
                " o.idEstadoOperacion IN ("+estadosLista+")  "+ where +  whereLista  +
                " "+ORDER+" "
                + "  ; ";
        
                
        
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, concepto.size()+5+numeroAdicionales);
        return R;
    }
    
    
    public String[][] listaOperacionesConcatenadas(ArrayList<String> concepto,ArrayList<String> estadosOperacion,ArrayList<String> idCatalogoOperacion,String adicionalCampo,String adicionalCondicion,int numeroAdicionales,String where,ArrayList<Boolean> lista){
          return listaOperacionesConcatenadas( concepto,estadosOperacion,idCatalogoOperacion,adicionalCampo,adicionalCondicion,numeroAdicionales,where,lista,null);
    }
    
    
    
    public String[][] listaOperacionesConcatenadas(ArrayList<String> concepto,ArrayList<String> estadosOperacion,ArrayList<String> idCatalogoOperacion,String adicionalCampo,String adicionalCondicion,int numeroAdicionales,String where){
        
       return listaOperacionesConcatenadas( concepto,estadosOperacion,idCatalogoOperacion,adicionalCampo,adicionalCondicion,numeroAdicionales,where,null);
        
    }
    
    
    
    public String[][] listaOperacionesConcatenadas(ArrayList<String> concepto,ArrayList<String> estadosOperacion,ArrayList<String> idCatalogoOperacion){
        
       return listaOperacionesConcatenadas( concepto,estadosOperacion,idCatalogoOperacion,"","",0,"");
        
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
                                + " bvo.idBaseValoresDeOperacion, "  //0
                                + " c.nombre ," //1
                                + " l.idLista ," //2 
                                + " c.idTipoVariable," //3 
                                + " c.nombreHTML, "  //4
                                + " c.idConcepto " + //5
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
    
    
    
    
            
            
     public String actualizaInformacion(String tipo,String valor,String idConceptoOperacion){
        
        
        String insertado=null;
        
        String SQL="";
        
        if (tipo.equals(tipoTexto)  ||  tipo.equals(tipoTextoLargo) ||  tipo.equals(catalogo) ){
            
            SQL = "UPDATE conceptooperacion SET `valorTexto`='"+valor+"'  WHERE   idConceptoOperacion="+idConceptoOperacion+"  ";
            
        }
        if (tipo.equals(tipoNumero)){
            
            
            SQL = "UPDATE conceptooperacion SET `valorNumerico`="+valor+"  WHERE   idConceptoOperacion="+idConceptoOperacion+"  ";
            
            
        }
        if (tipo.equals(tipoFecha)){
            
            
            
            SQL = "UPDATE conceptooperacion SET `valorFecha`='"+valor+"'  WHERE   idConceptoOperacion="+idConceptoOperacion+"  ";
            
            
        }
        
        if (tipo.equals(tipoArchivo)){
            
            SQL = "UPDATE conceptooperacion SET `idArchivo`="+valor+"  WHERE   idConceptoOperacion="+idConceptoOperacion+"  ";
          
            
        }
        
        utilidadVinculoBD.operacionSQL(SQL);
        
        SQL="SELECT idConceptoOperacion FROM conceptooperacion where idConceptoOperacion="+idConceptoOperacion+"; ";
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
