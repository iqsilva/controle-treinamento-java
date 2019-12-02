package Model;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
@SuppressWarnings("serial")
public class ManipulateDates extends java.util.Date{
	//OLHAR A FONTE 
	//https://msdn.microsoft.com/pt-br/library/ms186819(v=sql.120).aspx
	
	public Date StrToDate(String dataS){
		Date data=null;
		try{
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			data= new java.sql.Date( ((java.util.Date)formatter.parse(dataS)).getTime() );
		}catch (ParseException e){
			e.printStackTrace();
		}
		return data;
	}
	
	public String DateToStr(Date data){
		String dataS="";
		DateFormat df = new SimpleDateFormat("dd/MM/YYYY");
		dataS=df.format(data);
		return dataS;
	}
	public Date dataHoje(){
		java.util.Date dt = new Date(super.getTime());
		java.sql.Date d = new java.sql.Date (dt.getTime());
		/*String data="";
		int dia=0;
		int mes=0;
		int ano=0;
		LocalDateTime now = LocalDateTime.now();
		dia=now.getDayOfMonth();
		mes=now.getMonthValue();
		ano=now.getYear();
		
		data=String.valueOf(ano).concat("-").concat(String.valueOf(mes)).concat("-").concat(String.valueOf(dia));
		return (java.sql.Date) new Date(0);*/
		return d;
	}
}