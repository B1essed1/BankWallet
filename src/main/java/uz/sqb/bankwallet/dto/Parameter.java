package uz.sqb.bankwallet.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Parameter", propOrder = {
    "paramKey",
    "paramValue"
})
public class Parameter {

    @XmlElement(required = true)
    private String paramKey;

    @XmlElement(required = true)
    private String paramValue;
}