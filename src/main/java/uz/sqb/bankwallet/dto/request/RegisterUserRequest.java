package uz.sqb.bankwallet.dto.request;

import jakarta.xml.bind.annotation.*;
import lombok.Data;
import uz.sqb.bankwallet.dto.GenericAuthRequest;
import uz.sqb.bankwallet.utils.PUBLIC_STRINGS;

@Data
@XmlRootElement(name = "RegisterUserRequest",  namespace = PUBLIC_STRINGS.NAMESPACE_URI)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegisterUserRequest", propOrder = {
        "phoneNumber"
})
public class RegisterUserRequest extends GenericAuthRequest {

    @XmlElement(required = true)
    private String phoneNumber;
}
