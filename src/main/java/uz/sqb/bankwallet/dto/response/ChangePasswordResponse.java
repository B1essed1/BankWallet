package uz.sqb.bankwallet.dto.response;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;
import uz.sqb.bankwallet.dto.GenericResult;

@Getter
@Setter
@XmlRootElement(name = "ChangePasswordResponse", namespace = "http://uws.provider.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChangePasswordResponse", propOrder = {
    "errorMsg",
    "status",
    "timeStamp"
})
public class ChangePasswordResponse extends GenericResult {
    // All fields inherited from GenericResult
}