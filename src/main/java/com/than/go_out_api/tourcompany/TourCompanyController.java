package com.than.go_out_api.tourcompany;

import com.than.go_out_api.common.ApiResponse;
import com.than.go_out_api.tourcompany.dto.AddTourCompanyRequest;
import com.than.go_out_api.tourcompany.dto.TourCompanyDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/tour-company")
@RequiredArgsConstructor
@Slf4j
public class TourCompanyController {
  private final TourCompanyService tourCompanyService;

  @PostMapping
  public ResponseEntity<ApiResponse> addTourCompany(
      @RequestBody @Valid AddTourCompanyRequest request) {
    TourCompanyDto tourCompany = tourCompanyService.addTourCompany(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(HttpStatus.CREATED, "Success", tourCompany));
  }

  @PatchMapping("/{id}/approve")
  public ResponseEntity<ApiResponse> approveTourCompany(
      @PathVariable("id") Integer id) {
    TourCompanyDto tourCompany = tourCompanyService.approveTourCompany(id);
    return ResponseEntity.ok(new ApiResponse(HttpStatus.OK, "Success", tourCompany));
  }
}
