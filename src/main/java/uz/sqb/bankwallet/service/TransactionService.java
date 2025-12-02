package uz.sqb.bankwallet.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.sqb.bankwallet.dto.Parameter;
import uz.sqb.bankwallet.dto.request.*;
import uz.sqb.bankwallet.dto.response.*;
import uz.sqb.bankwallet.entity.Transaction;
import uz.sqb.bankwallet.entity.User;
import uz.sqb.bankwallet.enums.TransactionStatus;
import uz.sqb.bankwallet.exception.ExceptionWithStatusCode;
import uz.sqb.bankwallet.repository.TransactionRepository;
import uz.sqb.bankwallet.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;


    @Transactional
    public PerformTransactionResponse performTransaction(PerformTransactionRequest request) {
        PerformTransactionResponse result = new PerformTransactionResponse();
        String phone = getParameterValue(request.getParameters(), "phoneNumber");
        String walletNumber = getParameterValue(request.getParameters(), "walletNumber");

        if (phone == null && walletNumber == null) {
            throw ExceptionWithStatusCode.error("wallet.or.phone.required");
        }
        User user;
        if (walletNumber != null) {
            user = userRepository.findByWalletNumber(walletNumber).orElseThrow(() -> ExceptionWithStatusCode.error("wrong.wallet.number"));
        } else {
            user = userRepository.findByPhoneNumber(phone).orElseThrow(() -> ExceptionWithStatusCode.error("wrong.phone.number"));
        }
        Transaction transaction = new Transaction();
        transaction.setTransactionId(request.getTransactionId());
        transaction.setServiceId(request.getServiceId());
        transaction.setAmount(request.getAmount());
        transaction.setPhoneNumber(phone);
        transaction.setWalletNumber(walletNumber);
        transaction.setStatus(TransactionStatus.SUCCESS);
        transactionRepository.save(transaction);

        var wallet = user.getWallet();
        wallet.setBalance(wallet.getBalance() + request.getAmount());
        user.setWallet(wallet);
        userRepository.save(user);
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

    private String getParameterValue(List<Parameter> parameters, String key) {
        if (parameters != null) {
            for (Parameter param : parameters) {
                if (key.equals(param.getParamKey())) {
                    return param.getParamValue();
                }
            }
        }
        return null;
    }
}
