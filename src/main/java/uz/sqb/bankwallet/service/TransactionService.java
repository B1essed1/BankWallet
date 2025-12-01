package uz.sqb.bankwallet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.sqb.bankwallet.dto.Parameter;
import uz.sqb.bankwallet.dto.request.*;
import uz.sqb.bankwallet.dto.response.*;
import uz.sqb.bankwallet.entity.Transaction;
import uz.sqb.bankwallet.repository.TransactionRepository;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;


    public PerformTransactionResponse performTransaction(PerformTransactionRequest request) {
        PerformTransactionResponse result = new PerformTransactionResponse();


        return result;
    }


    public CheckTransactionResponse checkTransaction(CheckTransactionRequest request) {
        CheckTransactionResponse result = new CheckTransactionResponse();

        return result;
    }

    public GetStatementResponse getStatement(GetStatementRequest request) {
        GetStatementResponse result = new GetStatementResponse();



        return result;
    }


    public GetInformationResponse getInformation(GetInformationRequest request) {
        GetInformationResponse result = new GetInformationResponse();

        return result;
    }

    public CancelTransactionResponse cancelTransaction(CancelTransactionRequest request) {
        CancelTransactionResponse result = new CancelTransactionResponse();



        return result;
    }

    public TransactionStatementResponse transactionStatement(TransactionStatementRequest request) {
        TransactionStatementResponse result = new TransactionStatementResponse();

        return result;
    }







    private void validateCredentials(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Invalid credentials");
        }
    }

    private void extractParameters(PerformTransactionRequest request, Transaction transaction) {
        if (request.getParameters() != null) {
            for (Parameter param : request.getParameters()) {
                if ("phoneNumber".equals(param.getParamKey())) {
                    transaction.setPhoneNumber(param.getParamValue());
                } else if ("walletNumber".equals(param.getParamKey())) {
                    transaction.setWalletNumber(param.getParamValue());
                }
            }
        }
    }
}
