package uz.sqb.bankwallet.dto;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GenericAuthRequest", propOrder = {
        "username",
        "password"
})
@XmlRootElement
public class GenericAuthRequest {
    @XmlElement(required = true)
    private String password;

    @XmlElement(required = true)
    private String username;
}
