package com.than.go_out_api.tour;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tour {
  private int id;
  private String title;
  private int maxPeople;
}
