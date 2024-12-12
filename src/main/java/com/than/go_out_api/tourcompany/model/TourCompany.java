package com.than.go_out_api.tourcompany.model;

import org.springframework.data.annotation.Id;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourCompany {
  @Id
  private Integer id;
  private String name;
  private String status;
}
