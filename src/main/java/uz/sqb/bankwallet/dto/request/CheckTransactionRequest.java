package uz.sqb.bankwallet.dto.request;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Data;
import uz.sqb.bankwallet.dto.GenericAuthRequest;
import uz.sqb.bankwallet.utils.PUBLIC_STRINGS;

@Data
@XmlRootElement(name = "CheckTransactionRequest", namespace = PUBLIC_STRINGS.NAMESPACE_URI)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CheckTransactionRequest", propOrder = {
    "serviceId",
    "transactionId"
})
public class CheckTransactionRequest  extends GenericAuthRequest {


    private Long serviceId;

    private long transactionId;
}