package uz.sqb.bankwallet.dto.response;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;
import uz.sqb.bankwallet.dto.GenericResult;
import uz.sqb.bankwallet.dto.Parameter;
import uz.sqb.bankwallet.utils.PUBLIC_STRINGS;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@XmlRootElement(name = "CancelTransactionResponse",  namespace = PUBLIC_STRINGS.NAMESPACE_URI)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CancelTransactionResponse", propOrder = {
    "parameters"
})
public class CancelTransactionResponse extends GenericResult {

    private List<Parameter> parameters = new ArrayList<>();
}