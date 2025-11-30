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
@XmlRootElement(name = "PerformTransactionResponse", namespace = "http://uws.provider.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PerformTransactionResponse", propOrder = {
    "errorMsg",
    "status",
    "timeStamp",
    "parameters",
    "providerTrnId"
})
public class PerformTransactionResponse extends GenericResult {

    private List<Parameter> parameters = new ArrayList<>();

    private int providerTrnId;
}