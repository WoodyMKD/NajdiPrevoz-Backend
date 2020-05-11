package tomatosolutions.najdiprevoz.utils;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tomatosolutions.najdiprevoz.models.exceptions.BadRequestException;
import tomatosolutions.najdiprevoz.models.exceptions.ResourceNotFoundException;
import tomatosolutions.najdiprevoz.models.payloads.API.APIException;
import tomatosolutions.najdiprevoz.models.exceptions.security.EmailAlreadyExistsException;
import tomatosolutions.najdiprevoz.models.exceptions.security.UsernameAlreadyExistsException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    private ResponseEntity<Object> buildResponseEntity(APIException apiException) {
        return new ResponseEntity<>(apiException, apiException.getStatus());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    protected ResponseEntity<Object> handleUsernameNotFound(
            UsernameNotFoundException ex) {
        APIException apiException = new APIException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        return buildResponseEntity(apiException);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleResourceNotFound(
            ResourceNotFoundException ex) {
        APIException apiException = new APIException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        return buildResponseEntity(apiException);
    }

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<Object> handleBadRequestException(
            BadRequestException ex) {
        APIException apiException = new APIException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        return buildResponseEntity(apiException);
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    protected ResponseEntity<Object> handleUsernameAlreadyExists(
            UsernameAlreadyExistsException ex) {
        APIException apiException = new APIException(HttpStatus.CONFLICT, ex.getMessage(), ex);
        return buildResponseEntity(apiException);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    protected ResponseEntity<Object> handleEmailAlreadyExists(
            EmailAlreadyExistsException ex) {
        APIException apiException = new APIException(HttpStatus.CONFLICT, ex.getMessage(), ex);
        return buildResponseEntity(apiException);
    }
}