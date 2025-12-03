package uz.sqb.bankwallet.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.sqb.bankwallet.dto.Parameter;
import uz.sqb.bankwallet.dto.TransactionStatement;
import uz.sqb.bankwallet.dto.request.*;
import uz.sqb.bankwallet.dto.response.*;
import uz.sqb.bankwallet.entity.Transaction;
import uz.sqb.bankwallet.entity.User;
import uz.sqb.bankwallet.entity.Wallet;
import uz.sqb.bankwallet.enums.TransactionStatus;
import uz.sqb.bankwallet.exception.ExceptionWithStatusCode;
import uz.sqb.bankwallet.repository.TransactionRepository;
import uz.sqb.bankwallet.repository.UserRepository;
import uz.sqb.bankwallet.repository.WalletRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private static final Random random = new Random();
    private final WalletRepository walletRepository;

    @Transactional
    public PerformTransactionResponse performTransaction(PerformTransactionRequest request) {
        String phone = getParameterValue(request.getParameters(), "phoneNumber");
        String walletNumber = getParameterValue(request.getParameters(), "walletNumber");

        if (phone == null && walletNumber == null) {
            throw ExceptionWithStatusCode.error("wallet.or.phone.required");
        }

        if (transactionRepository.existsByTransactionIdAndServiceId(request.getTransactionId(), request.getServiceId())) {
            throw ExceptionWithStatusCode.error("requires.unique.transaction");
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
        transaction.setTransactionTime(LocalDateTime.now());
        transaction =transactionRepository.save(transaction);

        var wallet = user.getWallet();
        wallet.setBalance(wallet.getBalance() + request.getAmount());
        user.setWallet(wallet);
        userRepository.save(user);


        List<Parameter> parameters = new ArrayList<>();
        parameters.add(Parameter.setParameter("amount",String.valueOf(transaction.getAmount())));
        parameters.add(Parameter.setParameter("transactionId",String.valueOf(transaction.getTransactionId())));
        PerformTransactionResponse result = new PerformTransactionResponse();
        result.setProviderTrnId(transaction.getId());
        result.setParameters(parameters);
        return result;
    }


    public CheckTransactionResponse checkTransaction(CheckTransactionRequest request) {

        Transaction transaction = transactionRepository.findByTransactionIdAndServiceId(request.getTransactionId(), request.getServiceId())
                .orElseThrow(() -> ExceptionWithStatusCode.error("transaction.no.found"));


        List<Parameter> parameters = new ArrayList<>();
        parameters.add(Parameter.setParameter("transactionId", String.valueOf(request.getTransactionId())));
        parameters.add(Parameter.setParameter("providerTrnId", String.valueOf(transaction.getId())));
        parameters.add(Parameter.setParameter("transactionStatus", String.valueOf(transaction.getStatus().ordinal())));

        CheckTransactionResponse response = new CheckTransactionResponse();
        response.setParameters(parameters);
        response.setStatus(0);
        response.setErrorMsg("success");
        return response;
    }

    public GetStatementResponse getStatement(GetStatementRequest request) {
        List<TransactionStatement> statements = transactionRepository.findAllStatementByServiceId(request.getServiceId(), request.getDateFrom(), request.getDateTo());
        GetStatementResponse result = new GetStatementResponse();
        result.setTransactions(statements);
        return result;
    }


    public GetInformationResponse getInformation(GetInformationRequest request) {
        User user = userRepository.findByServiceId(request.getServiceId()).orElseThrow(
                () -> ExceptionWithStatusCode.error("information.not.found")
        );


        List<Parameter> parameters = new ArrayList<>();
        parameters.add(Parameter.setParameter("serviceId", String.valueOf(request.getServiceId())));
        parameters.add(Parameter.setParameter("limit", String.valueOf(random.nextInt(124500000))));
        parameters.add(Parameter.setParameter("phoneNumber", user.getPhoneNumber()));
        parameters.add(Parameter.setParameter("walletNumber", user.getWallet().getWalletNumber()));


        GetInformationResponse response = new GetInformationResponse();
        response.setParameters(parameters);
        response.setStatus(0);
        return response;
    }

    @Transactional
    public CancelTransactionResponse cancelTransaction(CancelTransactionRequest request) {

        Transaction transaction = transactionRepository.findByTransactionIdAndServiceId(request.getTransactionId(),request.getServiceId())
                .orElseThrow(() -> ExceptionWithStatusCode.error("transaction.no.found"));

        User user = userRepository.findByServiceId(request.getServiceId()).orElseThrow(
                () -> ExceptionWithStatusCode.error("user.not.found")
        );
        Wallet wallet = user.getWallet();

        switch (transaction.getStatus()) {

            case SUCCESS:
                if (user.getWallet().getBalance() < transaction.getAmount()) {
                    throw ExceptionWithStatusCode.error("user.balance.insufficient");
                }

                transaction.setStatus(TransactionStatus.CANCELLED);
                wallet.setBalance(wallet.getBalance() - transaction.getAmount());

                walletRepository.save(wallet);
                transactionRepository.save(transaction);
                break;

            case CANCELLED:
                throw ExceptionWithStatusCode.error("transaction.already.cancelled");


            default:
                throw ExceptionWithStatusCode.error("transaction.status.not.cancellable");
        }


        List<Parameter> parameters = new ArrayList<>();
        parameters.add(Parameter.setParameter("transactionId", String.valueOf(transaction.getTransactionId())));
        parameters.add(Parameter.setParameter("providerTrnId", String.valueOf(transaction.getId())));
        parameters.add(Parameter.setParameter("transactionStatus", String.valueOf(transaction.getStatus().ordinal())));
        parameters.add(Parameter.setParameter("amount", String.valueOf(transaction.getAmount())));

        CancelTransactionResponse result = new CancelTransactionResponse();
        result.setParameters(parameters);
        return result;
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
