package uz.sqb.bankwallet.dto;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ErrorResponse", propOrder = {
        "status",
        "errorMsg",
        "timeStamp"
})
@XmlRootElement
public  class ErrorResponse {

    @XmlElement(required = true)
    protected String errorMsg ;

    protected int status;

    @XmlElement(required = true)
    protected String timeStamp = String.valueOf(System.currentTimeMillis());
}