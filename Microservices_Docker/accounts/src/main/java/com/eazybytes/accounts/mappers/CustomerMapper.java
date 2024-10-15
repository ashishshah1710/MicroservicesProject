package com.eazybytes.accounts.mappers;

import com.eazybytes.accounts.dto.CustomerDTO;
import com.eazybytes.accounts.entity.Customer;

public class CustomerMapper {

  public static CustomerDTO mapToCustomerDTO(Customer customer, CustomerDTO CustomerDTO) {
    CustomerDTO.setName(customer.getName());
    CustomerDTO.setEmail(customer.getEmail());
    CustomerDTO.setMobileNumber(customer.getMobileNumber());
    return CustomerDTO;
  }

  public static Customer mapToCustomer(CustomerDTO CustomerDTO, Customer customer) {
    customer.setName(CustomerDTO.getName());
    customer.setEmail(CustomerDTO.getEmail());
    customer.setMobileNumber(CustomerDTO.getMobileNumber());
    return customer;
  }

}
