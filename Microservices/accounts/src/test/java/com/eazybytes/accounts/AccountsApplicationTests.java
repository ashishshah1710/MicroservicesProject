package com.eazybytes.accounts;

import com.eazybytes.accounts.dto.CustomerDTO;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.repository.AccountRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.IAccountService;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class AccountsApplicationTests {

  @Autowired
  IAccountService accountService;
  @Autowired
  AccountRepository accountRepository;
  @Autowired
  CustomerRepository customerRepository;

  @Test
  void contextLoads() {
    CustomerDTO customerDTO = new CustomerDTO();
    customerDTO.setName("Ashish");
    customerDTO.setEmail("ashish.shah@gmail.com");
    customerDTO.setMobileNumber("7787374303");
    accountService.createAccount(customerDTO);

    Optional<Customer> customer = customerRepository.findByMobileNumber("7787374303");
    Optional<Accounts> accounts = accountRepository.findById(customer.get().getCustomerId());

    Assertions.assertNotNull(customer);
    Assertions.assertNotNull(accounts);
  }

  @Test
  void contextLoadsNegative() {
    CustomerDTO customerDTO = new CustomerDTO();
    customerDTO.setName("Ashish");
    customerDTO.setEmail("ashish.shah@gmail.com");
    customerDTO.setMobileNumber("1254");
    try {
      accountService.createAccount(customerDTO);
    } catch (Exception e) {
      Assertions.assertEquals("Mobile number should be 10 digits", e.getMessage());
    }

  }

}
