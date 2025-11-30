package uz.sqb.bankwallet.dto.response;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;
import uz.sqb.bankwallet.dto.GenericResult;
import uz.sqb.bankwallet.dto.TransactionStatement;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@XmlRootElement(name = "GetStatementResponse", namespace = "http://uws.provider.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetStatementResponse", propOrder = {
    "errorMsg",
    "status",
    "timeStamp",
    "transactions"
})
public class GetStatementResponse extends GenericResult {

    private List<TransactionStatement> transactions = new ArrayList<>();
}