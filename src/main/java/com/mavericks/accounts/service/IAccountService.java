package com.mavericks.accounts.service;

import com.mavericks.accounts.dto.CustomerDTO;

public interface IAccountService  {
    /**
     * This method is used to create a new account for the given customer.
     *
     * @param customerDTO the customer for whom the account needs to be created.
     */
    void createAccount(CustomerDTO customerDTO);

    CustomerDTO getAccountDetailsByPhoneNumber(String mobileNumber);
}
