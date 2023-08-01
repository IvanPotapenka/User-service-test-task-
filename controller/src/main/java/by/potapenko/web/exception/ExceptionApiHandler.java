package by.potapenko.web.exception;

import by.potapenko.service.util.UserErrorResponse;
import by.potapenko.service.util.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionApiHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotFoundException e) {
        UserErrorResponse response = new UserErrorResponse(
                "User with this id not found", System.currentTimeMillis());
        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {
        UserErrorResponse response = new UserErrorResponse();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            response.setMassage(error.getDefaultMessage());
        });
        return new ResponseEntity<>(response, status);
    }
}
