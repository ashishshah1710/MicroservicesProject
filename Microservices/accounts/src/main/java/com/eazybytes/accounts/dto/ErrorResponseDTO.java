package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Schema(
    name = "Error Response",
    description = "Schema to hold Error Response information"
)
public class ErrorResponseDTO {

  @Schema(description = "API Path of the Request", example = "/api/create")
  private String apiPath;

  @Schema(description = "HTTP Status Code of the Response", example = "500")
  private HttpStatus errorCode;

  @Schema(description = "Error Message of the Response", example = "Internal Server Error")
  private String errorMessage;

  @Schema(description = "Error Time of the Response", example = "2021-07-01T10:00:00")
  private LocalDateTime errorTime;

}
