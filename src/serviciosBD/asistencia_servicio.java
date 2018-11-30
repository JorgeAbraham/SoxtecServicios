/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviciosBD;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Soxtec Desarrollo
 */
public class asistencia_servicio {
    
    
    public String[][] listaChecadas( ){
        
        String SQL= " SELECT rh.fecha, rh.hora, p.apellidoPaterno, p.apellidoMaterno, p.nombre, l.nombre" +
                    " FROM registrohuelladigital rh " +
                    " INNER JOIN persona p on (rh.idPersona = p.idPersona) " +
                    " INNER JOIN personalugar pl on (pl.Persona_idPersona = p.idPersona) " +
                    " INNER JOIN lugar l on (l.idLugar=pl.Lugar_idLugar) " +
                    " ORDER BY rh.fecha DESC , rh.hora DESC " +
                    " LIMIT 1000;   "; 
        String R[][]=ManejadorDeDatos.BD.ConsultaCuadro(SQL, 6);
        return R;
        
        
    }
    
    
    
    public DefaultTableModel  listaChecadasTabla() {
        
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = format.format( new Date()   );
        Calendar calendar =Calendar.getInstance(); //obtiene la fecha de hoy 
        calendar.add(Calendar.DATE, -1);
        
        java.util.Date diaAnterior = calendar.getTime();
        String dateStringAnterior = format.format( diaAnterior   );
        
        
        DefaultTableModel Tabla;
        String SQL= " SELECT " +
                    "	CONCAT(p.apellidoPaterno,' ',p.apellidoMaterno,' ',p.nombre), " +
                    "	hora1.fecha, " +
                    "	hora0.hora as UltCheAnterior , " +
                    " 	hora1.hora as PrimeraCh, " +
                    "	hora2.hora as UltChe, " +
                    "	SEC_TO_TIME(TIMESTAMPDIFF(SECOND, hora1.hora, hora2.hora )) as Hora1er, " +
                    "	ADDTIME(SEC_TO_TIME(TIMESTAMPDIFF(SECOND, hora0.hora, CAST('24:00:00' as TIME) )) , hora1.hora   )    as Hora2do " +
                    " FROM persona p " +
                    "	INNER JOIN personalugar pl on (pl.Persona_idPersona = p.idPersona)  " +
                    "	INNER JOIN lugar l on (l.idLugar=pl.Lugar_idLugar)   " +
                    "	LEFT JOIN  " +
                    "		( " +
                    "			select idPersona,max(fecha) as fecha, max(hora) as hora  " +
                    "			from registrohuelladigital rh  " +
                    "			where  fecha='"+dateStringAnterior+"'  " +
                    "			group by idPersona  " +
                    "		) as hora0 on (hora0.idPersona=p.idPersona) " +
                    " 	LEFT JOIN  " +
                    "		( " +
                    "			select idPersona,min(fecha) as fecha, min(hora) as hora  " +
                    "			from registrohuelladigital rh  " +
                    "			where  fecha='"+dateString+"'  " +
                    "			group by idPersona  " +
                    "		) as hora1 on (hora1.idPersona=p.idPersona) " +
                    " 	LEFT JOIN ( " +
                    "			select idPersona,max(fecha) as fecha, max(hora) as hora  " +
                    "			from registrohuelladigital rh  " +
                    "			where  fecha='"+dateString+"'  " +
                    "			group by idPersona  " +
                    "		) as hora2 on (hora2.idPersona=p.idPersona) " +
                    " WHERE  hora0.hora is not null OR " +
                    "	hora1.hora is not null  OR " +
                    "	hora2.hora is not null  " +
                    " ORDER BY   hora0.hora ASC " +
                    " LIMIT 10000;   "; 
        
        String columnas[]=new String[7];
        columnas[0]="NOMBRE"; 
        columnas[1]="FECHA"; 
        columnas[2]="ULTIMA CHECADA DIA ANTERIOR"; 
        columnas[3]="PRIMERA CHECADA";
        columnas[4]="ULTIMA CHECADA";
        columnas[5]="HORAS PRIMER TURNO";
        columnas[6]="HORAS SEGUNDO TURNO";
        
        
        Tabla=ManejadorDeDatos.BD.consultaTabla(SQL, columnas);
        return Tabla;
        
    }
    
    
    
    
}
