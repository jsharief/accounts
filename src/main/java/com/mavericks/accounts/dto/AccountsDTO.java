package com.mavericks.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountsDTO {

    private Long accountNumber;
    private String accountType;
    private String branchAddress;
    private int status;

}
