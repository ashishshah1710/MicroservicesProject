package com.eazybytes.accounts.mappers;

import com.eazybytes.accounts.dto.AccountsDTO;
import com.eazybytes.accounts.entity.Accounts;

public class AccountMapper {

  public static AccountsDTO mapToAccountsDTO(Accounts accounts, AccountsDTO AccountsDTO) {
    AccountsDTO.setAccountNumber(accounts.getAccountNumber());
    AccountsDTO.setAccountType(accounts.getAccountType());
    AccountsDTO.setBranchAddress(accounts.getBranchAddress());
    return AccountsDTO;
  }

  public static Accounts mapToAccounts(AccountsDTO AccountsDTO, Accounts accounts) {
    accounts.setAccountNumber(AccountsDTO.getAccountNumber());
    accounts.setAccountType(AccountsDTO.getAccountType());
    accounts.setBranchAddress(AccountsDTO.getBranchAddress());
    return accounts;
  }

}
