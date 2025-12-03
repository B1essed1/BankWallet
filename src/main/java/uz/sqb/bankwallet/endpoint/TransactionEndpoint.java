package uz.sqb.bankwallet.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
// OLD MANUAL CLASSES - COMMENTED OUT
// import uz.sqb.bankwallet.dto.request.*;
// import uz.sqb.bankwallet.dto.response.*;
// NEW XSD-GENERATED CLASSES
import uz.sqb.bankwallet.generated.CancelTransactionRequest;
import uz.sqb.bankwallet.generated.CancelTransactionResponse;
import uz.sqb.bankwallet.generated.CheckTransactionRequest;
import uz.sqb.bankwallet.generated.CheckTransactionResponse;
import uz.sqb.bankwallet.generated.GetInformationRequest;
import uz.sqb.bankwallet.generated.GetInformationResponse;
import uz.sqb.bankwallet.generated.GetStatementRequest;
import uz.sqb.bankwallet.generated.GetStatementResponse;
import uz.sqb.bankwallet.generated.PerformTransactionRequest;
import uz.sqb.bankwallet.generated.PerformTransactionResponse;
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


}