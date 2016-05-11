package pl.marcinbialecki.rest.app.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import pl.marcinbialecki.rest.core.exception.ErrorData;
import pl.marcinbialecki.rest.core.reponse.Response;

import javax.servlet.http.HttpServletResponse;

/**
 * Utility class for Spring Security.
 */
@Component
public final class SecurityUtils {

    private static final String RESPONSE_CONTENT_TYPE = "application/json;charset=UTF-8";

    @Autowired
    private ObjectMapper mapper;

    private SecurityUtils() {
    }

    private void setMapper(final ObjectMapper mapper) {
        this.mapper = mapper;
    }

    /**
     *
     * @param response
     * @param exception
     * @param status
     * @param message
     * @throws IOException
     */
    public void sendError(final HttpServletResponse response, final Exception exception, final int status,
                                 final String message) throws IOException {
        response.setContentType(RESPONSE_CONTENT_TYPE);
        response.setStatus(status);

        PrintWriter writer = response.getWriter();
        ErrorData errorData = new ErrorData();
        errorData.setStatus(status);
        errorData.setMessage(message);
        writer.write(mapper.writeValueAsString(new Response(status, message,
                errorData)));
        writer.flush();
        writer.close();
    }

    /**
     * Get the login of the current user.
     */
    public static String getCurrentUserLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String userName = null;
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof UserDetails) {
                UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
                userName = springSecurityUser.getUsername();
            } else if (authentication.getPrincipal() instanceof String) {
                userName = (String) authentication.getPrincipal();
            }
        }
        return userName;
    }

    /**
     * Check if a user is authenticated.
     *
     * @return true if the user is authenticated, false otherwise
     */
    public static boolean isAuthenticated() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Collection<? extends GrantedAuthority> authorities = securityContext.getAuthentication().getAuthorities();
        if (authorities != null) {
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(AuthoritiesConstants.ANONYMOUS)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Return the current user, or throws an exception, if the user is not
     * authenticated yet.
     *
     * @return the current user
     */
    public static User getCurrentUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof User) {
                return (User) authentication.getPrincipal();
            }
        }
        throw new IllegalStateException("User not found!");
    }

    /**
     * If the current user has a specific authority (security role).
     *
     * <p>The name of this method comes from the isUserInRole() method in the Servlet API</p>
     */
    public static boolean isCurrentUserInRole(String authority) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof UserDetails) {
                UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
                return springSecurityUser.getAuthorities().contains(new SimpleGrantedAuthority(authority));
            }
        }
        return false;
    }
}
