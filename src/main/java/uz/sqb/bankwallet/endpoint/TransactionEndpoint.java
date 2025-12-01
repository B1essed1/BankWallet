package uz.sqb.bankwallet.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import uz.sqb.bankwallet.dto.request.*;
import uz.sqb.bankwallet.dto.response.*;
import uz.sqb.bankwallet.service.TransactionService;

import static uz.sqb.bankwallet.utils.PUBLIC_STRINGS.NAMESPACE_URI;

@Endpoint
public class TransactionEndpoint {

    private final TransactionService transactionService;

    public TransactionEndpoint(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "PerformTransactionRequest")
    @ResponsePayload
    public PerformTransactionResponse performTransaction(@RequestPayload PerformTransactionRequest request) {
        return transactionService.performTransaction(request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CheckTransactionRequest")
    @ResponsePayload
    public CheckTransactionResponse checkTransaction(@RequestPayload CheckTransactionRequest request) {
        return transactionService.checkTransaction(request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CancelTransactionRequest")
    @ResponsePayload
    public CancelTransactionResponse cancelTransaction(@RequestPayload CancelTransactionRequest request) {
        return transactionService.cancelTransaction(request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetStatementRequest")
    @ResponsePayload
    public GetStatementResponse getStatement(@RequestPayload GetStatementRequest request) {
        return transactionService.getStatement(request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetInformationRequest")
    @ResponsePayload
    public GetInformationResponse getInformation(@RequestPayload GetInformationRequest request) {
        return transactionService.getInformation(request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "TransactionStatementRequest")
    @ResponsePayload
    public TransactionStatementResponse transactionStatement(@RequestPayload TransactionStatementRequest request) {
        return transactionService.transactionStatement(request);
    }

}