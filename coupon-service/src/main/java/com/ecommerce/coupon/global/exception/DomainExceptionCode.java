package com.ecommerce.coupon.global.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum DomainExceptionCode {

  // coupon
  COUPON_OUT_OF_STOCK(HttpStatus.BAD_REQUEST, "쿠폰이 모두 소진되었습니다."),
  COUPON_ALREADY_USED(HttpStatus.BAD_REQUEST, "이미 사용된 쿠폰입니다."),
  COUPON_EXPIRED(HttpStatus.BAD_REQUEST, "만료된 쿠폰입니다."),
  COUPON_ALREADY_ISSUED(HttpStatus.BAD_REQUEST, "이미 발급된 쿠폰입니다."),
  NOT_FOUND_COUPON(HttpStatus.NOT_FOUND, "쿠폰을 찾을 수 없습니다."),

  //User
  UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증에 실패하였습니다."),
  ;

  final HttpStatus status;
  final String message;
}