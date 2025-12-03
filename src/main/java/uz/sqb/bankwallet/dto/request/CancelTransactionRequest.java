package uz.sqb.bankwallet.dto.request;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.Setter;
import uz.sqb.bankwallet.dto.GenericAuthRequest;
import uz.sqb.bankwallet.utils.PUBLIC_STRINGS;

@Getter


 @Setter
@XmlRootElement(name = "CancelTransactionRequest",  namespace = PUBLIC_STRINGS.NAMESPACE_URI)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CancelTransactionRequest", propOrder = {
    "serviceId",
    "transactionId"
})
public class CancelTransactionRequest extends GenericAuthRequest {

    private Long serviceId;

    private Long transactionId;
}