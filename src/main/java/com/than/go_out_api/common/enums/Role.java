package com.than.go_out_api.common.enums;

public enum Role {
  ADMIN(1),
  CONSUMER(2),
  COMPANY(3);

  private final int id;

  Role(int id) {
    this.id = id;
  }

  public int getId() {
    return this.id;
  }
}
