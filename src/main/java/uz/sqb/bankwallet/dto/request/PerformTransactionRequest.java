package uz.sqb.bankwallet.dto.request;

import jakarta.xml.bind.annotation.*;
import lombok.Data;
import uz.sqb.bankwallet.dto.Parameter;
import uz.sqb.bankwallet.utils.PUBLIC_STRINGS;

import java.util.ArrayList;
import java.util.List;

@Data
@XmlRootElement(name = "PerformTransactionRequest", namespace = PUBLIC_STRINGS.NAMESPACE_URI)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PerformTransactionRequest", propOrder = {
    "username",
    "password",
    "amount",
    "parameters",
    "serviceId",
    "transactionId"/*,
    "transactionTime"*/
})
public class PerformTransactionRequest {

    @XmlElement(required = true)
    private String username;

    @XmlElement(required = true)
    private String password;

    @XmlElement(required = true)
    private Long amount;

    private List<Parameter> parameters = new ArrayList<>();

    private Long serviceId;

    private Long transactionId;

//    @XmlElement(required = true)
//    @XmlSchemaType(name = "dateTime")
//    private LocalDateTime transactionTime;
}