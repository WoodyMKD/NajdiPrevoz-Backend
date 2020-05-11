package tomatosolutions.najdiprevoz.utils.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import tomatosolutions.najdiprevoz.payloads.API.APIException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException {
        APIException apiException = new APIException(HttpStatus.UNAUTHORIZED);
        apiException.setMessage(e.getMessage());
        OutputStream out = httpServletResponse.getOutputStream();
        httpServletResponse.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, apiException);
        out.flush();
    }
}
