package gogreenserver.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class RestAuthenticationEntryPoint
    implements AuthenticationEntryPoint {

    @Override
    public void commence(
        final HttpServletRequest httpServletRequest,
        final HttpServletResponse httpServletResponse,
        final AuthenticationException exception) throws IOException, ServletException {

        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,
            "Unauthorized");
    }
}
