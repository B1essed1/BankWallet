package uz.sqb.bankwallet.dto.request;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;
import uz.sqb.bankwallet.dto.Parameter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@XmlRootElement(name = "GetInformationRequest", namespace = "http://uws.provider.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetInformationRequest", propOrder = {
    "password",
    "username",
    "parameters",
    "serviceId"
})
public class GetInformationRequest {

    @XmlElement(required = true)
    private String password;

    @XmlElement(required = true)
    private String username;

    private List<Parameter> parameters = new ArrayList<>();

    private int serviceId;
}