package pl.marcinbialecki.rest.core.reponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.marcinbialecki.rest.core.exception.ErrorData;

/**
 * Created by Marcin Białecki on 2016-05-11.
 */
@Getter
@AllArgsConstructor
public class Response {

    /**
     * Response status.
     */
    private Integer code;

    /**
     * Error details message.
     */
    private String message;

    /**
     * Error data.
     */
    private ErrorData errorData;

}