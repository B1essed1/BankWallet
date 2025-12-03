package uz.sqb.bankwallet.configuration;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointExceptionResolver;
import org.springframework.ws.soap.SoapMessage;
import uz.sqb.bankwallet.exception.ExceptionWithStatusCode;
import uz.sqb.bankwallet.generated.ErrorResponse;

@Component
public class SoapExceptionHandler implements EndpointExceptionResolver {

    @Override
    public boolean resolveException(MessageContext messageContext, Object endpoint, Exception ex) {
        try {
            ErrorResponse result = new ErrorResponse();

            if (ex instanceof ExceptionWithStatusCode e) {
                result.setStatus(e.getHttpStatusCode() != null ? e.getHttpStatusCode() : 400);
                result.setErrorMsg(e.getMessageKey()); // Or translate using MessageSource
            } else {
                result.setStatus(500);
                result.setErrorMsg(ex.getMessage());
            }

            // Marshal GenericResult into SOAP response
            SoapMessage response = (SoapMessage) messageContext.getResponse();
            JAXBContext context = JAXBContext.newInstance(ErrorResponse.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(result, response.getPayloadResult());

            return true; // Exception handled
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}