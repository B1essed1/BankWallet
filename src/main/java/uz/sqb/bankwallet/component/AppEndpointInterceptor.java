package uz.sqb.bankwallet.component;

import jakarta.xml.soap.SOAPBody;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.saaj.SaajSoapMessage;
import org.w3c.dom.NodeList;

@Component
public class AppEndpointInterceptor implements EndpointInterceptor {
    @Override
    public boolean handleRequest(MessageContext messageContext, Object endpoint) throws Exception {
        SaajSoapMessage soapMessage = (SaajSoapMessage) messageContext.getRequest();
        SOAPBody body = soapMessage.getSaajMessage().getSOAPBody();

       String username = getValue(body, "username");
        String password = getValue(body, "password");
        if (!"testuser".equals(username) || !"password123".equals(password)) {
            throw new RuntimeException("Invalid credentials");
        }

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
    public void afterCompletion(MessageContext messageContext, Object endpoint, @Nullable Exception ex) throws Exception {

    }
    private String extract(String xml, String start, String end) {
        int s = xml.indexOf(start);
        int e = xml.indexOf(end);
        if (s == -1 || e == -1) return null;
        return xml.substring(s + start.length(), e).trim();
    }


    private String getValue(SOAPBody body, String tagName) {
        NodeList list = body.getElementsByTagNameNS("*", tagName); // any namespace
        if (list != null && list.getLength() > 0) {
            return list.item(0).getTextContent().trim();
        }
        return null;
    }
}
