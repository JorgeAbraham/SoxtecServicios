/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviciosBD;

/**
 *
 * @author Abraham
 */
public class peticiones_web_servicio {
    
    
    public final static int menuPrincipal=1;
    
    int idVista;
    String nombreVista;
    int idTipoVista;
    String controlador;
    String metodo;
    String aliasVisible;
    
    String idUsuario;

    public int getIdVista() {
        return idVista;
    }

    public void setIdVista(int idVista) {
        this.idVista = idVista;
    }

    public String getNombreVista() {
        return nombreVista;
    }

    public void setNombreVista(String nombreVista) {
        this.nombreVista = nombreVista;
    }

    public int getIdTipoVista() {
        return idTipoVista;
    }

    public void setIdTipoVista(int idTipoVista) {
        this.idTipoVista = idTipoVista;
    }

   

    public String getControlador() {
        return controlador;
    }

    public void setControlador(String controlador) {
        this.controlador = controlador;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getAliasVisible() {
        return aliasVisible;
    }

    public void setAliasVisible(String aliasVisible) {
        this.aliasVisible = aliasVisible;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    
    
    
    
    public void getVistasPorNombre() {
        
        
        String SQL= "SELECT " +
                    "	v.idVista, " +
                    "	v.nombreVista, " +
                    "	v.idTipoVista, " +
                    "	v.controlador, " +
                    "	v.metodo, " +
                    "	v.aliasVisible " +
                    "FROM vista v " +
                    "INNER JOIN vistaperfil vp on (v.idVista=vp.idVista) " +
                    "INNER JOIN permiso p on (p.idPerfil=vp.idPerfil) " +
                    "INNER JOIN usuarios u on (u.idusuarios=p.idusuarios) " +
                    "WHERE v.nombreVista='"+nombreVista+"'  " +
                    "AND u.idusuarios  = '"+idUsuario+"' " +
                    ";";
        
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 6);
        
        
        if (R.length>0){
            for (int i=0;i<R[0].length;i++){

                idVista=Integer.parseInt(R[0][0]);
                nombreVista=R[0][1]!=null?R[0][1]:"";
                idTipoVista=Integer.parseInt(R[0][2]);
                controlador=R[0][3]!=null?R[0][3]:"";
                metodo=R[0][4]!=null?R[0][4]:"";
                aliasVisible=R[0][5]!=null?R[0][5]:"";


            }
        }
        
    }
    
    
  
    public String[][] getVistasPoridTipoVista() {
        
        String R[][];
        
        String SQL= "SELECT  " +
                    "	v.idVista,  " +
                    "	v.nombreVista,  " +
                    "	v.idTipoVista,  " +
                    "	v.controlador,  " +
                    "	v.metodo,  " +
                    "	v.aliasVisible  " +
                    "FROM vista v " +
                    "INNER JOIN vistaperfil vp on (v.idVista=vp.idVista) " +
                    "INNER JOIN permiso p on (p.idPerfil=vp.idPerfil) " +
                    "INNER JOIN usuarios u on (u.idusuarios=p.idusuarios) " +
                    "WHERE v.idTipoVista = '"+idTipoVista+"' " +
                    "AND u.idusuarios  = '"+idUsuario+"'  "+
                    "GROUP BY v.idVista   " +
                    "; ";
        
        R=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 6);
        return R;
        
    }
    
    
    
    
}
