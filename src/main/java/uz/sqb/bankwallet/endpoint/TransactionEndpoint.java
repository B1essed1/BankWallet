package uz.sqb.bankwallet.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import uz.sqb.bankwallet.dto.request.PerformTransactionRequest;
import uz.sqb.bankwallet.dto.response.PerformTransactionResponse;
import uz.sqb.bankwallet.service.TransactionService;

@Endpoint
public class TransactionEndpoint {

    private static final String NAMESPACE_URI = "http://uws.provider.com/";

    private final TransactionService transactionService;

    public TransactionEndpoint(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "PerformTransactionRequest")
    @ResponsePayload
    public PerformTransactionResponse performTransaction(@RequestPayload PerformTransactionRequest request) {
        return transactionService.performTransaction(request);
    }
//
//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetInformationRequest")
//    @ResponsePayload
//    public GetInformationResponse getInformation(@RequestPayload GetInformationRequest request) {
//        return transactionService.performTransaction(request);
//    }


}