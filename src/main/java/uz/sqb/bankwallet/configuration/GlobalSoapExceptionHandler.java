package uz.sqb.bankwallet.configuration;

import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointExceptionResolver;
import org.springframework.ws.soap.server.endpoint.SoapFaultDefinition;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class GlobalSoapExceptionHandler extends SoapFaultMappingExceptionResolver {

    @Override
    protected void customizeFault(Object endpoint, Exception ex, SoapFaultDefinition fault) {
        // You can customize SOAP fault here
        fault.setFaultCode(SoapFaultDefinition.SERVER);
        fault.setFaultStringOrReason("Service Error: " + ex.getMessage());
    }
}