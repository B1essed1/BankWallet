package uz.sqb.bankwallet.dto.response;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.Setter;
import uz.sqb.bankwallet.utils.PUBLIC_STRINGS;

@Getter
@Setter
@XmlRootElement(name = "TransactionStatementResponse",  namespace = PUBLIC_STRINGS.NAMESPACE_URI)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransactionStatementResponse", propOrder = {
        "parameters",
        "providerTrnId"
})
public class TransactionStatementResponse {

}
