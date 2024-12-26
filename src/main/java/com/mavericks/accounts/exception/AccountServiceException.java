package com.mavericks.accounts.exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
public class AccountServiceException extends RuntimeException {

    private HttpStatus errorCode;

    public AccountServiceException(String message, HttpStatus errorCode) {
        super(message);
        this.errorCode = errorCode;
    }


}
