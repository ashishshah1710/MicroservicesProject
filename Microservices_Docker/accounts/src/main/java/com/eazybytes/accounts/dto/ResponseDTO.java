package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(
    name = "Response",
    description = "Schema to hold Response information"
)
public class ResponseDTO {

  @Schema(description = "Status Code of the Response", example = "200")
  private String statusCode;

  @Schema(description = "Status Message of the Response", example = "Request process Success")
  private String statusMsg;

}
