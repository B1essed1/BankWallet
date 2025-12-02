package uz.sqb.bankwallet.dto;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GenericResult", propOrder = {
        "status",
        "errorMsg",
        "timeStamp"
})
@XmlRootElement
public  class GenericResult {

    @XmlElement(required = true)
    protected String errorMsg;

    protected int status;

    @XmlElement(required = true)
    protected String timeStamp;
}