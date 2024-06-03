package com.tanmoy.AuthUser.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * Base response class.
 */
@Data
@NoArgsConstructor
public class BaseResponse {

    public static final String DEFAULT_SUCCESS_MESSAGE = "Success";
    private static final String DEFAULT_ERROR_MESSAGE = "Error";

    private int code;
    private Status status;
    private String message;
    private Object data;

    public static BaseResponse getErrorResponse(int code, String message) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(code);
        baseResponse.setStatus(Status.FAIL);
        baseResponse.setMessage(message);
        return baseResponse;
    }

    public static BaseResponse getSuccessResponse() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(HttpStatus.OK.value());
        baseResponse.setMessage(DEFAULT_SUCCESS_MESSAGE);
        baseResponse.setStatus(Status.SUCCESS);
        return baseResponse;
    }

    public static BaseResponse getSuccessResponse(Object data) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(HttpStatus.OK.value());
        baseResponse.setMessage(DEFAULT_SUCCESS_MESSAGE);
        baseResponse.setStatus(Status.SUCCESS);
        baseResponse.setData(data);
        return baseResponse;
    }

    public enum Status {
        SUCCESS,
        FAIL,
    }

}
