/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviciosBD;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import utilidadesbasicas.utilidadVinculoBD;

/**
 *
 * @author usuario
 */
public class catalogo_servicio {

    public catalogo_servicio() {
    
    }
    
    
    public String[][] ultimoCatalogoPorId( String idCatalogo ){
        
        String SQL= " SELECT idCatalogo, valor, idCatalogoTipo FROM catalogo c  " +
                    " WHERE c.idCatalogoTipo="+idCatalogo+" " +
                    " ORDER BY c.idCatalogo DESC " +
                    " LIMIT 1 "+
                    " ;";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 3);
        return R;
    }
    
    
    
    public void borraCatalogoRelacionado(int columnaCatalogo,String idCatalogo,String idTipoRelacion){
    
        String SQL = "  DELETE FROM relacioncatalogo WHERE idCatalogo"+columnaCatalogo+"='"+idCatalogo+"' AND idTipoRelacionCatalogo='"+idTipoRelacion+"';  "; 
        utilidadVinculoBD.operacionSQL(SQL);
        
    }
    
    public void borraCatalogo(String idCatalogo){
    
        String SQL = "  DELETE FROM `catalogo` WHERE `idCatalogo`='"+idCatalogo+"' ;  "; 
        utilidadVinculoBD.operacionSQL(SQL);
        
    }
    

    
    public String insertaCatalogosConRelacion(String catalogo1,String catalogo2,String idTipoRelacion){
        
        String insertado=null;
        String SQL = "  INSERT INTO `relacioncatalogo` (`idCatalogo1`, `idCatalogo2`, `idTipoRelacionCatalogo`) VALUES ('"+catalogo1+"', '"+catalogo2+"', '"+idTipoRelacion+"');   "; 
        utilidadVinculoBD.operacionSQL(SQL);
        
        SQL="SELECT @@identity AS id";
        String R[][] = ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
        
        if (R.length>0){
            insertado=R[0][0];
        } 
        
        return insertado;
        
    } 
    
    
    public String insertaCatalogo(String nombre,String idCatalogoTipo){
        
        String insertado=null;
        String SQL = "  INSERT INTO catalogo (`valor`, `idCatalogoTipo`) VALUES ('"+nombre+"', '"+idCatalogoTipo+"');   "; 
        utilidadVinculoBD.operacionSQL(SQL);
        
        SQL="SELECT @@identity AS id";
        String R[][] = ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
        
        if (R.length>0){
            insertado=R[0][0];
        } 
        
        return insertado;
        
    } 
    
    
    public String[][] listaPorTipoCatalogoYRelacion( String idCatalogoTipo,int tipoRelacion ){
        
        String SQL= "  SELECT c1.idCatalogo,c1.valor FROM catalogo c " +
                    "  INNER JOIN relacioncatalogo rc on c.idCatalogo = rc.idCatalogo1 " +
                    "  INNER JOIN catalogo c1 on rc.idCatalogo2=c1.idCatalogo  " +
                    "  WHERE c.idCatalogo="+idCatalogoTipo+"  and rc.idTipoRelacionCatalogo="+tipoRelacion+"    "
                    + " ORDER BY c1.valor ASC  ;  ";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 2);
        return R;
    }
    
    
    
    public DefaultTableModel listaPorTipoCatalogoYRelacionModel(String idCatalogoTipo,int tipoRelacion) {
        DefaultTableModel Tabla;
        String SQL= "  SELECT c1.idCatalogo,c1.valor FROM catalogo c " +
                    "  INNER JOIN relacioncatalogo rc on c.idCatalogo = rc.idCatalogo1 " +
                    "  INNER JOIN catalogo c1 on rc.idCatalogo2=c1.idCatalogo  " +
                    "  where c.idCatalogo="+idCatalogoTipo+"  and rc.idTipoRelacionCatalogo="+tipoRelacion+"  ;  ";
        String columnas[]=new String[2];
        columnas[0]="ID"; 
        columnas[1]="Descripcion";
        Tabla=ManejadorDeDatos.BD.consultaTabla(SQL, columnas);
        return Tabla;
    }
    
    public DefaultTableModel  listaPorTipoCatalogoModel(int idCatalogoTipo) {
        
        DefaultTableModel Tabla;
        String SQL= "  SELECT  idCatalogo,valor " +
                    "FROM `catalogo`  " +
                    "WHERE idCatalogoTipo = "+idCatalogoTipo+" ;";
        String columnas[]=new String[2];
        columnas[0]="ID"; 
        columnas[1]="Descripcion";
        Tabla=ManejadorDeDatos.BD.consultaTabla(SQL, columnas);
        return Tabla;
        
    }
    
    public DefaultTableModel  listaPorTipoCatalogoModel(int idCatalogoTipo,String valor) {
        
        DefaultTableModel Tabla;
        String SQL= "  SELECT  idCatalogo,valor " +
                    "FROM `catalogo`  " +
                    "WHERE idCatalogoTipo = "+idCatalogoTipo+"  AND valor  LIKE '%"+valor+"%'    ;";
        String columnas[]=new String[2];
        columnas[0]="ID"; 
        columnas[1]="Descripcion";
        Tabla=ManejadorDeDatos.BD.consultaTabla(SQL, columnas);
        return Tabla;
        
    }
    
    
    public String[][] listaPorTipoCatalogo( int idCatalogoTipo ){
        
        String SQL= "  SELECT  idCatalogo,valor " +
                    "FROM `catalogo`  " +
                    "WHERE idCatalogoTipo = "+idCatalogoTipo+"    " +
                    "ORDER BY   valor ASC  ;";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 2);
        return R;
    }
    
    public String[][] catalogoPorId( String idCatalogo ){
        
        String SQL= "  SELECT  valor " +
                    "FROM `catalogo`  " +
                    "WHERE idCatalogo = "+idCatalogo+" ;";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
        return R;
    }
    
    
    
    public DefaultComboBoxModel listaComboPorTipoCatalogoEspacionEnBlanco( int idCatalogoTipo ){
        DefaultComboBoxModel combo;
        String SQL=" SELECT '' "
                + " UNION "
                + "  SELECT  valor " +
                    "FROM `catalogo`  " +
                    "WHERE idCatalogoTipo = "+idCatalogoTipo+" ;";
        combo=ManejadorDeDatos.BD.consultaCombo(SQL);
        return combo;
    }
    
    
    public DefaultComboBoxModel listaComboPorTipoCatalogo( int idCatalogoTipo ){
        DefaultComboBoxModel combo;
        String SQL="SELECT  valor " +
                    "FROM `catalogo`  " +
                    "WHERE idCatalogoTipo = "+idCatalogoTipo+" ;";
        combo=ManejadorDeDatos.BD.consultaCombo(SQL);
        return combo;
    }
    
    
    public String idCatalogoPorValorYTipo( String valor, String tipo ){
        
        String SQL= " SELECT idCatalogo FROM catalogo where idCatalogoTipo="+tipo+" AND valor='"+valor+"' ; ";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
        return R[0][0];
        
    }
    
    
    
    
    
    public String[][] listaCuentas( ){
        
        String SQL= " SELECT idCuentas, nombre, descripcion " +
                    " FROM `cuentas`; ";
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 3);
        return R;
        
        
    }
    
    
    public String insertaCuenta(String nombre, String descripcion){
        
        String insertado=null;
        String SQL = "  INSERT INTO `cuentas` (`nombre`, `descripcion`) "
                + "  VALUES ('"+nombre+"', '"+descripcion+"');   "; 
        utilidadVinculoBD.operacionSQL(SQL);
        
        SQL="SELECT @@identity AS id";
        String R[][] = ManejadorDeDatos.BD.ConsultaCuadro(SQL, 1);
        
        if (R.length>0){
            insertado=R[0][0];
        } 
        
        return insertado;
        
    } 
    
}
