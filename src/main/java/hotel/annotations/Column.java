package hotel.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by kanet on 03-Feb-17.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
    String columnName();
    String refTable() default "";
    String refColumn() default "";
}
