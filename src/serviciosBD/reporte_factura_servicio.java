/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviciosBD;

import utilidadesbasicas.utilidadVinculoBD;

/**
 *
 * @author Soxtec Desarrollo
 */
public class reporte_factura_servicio {
    
    
    
     public String[][] consultaReporteFactura( String idFactura){
        
        String SQL= " SELECT " +
                
                    "	idReporteFactura, " +  //0
                    "	idFactura, " +  //1
                    "	fecha,  " +  //2
                    "	numeroDeParte,  " + //3
                    "	campo1,  " + //4
                
                    "	campo2,  " + //5
                    "	campo3,  " + //6
                    "	campo4,  " + //7
                    "	campo5, " + //8
                    "	residente, " + //9
                
                    "	aprobacion, " +  //10
                    "	descripcion  " + //11
                
                    " FROM `reportefactura`   "
                + "  WHERE  idFactura="+idFactura+"   ; ";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 12);
        return R;
        
    }
    
    
    
    
    public String[][] consultaReporteDetalleFactura( String idReporteFacturacion){
        
        String SQL= " SELECT   "
                    + "   idfechasReporteFacturacion, "   //0
                    + "  idReporteFacturacion,  "    //1
                    + "  fecha,  " //2
                    + "  sorteos,  " //3
                    + "   rejected,   "  //4
                    + "   reworked   " + //5
                    " FROM `fechasreportefacturacion` "
                + "   WHERE idReporteFacturacion='"+idReporteFacturacion+"'  ; ";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 6);
        return R;
        
    }
    
    
    public void borraReporteDetalle(String idReporteFacturacion){
        String SQL= " DELETE FROM `fechasreportefacturacion` WHERE `idReporteFacturacion`='"+idReporteFacturacion+"';  ";
        utilidadVinculoBD.operacionSQL(SQL);
    }
    
    
    public String agregaDetalleReporte(String idReporteFacturacion, String fecha, String sorteos, String rejected, String reworked ){
        String SQL= "  INSERT INTO `fechasreportefacturacion` (`idReporteFacturacion`, `fecha`, `sorteos`, `rejected`, `reworked`)  "
                + "    VALUES ('"+idReporteFacturacion+"', '"+fecha+"', '"+sorteos+"', '"+rejected+"', '"+reworked+"');  ";
        utilidadVinculoBD.operacionSQL(SQL);
        
        SQL="SELECT @@identity AS id";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
        return R[0][0];
    }

    
    public void borraReporte(String idFactura){
        String SQL= " DELETE FROM `reportefactura` WHERE `idFactura`='"+idFactura+"';  ";
        utilidadVinculoBD.operacionSQL(SQL);
    }
    
    public String agregaReporte(
                                    String idFactura,
                                    String fecha, 
                                    String numeroDeParte, 
                                    String campo1, 
                                    String campo2, 
                                    
                                    String campo3, 
                                    String campo4, 
                                    String campo5, 
                                    String residente ,
                                    String aprobacion,
                                    
                                    
                                    String descripcion ){
        String SQL= "  INSERT INTO reportefactura (`idFactura`, `fecha`, `numeroDeParte`, `campo1`, `campo2`, `campo3`, `campo4`, `campo5`, `residente`, `aprobacion`, `descripcion`) "
                +   "   VALUES ('"+idFactura+"', '"+fecha+"', '"+numeroDeParte+"', '"+campo1+"', '"+campo2+"', '"+campo3+"', '"+campo4+"', '"+campo5+"', '"+residente+"', '"+aprobacion+"', '"+descripcion+"');  ";
        utilidadVinculoBD.operacionSQL(SQL);
        
        SQL="SELECT @@identity AS id";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
        return R[0][0];
    }




    
    
}
