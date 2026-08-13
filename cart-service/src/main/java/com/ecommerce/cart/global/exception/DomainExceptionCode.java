package com.ecommerce.cart.global.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum DomainExceptionCode {

  // Cart
  NOT_FOUND_CART(HttpStatus.NOT_FOUND, "장바구니를 찾을 수 없습니다."),
  NOT_FOUND_CART_ITEM(HttpStatus.NOT_FOUND, "장바구니 아이템을 찾을 수 없습니다."),

  UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증에 실패하였습니다."),
  ;

  final HttpStatus status;
  final String message;
}