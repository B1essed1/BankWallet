package uz.sqb.bankwallet.dto.request;

import jakarta.xml.bind.annotation.*;
import lombok.Data;
import uz.sqb.bankwallet.dto.GenericAuthRequest;
import uz.sqb.bankwallet.utils.PUBLIC_STRINGS;

@Data
@XmlRootElement(name = "ChangePasswordRequest", namespace = PUBLIC_STRINGS.NAMESPACE_URI)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChangePasswordRequest", propOrder = {
    "newPassword"
})
public class ChangePasswordRequest  extends GenericAuthRequest {

    @XmlElement(required = true)
    private String newPassword;
}