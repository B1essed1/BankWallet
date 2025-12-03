package uz.sqb.bankwallet.dto.request;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;
import uz.sqb.bankwallet.dto.GenericAuthRequest;
import uz.sqb.bankwallet.utils.LocalDateTimeAdapter;
import uz.sqb.bankwallet.utils.PUBLIC_STRINGS;

import java.time.LocalDateTime;

@Data
@XmlRootElement(name = "PerformTransactionRequest",  namespace = PUBLIC_STRINGS.NAMESPACE_URI)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PerformTransactionRequest", propOrder = {
        "amount",
        "providerTrnId",
        "transactionId",
        "transactionTime"
})
public class TransactionStatementRequest extends GenericAuthRequest {

    @XmlElement(required = true)
    private Long amount;
    @XmlElement(required = true)
    private Long providerTrnId;
    @XmlElement(required = true)
    private String transactionId;
    @XmlElement(required = true)
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime transactionTime;
}
