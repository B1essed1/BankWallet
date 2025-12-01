package uz.sqb.bankwallet.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.sqb.bankwallet.component.LocalizationComponent;
import uz.sqb.bankwallet.dto.ErrorResult;
import uz.sqb.bankwallet.dto.GenericResult;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final LocalizationComponent localizationComponent;

    @ExceptionHandler(ExceptionWithStatusCode.class)
    public ResponseEntity<? extends GenericResult> handleExceptionWithStatusCode(ExceptionWithStatusCode ex) {
        log.error("ExceptionWithStatusCode: {}", ex.getMessage(), ex);

        // Translate the error message
        String translatedMessage = localizationComponent.getMessage(ex.getMessageKey(), ex.getArgs());

        // Create your GenericResult implementation
        ErrorResult errorResult = new ErrorResult();
        errorResult.setErrorMsg(translatedMessage);
        errorResult.setStatus(ex.getHttpStatusCode());
        errorResult.setTimeStamp(LocalDateTime.now().toString());

        return ResponseEntity
                .status(ex.getHttpStatusCode())
                .body(errorResult);
    }

    // Optional: catch all other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<? extends GenericResult> handleGenericException(Exception ex) {
        log.error("Unexpected exception: {}", ex.getMessage(), ex);

        ErrorResult errorResult = new ErrorResult();
        errorResult.setErrorMsg("Internal server error");
        errorResult.setStatus(500);
        errorResult.setTimeStamp(LocalDateTime.now().toString());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResult);
    }
}