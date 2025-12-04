package uz.sqb.bankwallet.service;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
// OLD MANUAL CLASSES - COMMENTED OUT
// import uz.sqb.bankwallet.dto.Parameter;
// import uz.sqb.bankwallet.dto.TransactionStatement;
// import uz.sqb.bankwallet.dto.request.*;
// import uz.sqb.bankwallet.dto.response.*;
// NEW XSD-GENERATED CLASSES
import uz.sqb.bankwallet.data.TransactionStatementDto;
import uz.sqb.bankwallet.generated.CancelTransactionRequest;
import uz.sqb.bankwallet.generated.CancelTransactionResponse;
import uz.sqb.bankwallet.generated.CheckTransactionRequest;
import uz.sqb.bankwallet.generated.CheckTransactionResponse;
import uz.sqb.bankwallet.generated.GetInformationRequest;
import uz.sqb.bankwallet.generated.GetInformationResponse;
import uz.sqb.bankwallet.generated.GetStatementRequest;
import uz.sqb.bankwallet.generated.GetStatementResponse;
import uz.sqb.bankwallet.generated.Parameter;
import uz.sqb.bankwallet.generated.PerformTransactionRequest;
import uz.sqb.bankwallet.generated.PerformTransactionResponse;
import uz.sqb.bankwallet.generated.TransactionStatement;
import uz.sqb.bankwallet.entity.Transaction;
import uz.sqb.bankwallet.entity.User;
import uz.sqb.bankwallet.entity.Wallet;
import uz.sqb.bankwallet.enums.TransactionStatus;
import uz.sqb.bankwallet.exception.ExceptionWithStatusCode;
import uz.sqb.bankwallet.repository.TransactionRepository;
import uz.sqb.bankwallet.repository.UserRepository;
import uz.sqb.bankwallet.repository.WalletRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private static final Random random = new Random();

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
        parameters.add(createParameter("amount", String.valueOf(transaction.getAmount())));
        parameters.add(createParameter("transactionId", String.valueOf(transaction.getTransactionId())));
        PerformTransactionResponse result = new PerformTransactionResponse();
        result.setProviderTrnId(transaction.getId());
        result.getParameters().addAll(parameters);
        result.setStatus(0);
        result.setErrorMsg("success");
        result.setTimeStamp(String.valueOf(System.currentTimeMillis()));
        return result;
    }


    public CheckTransactionResponse checkTransaction(CheckTransactionRequest request) {

        Transaction transaction = transactionRepository.findByTransactionIdAndServiceId(request.getTransactionId(), request.getServiceId())
                .orElseThrow(() -> ExceptionWithStatusCode.error("transaction.no.found"));


        List<Parameter> parameters = new ArrayList<>();
        parameters.add(createParameter("transactionId", String.valueOf(request.getTransactionId())));
        parameters.add(createParameter("providerTrnId", String.valueOf(transaction.getId())));
        parameters.add(createParameter("transactionStatus", String.valueOf(transaction.getStatus().ordinal())));

        CheckTransactionResponse response = new CheckTransactionResponse();
        response.getParameters().addAll(parameters);
        response.setStatus(0);
        response.setErrorMsg("success");
        response.setTimeStamp(String.valueOf(System.currentTimeMillis()));
        return response;
    }

    public GetStatementResponse getStatement(GetStatementRequest request) {
        // Convert dateFrom and dateTo from String to LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime dateFrom = LocalDateTime.parse(request.getDateFrom(), formatter);
        LocalDateTime dateTo = LocalDateTime.parse(request.getDateTo(), formatter);

        List<uz.sqb.bankwallet.data.TransactionStatementDto> statements = transactionRepository.findAllStatementByServiceId(request.getServiceId(), dateFrom, dateTo);

        // Convert to XSD-generated TransactionStatement objects
        List<TransactionStatement> xsdStatements = new ArrayList<>();
        for (TransactionStatementDto stmt : statements) {
            TransactionStatement xsdStmt = new TransactionStatement();
            xsdStmt.setAmount(stmt.getAmount());
            xsdStmt.setProviderTrnId(stmt.getProviderTrnId());
            xsdStmt.setTransactionId(stmt.getTransactionId());
            xsdStmt.setTransactionTime(stmt.getTransactionTime().format(formatter));
            xsdStatements.add(xsdStmt);
        }

        GetStatementResponse result = new GetStatementResponse();
        result.getTransactions().addAll(xsdStatements);
        result.setStatus(0);
        result.setErrorMsg("success");
        result.setTimeStamp(String.valueOf(System.currentTimeMillis()));
        return result;
    }


    public GetInformationResponse getInformation(GetInformationRequest request) {
        User user = userRepository.findByServiceId(request.getServiceId()).orElseThrow(
                () -> ExceptionWithStatusCode.error("information.not.found")
        );


        List<Parameter> parameters = new ArrayList<>();
        parameters.add(createParameter("serviceId", String.valueOf(request.getServiceId())));
        parameters.add(createParameter("limit", String.valueOf(random.nextInt(124500000))));
        parameters.add(createParameter("phoneNumber", user.getPhoneNumber()));
        parameters.add(createParameter("walletNumber", user.getWallet().getWalletNumber()));

        GetInformationResponse response = new GetInformationResponse();
        response.getParameters().addAll(parameters);
        response.setStatus(0);
        response.setErrorMsg("success");
        response.setTimeStamp(String.valueOf(System.currentTimeMillis()));
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
        parameters.add(createParameter("transactionId", String.valueOf(transaction.getTransactionId())));
        parameters.add(createParameter("providerTrnId", String.valueOf(transaction.getId())));
        parameters.add(createParameter("transactionStatus", String.valueOf(transaction.getStatus().ordinal())));
        parameters.add(createParameter("amount", String.valueOf(transaction.getAmount())));

        CancelTransactionResponse result = new CancelTransactionResponse();
        result.getParameters().addAll(parameters);
        result.setStatus(0);
        result.setErrorMsg("success");
        result.setTimeStamp(String.valueOf(System.currentTimeMillis()));
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

    // Helper method to create Parameter objects from XSD-generated class
    private Parameter createParameter(String key, String value) {
        Parameter parameter = new Parameter();
        parameter.setParamKey(key);
        parameter.setParamValue(value);
        return parameter;
    }
}
