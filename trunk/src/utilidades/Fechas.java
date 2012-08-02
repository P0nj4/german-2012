package utilidades;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase que contiene metodos reusables como convertir una fecha a string
 * @author German
 *
 */
public class Fechas {

    
	/**
	 * Dado un elemento del tipo date retorna un string de fecha en el siguiente formato yyyy-MM-dd
	 * @param fecha
	 * @return String
	 */
	public static String fechaToShortString(Date fecha){
		Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String newfecha = formatter.format(fecha);
        return newfecha;
		
	}
	
	/**
	 * Dado un elemento del tipo date retorna un string de fecha en el siguiente formato yyyy-MM-dd hh:mm:ss
	 * @param fecha
	 * @return String
	 */
	public static String fechaToLongString(Date fecha){
		Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String newfecha = formatter.format(fecha);
		return newfecha;
	}

	/**
	 * Dado un elemento del tipo date y un formato al que se desee convertir retorna un string de fecha en el formato deseado, ej. "yy-MM-dd"
	 * @param fecha
	 * @return String
	 */
	public static String fechaToShortString(Date fecha, String format){
		Format formatter = new SimpleDateFormat(format);
        String newfecha = formatter.format(fecha);
		return newfecha;
	}

}
