/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviciosBD;

import utilidadesbasicas.archivoSerializableParaBD;
import utilidadesbasicas.utilidadVinculoBD;

/**
 *
 * @author Abraham
 */
public class documentos_servicio {
    
    
     public String[][] getDocumentosPorTipoEIdDeEmpleado(String tipoDocumento){
        String R[][];
         
        String SQL= " SELECT   " +
                    "	r.idRequisitos,   " +
                    "	r.idPersona,  " +
                    "	r.valorTexto,  " +
                    "	r.valorNumerico,  " +
                    "	r.valorFecha,  " +
                    "	r.idArchivo,  " +
                    "	r.idVariableAlmacenamiento,  " +
                    "	r.indice, " +
                    "	CONCAT(p.apellidoPaterno,' ',p.apellidoMaterno,' ',p.nombre  ) " +
                    " FROM persona p   " +
                    " LEFT JOIN requisitos r  on r.idPersona=p.idPersona " +
                    " LEFT JOIN variablealmacenamiento va on (r.idVariableAlmacenamiento=va.idVariableAlmacenamiento)   " +
                    " WHERE   va.idRazonAlmacenamiento = "+tipoDocumento+"    " +
                    " ORDER BY p.apellidoPaterno ASC, p.apellidoMaterno ASC;    ";
                 
        R=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 9);
        
