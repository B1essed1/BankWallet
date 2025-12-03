package uz.sqb.bankwallet.dto.request;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.Setter;
import uz.sqb.bankwallet.dto.GenericAuthRequest;
import uz.sqb.bankwallet.dto.Parameter;
import uz.sqb.bankwallet.utils.PUBLIC_STRINGS;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@XmlRootElement(name = "GetInformationRequest",  namespace = PUBLIC_STRINGS.NAMESPACE_URI)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetInformationRequest", propOrder = {

    "parameters",
    "serviceId"
})
public class GetInformationRequest extends GenericAuthRequest {


    private List<Parameter> parameters = new ArrayList<>();

    private Long serviceId;
}