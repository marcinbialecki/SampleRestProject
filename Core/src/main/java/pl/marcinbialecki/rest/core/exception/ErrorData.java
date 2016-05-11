package pl.marcinbialecki.rest.core.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Marcin Białecki on 2016-05-11.
 */
@Getter
@Setter
public class ErrorData {

    /**
     * Response status.
     */
    private Integer status;

    /**
     * Error details message.
     */
    private String message;

    /**
     * Url.
     */
    private String url;

}
