package com.than.go_out_api.tourcompany.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourCompanyDto {
  private Integer id;
  private String name;
  private String status;
}
