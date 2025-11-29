/*
package uz.sqb.bankwallet.configuration;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.sqb.bankwallet.component.LocalizationComponent;
import uz.sqb.bankwallet.data.base.ResponseWrapper;
import uz.sqb.bankwallet.exception.ExceptionWithStatusCode;


@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ExceptionController{

    private final LocalizationComponent localizationComponent;

    @ExceptionHandler({ExceptionWithStatusCode.class})
    ResponseEntity<ResponseWrapper<?>> handeCustomErrors(ExceptionWithStatusCode ex, HttpServletRequest request, HttpServletResponse response) {
        //TODO  internationalization
        log.error(ex.getMessage(), ex.getStackTrace());

        String message  = localizationComponent.getMessage(ex.getMessageKey());
        message = message.isBlank()?ex.getMessageKey():message;
        return ResponseEntity.status(ex.getHttpStatusCode()).body(ResponseWrapper.error(ex.getHttpStatusCode(), message));
    }

    @ExceptionHandler({Throwable.class})
    ResponseEntity<ResponseWrapper<?>> handleOtherErrors(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        log.error(ex.getMessage(), ex.getStackTrace());

        return ResponseEntity.status(500).body(ResponseWrapper.internalServerError("Internal server error"));
    }
}

*/
