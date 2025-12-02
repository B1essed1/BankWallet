package uz.sqb.bankwallet.dto.request;

import jakarta.xml.bind.annotation.*;
import lombok.Data;
import uz.sqb.bankwallet.utils.PUBLIC_STRINGS;

@Data
@XmlRootElement(name = "RegisterUserRequest",  namespace = PUBLIC_STRINGS.NAMESPACE_URI)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegisterUserRequest", propOrder = {
        "username",
        "password",
        "phoneNumber"
})
public class RegisterUserRequest {

    @XmlElement(required = true)
    private String username;

    @XmlElement(required = true)
    private String password;

    @XmlElement(required = true)
    private String phoneNumber;
}
