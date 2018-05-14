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
 * @author Abraham
 */
public class detalle_factura_servicio {
    int idDetalleFactura;
    String descripcion;
    float cantidad;
    float preciounitario;
    float total;
    int idFactura;

    public int getIdDetalleFactura() {
        return idDetalleFactura;
    }

    public void setIdDetalleFactura(int idDetalleFactura) {
        this.idDetalleFactura = idDetalleFactura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public float getPreciounitario() {
        return preciounitario;
    }

    public void setPreciounitario(float preciounitario) {
        this.preciounitario = preciounitario;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }
    
    public void insertaDetalleFactura(){
        
        
        String SQL="INSERT INTO `detallefactura` (`descripcion`, `cantidad`, `precioUnitario`, `total`, `idFactura`) "
                + "VALUES ('"+descripcion+"', '"+cantidad+"', '"+preciounitario+"', '"+total+"', '"+idFactura+"');   ";
        utilidadVinculoBD.operacionSQL(SQL);

    }
         
    
    public detalle_factura_servicio informacionConceptoPrincipalDescripcion(String idFactura) {
        
        String R[][];
        String SQL="SELECT idDetalleFactura, descripcion, cantidad, precioUnitario, total, idFactura " +
                    "FROM detallefactura where precioUnitario=0 and idFactura="+idFactura+";";
                    
        R=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 6);
        
        detalle_factura_servicio detalleFactura= new detalle_factura_servicio();
        detalleFactura.setIdDetalleFactura(Integer.parseInt(R[0][0]));
        detalleFactura.setDescripcion(R[0][1]);
        detalleFactura.setCantidad(Float.parseFloat(R[0][2]));
        detalleFactura.setPreciounitario(Float.parseFloat(R[0][3]));
        detalleFactura.setTotal(Float.parseFloat(R[0][4]));
        detalleFactura.setIdFactura(Integer.parseInt(R[0][5]));
        
        
        
        return detalleFactura;
        
    }
    
    public DefaultTableModel informacionConceptoDescripcion(String idFactura) {
        
        DefaultTableModel Tabla;
        String SQL="SELECT cantidad,descripcion,  precioUnitario " +
                    "FROM detallefactura where precioUnitario<>0 and idFactura="+idFactura+"  AND "  +
                    "  descripcion<>'"+factura_servicio.STRhorasExtra+"'    AND descripcion<>'"+factura_servicio.STRhorasRegulares+"'  ;";
                    
        String columnas[]=new String[3];
        columnas[0]="Cantidad";
        columnas[1]="Descripción";
        columnas[2]="Precio";
        Tabla=ManejadorDeDatos.BD.consultaTabla(SQL, columnas);
        return Tabla;
        
    }
    
    
    
}
