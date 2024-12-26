package com.mavericks.accounts.mapper;

import com.mavericks.accounts.dto.CustomerDTO;
import com.mavericks.accounts.entity.Customer;
public class CustomerMapper {
    public static CustomerDTO mapToCustomerDto(Customer customer) {
        CustomerDTO customerDto = new CustomerDTO();
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    public static Customer mapToCustomer(CustomerDTO customerDto) {
        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }
}