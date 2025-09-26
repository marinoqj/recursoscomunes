package es.golemdr.rrcc.common.ext.utils.tools;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DateUtils {
	
	private static Logger log = LogManager.getLogger(DateUtils.class);

	private DateUtils() {
		throw new IllegalStateException("Clase de Utilidad");
	}

	
	public static String getSysDate(String formato) {

		Date date = new Date();

		SimpleDateFormat fmt = new SimpleDateFormat(formato);

		return fmt.format(date);
	}
	

	public static String convertDateToString(Date date, String format){
		String result = null;
		if (date != null) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
			result = simpleDateFormat.format(date);			
		}

		return result;
	}
	
	public static String convertDateSQLToString(java.sql.Date date, String formato){
		String result = "";
		if (date != null) {
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato, Locale.forLanguageTag("es-ES"));
		    LocalDate fromDate = date.toLocalDate();
		    result = fromDate.format(formatter);		
		}

		return result;
	}	
	
	public static Date convertStringToDate(String dd, String mm, String yy) {
		Date result = null;
		String sdate;

		sdate = formatearFecha(yy,mm,dd); 
		if (! isNotWellFormat(sdate)){

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			simpleDateFormat.setLenient(false);
			try {
				result = simpleDateFormat.parse(sdate);
			} catch (ParseException e) {
				result = null;
			}
		}

		return result;
	}
	
	public static Date convertString2Date(String fecha, String formato) {

		Date resultado = null;
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formato);
		
		try {
			resultado = simpleDateFormat.parse(fecha);
		} catch (ParseException e) {
			resultado = null;
		}
		

		return resultado;
	}
	
	public static java.sql.Date convertirString2DateSQL(String fechaTexto, String formato) {
		SimpleDateFormat sdf = new SimpleDateFormat(formato);
		java.util.Date fecha = null;
		java.sql.Date fechaConvertida = null;
		
		try {
			if (fechaTexto != null && !fechaTexto.trim().isEmpty()) {
				fecha = sdf.parse(fechaTexto);
				fechaConvertida = new java.sql.Date(fecha.getTime());				
			}
		} catch (ParseException e) {
			log.warn("Se produjo una excepción al convertir una cadena de caracteres a fecha:", e);
		}
		
		return fechaConvertida;
	}
	
	/**
	 * Formatea el mes y el dia para que tengan 2 digitos
	 * @param anyo    Anyo
	 * @param mes     Mes 
	 * @param dia     Dia
	 * @return  String La fecha con formato y-m-d, con el mes y el dia con 2 digitos.
	 */
	public static String formatearFecha(String anyo, String mes, String dia){

		mes = (mes.length()==1)?'0' + mes:mes; 
		dia = (dia.length()==1)?'0'+ dia:dia;

		return ((anyo+mes+dia).length()==0)?"":(dia + "/" + mes + "/" + anyo);
	}
	
	/**
	 * Comprueba que una fecha este bien formateada, el formato es: dd/mm/yyyy
	 * @param date
	 * @return
	 */
	public static boolean isNotWellFormat(String date) {
		int LONGITUD_FECHA_FORMATO_YYYY_MMD_DD = 10; // OJO    de DateUtils. private static final int LONGITUD_FECHA_FORMATO_YYYY_MMD_DD = 10;
		boolean notWellFormat = true;
		if (date.length() == LONGITUD_FECHA_FORMATO_YYYY_MMD_DD && date.indexOf("/") == 2 && date.lastIndexOf("/") == 5) {
			notWellFormat = false;
		}
		return notWellFormat;
	}
	
}

