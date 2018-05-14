/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviciosBD;

import utilidadesbasicas.utilidadVinculoBD;

/**
 *
 * @author Abraham
 */
public class horas_trabajadas_factura_servicio {
    
    
    int idHorasTrabajadasFactura;
    String fecha;
    int idPersona;
    String horaEntrada;
    float horasTrabajadas;
    boolean turnoMatutuno;
    boolean turnoVespertino;
    int idFactura;

    public int getIdHorasTrabajadasFactura() {
        return idHorasTrabajadasFactura;
    }

    public void setIdHorasTrabajadasFactura(int idHorasTrabajadasFactura) {
        this.idHorasTrabajadasFactura = idHorasTrabajadasFactura;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public float getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(float horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

    public boolean isTurnoMatutuno() {
        return turnoMatutuno;
    }

    public void setTurnoMatutuno(boolean turnoMatutuno) {
        this.turnoMatutuno = turnoMatutuno;
    }

    public boolean isTurnoVespertino() {
        return turnoVespertino;
    }

    public void setTurnoVespertino(boolean turnoVespertino) {
        this.turnoVespertino = turnoVespertino;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }
    
     public void insertaHorasTrabanadasFactura(){
        
        String SQL="INSERT INTO `horastrabajadas` (`fecha`, `idPersona`, `horaEntrada`, `horasTrabajadas`, `turnoMatutino`, `turnoVespertino`, `idFactura`) "
                 + "VALUES ('"+fecha+"', '"+idPersona+"', '"+horaEntrada+"', '"+horasTrabajadas+"', "+turnoMatutuno+", "+turnoVespertino+", '"+idFactura+"');";
        
        utilidadVinculoBD.operacionSQL(SQL);

    }
            
    
    
    
    
    
    
}
