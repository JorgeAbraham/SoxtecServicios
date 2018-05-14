/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviciosBD;

import utilidadesbasicas.utilidadVinculoBD;

/**
 *
 * @author usuario
 */
public class pagos_fijos_servicio {
    

    public String[][] listaPagosFijos( ){
        
        String SQL= " SELECT " +
                    "	pf.idPagosFijos, " +
                    "	DATE_FORMAT(pf.fechaUltimoPago, '%d/%m/%Y'),  " +
                    "	pf.Titulo,  " +
                    "	pf.Descripcion,  " +
                    "	p.nombre,  " +
                    "	pf.monto,  " +
                    "	DATE_FORMAT(pf.fechaDeCalculo, '%d/%m/%Y') " +
                    " FROM pagosfijos pf " +
                    " INNER JOIN periodo p on (pf.idPeriodo = p.idPeriodo) ";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 7);
        return R;
        
    }
    
    
    
    public String[][] listaPeriodos( ){
        
        String SQL= " SELECT idPeriodo, nombre, cantidad, idUnidadDeTiempo " +
                    " FROM `periodo`; ";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 4);
        return R;
        
    }
    
    
    public String[][] listaPeriodos( String idPeriodo){
        
        String SQL= " SELECT idPeriodo, nombre, cantidad, idUnidadDeTiempo " +
                    " FROM periodo   "
                    + "  WHERE idPeriodo = "+idPeriodo+"  ; ";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 4);
        return R;
        
    }
    
    
    
    
    public String insertaPagoFijo(String fechaUltimoPago, String Titulo, String Descripcion, String idPeriodo, String monto, String fechaDeCalculo,String cuenta){
        
        String insertado=null;
        String SQL = "  INSERT INTO `pagosfijos` (`fechaUltimoPago`, `Titulo`, `Descripcion`, `idPeriodo`, `monto`, `fechaDeCalculo`, `cuenta`) "
                + "  VALUES ('"+fechaUltimoPago+"', '"+Titulo+"', '"+Descripcion+"', '"+idPeriodo+"', '"+monto+"', '"+fechaDeCalculo+"'  ,'"+cuenta+"' ); "; 
        utilidadVinculoBD.operacionSQL(SQL);
        
        SQL="SELECT @@identity AS id";
        String R[][] = ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
        
        if (R.length>0){
            insertado=R[0][0];
        } 
        
        return insertado;
        
    } 
    
    public String insertaCorreoPagoFijo(String PagoFijo, String Usuario){
        
        String insertado=null;
        String SQL = "  INSERT INTO `correopagofijo` (`idPagosFijos`, `idusuarios`)  "
                + "  VALUES ('"+PagoFijo+"', '"+Usuario+"');   ";
                
        utilidadVinculoBD.operacionSQL(SQL);
        
        SQL="SELECT @@identity AS id";
        String R[][] = ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
        
        if (R.length>0){
            insertado=R[0][0];
        } 
        
        return insertado;
        
    } 
    
    
    public String insertaPagoProgramado(String fechaDePago, String idOperacion, String idPagosFijos,String activo,String token){
        
        String insertado=null;
        String SQL = "  INSERT INTO `pagosprogramados` (`fechaDePago`, `idOperacion`, `idPagosFijos`, `activo`, `token`) "
                + "   VALUES    ('"+fechaDePago+"', '"+idOperacion+"', '"+idPagosFijos+"', "+activo+", '"+token+"');  ";
                
        utilidadVinculoBD.operacionSQL(SQL);
        
        SQL="SELECT @@identity AS id";
        String R[][] = ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
        
        if (R.length>0){
            insertado=R[0][0];
        } 
        
        return insertado;
        
    } 


    public String[][] listaPagosProgramados( ){
        
        String SQL= " SELECT  " +
                    "	pf.idPagosFijos,  " +  //0
                    "	pf.fechaUltimoPago,  "  +  //1
                    "	pf.Titulo,  " + //2
                    "	pf.Descripcion,  " + //3
                    "	pf.idPeriodo,  " + //4
                    "	pf.monto,  " + //5
                    "	DATE_FORMAT(pf.fechaDeCalculo, '%d/%m/%Y'),  " + //6
                    "	pf.cuenta,  " + //7
                    "	pp.idPagosProgramados,  " +//8
                    "	DATE_FORMAT(pp.fechaDePago, '%d/%m/%Y'),  " + //9
                    "	pp.idOperacion,  " + //10
                    "	pp.idPagosFijos,  " + //11
                    "	pp.activo,  " + //12
                    "	pp.token  " + //13
                    "FROM pagosprogramados pp " +
                    "INNER JOIN pagosfijos  pf on (pp.idPagosFijos=pf.idPagosFijos) ";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 14);
        return R;
        
    }
    
    
    public String[][] listaPagosProgramadosSinPagar( ){
        
        String SQL= " SELECT   " +
                    "	pf.idPagosFijos,  	" + //0
                    "	pf.fechaUltimoPago,  	" + //1
                    "	pf.Titulo,  	" + //2
                    "	pf.Descripcion,  	" + //3
                    "	pf.idPeriodo,  	" + //4
                    "	pf.monto,  	" + //5
                    "	pf.fechaDeCalculo,  	" + //6
                    "	pf.cuenta,  	" + //7
                    "	pp.idPagosProgramados,  	" + //8
                    "	pp.fechaDePago,  	" + //9
                    "	pp.idOperacion,  	" + //10
                    "	pp.idPagosFijos,  	" + //11
                    "	pp.activo,  	" + //12
                    "	pp.token  , " + //13
                    "	u.email  " + //14
                    " FROM pagosprogramados pp  " +
                    " INNER JOIN pagosfijos  pf on (pp.idPagosFijos=pf.idPagosFijos)  " +
                    " INNER JOIN operacion o on (o.idOperacion=pp.idOperacion)  " +
                    " INNER JOIN correopagofijo cpf on (cpf.idPagosFijos=pf.idPagosFijos) " +
                    " INNER JOIN usuarios u on (u.idusuarios=cpf.idusuarios) " +
                    " WHERE (o.idCatalogoOperacion = 10 OR o.idCatalogoOperacion = 11 OR o.idCatalogoOperacion = 12 )  " +
                    " AND (o.idEstadoOperacion = 9  OR o.idEstadoOperacion = 10 OR o.idEstadoOperacion = 11 OR o.idEstadoOperacion = 12 OR o.idEstadoOperacion = 13 OR o.idEstadoOperacion = 14 )  ; ";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 15);
        return R;
        
    }
    
    
    
}
