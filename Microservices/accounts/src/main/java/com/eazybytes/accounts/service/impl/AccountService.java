package com.eazybytes.accounts.service.impl;

import com.eazybytes.accounts.constants.AccountsConstants;
import com.eazybytes.accounts.dto.AccountsDTO;
import com.eazybytes.accounts.dto.CustomerDTO;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.exception.CustomerAlreadyExistException;
import com.eazybytes.accounts.exception.ResourceNotFoundException;
import com.eazybytes.accounts.mappers.AccountMapper;
import com.eazybytes.accounts.mappers.CustomerMapper;
import com.eazybytes.accounts.repository.AccountRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.IAccountService;
import java.util.Optional;
import java.util.Random;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountService implements IAccountService {

  private AccountRepository accountRepository;
  private CustomerRepository customerRepository;

  /**
   * @param customerDTO - CustomerDto Object
   */
  @Override
  public void createAccount(CustomerDTO customerDTO) {
    // Business logic to create account
    Customer customer = CustomerMapper.mapToCustomer(customerDTO, new Customer());
    Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(
        customerDTO.getMobileNumber());
    if (optionalCustomer.isPresent()) {
      throw new CustomerAlreadyExistException("Customer already registered with given mobileNumber "
          + customerDTO.getMobileNumber());
    }
    Customer saved = customerRepository.save(customer);
    accountRepository.save(createNewAccount(saved));
  }

  @Override
  public CustomerDTO fetchAccountDetails(String mobileNumber) {

    Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
        () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

    Accounts accounts = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
        () -> new ResourceNotFoundException("Account", "customerId",
            customer.getCustomerId().toString()));

    CustomerDTO customerDTO = CustomerMapper.mapToCustomerDTO(customer, new CustomerDTO());
    customerDTO.setAccountsDTO(
        AccountMapper.mapToAccountsDTO(accounts, new AccountsDTO()));

    return customerDTO;
  }

  @Override
  public Boolean updateAccount(CustomerDTO customerDTO) {
    boolean isUpdated = false;
    AccountsDTO accountsDTO = customerDTO.getAccountsDTO();
    if (accountsDTO != null) {
      Accounts accounts = accountRepository.findById(accountsDTO.getAccountNumber()).
          orElseThrow(() -> new ResourceNotFoundException("Account", "AccountNumber",
              accountsDTO.getAccountNumber().toString()));
      AccountMapper.mapToAccounts(accountsDTO, accounts);
      accounts = accountRepository.save(accounts);

      Long customerId = accounts.getCustomerId();
      Customer customer = customerRepository.findById(customerId).orElseThrow(
          () -> new ResourceNotFoundException("Customer", "CustomerId", customerId.toString()));

      CustomerMapper.mapToCustomer(customerDTO, customer);
      customerRepository.save(customer);

      isUpdated = true;

    }

    return isUpdated;
  }

  /**
   * @param mobileNumber
   * @return the boolean value
   */
  @Override
  public Boolean deleteAccount(String mobileNumber) {
    Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
        () -> new ResourceNotFoundException("Customer", "MobileNumber", mobileNumber));

    customerRepository.deleteById(customer.getCustomerId());
    accountRepository.deleteByCustomerId(customer.getCustomerId());
    return true;
  }

  /**
   * @param customer - Customer Object
   * @return the new account details
   */
  private Accounts createNewAccount(Customer customer) {
    Accounts newAccount = new Accounts();
    newAccount.setCustomerId(customer.getCustomerId());
    long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

    newAccount.setAccountNumber(randomAccNumber);
    newAccount.setAccountType(AccountsConstants.SAVINGS);
    newAccount.setBranchAddress(AccountsConstants.ADDRESS);
    return newAccount;
  }

}
