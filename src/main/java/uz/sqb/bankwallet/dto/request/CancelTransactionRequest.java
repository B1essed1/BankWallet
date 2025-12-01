package uz.sqb.bankwallet.dto.request;

import jakarta.xml.bind.annotation.*;
import lombok.Data;
import uz.sqb.bankwallet.utils.PUBLIC_STRINGS;

@Data
@XmlRootElement(name = "CancelTransactionRequest",  namespace = PUBLIC_STRINGS.NAMESPACE_URI)
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