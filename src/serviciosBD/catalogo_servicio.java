/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviciosBD;

import javax.swing.DefaultComboBoxModel;
import utilidadesbasicas.utilidadVinculoBD;

/**
 *
 * @author usuario
 */
public class catalogo_servicio {

    public catalogo_servicio() {
    
    }
    
    
    
    public String[][] listaPorTipoCatalogo( int idCatalogoTipo ){
        
        String SQL= "  SELECT  idCatalogo,valor " +
                    "FROM `catalogo`  " +
                    "WHERE idCatalogoTipo = "+idCatalogoTipo+" ;";
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
