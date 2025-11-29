package uz.sqb.bankwallet.service;

import com.provider.uws.Parameter;
import com.provider.uws.PerformTransactionRequest;
import com.provider.uws.PerformTransactionResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.sqb.bankwallet.entity.Transaction;
import uz.sqb.bankwallet.repository.TransactionRepository;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;


    public PerformTransactionResult performTransaction(PerformTransactionRequest request) {
        PerformTransactionResult result = new PerformTransactionResult();

        try {
            validateCredentials(request.getUsername(), request.getPassword());

            Transaction transaction = new Transaction();
            transaction.setTransactionId((long) request.getTransactionId());
            transaction.setServiceId((long) request.getServiceId());
            transaction.setAmount(request.getAmount().longValue());
            transaction.setTransactionTime(xmlGregorianCalendarToLocalDateTime(request.getTransactionTime()));

            extractParameters(request, transaction);

            Transaction savedTransaction = transactionRepository.save(transaction);
            transaction.setStatus(Transaction.TransactionStatus.COMPLETED);
            transactionRepository.save(transaction);

            result.setStatus(0);
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

    private LocalDateTime xmlGregorianCalendarToLocalDateTime(XMLGregorianCalendar calendar) {
        if (calendar == null) {
            return LocalDateTime.now();
        }
        return calendar.toGregorianCalendar()
                .toZonedDateTime()
                .withZoneSameInstant(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}
