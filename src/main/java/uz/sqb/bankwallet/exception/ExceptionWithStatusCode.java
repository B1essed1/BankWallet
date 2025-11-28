package uz.sqb.bankwallet.exception;

import lombok.Getter;

import java.util.Objects;

@Getter
public class ExceptionWithStatusCode extends RuntimeException {
    private Integer httpStatusCode;
    private String messageKey;
    private Objects[] args;

    public  ExceptionWithStatusCode(Integer status, String messageKey){
        this.httpStatusCode =status;
        this.messageKey = messageKey;
    }

    public ExceptionWithStatusCode( String messageKey){
        this.httpStatusCode =400;
        this.messageKey = messageKey;
    }

    public ExceptionWithStatusCode(Integer status, String messageKey,Throwable cause){
        super(cause);
        this.httpStatusCode =status;
        this.messageKey = messageKey;
    }



    public ExceptionWithStatusCode(Integer httpStatusCode, String messageKey, Objects[] args) {
        this.httpStatusCode = httpStatusCode;
        this.messageKey = messageKey;
        this.args = args;
    }

    public ExceptionWithStatusCode(Throwable cause, Integer httpStatusCode, String messageKey, Objects[] args) {
        super(cause);
        this.httpStatusCode = httpStatusCode;
        this.messageKey = messageKey;
        this.args = args;
    }

    public void andExpectMessage(String s) {

    }

    public static ExceptionWithStatusCode error(String messageKey, Objects[] args) {
        return new ExceptionWithStatusCode(500, messageKey, args);
    }


    public static ExceptionWithStatusCode badRequest(String messageKey, Objects[] args) {
        return new ExceptionWithStatusCode(400, messageKey, args);
    }

    public static ExceptionWithStatusCode badRequest(String messageKey) {
        return new ExceptionWithStatusCode(400, messageKey);
    }

    public static ExceptionWithStatusCode forbidden(String messageKey) {
        return new ExceptionWithStatusCode(403, messageKey);
    }

    public static ExceptionWithStatusCode unauthorized(String messageKey) {
        return new ExceptionWithStatusCode(401, messageKey);
    }
}
