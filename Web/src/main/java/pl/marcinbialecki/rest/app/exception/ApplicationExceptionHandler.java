package pl.marcinbialecki.rest.app.exception;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Marcin Białecki on 2016-05-11.
 */
@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public String exception(AccessDeniedException e) {
        return "{\"status\":\"access denied\"}";
    }
}
