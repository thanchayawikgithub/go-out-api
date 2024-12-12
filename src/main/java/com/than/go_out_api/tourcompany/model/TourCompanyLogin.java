package com.than.go_out_api.tourcompany.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourCompanyLogin {
  @Id
  private Integer id;
  private AggregateReference<TourCompany, Integer> tourCompanyId;
  private String username;
  private String password;
}
