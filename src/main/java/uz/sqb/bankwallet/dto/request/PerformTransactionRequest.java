package uz.sqb.bankwallet.dto.request;

import jakarta.xml.bind.annotation.*;
import lombok.Data;
import uz.sqb.bankwallet.dto.Parameter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@XmlRootElement(name = "PerformTransactionRequest", namespace = "http://uws.provider.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PerformTransactionRequest", propOrder = {
    "username",
    "password",
    "amount",
    "parameters",
    "serviceId",
    "transactionId",
    "transactionTime"
})
public class PerformTransactionRequest {

    @XmlElement(required = true)
    private String username;

    @XmlElement(required = true)
    private String password;

    @XmlElement(required = true)
    private BigDecimal amount;

    private List<Parameter> parameters = new ArrayList<>();

    private int serviceId;

    private int transactionId;

    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    private LocalDateTime transactionTime;
}