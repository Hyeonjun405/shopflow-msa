package com.ecommerce.Payment.global.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum DomainExceptionCode {

  // payment
  CANNOT_CANCEL_PAYMENT(HttpStatus.BAD_REQUEST, "취소할 수 없는 결제입니다."),
  CANNOT_PAY_ORDER(HttpStatus.BAD_REQUEST, "결제할 수 없는 주문입니다."),
  ALREADY_PAID(HttpStatus.BAD_REQUEST, "이미 결제된 주문입니다."),
  NOT_FOUND_PAYMENT(HttpStatus.NOT_FOUND, "결제 내역을 찾을 수 없습니다."),
  UNSUPPORTED_PAYMENT_TYPE(HttpStatus.BAD_REQUEST, "지원하지 않는 결제 수단입니다."),
  PAYMENT_FAILED(HttpStatus.BAD_REQUEST, "결제에 실패하였습니다."),
  PAYMENT_CANCEL_FAILED(HttpStatus.BAD_REQUEST, "결제 취소에 실패하였습니다."),


  UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증에 실패하였습니다."),
  ;

  final HttpStatus status;
  final String message;
}