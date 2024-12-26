package com.mavericks.accounts.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDTO {


    @NotEmpty(message = "Customer name should not be empty")
    @Size(min = 2, message = "Customer name should have at least 2 characters")
    private String name;

    @NotEmpty(message = "Customer email should not be empty")
    @Email(message = "Invalid email address")
    private String email;

    @Pattern(regexp = "^\\d{10}$", message = "Mobile number should be 10 digits")
    private String mobileNumber;

    @JsonProperty("accounts")
    private AccountsDTO accountsDTO;
}
