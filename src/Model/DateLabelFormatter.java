package Model;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFormattedTextField.AbstractFormatter;
@SuppressWarnings("serial")
public class DateLabelFormatter extends AbstractFormatter{
	
	private String datePattern = "dd/MM/yyyy";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
	String data1=dateFormatter.format(new Date());
	Calendar cal=null;
	String  data2="";
	public Object stringToValue(String text) throws ParseException {
		return dateFormatter.parseObject(text);
	}

	@Override
	public String valueToString(Object value) throws ParseException {
		if(value!=null){
			cal= (Calendar) value;
			//cal.add(Calendar.YEAR, +1);
			data2 = dateFormatter.format(cal.getTime());
			/*if(date1.compareTo(date2)>0){
                System.out.println("Date1 is after Date2");
            }else if(date1.compareTo(date2)<0){
                System.out.println("Date1 is before Date2");
            }else{
                System.out.println("Date1 is equal to Date2");
            }*/
		}
		return data2;
	}
}
