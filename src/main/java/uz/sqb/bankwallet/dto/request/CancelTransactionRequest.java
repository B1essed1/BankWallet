package uz.sqb.bankwallet.dto.request;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlRootElement(name = "CancelTransactionRequest", namespace = "http://uws.provider.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CancelTransactionRequest", propOrder = {
    "password",
    "username",
    "serviceId",
    "transactionId"
})
public class CancelTransactionRequest {

    @XmlElement(required = true)
    private String password;

    @XmlElement(required = true)
    private String username;

    private int serviceId;

    private long transactionId;
}