package uz.sqb.bankwallet.dto.request;

import jakarta.xml.bind.annotation.*;
import lombok.Data;
import uz.sqb.bankwallet.utils.PUBLIC_STRINGS;

import java.time.LocalDateTime;

@Data
@XmlRootElement(name = "GetStatementRequest", namespace = PUBLIC_STRINGS.NAMESPACE_URI)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetStatementRequest", propOrder = {
    "password",
    "username",
    "serviceId",
    "dateFrom",
    "dateTo"
})
public class GetStatementRequest {

    @XmlElement(required = true)
    private String password;

    @XmlElement(required = true)
    private String username;

    private int serviceId;

    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    private LocalDateTime dateFrom;

    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    private LocalDateTime dateTo;
}