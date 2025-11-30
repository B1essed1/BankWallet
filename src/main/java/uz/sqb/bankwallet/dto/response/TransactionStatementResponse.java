package uz.sqb.bankwallet.dto.response;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlRootElement(name = "TransactionStatementResponse", namespace = "http://uws.provider.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransactionStatementResponse", propOrder = {
        "errorMsg",
        "status",
        "timeStamp",
        "parameters",
        "providerTrnId"
})
public class TransactionStatementResponse {

}
