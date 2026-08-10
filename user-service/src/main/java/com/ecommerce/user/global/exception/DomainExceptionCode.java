package com.ecommerce.user.global.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum DomainExceptionCode {

  NOT_FOUND_USER(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다."),
  DUPLICATE_EMAIL(HttpStatus.CONFLICT, "이미 사용중인 이메일입니다."),
  UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증에 실패하였습니다."),
  NO_CHANGES(HttpStatus.BAD_REQUEST, "변경된 정보가 없습니다."),
  ALREADY_WITHDRAWN(HttpStatus.BAD_REQUEST, "이미 탈퇴한 회원입니다."),
  WITHDRAWN_USER(HttpStatus.BAD_REQUEST, "탈퇴한 회원입니다."),
  SUSPENDED_USER(HttpStatus.FORBIDDEN, "정지된 계정입니다."),
  ;

  final HttpStatus status;
  final String message;
}