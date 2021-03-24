package hotel.converter;

import java.sql.Date;

/**
 * Created by kanet on 21-Dec-16.
 */
public class DateConverter implements IConverter {

    public Object getAsObject(Object value){
        Date date = null;
        String iVal = String.valueOf(value.toString());
        date = Date.valueOf(iVal);// new SimpleDateFormat("yyyy-MM-dd").parse(iVal);
        return date;
    }
}
