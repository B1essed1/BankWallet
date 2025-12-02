package uz.sqb.bankwallet.component;

import jakarta.xml.soap.SOAPBody;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.saaj.SaajSoapMessage;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import uz.sqb.bankwallet.entity.User;
import uz.sqb.bankwallet.exception.ExceptionWithStatusCode;
import uz.sqb.bankwallet.repository.UserRepository;
import uz.sqb.bankwallet.utils.Utils;

import java.util.Set;

@Component
public class AppEndpointInterceptor implements EndpointInterceptor {

    private final UserRepository userRepository;

    public AppEndpointInterceptor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private Set<String> whiteList =  Set.of("RegisterUserRequest");

    @Override
    public boolean handleRequest(MessageContext messageContext, Object endpoint) throws Exception {
        SaajSoapMessage soapMessage = (SaajSoapMessage) messageContext.getRequest();
        SOAPBody body = soapMessage.getSaajMessage().getSOAPBody();

        // Get the operation name (localPart)
        String operationName = getOperationName(body);

        // Skip authentication for RegisterUserRequest
        if (whiteList.contains(operationName)) {
            return true;
        }

        // For all other operations, perform authentication
        String username = getValue(body, "username");
        String password = getValue(body, "password");

        User user = userRepository.findByUsername(username).orElseThrow(() -> ExceptionWithStatusCode.error("user.or.password.invalid"));
        if (!Utils.matches(password, user.getPassword())) throw ExceptionWithStatusCode.error("user.or.password.invalid");

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


    private String getValue(SOAPBody body, String tagName) {
        NodeList list = body.getElementsByTagNameNS("*", tagName); // any namespace
        if (list != null && list.getLength() > 0) {
            return list.item(0).getTextContent().trim();
        }
        return null;
    }

    private String getOperationName(SOAPBody body) {
        Node firstChild = body.getFirstChild();
        while (firstChild != null && firstChild.getNodeType() != Node.ELEMENT_NODE) {
            firstChild = firstChild.getNextSibling();
        }
        return firstChild != null ? firstChild.getLocalName() : null;
    }
}
