package com.than.go_out_api.tourcompany.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddTourCompanyRequest {
  @NotBlank(message = "Name is required")
  private String name;
}
