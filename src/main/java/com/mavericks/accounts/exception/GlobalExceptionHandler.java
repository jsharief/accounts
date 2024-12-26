package com.mavericks.accounts.exception;

import com.mavericks.accounts.dto.ErrorResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AccountServiceException.class)
    public ResponseEntity<ErrorResponseDTO> handleAccountServiceException(AccountServiceException accountServiceException, WebRequest webRequest) {

        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .apiPath(webRequest.getDescription(false))
                .errorMessage(accountServiceException.getMessage())
                .errorCode(accountServiceException.getErrorCode())
                .errorTime(LocalDateTime.now()).build();
        HttpStatus errorCode = accountServiceException.getErrorCode();
        return new ResponseEntity<>(errorResponseDTO,errorCode);

    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGlobalException(Exception genericException, WebRequest webRequest) {

        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .apiPath(webRequest.getDescription(false))
                .errorMessage(genericException.getMessage())
                .errorCode(HttpStatus.INTERNAL_SERVER_ERROR)
                .errorTime(LocalDateTime.now()).build();
               return new ResponseEntity<>(errorResponseDTO,HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException mex, org.springframework.http.HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        List<ObjectError> errorList = mex.getBindingResult().getAllErrors();
        errorList.forEach(error -> errors.put(((FieldError)error).getField(), error.getDefaultMessage()));

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


}
