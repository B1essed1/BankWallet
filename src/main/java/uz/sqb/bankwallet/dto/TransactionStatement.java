package uz.sqb.bankwallet.dto;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.AllArgsConstructor;
import lombok.Data;
import uz.sqb.bankwallet.utils.LocalDateTimeAdapter;

import java.time.LocalDateTime;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransactionStatement", propOrder = {
        "amount",
        "providerTrnId",
        "transactionId",
        "transactionTime"
})
@AllArgsConstructor
public class TransactionStatement {

    @XmlElement(required = true)
    private Long amount;

    @XmlElement(required = true)
    private Long providerTrnId;

    @XmlElement(required = true)
    private Long transactionId;

    @XmlElement(required = true)
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime transactionTime;
}