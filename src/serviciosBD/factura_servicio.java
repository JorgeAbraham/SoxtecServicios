/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviciosBD;


import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import utilidadesbasicas.utilidadVinculoBD;

/**
 *
 * @author Abraham
 */
public class factura_servicio {
    
    public static final String STRhorasRegulares="REGULAR MAN HOURS";
    public static final String STRhorasExtra="PREMIUM MAN HOURS";
    
    
    int idFactura;
    String claveFactura;
    String descripcion;
    String fecha;
    String nombre;
    String direccion;
    String solicitante;
    String po;
    String vendedor;
    String periodoInicio;
    String periodoFin;
    float subTotal;
    float impuestos;
    float expTram;
    float total;
    float precioHorasRegulares;
    float precioHorasExtra;
    
    String lugar;
    String estatus;
   

    public factura_servicio() {
        
    }
    
    
    

    public factura_servicio(int idFactura, String claveFactura, String descripcion, String fecha, String nombre, String direccion, String solicitante, String po, String vendedor, String periodoInicio, String periodoFin, float subTotal, float impuestos, float expTram, float total) {
        this.idFactura = idFactura;
        this.claveFactura = claveFactura;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.nombre = nombre;
        this.direccion = direccion;
        this.solicitante = solicitante;
        this.po = po;
        this.vendedor = vendedor;
        this.periodoInicio = periodoInicio;
        this.periodoFin = periodoFin;
        this.subTotal = subTotal;
        this.impuestos = impuestos;
        this.expTram = expTram;
        this.total = total;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public String getClaveFactura() {
        return claveFactura;
    }

    public void setClaveFactura(String claveFactura) {
        this.claveFactura = claveFactura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public String getPo() {
        return po;
    }

    public void setPo(String po) {
        this.po = po;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getPeriodoInicio() {
        return periodoInicio;
    }

    public void setPeriodoInicio(String periodoInicio) {
        this.periodoInicio = periodoInicio;
    }

    public String getPeriodoFin() {
        return periodoFin;
    }

    public void setPeriodoFin(String periodoFin) {
        this.periodoFin = periodoFin;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public float getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(float impuestos) {
        this.impuestos = impuestos;
    }

    public float getExpTram() {
        return expTram;
    }

    public void setExpTram(float expTram) {
        this.expTram = expTram;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getPrecioHorasRegulares() {
        return precioHorasRegulares;
    }

    public void setPrecioHorasRegulares(float precioHorasRegulares) {
        this.precioHorasRegulares = precioHorasRegulares;
    }

    public float getPrecioHorasExtra() {
        return precioHorasExtra;
    }

    public void setPrecioHorasExtra(float precioHorasExtra) {
        this.precioHorasExtra = precioHorasExtra;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
    
    
    
    
    
    public boolean claveFacturaRepetida(String claveFactura){
        boolean factura;
        
        String SQL="select claveFactura from factura where claveFactura='"+claveFactura+"' ; ";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
        
        factura = R.length>0;
        return factura;
    }
    
    public boolean claveOrdenRepetida(String po){
        boolean factura;
        
        String SQL="select po from factura where po='"+po+"' ;";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
        
        factura = R.length>0;
        return factura;
    }
    
    
    public DefaultTableModel  LISTAfacturasEstado() {
        
        DefaultTableModel Tabla;
        String SQL= "   SELECT  " +
                    "	f.claveFactura, " +
                    "   f.idfactura,  "+
                    "	f.po, " +
                    "	f.periodoInicio, " +
                    "	f.periodoFin, " +
                    "	f.fechaPago, " +
                    "	f.total, " +
                    "	c.valor " +
                    "FROM factura f " +
                    "left join catalogo c on c.idCatalogo=f.estadoPago"
                    + " ORDER BY f.idfactura DESC  ;";
        String columnas[]=new String[8];
        columnas[0]="Invoice"; 
        columnas[1]="Internal ID"; 
        columnas[2]="Order Puchase"; 
        columnas[3]="Start Date"; 
        columnas[4]="End Date"; 
        columnas[5]="Payment Date"; 
        columnas[6]="TOTAL"; 
        columnas[7]="State"; 
        Tabla=ManejadorDeDatos.BD.consultaTabla(SQL, columnas);
        return Tabla;
        
    }
    
    public boolean insertaFactura(
            
            boolean actualizarInformacion,
            
            DefaultTableModel tablaConceptos,
            String primerConceptoEscrito,
            String precioHorasRegulares,
            String precioHorasExtras,
            String totalHorasRegulares,
            
            String totalHorasExtras,
            int idEmpleadoMatutino[],
            String fechasMatutuno[][],
            JTextField TXThoraEntradaMatutino[][],
            JTextField TXThoraTrabajadaMatutino[][],
                
            int idEmpleadoVespertino[],
            String fechasVespertino[][],
            JTextField TXThoraEntradaVespertino[][],
            JTextField TXThoraTrabajadaVespertino[][],
            
            int idFacturaActual
             
    
    ){
        
        boolean guardadoExitosoBaseDeDatos=true;
        
        
        String SQL;
        String R[][]=null;
        
        if (!actualizarInformacion){
            
            SQL = "INSERT INTO `factura` "
                    + "       (`claveFactura`,`descripcion`, `fecha`, `nombre`, `direccion`, `solicitante`, `po`, `vendedor`, `periodoInicio`, `periodoFin`, `subtotal`, `impuestos`, `expTram`, `total`,`costoHorasRegulares`,  `costoHrasExtra`,`lugar`,`estado`  ) "  
                    + "VALUES ('"+claveFactura+"', '"+descripcion+"', '"+fecha+"', '"+nombre+"', '"+direccion+"', '"+solicitante+"', '"+po+"', '"+vendedor+"', '"+periodoInicio+"', '"+periodoFin+"', '"+subTotal+"', '"+impuestos+"', '"+expTram+"', '"+total+"' , '"+precioHorasRegulares+"','"+precioHorasExtras+"' ,"+lugar+","+estatus+"  );";
            utilidadVinculoBD.operacionSQL(SQL);
            
            SQL="SELECT @@identity AS id";
            
            R = ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
        
        }else{
            
            
            SQL = "UPDATE `factura` SET `claveFactura`='"+claveFactura+"', `descripcion`='"+descripcion+"', `fecha`='"+fecha+"', `nombre`='"+nombre+"', "
                    + "`direccion`='"+direccion+"', `solicitante`='"+solicitante+"', `po`='"+po+"', `vendedor`='"+vendedor+"', `periodoInicio`='"+periodoInicio+"',"
                    + " `periodoFin`='"+periodoFin+"', `subtotal`='"+subTotal+"', `impuestos`='"+impuestos+"', `expTram`='"+expTram+"', `total`='"+total+"', "
                    + "`costoHorasRegulares`='"+precioHorasRegulares+"', `costoHrasExtra`='"+precioHorasExtras+"',  `lugar`='"+lugar+"',   `estado`='"+estatus+"'   "  
                    + "     WHERE `idFactura`='"+idFactura+"';";
            
            utilidadVinculoBD.operacionSQL(SQL);
            
            
            R=new String[1][1];
            R[0][0] =idFacturaActual+"";
            
            
            SQL = "DELETE FROM `horastrabajadas` WHERE `idhorasTrabajadas`='"+idFactura+"';";
            utilidadVinculoBD.operacionSQL(SQL);
            
            SQL = "DELETE FROM `detallefactura` WHERE `idFactura`='"+idFactura+"';";
            utilidadVinculoBD.operacionSQL(SQL);

            
            
            
        }
         
        
       
         
         
         if (R.length>0){
            idFactura = Integer.parseInt(R[0][0]);
             
            //detalle de Facturas Principal texto Libre
            detalle_factura_servicio detalleFactura=new detalle_factura_servicio();
            detalleFactura.setIdFactura(idFactura);
            detalleFactura.setDescripcion(primerConceptoEscrito);
            detalleFactura.setCantidad(0);
            detalleFactura.setPreciounitario(0);
            detalleFactura.setTotal(0);
            detalleFactura.insertaDetalleFactura();
            
            SQL="SELECT @@identity AS id";
            R=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
            if (R.length<=0){
               guardadoExitosoBaseDeDatos=false;
            }
            
            
            //Regular man hours: Horas Regulares
            detalleFactura=new detalle_factura_servicio();
            detalleFactura.setIdFactura(idFactura);
            detalleFactura.setDescripcion(STRhorasRegulares);
            detalleFactura.setCantidad(Float.parseFloat(totalHorasRegulares));
            detalleFactura.setPreciounitario(Float.parseFloat(precioHorasRegulares));
            detalleFactura.setTotal(Float.parseFloat(totalHorasRegulares)*Float.parseFloat(precioHorasRegulares));
            detalleFactura.insertaDetalleFactura();
            
            SQL="SELECT @@identity AS id";
            R=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
            if (R.length<=0){
               guardadoExitosoBaseDeDatos=false;
            }
            
            
             //Premium man hours:   Horas Extras
            detalleFactura=new detalle_factura_servicio();
            detalleFactura.setIdFactura(idFactura);
            detalleFactura.setDescripcion(STRhorasExtra);
            detalleFactura.setCantidad(Float.parseFloat(totalHorasExtras));
            detalleFactura.setPreciounitario(Float.parseFloat(precioHorasExtras));
            detalleFactura.setTotal(Float.parseFloat(totalHorasExtras)*Float.parseFloat(precioHorasExtras));
            detalleFactura.insertaDetalleFactura();
            
            SQL="SELECT @@identity AS id";
            R=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
            if (R.length<=0){
               guardadoExitosoBaseDeDatos=false;
            }
       
            
            
            
            // Se guardan los conceptos
            
            for (int i=0;i<tablaConceptos.getRowCount();i++){
                detalleFactura=new detalle_factura_servicio();
                detalleFactura.setIdFactura(idFactura);
                detalleFactura.setCantidad(Float.parseFloat(tablaConceptos.getValueAt(i, 0)+""));
                detalleFactura.setDescripcion(tablaConceptos.getValueAt(i, 1)+"");
                detalleFactura.setPreciounitario(Float.parseFloat(tablaConceptos.getValueAt(i, 2)+""));
                float totalConcepto=Float.parseFloat(tablaConceptos.getValueAt(i, 0)+"")*Float.parseFloat(tablaConceptos.getValueAt(i, 2)+"");
                detalleFactura.setTotal(totalConcepto);
                detalleFactura.insertaDetalleFactura();
                SQL="SELECT @@identity AS id";
                R=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
                if (R.length<=0){
                    guardadoExitosoBaseDeDatos=false;
                }
                
            }
             
            
            //Inserta las horas matutinas
               
            horas_trabajadas_factura_servicio horasTrabajadasFechas;
            
       
            if (fechasMatutuno!=null){
                for (int i=0;i<fechasMatutuno.length;i++){

                    horasTrabajadasFechas = new horas_trabajadas_factura_servicio();
                    horasTrabajadasFechas.setIdFactura(idFactura);
                    horasTrabajadasFechas.setIdPersona(idEmpleadoMatutino[i]);
                    horasTrabajadasFechas.setTurnoMatutuno(true);
                    horasTrabajadasFechas.setTurnoVespertino(false);
                    for (int j=0;j<fechasMatutuno[0].length;j++){
                        horasTrabajadasFechas.setFecha(fechasMatutuno[i][j]);
                        horasTrabajadasFechas.setHorasTrabajadas(Float.parseFloat(TXThoraTrabajadaMatutino[i][j].getText()));
                        horasTrabajadasFechas.setHoraEntrada(TXThoraEntradaMatutino[i][j].getText());
                        horasTrabajadasFechas.insertaHorasTrabanadasFactura();
                    }
                }
            }
            //Inserta las horas vespertinas
            
            if (fechasVespertino!=null){
                for (int i=0;i<fechasVespertino.length;i++){

                    horasTrabajadasFechas = new horas_trabajadas_factura_servicio();
                    horasTrabajadasFechas.setIdFactura(idFactura);
                    horasTrabajadasFechas.setIdPersona(idEmpleadoVespertino[i]);
                    horasTrabajadasFechas.setTurnoMatutuno(false);
                    horasTrabajadasFechas.setTurnoVespertino(true);
                    for (int j=0;j<fechasVespertino[0].length;j++){
                        horasTrabajadasFechas.setFecha(fechasVespertino[i][j]);
                        horasTrabajadasFechas.setHorasTrabajadas(Float.parseFloat(TXThoraTrabajadaVespertino[i][j].getText()));
                        horasTrabajadasFechas.setHoraEntrada(TXThoraEntradaVespertino[i][j].getText());
                        horasTrabajadasFechas.insertaHorasTrabanadasFactura();
                    }
                }
            }

            
            
                          
             
             
         }else{
             
             guardadoExitosoBaseDeDatos=false;
         }
        
         
         
         
         return guardadoExitosoBaseDeDatos;
         
        

    }
    
    
    public DefaultTableModel  LISTAfacturas() {
        
        DefaultTableModel Tabla;
        String SQL="SELECT claveFactura,fecha,total FROM factura ORDER BY fecha DESC;";
        String columnas[]=new String[3];
        columnas[0]="Clave"; 
        columnas[1]="Fecha"; 
        columnas[2]="Total"; 
        Tabla=ManejadorDeDatos.BD.consultaTabla(SQL, columnas);
        return Tabla;
        
    }
    
    
    
     public DefaultTableModel  LISTAfacturas(String clave) {
        
        DefaultTableModel Tabla;
        String SQL="SELECT claveFactura,fecha,total FROM factura where claveFactura like '%"+clave+"%' ORDER BY fecha DESC;";
        String columnas[]=new String[3];
        columnas[0]="Clave"; 
        columnas[1]="Fecha"; 
        columnas[2]="Total"; 
        Tabla=ManejadorDeDatos.BD.consultaTabla(SQL, columnas);
        return Tabla;
        
    }
     
    public String[][] LISTAIdsFacturas() {
        
        String R[][];
        String SQL="SELECT idFactura FROM factura ORDER BY fecha DESC;";
        R=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
        return R;
        
    }
            
    public String[][] LISTAIdsFacturas(String clave) {
        
        String R[][];
        String SQL="SELECT idFactura FROM factura where claveFactura like '%"+clave+"%' ORDER BY fecha DESC;";
        R=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
        return R;
        
    }
    
    
    public String[][] informacionFactura(String idFactura) {
        
        String R[][];
        String SQL=
                    "SELECT  " +
                    "	f.idFactura,  " +  //0
                    "	f.claveFactura,  " + //1
                    "	f.descripcion,  " + //2
                    "	f.fecha,  " + //3
                    "	f.nombre,  " + //4
                    "	f.direccion,  " + //5
                    "	f.solicitante,  " + //6
                    "	f.po,  " + //7
                    "	f.vendedor,  " + //8
                    "	f.periodoInicio,  " + //9
                    "	f.periodoFin,  " + //10
                    "	f.subtotal,  " + //11
                    "	f.impuestos,  " + //12
                    "	f.expTram,  " + //13
                    "	f.total,   " + //14
                    "	f.costoHorasRegulares,   " + //15
                    "	f.costoHrasExtra  , " + //16
                    "	l.nombre, " + //17
                    "	c.valor,  " + //18
                    "	df.descripcion  " + //19
                    "FROM factura f "
                
                    + " left join lugar l on l.idLugar = f.lugar " 
                    + " left join catalogo c on c.idCatalogo = f.estado "
                    + " left join detallefactura df on f.idFactura=df.idFactura  AND precioUnitario = 0  "
                
              
                    + "WHERE f.idFactura="+idFactura+" ;";
        R=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 20);
        return R;
        
    }
    
    
    
    
    
    
    public String[][] informacionHorasTrabajadas(String idFactura,boolean turnoMatutino, boolean turnoVespertino){
         String R[][];
         
         String SQL="SELECT  " +
                 
                 "idhorasTrabajadas, " +
                    "fecha, " +
                    "h.idPersona, " +
                    "horasTrabajadas, " +
                    "turnoMatutino, " +
                   
                 "turnoVespertino, " +
                    "idFactura, " +
                    "p.apellidoPaterno, " +
                    "p.apellidoMaterno, " +
                    "p.nombre ," +
                 
                    "horaEntrada "+
                 
                 "FROM horastrabajadas h " +
                    "INNER JOIN persona p ON h.idPersona=p.idPersona " +
                    "where h.idFactura=  "+idFactura+"  "
                 + "and  turnoMatutino= "+turnoMatutino+"   "
                 + "and  turnoVespertino = "+turnoVespertino+" "
                 
                 + "ORDER BY fecha,h.idPersona  "
                 + ";";
          R=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 11);
         
        
        
         return R;
    }
    
    
    
    public DefaultTableModel informacionTrabajadores(String idFactura,boolean turnoMatutino, boolean turnoVespertino){
         DefaultTableModel Tabla;
         
         String SQL="   SELECT  " +
                    "CONCAT(p.apellidoPaterno,' ',  " +
                    "p.apellidoMaterno,' ',  " +
                    "p.nombre), h.idPersona  " +
                    "FROM horastrabajadas h  " +
                    "INNER JOIN persona p ON h.idPersona=p.idPersona  " +
                    "where h.idFactura=  "+idFactura+"  " +
                    "and  turnoMatutino= "+turnoMatutino+"   " +
                    "and  turnoVespertino = "+turnoVespertino+" " +
                    "GROUP BY CONCAT(p.apellidoPaterno,'-',  " +
                    "p.apellidoMaterno,'-',  " +
                    "p.nombre)  " +
                    "ORDER BY fecha,h.idPersona    " +
                    ";";
        String columnas[]=new String[2];
        columnas[0]="Empleado"; 
        columnas[1]="Num"; 
        Tabla=ManejadorDeDatos.BD.consultaTabla(SQL, columnas);
        return Tabla;
    }
       
         
    public String actualizaFacturaEstado(String idFactura,String estado,String fecha){
        
        String insertado=null;
        String SQL = "  UPDATE `factura` SET `estadoPago`='"+estado+"' ,  fechaPago='"+fecha+"'    WHERE `idFactura`='"+idFactura+"';   "   ;
        utilidadVinculoBD.operacionSQL(SQL);
        
        SQL="SELECT @@identity AS id";
        String R[][] = ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
        
        if (R.length>0){
            insertado=R[0][0];
        } 
        
        return insertado;
        
    } 
    
    
    public String actualizaFacturaEstado(String idFactura,String estado){
        
        String insertado=null;
        String SQL = "  UPDATE `factura` SET `estadoPago`='"+estado+"'   WHERE `idFactura`='"+idFactura+"';   "   ;
        utilidadVinculoBD.operacionSQL(SQL);
        
        SQL="SELECT @@identity AS id";
        String R[][] = ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
        
        if (R.length>0){
            insertado=R[0][0];
        } 
        
        return insertado;
        
    } 
    
   

 
    
}
