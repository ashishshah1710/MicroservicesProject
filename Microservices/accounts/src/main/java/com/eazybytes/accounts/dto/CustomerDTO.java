package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
    name = "Customer",
    description = "Schema to hold Customer And Account information"
)
public class CustomerDTO {

  @NotEmpty(message = "Name cannot be null or empty")
  @Size(min = 5, max = 30, message = "Name should be between 5 and 30 characters")
  @Schema(description = "Name of the Customer", example = "John Doe")
  private String name;

  @NotEmpty(message = "Email cannot be null or empty")
  @Email(message = "Email should be valid")
  @Schema(description = "Email of the Customer", example = "abc@gmail.com")
  private String email;

  @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits")
  @Schema(description = "Mobile number of the Customer", example = "1234567890")
  private String mobileNumber;

  @Schema(description = "Account details of the Customer")
  private AccountsDTO accountsDTO;
}
