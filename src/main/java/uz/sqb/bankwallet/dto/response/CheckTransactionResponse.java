package uz.sqb.bankwallet.dto.response;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;
import uz.sqb.bankwallet.dto.ErrorResponse;
import uz.sqb.bankwallet.dto.Parameter;
import uz.sqb.bankwallet.utils.PUBLIC_STRINGS;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@XmlRootElement(name = "CheckTransactionResponse",  namespace = PUBLIC_STRINGS.NAMESPACE_URI)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CheckTransactionResponse", propOrder = {
    "parameters",
    "providerTrnId"
})
public class CheckTransactionResponse extends ErrorResponse {

    private List<Parameter> parameters = new ArrayList<>();

    private Long providerTrnId;
}