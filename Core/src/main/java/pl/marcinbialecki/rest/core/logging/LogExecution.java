package pl.marcinbialecki.rest.core.logging;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Marcin Białecki on 2016-05-11.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface LogExecution {
}
