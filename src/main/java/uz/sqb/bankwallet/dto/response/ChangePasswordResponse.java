package uz.sqb.bankwallet.dto.response;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;
import uz.sqb.bankwallet.dto.GenericResult;
import uz.sqb.bankwallet.utils.PUBLIC_STRINGS;

@Getter
@Setter
@XmlRootElement(name = "ChangePasswordResponse",  namespace = PUBLIC_STRINGS.NAMESPACE_URI)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChangePasswordResponse", propOrder = {
    "errorMsg",
    "status",
    "timeStamp"
})
public class ChangePasswordResponse extends GenericResult {
    // All fields inherited from GenericResult
}