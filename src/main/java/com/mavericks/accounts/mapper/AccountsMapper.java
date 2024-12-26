package com.mavericks.accounts.mapper;

import com.mavericks.accounts.dto.AccountsDTO;
import com.mavericks.accounts.entity.Accounts;

public class AccountsMapper {

    public static AccountsDTO mapToAccountsDto(Accounts accounts) {
        AccountsDTO accountsDto = new AccountsDTO();
        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        return accountsDto;
    }

    public static Accounts mapToAccounts(AccountsDTO accountsDto, Accounts accounts) {
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        return accounts;
    }


}
