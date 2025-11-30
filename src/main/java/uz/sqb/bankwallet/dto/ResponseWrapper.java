package uz.sqb.bankwallet.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class ResponseWrapper<T> {

    @Setter
    Integer code = 2000;

    T data;

    List<UUID> errorRef;

    String errorMessage;

    Long timestamp;


    //TODO: constructor chain should be fixed


    public ResponseWrapper(T data) {
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }

    public ResponseWrapper(Integer code, T data) {
        this.data = data;
        this.code =code;
        this.timestamp = System.currentTimeMillis();
    }
    public ResponseWrapper(Integer code, String errorMessage) {
        this.code = code;
        this.timestamp = System.currentTimeMillis();
    }

    public ResponseWrapper(Integer code, String errorMessage, T data) {
        this.code = code;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }



    public static <T> ResponseWrapper<T> success(T data) {
        return new ResponseWrapper<>(200, data);
    }

    public static <T> ResponseWrapper<T> created(T data) {
        return new ResponseWrapper<>(201, data);
    }
    public static <T> ResponseWrapper<T> error(Integer code, String errorMessage) {
        return new ResponseWrapper<>(code, errorMessage);
    }

    public static <T> ResponseWrapper<T> badRequest(String errorMessage) {
        return new ResponseWrapper<>(400, errorMessage);
    }

    public static <T> ResponseWrapper<T> unauthorized(String errorMessage) {
        return new ResponseWrapper<>(401, errorMessage);
    }

    public static <T> ResponseWrapper<T> forbidden(String errorMessage) {
        return new ResponseWrapper<>(403, errorMessage);
    }

    public static <T> ResponseWrapper<T> notFound(String errorMessage) {
        return new ResponseWrapper<>(404, errorMessage);
    }

    public static <T> ResponseWrapper<T> internalServerError(String errorMessage) {
        return new ResponseWrapper<>(500, errorMessage);
    }

    public static <T> ResponseWrapper<T> conflict(String errorMessage) {
        return new ResponseWrapper<>(409, errorMessage);
    }

    public static <T> ResponseWrapper<T> unsupportedMediaType(String errorMessage) {
        return new ResponseWrapper<>(415, errorMessage);
    }

    public static <T> ResponseWrapper<T> tooManyRequests(String errorMessage) {
        return new ResponseWrapper<>(429, errorMessage);
    }

    public static <T> ResponseWrapper<T> serviceUnavailable(String errorMessage) {
        return new ResponseWrapper<>(503, errorMessage);
    }

    public static <T> ResponseWrapper<T> gatewayTimeout(String errorMessage) {
        return new ResponseWrapper<>(504, errorMessage);
    }

    public static <T> ResponseWrapper<T> notAcceptable(String errorMessage) {
        return new ResponseWrapper<>(406, errorMessage);
    }

    public static <T> ResponseWrapper<T> tooManyRedirects(String errorMessage) {
        return new ResponseWrapper<>(414, errorMessage);
    }

    public static <T> ResponseWrapper<T> tooLarge(String errorMessage) {
        return new ResponseWrapper<>(413, errorMessage);
    }

    public static <T> ResponseWrapper<T> requestEntityTooLarge(String errorMessage) {
        return new ResponseWrapper<>(413, errorMessage);
    }

    public static <T> ResponseWrapper<T> requestHeaderFieldsTooLarge(String errorMessage) {
        return new ResponseWrapper<>(431, errorMessage);
    }
}