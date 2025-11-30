package uz.sqb.bankwallet.service;

import uz.sqb.bankwallet.dto.GenericResult;

import java.time.LocalDateTime;
import java.util.function.Supplier;

public abstract class BaseService {

    /**
     * Wraps service logic with exception handling and returns GenericResult
     */
    protected <T extends GenericResult> T executeWithExceptionHandling(
            Supplier<T> operation,
            Supplier<T> errorResponseSupplier) {
        try {
            return operation.get();
        } catch (Exception e) {
            T errorResponse = errorResponseSupplier.get();
            errorResponse.setStatus(1);
            errorResponse.setErrorMsg(e.getMessage() != null ? e.getMessage() : "Internal Server Error");
            errorResponse.setTimeStamp(LocalDateTime.now().toString());
            return errorResponse;
        }
    }

    /**
     * Creates a success response with common fields populated
     */
    protected <T extends GenericResult> T createSuccessResponse(T response) {
        response.setStatus(0);
        response.setErrorMsg("Success");
        response.setTimeStamp(LocalDateTime.now().toString());
        return response;
    }

    /**
     * Creates an error response with common fields populated
     */
    protected <T extends GenericResult> T createErrorResponse(T response, String errorMessage) {
        response.setStatus(1);
        response.setErrorMsg(errorMessage);
        response.setTimeStamp(LocalDateTime.now().toString());
        return response;
    }
}