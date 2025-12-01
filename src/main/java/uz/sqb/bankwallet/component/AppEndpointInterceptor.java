package uz.sqb.bankwallet.component;

import jakarta.xml.soap.SOAPBody;
import org.springframework.stereotype.Component;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.saaj.SaajSoapMessage;

@Component
public class AppEndpointInterceptor implements org.springframework.ws.server.EndpointInterceptor {

    @Override
    public boolean handleRequest(MessageContext messageContext, Object endpoint) throws Exception {
        SaajSoapMessage soapMessage = (SaajSoapMessage) messageContext.getRequest();
        SOAPBody body = soapMessage.getSaajMessage().getSOAPBody();
        String xml = body.getTextContent();
        // Extract values (simple way, works for your fixed structure)
        String username = extract(xml, "<uws:username>", "</uws:username>");
        String password = extract(xml, "<uws:password>", "</uws:password>");

        return true;
    }

    @Override
    public boolean handleResponse(MessageContext messageContext, Object endpoint) throws Exception {
        return false;
    }

    @Override
    public boolean handleFault(MessageContext messageContext, Object endpoint) throws Exception {
        return false;
    }

    @Override
    public void afterCompletion(MessageContext messageContext, Object endpoint, Exception ex) throws Exception {

    }

    private String extract(String xml, String start, String end) {
        int s = xml.indexOf(start);
        int e = xml.indexOf(end);
        if (s == -1 || e == -1) return null;
        return xml.substring(s + start.length(), e).trim();
    }
}
