package com.mavericks.accounts.service.impl;

import com.mavericks.accounts.dto.AccountsDTO;
import com.mavericks.accounts.dto.CustomerDTO;
import com.mavericks.accounts.entity.Accounts;
import com.mavericks.accounts.entity.Customer;
import com.mavericks.accounts.exception.AccountServiceException;
import com.mavericks.accounts.mapper.AccountsMapper;
import com.mavericks.accounts.mapper.CustomerMapper;
import com.mavericks.accounts.repository.AccountsRepository;
import com.mavericks.accounts.repository.CustomerRepository;
import com.mavericks.accounts.service.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.Random;

import static com.mavericks.accounts.constant.AccountsConstants.ADDRESS;
import static com.mavericks.accounts.constant.AccountsConstants.SAVINGS;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private final AccountsRepository accountsRepository;

    private final CustomerRepository customerRepository;



    /**
     * Creates a new account for the specified customer.
     *
     * @param customerDTO the data transfer object containing customer details
     */
    @Override
    public void createAccount(CustomerDTO customerDTO) {
        Customer customerEntity = CustomerMapper.mapToCustomer(customerDTO);
        customerRepository.findByMobileNumber(customerEntity.getMobileNumber()).ifPresentOrElse(customer -> {
            throw new AccountServiceException("Customer with mobile number " + customer.getMobileNumber() + " or email " + customer.getEmail() + " already exists", HttpStatus.BAD_REQUEST);
        }, () -> {
            Customer savedCustomer = customerRepository.save(customerEntity);
            Accounts accounts = createNewAccount(savedCustomer);
            accountsRepository.save(accounts);
        });
    }

    @Override
    public CustomerDTO getAccountDetailsByPhoneNumber(String mobileNumber) {

         Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                 () -> new AccountServiceException("Customer with mobile number " + mobileNumber + " not found", HttpStatus.NOT_FOUND)
         );

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new AccountServiceException("Customer with mobile number " + mobileNumber + " not found", HttpStatus.NOT_FOUND)
        );

        CustomerDTO customerDTO = CustomerMapper.mapToCustomerDto(customer);
        AccountsDTO accountsDTO = AccountsMapper.mapToAccountsDto(accounts);
        customerDTO.setAccountsDTO(accountsDTO);
        return customerDTO;
    }

    private Accounts createNewAccount(Customer customer) {
        Accounts accounts = new Accounts();
        accounts.setCustomerId(customer.getCustomerId());
        long randomAccountNumber = 100000000L + new Random().nextInt(900000000);
        accounts.setAccountType(SAVINGS);
        accounts.setAccountNumber(randomAccountNumber);
        accounts.setBranchAddress(ADDRESS);
        return accounts;
    }
}
