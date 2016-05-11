package pl.marcinbialecki.rest.app.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import pl.marcinbialecki.rest.core.exception.ErrorData;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Returns a 401 error code (Unauthorized) to the client.
 */
@Component
public class Http401UnauthorizedEntryPoint implements AuthenticationEntryPoint {

    private final Logger log = LoggerFactory.getLogger(Http401UnauthorizedEntryPoint.class);

    @Autowired
    private SecurityUtils securityUtils;

    /**
     * Always returns a 401 error code to the client.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
        throws IOException,
        ServletException {

        log.debug("Pre-authenticated entry point called. Rejecting access");
        securityUtils.sendError(response, exception, HttpServletResponse.SC_UNAUTHORIZED,
                "Authentication failed");
    }

    public void setSecurityUtils(SecurityUtils securityUtils) {
        this.securityUtils = securityUtils;
    }
}
