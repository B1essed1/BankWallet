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
})
public class ChangePasswordResponse extends GenericResult {


    public static ChangePasswordResponse success() {
        ChangePasswordResponse response = new ChangePasswordResponse();
        response.setErrorMsg("success");
        response.setStatus(0);
        response.setTimeStamp(String.valueOf(System.currentTimeMillis()));
        return response;
    }
    // All fields inherited from GenericResult
}