        return R;
    }
    
    
    
    
    
    public String[][] getDocumentosPorTipoEIdDeEmpleado(String tipoDocumento,String idEmpleado){
        String R[][];
         
        String SQL="SELECT  	" +
                    "	va.idVariableAlmacenamiento ,    " +  //0
                    "	va.nombre, 	" + //1
                    "	r.valorTexto, 	" + //2
                    "	r.valorNumerico, 	" + //3
                    "	DATE_FORMAT(r.valorFecha, '%d/%m/%Y'),	" + //4
                    "	MAX(r.idArchivo),     " + //5
                    "	va.idTipoVariable,    " + //6
                    "	r.idPersona,    " + //7
                    "	MAX(r.idRequisitos)  " + //8
                    "FROM variablealmacenamiento va " +
                    "left join (select * from requisitos order by idRequisitos DESC ) as r on (va.idVariableAlmacenamiento = r.idVariableAlmacenamiento  and  (  r.idPersona is null OR r.idPersona = "+idEmpleado+" )   ) " +
                    "where  	" +
                    "va.idRazonAlmacenamiento =  "+tipoDocumento+"  	" +
                    "group by   " +
                    "va.idVariableAlmacenamiento   " +
                    "order by orden asc";
                 
        R=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 9);
        
        return R;
    }
    
    
    public String[][] getDocumentosTodosLosRegistrosPorTipoEIdDeEmpleado(String tipoDocumento,String idEmpleado){
        String R[][];
         
        String SQL= "SELECT " +
                    " r.idRequisitos, "  //0
                    + " r.idPersona," //1
                    + " r.valorTexto," //2
                    + " r.valorNumerico," //3
                    + " r.valorFecha," //4
                    + " r.idArchivo," //5
                    + " r.idVariableAlmacenamiento," //6
                    + " r.indice " + //7
                    " FROM requisitos r " +
                    " INNER JOIN variablealmacenamiento va on (r.idVariableAlmacenamiento=va.idVariableAlmacenamiento) "+
                    " WHERE  " +
                    "r.idPersona = "+idEmpleado+" AND va.idRazonAlmacenamiento = "+tipoDocumento+" " 
                    +"  ORDER BY indice ASC, idRequisitos DESC;     ";        
        R=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 8);
        return R;
    }
    
    
    
    public String[][] getDocumentoById(String id){
         String R[][];
         
         String SQL="SELECT  " +
                    "	r.idRequisitos, " +
                    "	r.valorTexto, " +
                    "	r.valorNumerico, " +
                    "	r.valorFecha, " +
                    "	r.idArchivo  " +
                    "FROM  requisitos r  " +
                    "where  " +
                    "	r.idRequisitos =  "+id+"  ";
          R=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 5);
         
        
        
         return R;
    }
    
    
    public String insertDocument(archivoSerializableParaBD archivo){
        
        String ultimoInsertado=null;
        
        utilidadVinculoBD.operacionSQLupdateBlob("Archivo", "idArchivo", 0, "archivo", archivo,"nombreArchivo",true);
        
        String R[][];
        String SQL=" SELECT @@identity  ";
        R=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
        if (R.length>0){
            ultimoInsertado=R[0][0];
        }
        return ultimoInsertado;
    }
    
    public String insertDocument(archivoSerializableParaBD archivo,int id){
        
        utilidadVinculoBD.operacionSQLupdateBlob("Archivo", "idArchivo", id, "archivo", archivo,"nombreArchivo",false);
        return id+"";
        
    }
    
    
    
    public String insertaRequisito(String idPersona,String idArchivo,String idVariableAlmacenamiento){
        
        String insertado=null;
        String SQL = "INSERT INTO requisitos (`idPersona`, `idArchivo`, `idVariableAlmacenamiento`)  "
                + "  VALUES ('"+idPersona+"', '"+idArchivo+"', '"+idVariableAlmacenamiento+"');";
        utilidadVinculoBD.operacionSQL(SQL);
        
        SQL="SELECT @@identity AS id";
        String R[][] = ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
        
        if (R.length>0){
            insertado=R[0][0];
        } 
        
        return insertado;
        
    }
    
    public String insertaRequisito(String idPersona,String idArchivo,String idVariableAlmacenamiento, String indice){
        
        String insertado=null;
        String SQL = "INSERT INTO requisitos (`idPersona`, `idArchivo`, `idVariableAlmacenamiento`,`indice`)  "
                + "  VALUES ('"+idPersona+"', '"+idArchivo+"', '"+idVariableAlmacenamiento+"','"+indice+"');";
        utilidadVinculoBD.operacionSQL(SQL);
        
        SQL="SELECT @@identity AS id";
        String R[][] = ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
        
        if (R.length>0){
            insertado=R[0][0];
        } 
        
        return insertado;
        
    }
    
    public String consultaIndiceDeRequerimiento(String idPersona, String idVariableAlmacenamiento ){
        
        String indice=null;
        
        String SQL= "SELECT r.indice  " +
                    "FROM variablealmacenamiento va  " +
                    "INNER JOIN requisitos r on (va.idVariableAlmacenamiento = r.idVariableAlmacenamiento   ) " +
                    "WHERE  " +
                    "va.idRazonAlmacenamiento = "+idVariableAlmacenamiento+"  " +
                    "AND " +
                    "r.idPersona = "+idPersona+" " +
                    "ORDER BY r.indice DESC "+
                    " ;   ";
        String R[][] = ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
        
        if (R.length>0){
            indice=R[0][0];
        } 
        
        return indice;
    }
    
    public String insertaInformacion(String idPersona,String idVariableAlmacenamiento,String tipo,String valor){
        
        
        String insertado=null;
        
        String SQL="";
        
        
        
        if (tipo.equals("1")){
            
            SQL = "INSERT INTO requisitos (`idPersona`, `valorTexto`, `idVariableAlmacenamiento`)  "
                    + "  VALUES ('"+idPersona+"', '"+valor+"', '"+idVariableAlmacenamiento+"');";
            
        }
        if (tipo.equals("3")){
            
            SQL = "INSERT INTO requisitos (`idPersona`, `valorNumerico`, `idVariableAlmacenamiento`)  "
                    + "  VALUES ('"+idPersona+"', '"+valor+"', '"+idVariableAlmacenamiento+"');";
            
        }
        if (tipo.equals("4")){
            
            SQL = "INSERT INTO requisitos (`idPersona`, `valorFecha`, `idVariableAlmacenamiento`)  "
                    + "  VALUES ('"+idPersona+"', '"+valor+"', '"+idVariableAlmacenamiento+"');";
            
        }
        
        
        
        utilidadVinculoBD.operacionSQL(SQL);
        
        
        SQL="SELECT @@identity AS id";
        String R[][] = ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
        
        if (R.length>0){
            insertado=R[0][0];
        } 
        
        return insertado;
        
        
        
        
    }
    
    
    
    public String insertaInformacion(String idPersona,String idVariableAlmacenamiento,String tipo,String valor, String indice){
        
        String insertado=null;
        
        String SQL="";
        
        
        
        if (tipo.equals("1")){
            
            SQL = "INSERT INTO requisitos (`idPersona`, `valorTexto`, `idVariableAlmacenamiento`,`indice`)  "
                    + "  VALUES ('"+idPersona+"', '"+valor+"', '"+idVariableAlmacenamiento+"',"+indice+");";
            
        }
        if (tipo.equals("3")){
            
            SQL = "INSERT INTO requisitos (`idPersona`, `valorNumerico`, `idVariableAlmacenamiento`,`indice`)  "
                    + "  VALUES ('"+idPersona+"', '"+valor+"', '"+idVariableAlmacenamiento+"',"+indice+");";
            
        }
        if (tipo.equals("4")){
            
            SQL = "INSERT INTO requisitos (`idPersona`, `valorFecha`, `idVariableAlmacenamiento`,`indice`)  "
                    + "  VALUES ('"+idPersona+"', '"+valor+"', '"+idVariableAlmacenamiento+"',"+indice+");";
            
        }
        
        
        
        utilidadVinculoBD.operacionSQL(SQL);
        
        
        SQL="SELECT @@identity AS id";
        String R[][] = ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
        
        if (R.length>0){
            insertado=R[0][0];
        } 
        
        return insertado;
        
    }
    
    
    
}
