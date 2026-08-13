package com.ecommerce.order.global.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum DomainExceptionCode {

  //Order
  CANNOT_CANCEL_ORDER(HttpStatus.BAD_REQUEST, "취소할 수 없는 주문입니다."),
  NOT_FOUND_ORDER(HttpStatus.NOT_FOUND, "주문을 찾을 수 없습니다."),
  NOT_MEET_MIN_ORDER_PRICE(HttpStatus.BAD_REQUEST, "최소 주문금액을 충족하지 못했습니다."),

  UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증에 실패하였습니다."),
  ;

  final HttpStatus status;
  final String message;
}