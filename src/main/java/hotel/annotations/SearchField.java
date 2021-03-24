package hotel.annotations;


import hotel.enums.SearchType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by kanet on 18-Jan-17.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface SearchField {
    SearchType type() default SearchType.EQUALS;
}
