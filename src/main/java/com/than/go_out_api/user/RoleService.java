package com.than.go_out_api.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleService {
  private final RoleRepository roleRepository;
  private final Logger logger = LoggerFactory.getLogger(RoleService.class);

  public Iterable<Role> getAllRoles() {
    var roles = roleRepository.findAll();
    logger.info("get all roles : {}", roles);
    return roles;
  }
}
