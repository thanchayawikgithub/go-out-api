package com.than.go_out_api.tourcompany;

import com.than.go_out_api.common.enums.TourCompanyStatus;
import com.than.go_out_api.common.exception.ResourceNotFoundException;
import com.than.go_out_api.tourcompany.dto.AddTourCompanyRequest;
import com.than.go_out_api.tourcompany.dto.TourCompanyDto;
import com.than.go_out_api.tourcompany.model.TourCompany;
import com.than.go_out_api.tourcompany.model.TourCompanyLogin;
import com.than.go_out_api.tourcompany.model.TourCompanyWallet;
import com.than.go_out_api.tourcompany.repository.TourCompanyLoginRepository;
import com.than.go_out_api.tourcompany.repository.TourCompanyRepository;
import com.than.go_out_api.tourcompany.repository.TourCompanyWalletRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.Instant;

import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TourCompanyServiceImpl implements TourCompanyService {
  private final TourCompanyRepository tourCompanyRepository;
  private final TourCompanyLoginRepository tourCompanyLoginRepository;
  private final TourCompanyWalletRepository tourCompanyWalletRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  @Transactional
  public TourCompanyDto addTourCompany(AddTourCompanyRequest request) {
    TourCompany tourCompany = new TourCompany(null, request.getName(), TourCompanyStatus.WAITING.name());
    TourCompany savedTourCompany = tourCompanyRepository.save(tourCompany);
    log.info("[addTourCompany] added tour company: {}", tourCompany);

    TourCompanyLogin tourCompanyLogin = createTourCompanyLogin(savedTourCompany, request.getUsername(),
        request.getPassword());
    tourCompanyLoginRepository.save(tourCompanyLogin);
    log.info("[approveTourCompany] created tour company credential: {}", tourCompanyLogin);

    return new TourCompanyDto(savedTourCompany.getId(), savedTourCompany.getName(),
        savedTourCompany.getStatus());
  }

  @Override
  @Transactional
  public TourCompanyDto approveTourCompany(Integer id) {
    TourCompany tourCompany = tourCompanyRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("tour company id: " + id + " not found"));
    tourCompany.setStatus(TourCompanyStatus.APPROVED.name());
    TourCompany savedTourCompany = tourCompanyRepository.save(tourCompany);
    log.info("[approveTourCompany] approved tour company: {}", tourCompany);

    TourCompanyWallet tourCompanyWallet = createTourCompanyWallet(savedTourCompany);
    tourCompanyWalletRepository.save(tourCompanyWallet);
    log.info("[approveTourCompany] created tour company wallet: {}", tourCompanyWallet);

    return new TourCompanyDto(savedTourCompany.getId(), savedTourCompany.getName(),
        savedTourCompany.getStatus());
  }

  private TourCompanyLogin createTourCompanyLogin(TourCompany tourCompany, String username, String password) {
    AggregateReference<TourCompany, Integer> tourCompanyRef = AggregateReference.to(tourCompany.getId());
    return new TourCompanyLogin(null, tourCompanyRef, username,
        passwordEncoder.encode(password));
  }

  private TourCompanyWallet createTourCompanyWallet(TourCompany tourCompany) {
    AggregateReference<TourCompany, Integer> tourCompanyRef = AggregateReference.to(tourCompany.getId());
    return new TourCompanyWallet(null, tourCompanyRef, Instant.now(), BigDecimal.ZERO);
  }
}
