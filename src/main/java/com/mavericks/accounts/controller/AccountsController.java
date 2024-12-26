package com.mavericks.accounts.controller;

import com.mavericks.accounts.constant.AccountsConstants;
import com.mavericks.accounts.dto.CustomerDTO;
import com.mavericks.accounts.dto.ResponseDTO;
import com.mavericks.accounts.service.IAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.mavericks.accounts.constant.AccountsConstants.*;

@RestController
@RequestMapping(path="/accounts/v1", produces = "application/json")
@RequiredArgsConstructor
@Validated
public class AccountsController {

     private final IAccountService accountService;

    @GetMapping("/test")
    public String sayHello() {
        return "Hello from Accounts Services Test Service";
    }

    @PostMapping (value = "/create")
    public ResponseEntity<ResponseDTO> createAccount(@Valid @RequestBody CustomerDTO customerDTO) {
        accountService.createAccount(customerDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
             //   .header("Location", "http://localhost:8080/mavericks/accounts/v1/" + customerDTO.getMobileNumber())
                .body(new ResponseDTO(STATUS_201, MESSAGE_201));


    }

    @GetMapping(value = "/fetch/{mobileNumber}")
    public ResponseEntity<CustomerDTO> getAccountDetailsByPhoneNumber(@PathVariable String mobileNumber) {
        CustomerDTO customerDTO = accountService.getAccountDetailsByPhoneNumber(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(customerDTO);
    }

}
