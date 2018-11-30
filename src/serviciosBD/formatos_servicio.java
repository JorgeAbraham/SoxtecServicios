package serviciosBD;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Soxtec Desarrollo
 */
public class formatos_servicio {
    
    
    public String[][] listaRevisionesDePuertas(  String idPersona  ){
        
        
        String inspector="";
        if (idPersona==null){
            inspector = "";
        }else{
            inspector = "AND co3.valorTexto="+idPersona+"   " ;
                 
        }
        
        String SQL= "SELECT 	 " +
                    "	o.idOperacion, " +  //0
                    "	(CASE WHEN c.idConcepto= 55 THEN DATE_FORMAT(co.valorFecha,'%d/%m/%Y')   END) AS FechaInspeccion  , 		 " +  //1
                    "	(CASE WHEN c2.idConcepto= 64 THEN co2.valorTexto END) AS HoraInspeccion, 		 " + //2
                    "	(CASE WHEN c3.idConcepto= 56 THEN co3.valorTexto END) AS Inspector, 		 " +  //3
                    "	(CASE WHEN c4.idConcepto= 58 THEN co4.valorTexto END) AS NumeroParte, 		 " + //4
                    "	(CASE WHEN c5.idConcepto= 63 THEN co5.valorTexto END) AS Turno, 		  " + //5
                    "	o.idEstadoOperacion , " + //6
                    "	eo.Estado " + //7
                    " " +
                    "FROM Operacion o   	 " +
                    "INNER JOIN pertenenciadevalores pv on (o.idOperacion=pv.idOperacion) 	 " +
                    "INNER JOIN concepto c on c.idConcepto = pv.idConcepto   AND  (c.idConcepto=55 ) 	 " +
                    "INNER JOIN conceptooperacion co on co.idConceptoOperacion = pv.idConceptoOperacion   " +
                    "	 " +
                    "INNER JOIN pertenenciadevalores pv2 on (o.idOperacion=pv2.idOperacion) 	 " +
                    "INNER JOIN concepto c2 on c2.idConcepto = pv2.idConcepto   AND  (c2.idConcepto=64 ) 	 " +
                    "INNER JOIN conceptooperacion co2 on co2.idConceptoOperacion = pv2.idConceptoOperacion  	 " +
                    " " +
                    "INNER JOIN pertenenciadevalores pv3 on (o.idOperacion=pv3.idOperacion) 	 " +
                    "INNER JOIN concepto c3 on c3.idConcepto = pv3.idConcepto   AND  (c3.idConcepto=56 ) 	 " +
                    "INNER JOIN conceptooperacion co3 on co3.idConceptoOperacion = pv3.idConceptoOperacion  	 " +
                    " " +
                    "INNER JOIN pertenenciadevalores pv4 on (o.idOperacion=pv4.idOperacion) 	 " +
                    "INNER JOIN concepto c4 on c4.idConcepto = pv4.idConcepto   AND  (c4.idConcepto=58 ) 	 " +
                    "INNER JOIN conceptooperacion co4 on co4.idConceptoOperacion = pv4.idConceptoOperacion  	 " +
                    " " +
                    "INNER JOIN pertenenciadevalores pv5 on (o.idOperacion=pv5.idOperacion) 	 " +
                    "INNER JOIN concepto c5 on c5.idConcepto = pv5.idConcepto   AND  (c5.idConcepto=63 ) 	 " +
                    "INNER JOIN conceptooperacion co5 on co5.idConceptoOperacion = pv5.idConceptoOperacion  	 " +
                    " " +
                    "INNER JOIN estadooperacion eo on (eo.idEstadoOperacion = o.idEstadoOperacion) " +
                    " " +
                    "WHERE 	 " +
                    "(o.idCatalogoOperacion = 13 AND (o.idEstadoOperacion=19 OR o.idEstadoOperacion=20  ) ) 	  " +
                    inspector +
                    " AND   co.valorFecha = CURDATE() "+
                    "	  " +
                    "GROUP BY o.idOperacion " + 
                    "ORDER BY o.idOperacion DESC   "+
                    "limit 1000 "
                    ;

        
        
        
        
        
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 8);
        return R;
        
        
    }
    
    
    public String[][] listaRevisionesDeArnes(  String idPersona  ){
        
        
        String inspector="";
        if (idPersona==null){
            inspector = "";
        }else{
            inspector = "AND co3.valorTexto="+idPersona+"   " ;
                 
        }
        
        String SQL= "SELECT 	 " +
                    "	o.idOperacion, " +  //0
                    "	(CASE WHEN c.idConcepto= 65 THEN DATE_FORMAT(co.valorFecha,'%d/%m/%Y')   END) AS FechaInspeccion  , 		 " +  //1
                    "	(CASE WHEN c2.idConcepto= 66 THEN co2.valorTexto END) AS HoraInspeccion, 		 " + //2
                    "	(CASE WHEN c3.idConcepto= 67 THEN co3.valorTexto END) AS Inspector, 		 " +  //3
                    "	(CASE WHEN c4.idConcepto= 69 THEN co4.valorTexto END) AS NumeroParte, 		 " + //4
                    "	(CASE WHEN c5.idConcepto= 68 THEN co5.valorTexto END) AS Turno, 		  " + //5
                    "	o.idEstadoOperacion , " + //6
                    "	eo.Estado " + //7
                    " " +
                    "FROM Operacion o   	 " +
                    "INNER JOIN pertenenciadevalores pv on (o.idOperacion=pv.idOperacion) 	 " +
                    "INNER JOIN concepto c on c.idConcepto = pv.idConcepto   AND  (c.idConcepto=65 ) 	 " +
                    "INNER JOIN conceptooperacion co on co.idConceptoOperacion = pv.idConceptoOperacion   " +
                    "	 " +
                    "INNER JOIN pertenenciadevalores pv2 on (o.idOperacion=pv2.idOperacion) 	 " +
                    "INNER JOIN concepto c2 on c2.idConcepto = pv2.idConcepto   AND  (c2.idConcepto=66 ) 	 " +
                    "INNER JOIN conceptooperacion co2 on co2.idConceptoOperacion = pv2.idConceptoOperacion  	 " +
                    " " +
                    "INNER JOIN pertenenciadevalores pv3 on (o.idOperacion=pv3.idOperacion) 	 " +
                    "INNER JOIN concepto c3 on c3.idConcepto = pv3.idConcepto   AND  (c3.idConcepto=67 ) 	 " +
                    "INNER JOIN conceptooperacion co3 on co3.idConceptoOperacion = pv3.idConceptoOperacion  	 " +
                    " " +
                    "INNER JOIN pertenenciadevalores pv4 on (o.idOperacion=pv4.idOperacion) 	 " +
                    "INNER JOIN concepto c4 on c4.idConcepto = pv4.idConcepto   AND  (c4.idConcepto=69 ) 	 " +
                    "INNER JOIN conceptooperacion co4 on co4.idConceptoOperacion = pv4.idConceptoOperacion  	 " +
                    " " +
                    "INNER JOIN pertenenciadevalores pv5 on (o.idOperacion=pv5.idOperacion) 	 " +
                    "INNER JOIN concepto c5 on c5.idConcepto = pv5.idConcepto   AND  (c5.idConcepto=68 ) 	 " +
                    "INNER JOIN conceptooperacion co5 on co5.idConceptoOperacion = pv5.idConceptoOperacion  	 " +
                    " " +
                    "INNER JOIN estadooperacion eo on (eo.idEstadoOperacion = o.idEstadoOperacion) " +
                    " " +
                    "WHERE 	 " +
                    "(o.idCatalogoOperacion = 16 AND (o.idEstadoOperacion=19 OR o.idEstadoOperacion=20  ) ) 	  " +
                    inspector +
                    " AND   co.valorFecha = CURDATE() "+
                    "	  " +
                    "GROUP BY o.idOperacion " + 
                    "ORDER BY o.idOperacion DESC   "+
                    "limit 1000 "
                    ;

        
        
        
        
        
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 8);
        return R;
        
        
    }
    
    
}
