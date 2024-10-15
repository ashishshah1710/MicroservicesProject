package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.CustomerDTO;

public interface IAccountService {

  /**
   * @param customerDTO - CustomerDto Object
   */
  void createAccount(CustomerDTO customerDTO);

  CustomerDTO fetchAccountDetails(String mobileNumber);

  Boolean updateAccount(CustomerDTO customerDTO);

  Boolean deleteAccount(String mobileNumber);
}


