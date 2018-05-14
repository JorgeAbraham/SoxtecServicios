/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviciosBD;

import java.util.ArrayList;
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
    
     public persona_servicio() {
         
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
    
    public DefaultTableModel LISTAempleados(String textoBusqueda){
        
        DefaultTableModel Tabla;
        String SQL="SELECT CONCAT(p.apellidoPaterno,' ',p.apellidoMaterno,' ',nombre ),idPersona  FROM persona p INNER JOIN tipopersona tp  ON (p.idTipoPersona=tp.idTipoPersona)  WHERE p.idTipoPersona=2   "
                + " AND "
                + " ("
                + "p.apellidoPaterno like '%"+textoBusqueda+"%'  OR "
                + "p.apellidoMaterno like '%"+textoBusqueda+"%'  OR "
                + "nombre  like '%"+textoBusqueda+"%'    "
                + "OR  CONCAT(p.apellidoPaterno,' ',p.apellidoMaterno,' ',nombre )  like '%"+textoBusqueda+"%'   "
                + ")   "
                + " ; ";
        String columnas[]=new String[2];
        columnas[0]="Empleado";
        columnas[1]="Num"; 
        Tabla=ManejadorDeDatos.BD.consultaTabla(SQL, columnas);
        return Tabla;
    }
    
    
    public DefaultTableModel  LISTAempleados() {
        
        DefaultTableModel Tabla;
        String SQL="SELECT CONCAT(p.apellidoPaterno,' ',p.apellidoMaterno,' ',nombre ),idPersona  FROM persona p INNER JOIN tipopersona tp  ON (p.idTipoPersona=tp.idTipoPersona)  WHERE p.idTipoPersona=2; ";
        String columnas[]=new String[2];
        columnas[0]="Empleado"; 
        columnas[1]="Num"; 
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
    
    
    public String[][] LISTAempleadosEstadoActivoYUsuarioAdministradoresString(String estado) {
        String Tabla[][];
        String SQL= "SELECT * " +
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
                    "UNION " +
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
                    "	WHERE p.idTipoPersona=2 AND r.idVariableAlmacenamiento = 29  " +
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
                    "	WHERE p.idTipoPersona=2  AND r.idVariableAlmacenamiento = 29 " +
                    "	ORDER BY idPersona ASC, idRequisitos DESC) as v " +
                    " GROUP BY v.idPersona " +
                    " HAVING v.valorTexto='sinDefinir' " +
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
