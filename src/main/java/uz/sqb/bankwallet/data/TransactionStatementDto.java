package uz.sqb.bankwallet.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class TransactionStatementDto {
    protected Long amount;
    protected Long providerTrnId;
    protected Long transactionId;
    protected LocalDateTime transactionTime;
}
