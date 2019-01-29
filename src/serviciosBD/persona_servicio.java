/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviciosBD;

import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import utilidadesbasicas.utilidadVinculoBD;

/**
 *
 * @author Abraham
 */
public class persona_servicio {
    
    int idPersona;
    

    String apellidoPaterno;
    String apellidoMaterno;
    String nombre;
    int idTipoPersona;
     
     
    
    public static String ALTA="alta";
    
    public static String BAJA="baja";
    
    public static String PARO_TECNICO="paroTecnico";
    
    public static String SOLICITUD_BAJA="solictudBaja";
    
    public static String SOLICITUD_ALTA="solictudAlta";
    
     public persona_servicio() {
         
     }
    
     
    public String[][] LISTAEmpleadosPorStringYSinAsignar(String condiciones,String whereCondiciones) {
    
        
        String Where="";
        
        if (whereCondiciones!=null){
            Where="WHERE "+whereCondiciones;
        }
        
        
        
        String Tabla[][];
        String SQL= " SELECT * FROM ( "
                +   " Select " +
                    " *  " +
                    " FROM " +
                    "	(SELECT  " +
                    "		CONCAT(p.apellidoPaterno,' ',p.apellidoMaterno,' ',p.nombre ) as name, " +
                    "		p.idPersona, " +
                    "		r.idRequisitos, " +
                    "		r.valorTexto, " +
                    "           l.nombre "+
                    "	FROM persona p  " +
                    "	INNER JOIN tipopersona tp  ON (p.idTipoPersona=tp.idTipoPersona) " +
                    "	INNER JOIN requisitos r ON r.idPersona=p.idPersona " +
                    "   LEFT JOIN personalugar pl ON p.idPersona=pl.Persona_idPersona " +
                    "   LEFT JOIN lugar l on pl.Lugar_idLugar=l.idLugar "+
                    "	WHERE p.idTipoPersona=2  AND r.idVariableAlmacenamiento = 30 " +
                    "	ORDER BY idPersona ASC, idRequisitos DESC) as v " +
                    " GROUP BY v.idPersona " +
                    " HAVING "+condiciones+"  " +
                    " UNION " +
                    " SELECT " +
                    "		CONCAT(p.apellidoPaterno,' ',p.apellidoMaterno,' ',p.nombre ) as name, " +
                    "		p.idPersona, " +
                    "		r.idRequisitos, " +
                    "		r.valorTexto ," +
                    "           l.nombre "+
                    "	FROM persona p  " +
                    "	INNER JOIN tipopersona tp  ON (p.idTipoPersona=tp.idTipoPersona) " +
                    "	LEFT JOIN requisitos r ON r.idPersona=p.idPersona " +
                    "   LEFT JOIN personalugar pl ON p.idPersona=pl.Persona_idPersona " +
                    "   LEFT JOIN lugar l on pl.Lugar_idLugar=l.idLugar "+
                    "	WHERE p.idTipoPersona=2  AND r.idRequisitos is null   "
                + "    ) as Tabla   "+Where+"   ORDER BY name ";
     
        Tabla=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 5);
        return Tabla;
    
    }
     
     
     
    public String[][] LISTAEmpleadosPorStringYSinAsignar(String condiciones) {
        
        return LISTAEmpleadosPorStringYSinAsignar(condiciones,null);
        
    }
     
     
     
     
    public persona_servicio(int idUsuario) {
        String SQL="SELECT p.idPersona, p.apellidoPaterno, p.apellidoMaterno, p.nombre, p.idTipoPersona " +
                    "FROM persona p " +
                    "INNER JOIN usuarios u ON (u.idPersona=p.idPersona) " +
                    "WHERE u.idusuarios="+idUsuario+"; ";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 5);
        
        for (int i=0;i<R[0].length;i++){
            idPersona=Integer.parseInt(R[0][0]);
            apellidoPaterno=R[0][1]!=null?R[0][1]:"";
            apellidoMaterno=R[0][2]!=null?R[0][2]:"";
            nombre=R[0][3]!=null?R[0][3]:"";
            idTipoPersona=Integer.parseInt(R[0][4]);
        }
        
    }
    
    
    public void get(){
        String SQL="SELECT p.idPersona, p.apellidoPaterno, p.apellidoMaterno, p.nombre, p.idTipoPersona " +
                    "FROM persona p " +
                    "WHERE p.idPersona="+idPersona+"; ";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 5);
        
        for (int i=0;i<R.length;i++){
            idPersona=Integer.parseInt(R[0][0]);
            apellidoPaterno=R[0][1]!=null?R[0][1]:"";
            apellidoMaterno=R[0][2]!=null?R[0][2]:"";
            nombre=R[0][3]!=null?R[0][3]:"";
            idTipoPersona=Integer.parseInt(R[0][4]);
        }
    }
    
    
    public DefaultComboBoxModel listaComboPorPersonaEspacionEnBlanco( ){
        DefaultComboBoxModel combo;
        String SQL=" SELECT '' "
                + " UNION "
                + "  SELECT CONCAT('[',idPersona,']-', apellidoPaterno,' ', apellidoMaterno,' ', nombre)   " +
                "FROM persona     "+
                " WHERE idTipoPersona=2;     ";
        combo=ManejadorDeDatos.BD.consultaCombo(SQL);
        return combo;
    }
    
    
    public DefaultComboBoxModel listaComboPorPersonaEspacionEnBlanco(  ArrayList ids  ){
        DefaultComboBoxModel combo;
        
        String IDS=utilidadesbasicas.listToString.listaEntreComas(ids);
        
        
        String SQL=" SELECT '' "
                + " UNION "
                + "  SELECT CONCAT('[',idPersona,']-', apellidoPaterno,' ', apellidoMaterno,' ', nombre)   " 
                + "  FROM persona     "+
                " WHERE idTipoPersona=2      "
                + "  AND  idPersona IN("+IDS+")    ;     ";
        combo=ManejadorDeDatos.BD.consultaCombo(SQL);
        return combo;
    }
    
    
    public DefaultTableModel LISTAempleados(String textoBusqueda){
        
        DefaultTableModel Tabla;
        String SQL="SELECT CONCAT(p.apellidoPaterno,' ',p.apellidoMaterno,' ',p.nombre ),p.idPersona ,l.nombre  "
                + "   FROM persona p  "
                + " INNER JOIN tipopersona tp  ON (p.idTipoPersona=tp.idTipoPersona)  "
                + " LEFT JOIN personalugar pl ON (pl.Persona_idPersona  = p.idPersona) " 
                + " LEFT JOIN lugar l ON (l.idLugar=pl.Lugar_idLugar) " 
                + "     WHERE p.idTipoPersona=2   "
                + " AND "
                + " ("
                + "p.apellidoPaterno like '%"+textoBusqueda+"%'  OR "
                + "p.apellidoMaterno like '%"+textoBusqueda+"%'  OR "
                + "p.nombre  like '%"+textoBusqueda+"%'    "
                + "OR  CONCAT(p.apellidoPaterno,' ',p.apellidoMaterno,' ',p.nombre )  like '%"+textoBusqueda+"%'   "
                + ")   "
                + " ; ";
        String columnas[]=new String[3];
        columnas[0]="Employee";
        columnas[1]="Num"; 
        columnas[2]="Place"; 
        Tabla=ManejadorDeDatos.BD.consultaTabla(SQL, columnas);
        return Tabla;
    }
    
    
    public DefaultTableModel  LISTAempleados() {
        
        DefaultTableModel Tabla;
        String SQL=" SELECT CONCAT(p.apellidoPaterno,' ',p.apellidoMaterno,' ',p.nombre ),p.idPersona  ,l.nombre " +
                    " FROM persona p  " +
                    " INNER JOIN tipopersona tp  ON (p.idTipoPersona=tp.idTipoPersona)   " +
                    " LEFT JOIN personalugar pl ON (pl.Persona_idPersona  = p.idPersona) " +
                    " LEFT JOIN lugar l ON (l.idLugar=pl.Lugar_idLugar) " +
                    " WHERE p.idTipoPersona=2;  ";
        String columnas[]=new String[3];
        columnas[0]="Empleado"; 
        columnas[1]="Num"; 
        columnas[2]="Lugar"; 
        Tabla=ManejadorDeDatos.BD.consultaTabla(SQL, columnas);
        return Tabla;
        
    }
    
    
    
    
    
    public String[][] LISTAempleadosString() {
        
        String Tabla[][];
        String SQL="SELECT "
                + " CONCAT(p.apellidoPaterno,' ',p.apellidoMaterno,' ',p.nombre ) , "
                + " idPersona ,  "
                + " l.nombre "
                + " FROM persona p "
                + " INNER JOIN tipopersona tp  ON (p.idTipoPersona=tp.idTipoPersona) "
                + " LEFT JOIN personalugar pl ON p.idPersona=pl.Persona_idPersona " 
                + " LEFT JOIN lugar l on pl.Lugar_idLugar=l.idLugar "
                + "  WHERE p.idTipoPersona=2; ";
     
        Tabla=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 3);
        return Tabla;
        
    }
    
    
    public String[][] LISTAempleadosString(boolean Ordenado) {
        
        
        String ordenado="";
        if (Ordenado==true){
            ordenado="ORDER BY  p.apellidoPaterno ASC";
        }
        
        
        String Tabla[][];
        String SQL="SELECT "
                + " CONCAT(p.apellidoPaterno,' ',p.apellidoMaterno,' ',p.nombre ) , "
                + " idPersona ,  "
                + " l.nombre "
                + " FROM persona p "
                + " INNER JOIN tipopersona tp  ON (p.idTipoPersona=tp.idTipoPersona) "
                + " LEFT JOIN personalugar pl ON p.idPersona=pl.Persona_idPersona " 
                + " LEFT JOIN lugar l on pl.Lugar_idLugar=l.idLugar "
                + "  WHERE p.idTipoPersona=2    "
                + ordenado
                + " ; ";
     
        Tabla=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 3);
        return Tabla;
        
    }
    
    
    public String[][] LISTAempleadosString( String BuscarNombre ,String Lugar) {
        
        String SQLbuscarNombre="";
        if (BuscarNombre!=null){
        
            
            SQLbuscarNombre =SQLbuscarNombre + "AND (  (p.apellidoPaterno like '%"+BuscarNombre+"%'  OR  p.apellidoMaterno like '%"+BuscarNombre+"%'   OR  p.nombre like '%"+BuscarNombre+"%'  )     ";
            SQLbuscarNombre =SQLbuscarNombre + "OR  CONCAT(p.apellidoPaterno,' ',p.apellidoMaterno,' ',p.nombre )  like '%"+BuscarNombre+"%'   ";
            SQLbuscarNombre =SQLbuscarNombre+" ) ";  
        }
        
         String SQLlugar="";
        if (Lugar!=null && !Lugar.equals("")  ){
        
            
            SQLlugar =SQLlugar + "AND (     ";
            SQLlugar =SQLlugar +  " l.idLugar =  "+ Lugar;
            SQLlugar =SQLlugar+" ) ";  
        }
        
        String Tabla[][];
        String SQL="SELECT "
                + " CONCAT(p.apellidoPaterno,' ',p.apellidoMaterno,' ',p.nombre ) , "
                + " idPersona ,  "
                + " l.nombre "
                + " FROM persona p "
                + " INNER JOIN tipopersona tp  ON (p.idTipoPersona=tp.idTipoPersona) "
                + " LEFT JOIN personalugar pl ON p.idPersona=pl.Persona_idPersona " 
                + " LEFT JOIN lugar l on pl.Lugar_idLugar=l.idLugar "
                + "  WHERE p.idTipoPersona=2     "  + SQLbuscarNombre + SQLlugar 
                + "   ; ";
     
        Tabla=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 3);
        return Tabla;
        
    }
    
    
    public String[][] porcentajeRequisitosLlenos(String idRazonAlmacentamiento ) {
        
        String Tabla[][];
        String SQL= "SELECT " +
                    "	CONCAT(p.apellidoPaterno,' ',p.apellidoMaterno,' ',p.nombre ),  " +
                    "	p.idPersona,  " +
                    "	l.nombre,   " +
                    "	vam.idVariableAlmacenamiento,  " +
                    "	vam.nombre as NombreVariable   ,  " +
                    "	SUM( IF( r2.idVariableAlmacenamiento IS NOT NULL, 1, 0) ) AS ValorExiste    ,  " +
                    "	SUM( IF( r2.idVariableAlmacenamiento IS NULL, 1, 0) ) AS ValorNoExiste    ,  " +
                    "	SUM( IF( r2.idVariableAlmacenamiento IS NOT NULL, 1, 0) ) * 100 / SUM( IF( r2.idVariableAlmacenamiento IS NULL, 1, 0) )  AS Porcentaje   " +
                    " FROM persona p  " +
                    " LEFT JOIN personalugar pl ON p.idPersona=pl.Persona_idPersona    	" +
                    " LEFT JOIN lugar l on pl.Lugar_idLugar=l.idLugar 	" +
                    " INNER JOIN variablealmacenamiento vam  " +
                    " LEFT JOIN (  " +
                    "	SELECT * FROM requisitos r  " +
                    "    GROUP BY r.idPersona,r.idVariableAlmacenamiento  " +
                    " ) AS r2  ON r2.idPersona=p.idPersona AND r2.idVariableAlmacenamiento = vam.idVariableAlmacenamiento  " +
                    " WHERE p.idTipoPersona=2 AND vam.idRazonAlmacenamiento="+idRazonAlmacentamiento+" " +
                    " GROUP BY p.idPersona " +
                    " ORDER BY p.idPersona, vam.idVariableAlmacenamiento  ";
     
        Tabla=ManejadorDeDatos.BD.ConsultaCuadro(SQL,8);
        return Tabla;
        
    }
    
    
    
    public String[][] LISTAempleadosEstadoActivoYUsuarioAdministradoresString(String estado) {
        String Tabla[][];
        String SQL= /*"SELECT * " +
                    "FROM " +
                    "	(SELECT " +
                    "		CONCAT(p.apellidoPaterno,' ',p.apellidoMaterno,' ',p.nombre ), " +
                    "		p.idPersona, " +
                    "		r.idRequisitos, " +
                    "		r.valorTexto " +
                    "	FROM persona p  " +
                    "	INNER JOIN tipopersona tp  ON (p.idTipoPersona=tp.idTipoPersona)  " +
                    "	INNER JOIN requisitos r ON r.idPersona=p.idPersona " +
                    "	WHERE p.idTipoPersona=2 AND r.idVariableAlmacenamiento = 29  " +
                    "	ORDER BY idPersona ASC, idRequisitos DESC) as v " +
                    "GROUP BY v.idPersona " +
                    "HAVING v.valorTexto='"+estado+"'    "+
                    "UNION " + */
                    "SELECT 		" +
                    "	CONCAT(p.apellidoPaterno,' ',p.apellidoMaterno,' ',p.nombre ), 		" +
                    "	p.idPersona, 		" +
                    "	0, 		" +
                    "	0 " +
                    "	FROM persona p  	" +
                    "INNER JOIN tipopersona tp  ON (p.idTipoPersona=tp.idTipoPersona)  	" +
                    "WHERE p.idTipoPersona=1    ";
     
        Tabla=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 4);
        return Tabla;
        
    }
    
    
    
    public String[][] LISTAUsuarioString() {
        
        String Tabla[][];
        String SQL= "SELECT 		" +
                    "	CONCAT(p.apellidoPaterno,' ',p.apellidoMaterno,' ',p.nombre ), 		p.idPersona	" +
                    "	FROM persona p  	" +
                    "	INNER JOIN tipopersona tp  ON (p.idTipoPersona=tp.idTipoPersona)  	" +
                    "	LEFT JOIN personalugar pl ON p.idPersona=pl.Persona_idPersona    " +
                    "	WHERE " +
                    "	p.idTipoPersona=1 	" +
                    "	ORDER BY idPersona ASC ";
     
        Tabla=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 2);
        return Tabla;
        
    }
    
    
    
    public String[][] LISTAempleadosEstadoActivoString(String estado) {
        
        String Tabla[][];
        String SQL= "SELECT * " +
                    "FROM " +
                    "	(SELECT " +
                    "		CONCAT(p.apellidoPaterno,' ',p.apellidoMaterno,' ',p.nombre ), " +
                    "		p.idPersona, " +
                    "		r.idRequisitos, " +
                    "		r.valorTexto ," +
                    "           l.nombre "+ 
                    "	FROM persona p  " +
                    "	INNER JOIN tipopersona tp  ON (p.idTipoPersona=tp.idTipoPersona)  " +
                    "	INNER JOIN requisitos r ON r.idPersona=p.idPersona " +
                    "   LEFT JOIN personalugar pl ON p.idPersona=pl.Persona_idPersona " +
                    "   LEFT JOIN lugar l on pl.Lugar_idLugar=l.idLugar "+
                    "	WHERE p.idTipoPersona=2 AND r.idVariableAlmacenamiento = 30  " +
                    "	ORDER BY idPersona ASC, idRequisitos DESC) as v " +
                    "GROUP BY v.idPersona " +
                    "HAVING v.valorTexto='"+estado+"' ";
     
        Tabla=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 5);
        return Tabla;
        
    }
    
    
   
    
    
    public String[][] LISTAempleadosEstadoActivoString(String estado, String BuscarNombre, String Lugar) {
        
        String SQLbuscarNombre="";
        if (BuscarNombre!=null){
        
            
            SQLbuscarNombre =SQLbuscarNombre + "AND (  (p.apellidoPaterno like '%"+BuscarNombre+"%'  OR  p.apellidoMaterno like '%"+BuscarNombre+"%'   OR  p.nombre like '%"+BuscarNombre+"%'  )     ";
            SQLbuscarNombre =SQLbuscarNombre + " OR  CONCAT(p.apellidoPaterno,' ',p.apellidoMaterno,' ',p.nombre )  like '%"+BuscarNombre+"%'   ";
            SQLbuscarNombre =SQLbuscarNombre+" ) ";  
        }
        
        String SQLlugar="";
        if (Lugar!=null && !Lugar.equals("")  ){
        
            
            SQLlugar =SQLlugar + "AND (     ";
            SQLlugar =SQLlugar +  " l.idLugar =  "+ Lugar;
            SQLlugar =SQLlugar+" ) ";  
        }
         
        
        String Tabla[][];
        String SQL= "SELECT * " +
                    "FROM " +
                    "	(SELECT " +
                    "		CONCAT(p.apellidoPaterno,' ',p.apellidoMaterno,' ',p.nombre ), " +
                    "		p.idPersona, " +
                    "		r.idRequisitos, " +
                    "		r.valorTexto ," +
                    "           l.nombre "+ 
                    "	FROM persona p  " +
                    "	INNER JOIN tipopersona tp  ON (p.idTipoPersona=tp.idTipoPersona)  " +
                    "	INNER JOIN requisitos r ON r.idPersona=p.idPersona " +
                    "   LEFT JOIN personalugar pl ON p.idPersona=pl.Persona_idPersona " +
                    "   LEFT JOIN lugar l on pl.Lugar_idLugar=l.idLugar "+
                    "	WHERE p.idTipoPersona=2 AND r.idVariableAlmacenamiento = 30  " +SQLbuscarNombre +  SQLlugar+
                    "	ORDER BY idPersona ASC, idRequisitos DESC) as v " +
                    "GROUP BY v.idPersona " +
                    "HAVING v.valorTexto='"+estado+"' ";
     
        Tabla=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 5);
        return Tabla;
        
    }
    
    
    
    public String[][] LISTAempleadosEstadoSinAsignar() {
        
        String Tabla[][];
        String SQL= " Select " +
                    " *  " +
                    " FROM " +
                    "	(SELECT  " +
                    "		CONCAT(p.apellidoPaterno,' ',p.apellidoMaterno,' ',p.nombre ), " +
                    "		p.idPersona, " +
                    "		r.idRequisitos, " +
                    "		r.valorTexto, " +
                    "           l.nombre "+
                    "	FROM persona p  " +
                    "	INNER JOIN tipopersona tp  ON (p.idTipoPersona=tp.idTipoPersona) " +
                    "	INNER JOIN requisitos r ON r.idPersona=p.idPersona " +
                    "   LEFT JOIN personalugar pl ON p.idPersona=pl.Persona_idPersona " +
                    "   LEFT JOIN lugar l on pl.Lugar_idLugar=l.idLugar "+
                    "	WHERE p.idTipoPersona=2  AND r.idVariableAlmacenamiento = 30 " +
                    "	ORDER BY idPersona ASC, idRequisitos DESC) as v " +
                    " GROUP BY v.idPersona " +
                    " HAVING v.valorTexto='sinDefinir' or v.valorTexto='ingresado'  " +
                    " UNION " +
                    " SELECT " +
                    "		CONCAT(p.apellidoPaterno,' ',p.apellidoMaterno,' ',p.nombre ), " +
                    "		p.idPersona, " +
                    "		r.idRequisitos, " +
                    "		r.valorTexto ," +
                    "           l.nombre "+
                    "	FROM persona p  " +
                    "	INNER JOIN tipopersona tp  ON (p.idTipoPersona=tp.idTipoPersona) " +
                    "	LEFT JOIN requisitos r ON r.idPersona=p.idPersona " +
                    "   LEFT JOIN personalugar pl ON p.idPersona=pl.Persona_idPersona " +
                    "   LEFT JOIN lugar l on pl.Lugar_idLugar=l.idLugar "+
                    "	WHERE p.idTipoPersona=2  AND r.idRequisitos is null ";
     
        Tabla=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 5);
        return Tabla;
        
    }
    
    
    public String[][] LISTAempleadosEstadoSinAsignar(String BuscarNombre,String Lugar) {
        
        
        String SQLbuscarNombre="";
        if (BuscarNombre!=null){
        
            
            SQLbuscarNombre =SQLbuscarNombre + "AND (  (p.apellidoPaterno like '%"+BuscarNombre+"%'  OR  p.apellidoMaterno like '%"+BuscarNombre+"%'   OR  p.nombre like '%"+BuscarNombre+"%'  )     ";
            SQLbuscarNombre =SQLbuscarNombre + "OR  CONCAT(p.apellidoPaterno,' ',p.apellidoMaterno,' ',p.nombre )  like '%"+BuscarNombre+"%'   ";
            SQLbuscarNombre =SQLbuscarNombre+" ) ";  
        }
        
        String SQLlugar="";
        if (Lugar!=null  && !Lugar.equals("")  ){
        
            
            SQLlugar =SQLlugar + "AND (     ";
            SQLlugar =SQLlugar +  " l.idLugar =  "+ Lugar;
            SQLlugar =SQLlugar+" ) ";  
        }
        
        
        String Tabla[][];
        String SQL= " Select " +
                    " *  " +
                    " FROM " +
                    "	(SELECT  " +
                    "		CONCAT(p.apellidoPaterno,' ',p.apellidoMaterno,' ',p.nombre ), " +
                    "		p.idPersona, " +
                    "		r.idRequisitos, " +
                    "		r.valorTexto, " +
                    "           l.nombre , "+
                    "           DATE_FORMAT(r2.valorFecha, '%d/%m/%Y') "+
                    "	FROM persona p  " +
                    "	INNER JOIN tipopersona tp  ON (p.idTipoPersona=tp.idTipoPersona) " +
                    "	INNER JOIN requisitos r ON r.idPersona=p.idPersona " +
                    "   INNER JOIN requisitos r2 ON  ( r2.idPersona=p.idPersona  AND r2.idVariableAlmacenamiento = 1   ) "+
                    "   LEFT JOIN personalugar pl ON p.idPersona=pl.Persona_idPersona " +
                    "   LEFT JOIN lugar l on pl.Lugar_idLugar=l.idLugar "+
                    "   "+
                
                
                    "	WHERE p.idTipoPersona=2  AND r.idVariableAlmacenamiento = 30 " +  SQLbuscarNombre  + SQLlugar +
                    "	ORDER BY idPersona ASC, idRequisitos DESC) as v " +
                    " GROUP BY v.idPersona " +
                    " HAVING v.valorTexto='sinDefinir' or v.valorTexto='ingresado'  " +
                    " UNION " +
                    " SELECT " +
                    "		CONCAT(p.apellidoPaterno,' ',p.apellidoMaterno,' ',p.nombre ), " +
                    "		p.idPersona, " +
                    "		r.idRequisitos, " +
                    "		r.valorTexto ," +
                    "           l.nombre ,"+
                    "           DATE_FORMAT(r2.valorFecha, '%d/%m/%Y') "+
                    
                    "	FROM persona p  " +
                    "	INNER JOIN tipopersona tp  ON (p.idTipoPersona=tp.idTipoPersona) " +
                    "	LEFT JOIN requisitos r ON r.idPersona=p.idPersona " +
                    "   INNER JOIN requisitos r2 ON  ( r2.idPersona=p.idPersona  AND r2.idVariableAlmacenamiento = 1   ) "+
                    "   LEFT JOIN personalugar pl ON p.idPersona=pl.Persona_idPersona " +
                    "   LEFT JOIN lugar l on pl.Lugar_idLugar=l.idLugar "+
                    "	WHERE p.idTipoPersona=2  AND r.idRequisitos is null "  +  SQLbuscarNombre  + SQLlugar  ;
     
        Tabla=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 6);
        return Tabla;
        
    }
    
    

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdTipoPersona() {
        return idTipoPersona;
    }

    public void setIdTipoPersona(int idTipoPersona) {
        this.idTipoPersona = idTipoPersona;
    }
    
    public boolean add(){
        
        boolean correct=false;
        
        String SQL="INSERT INTO `persona` (`apellidoPaterno`, `apellidoMaterno`, `nombre`, `idTipoPersona`)    "
                + "VALUES ('"+apellidoPaterno+"', '"+apellidoMaterno+"', '"+nombre+"', '"+idTipoPersona+"'); ";
        utilidadVinculoBD.operacionSQL(SQL);
        
        SQL="SELECT @@identity AS id";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
          
        if (R.length>0){
            correct=true;
        }
        
        return correct;
        
        
    }
    
    public String agregar(){
        
       
        String SQL="INSERT INTO `persona` (`apellidoPaterno`, `apellidoMaterno`, `nombre`, `idTipoPersona`)    "
                + "VALUES ('"+apellidoPaterno+"', '"+apellidoMaterno+"', '"+nombre+"', '"+idTipoPersona+"'); ";
        utilidadVinculoBD.operacionSQL(SQL);
        
        SQL="SELECT @@identity AS id";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
        return R[0][0];
        
        
    }
    
    public String agregaPersonaLugar(String lugar,String persona, String relacion){
        String SQL="INSERT INTO `personalugar` (`Lugar_idLugar`, `Persona_idPersona`, `idRelacionLugares`)  "
                + " VALUES ('"+lugar+"', '"+persona+"', '"+relacion+"'); ";
        utilidadVinculoBD.operacionSQL(SQL);
        
        SQL="SELECT @@identity AS id";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
        return R[0][0];
    }
    
    
    public String editaLugarPersona(String persona,String lugar){
        
        String SQL= " UPDATE `personalugar` SET `Lugar_idLugar`='"+lugar+"'  WHERE `Persona_idPersona`='"+persona+"'; ";
        utilidadVinculoBD.operacionSQL(SQL);
        
        SQL= "SELECT Persona_idPersona FROM personalugar  WHERE`Persona_idPersona`='"+persona+"' ; ";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
        return R[0][0];
    }
    
    
    public boolean update(){
        
        boolean correct=false;
        
        String SQL="UPDATE `persona` SET `apellidoPaterno`='"+apellidoMaterno+"', `apellidoMaterno`='"+apellidoPaterno+"', `nombre`='"+nombre+"', `idTipoPersona`='"+idTipoPersona+"' WHERE `idPersona`='"+idPersona+"'; ";
        utilidadVinculoBD.operacionSQL(SQL);
       
        get();
        if(idPersona!=0){
              correct=true;
        }
        return correct;
       
       
    } 
    
    
    
    
    
}
