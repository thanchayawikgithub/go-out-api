package com.than.go_out_api.tourcompany;

import com.than.go_out_api.common.enums.TourCompanyStatus;
import com.than.go_out_api.common.exception.ResourceNotFoundException;
import com.than.go_out_api.tourcompany.dto.AddTourCompanyRequest;
import com.than.go_out_api.tourcompany.dto.TourCompanyDto;
import com.than.go_out_api.tourcompany.model.TourCompany;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TourCompanyServiceImpl implements TourCompanyService {
  private final TourCompanyRepository tourCompanyRepository;

  @Override
  public TourCompanyDto addTourCompany(AddTourCompanyRequest request) {
    TourCompany tourCompany = new TourCompany(null, request.getName(), TourCompanyStatus.WAITING.name());
    TourCompany savedTourCompany = tourCompanyRepository.save(tourCompany);
    log.info("[addTourCompany] added tour company: {}", tourCompany);
    return new TourCompanyDto(savedTourCompany.getId(), savedTourCompany.getName(),
        savedTourCompany.getStatus());
  }

  @Override
  public TourCompanyDto approveTourCompany(Integer id) {
    TourCompany tourCompany = tourCompanyRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("tour company id: " + id + " not found"));
    tourCompany.setStatus(TourCompanyStatus.APPROVED.name());
    TourCompany savedTourCompany = tourCompanyRepository.save(tourCompany);
    log.info("[approveTourCompany] approved tour company: {}", tourCompany);
    return new TourCompanyDto(savedTourCompany.getId(), savedTourCompany.getName(),
        savedTourCompany.getStatus());
  }
}
