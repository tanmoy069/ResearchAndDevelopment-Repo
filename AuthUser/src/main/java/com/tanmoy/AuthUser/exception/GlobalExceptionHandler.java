package com.tanmoy.AuthUser.exception;

import com.tanmoy.AuthUser.response.BaseResponse;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<BaseResponse> handle(IllegalArgumentException ex) {
        LOGGER.debug("Illegal Exception Occurred", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(BaseResponse.getErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<BaseResponse> handle(NotFoundException ex) {
        LOGGER.debug("Not Found Exception Occurred", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(BaseResponse.getErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<BaseResponse> handle(DuplicateKeyException ex) {
        LOGGER.error("Duplicate Exception Occurred", ex);
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .body(BaseResponse.getErrorResponse(HttpStatus.NOT_ACCEPTABLE.value(), getFormattedDuplicateErrorMessage(ex)));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<BaseResponse> handle(ConstraintViolationException ex) {
        LOGGER.error("Constraint violation Exception Occurred", ex);
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .body(BaseResponse.getErrorResponse(HttpStatus.NOT_ACCEPTABLE.value(), ex.getMessage()));
    }

    private String getFormattedDuplicateErrorMessage(DuplicateKeyException ex) {
        try {
            String message = ex.getMessage();
            int idx = message.indexOf("index:");
            String trimmed = message.substring(idx + 7);
            int spaceIndex = trimmed.indexOf(" ");
            String keyName = trimmed.substring(0, spaceIndex);
            return "Data already exists with property " + keyName;
        } catch (Exception exception) {
            LOGGER.error("Error during duplicate key name extraction", exception);
            return "Data already exists!";
        }
    }
}
