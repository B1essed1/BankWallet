package uz.sqb.bankwallet.dto.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.Setter;
import uz.sqb.bankwallet.dto.ErrorResponse;
import uz.sqb.bankwallet.dto.Parameter;
import uz.sqb.bankwallet.entity.User;
import uz.sqb.bankwallet.utils.PUBLIC_STRINGS;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@XmlRootElement(name = "RegisterUserResponse",  namespace = PUBLIC_STRINGS.NAMESPACE_URI)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegisterUserResponse", propOrder = {
        "parameters",
        "username",
        "walletNumber",
        "phoneNumber"
})
public class RegisterUserResponse extends ErrorResponse {
    private List<Parameter> parameters = new ArrayList<Parameter>();
    private String username;
    private String phoneNumber;
    private String walletNumber;


    public  RegisterUserResponse from(User user) {
        username = user.getUsername();
        phoneNumber = user.getPhoneNumber();
        walletNumber = user.getWallet().getWalletNumber();
        timeStamp = String.valueOf(System.currentTimeMillis());
        errorMsg = "success";
        status = 0;
        return this;
    }
}
