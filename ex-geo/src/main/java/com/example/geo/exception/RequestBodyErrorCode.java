package com.example.geo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum RequestBodyErrorCode implements ErrorCodeType {

//    STATISTICS_STATISTICSTYPE_INVALID("통계 유형이 유효하지 않습니다.", HttpStatus.BAD_REQUEST, "typeMismatch.type"),
//    STATISTICS_START_INVALID("통계 시작일시 형식이 유효하지 않습니다.", HttpStatus.BAD_REQUEST, "typeMismatch.start"),
//    STATISTICS_END_INVALID("통계 종료일시 형식이 유효하지 않습니다.", HttpStatus.BAD_REQUEST, "typeMismatch.end"),
//    STATISTICS_STATISTICSVALUE_INVALID("통계 값이 유효하지 않습니다.", HttpStatus.BAD_REQUEST, "typeMismatch.value"),
    ;

    private final String message;
    private final HttpStatus httpStatus;
    private final String validationCode;

    public static RequestBodyErrorCode getBy(String validationCode) {
        for (RequestBodyErrorCode errorCode : values()) {
            if (validationCode.equals(errorCode.getValidationCode())) {
                return errorCode;
            }
        }
        throw new CustomException(ErrorCode.NOT_FOUND_ERROR_CODE);
    }

}
