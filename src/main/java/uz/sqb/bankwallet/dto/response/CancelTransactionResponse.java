package uz.sqb.bankwallet.dto.response;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;
import uz.sqb.bankwallet.dto.GenericResult;
import uz.sqb.bankwallet.dto.Parameter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@XmlRootElement(name = "CancelTransactionResponse", namespace = "http://uws.provider.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CancelTransactionResponse", propOrder = {
    "errorMsg",
    "status",
    "timeStamp",
    "parameters"
})
public class CancelTransactionResponse extends GenericResult {

    private List<Parameter> parameters = new ArrayList<>();
}