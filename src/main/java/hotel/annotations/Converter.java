package hotel.annotations;


import hotel.converter.IConverter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by kanet on 21-Dec-16.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Converter {

    Class<? extends IConverter> clazz();
}
