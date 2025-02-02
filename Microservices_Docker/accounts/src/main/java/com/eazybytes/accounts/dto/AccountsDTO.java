package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
    name = "Accounts",
    description = "Schema to hold Account information"
)
public class AccountsDTO {

  @NotEmpty(message = "Account number cannot be null or empty")
  @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number should be 10 digits")
  @Schema(
      description = "Account number of the Customer",
      example = "1234567890"
  )
  private Long accountNumber;

  @NotEmpty(message = "Account type cannot be null or empty")
  @Schema(
      description = "Account type of the Customer",
      example = "Savings"
  )
  private String accountType;

  @NotEmpty(message = "Branch address cannot be null or empty")
  @Schema(
      description = "Branch address of the Customer",
      example = "123 Main Street, New York"
  )
  private String branchAddress;

}
