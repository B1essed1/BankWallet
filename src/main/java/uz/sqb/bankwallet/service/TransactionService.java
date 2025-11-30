package uz.sqb.bankwallet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.sqb.bankwallet.dto.Parameter;
import uz.sqb.bankwallet.dto.request.PerformTransactionRequest;
import uz.sqb.bankwallet.dto.response.PerformTransactionResponse;
import uz.sqb.bankwallet.entity.Transaction;
import uz.sqb.bankwallet.enums.TransactionStatus;
import uz.sqb.bankwallet.repository.TransactionRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;


    public PerformTransactionResponse performTransaction(PerformTransactionRequest request) {
        PerformTransactionResponse result = new PerformTransactionResponse();

        try {
            validateCredentials(request.getUsername(), request.getPassword());

            Transaction transaction = new Transaction();
            transaction.setTransactionId((long) request.getTransactionId());
            transaction.setServiceId((long) request.getServiceId());
            transaction.setAmount(request.getAmount().longValue());
            transaction.setTransactionTime(request.getTransactionTime() != null ? request.getTransactionTime() : LocalDateTime.now());

            extractParameters(request, transaction);

            Transaction savedTransaction = transactionRepository.save(transaction);
            transaction.setStatus(TransactionStatus.SUCCESS);
            transactionRepository.save(transaction);

            result.setStatus(TransactionStatus.SUCCESS.ordinal());
            result.setErrorMsg("Success");
            result.setProviderTrnId(savedTransaction.getId().intValue());
            result.setTimeStamp(LocalDateTime.now().toString());

        } catch (Exception e) {
            result.setStatus(1);
            result.setErrorMsg(e.getMessage());
            result.setProviderTrnId(0);
            result.setTimeStamp(LocalDateTime.now().toString());
        }

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
