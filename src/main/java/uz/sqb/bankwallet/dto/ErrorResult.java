package uz.sqb.bankwallet.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@XmlRootElement
public class ErrorResult extends ErrorResponse {
    // GenericResult already has errorMsg, status, timeStamp
}