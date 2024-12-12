package com.than.go_out_api.tourcompany.model;

import java.math.BigDecimal;
import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourCompanyWallet {
  @Id
  private Integer id;
  private AggregateReference<TourCompany, Integer> tourCompanyId;
  private Instant lastUpdated;
  private BigDecimal balance;
}
