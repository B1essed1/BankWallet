package uz.sqb.bankwallet.dto;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransactionStatement", propOrder = {
    "amount",
    "providerTrnId",
    "transactionId",
    "transactionTime"
})
public class TransactionStatement {

    private long amount;

    private long providerTrnId;

    private long transactionId;

    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    private LocalDateTime transactionTime;
}