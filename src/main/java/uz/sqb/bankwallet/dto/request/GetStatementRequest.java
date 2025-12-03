package uz.sqb.bankwallet.dto.request;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;
import uz.sqb.bankwallet.dto.GenericAuthRequest;
import uz.sqb.bankwallet.utils.LocalDateTimeAdapter;
import uz.sqb.bankwallet.utils.PUBLIC_STRINGS;

import java.time.LocalDateTime;

@Data
@XmlRootElement(name = "GetStatementRequest", namespace = PUBLIC_STRINGS.NAMESPACE_URI)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetStatementRequest", propOrder = {
    "serviceId",
    "dateFrom",
    "dateTo"
})
public class GetStatementRequest extends GenericAuthRequest {

    @XmlElement(required = true)
    private Long serviceId;

    @XmlElement(required = true)
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime dateFrom;

    @XmlElement(required = true)
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime dateTo;
}