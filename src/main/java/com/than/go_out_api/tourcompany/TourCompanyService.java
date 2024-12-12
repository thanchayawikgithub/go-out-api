package com.than.go_out_api.tourcompany;

import com.than.go_out_api.tourcompany.dto.AddTourCompanyRequest;
import com.than.go_out_api.tourcompany.dto.TourCompanyDto;

public interface TourCompanyService {
  TourCompanyDto addTourCompany(AddTourCompanyRequest request);

  TourCompanyDto approveTourCompany(Integer id);
}
