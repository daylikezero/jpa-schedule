package com.example.jpaschedule.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "001_INVALID_PARAMETER", "파라미터 값을 확인해주세요."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "002_USER_NOT_FOUND", "존재하지 않는 유저 ID 입니다."),
    SCHEDULE_NOT_FOUND(HttpStatus.NOT_FOUND, "003_SCHEDULE_NOT_FOUND", "존재하지 않는 일정 ID 입니다."),
    ENTITY_DELETED(HttpStatus.NOT_FOUND, "004_ENTITY_DELETED", "이미 삭제된 정보입니다."),
    INVALID_CONSTRAINTS(HttpStatus.BAD_REQUEST, "005_INVALID_CONSTRAINTS", "필수 조건을 확인해주세요."),
    EMAIL_INCORRECT(HttpStatus.UNAUTHORIZED, "006_EMAIL_INCORRECT", "이메일이 일치하지 않습니다."),
    PASSWORD_INCORRECT(HttpStatus.UNAUTHORIZED, "007_PASSWORD_INCORRECT", "비밀번호가 일치하지 않습니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "008_UNAUTHORIZED", "접근 권한이 없습니다."),
    UNKNOWN(HttpStatus.INTERNAL_SERVER_ERROR, "999_UNKNOWN", "알 수 없는 오류가 발생했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
