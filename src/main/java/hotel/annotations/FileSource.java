package hotel.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by kanet on 12-Dec-16.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface FileSource {
    String name();

    String path() default "";
}
