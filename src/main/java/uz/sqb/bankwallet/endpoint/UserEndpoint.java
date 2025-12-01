package uz.sqb.bankwallet.endpoint;


import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import uz.sqb.bankwallet.dto.request.ChangePasswordRequest;
import uz.sqb.bankwallet.dto.response.ChangePasswordResponse;
import uz.sqb.bankwallet.service.UserService;

import static uz.sqb.bankwallet.utils.PUBLIC_STRINGS.NAMESPACE_URI;

@Endpoint
public class UserEndpoint {

    private final UserService userService;

    public UserEndpoint(UserService userService) {
        this.userService = userService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ChangePasswordRequest")
    @ResponsePayload
    public ChangePasswordResponse changePassword(@RequestPayload ChangePasswordRequest request) {
        return userService.changePassword(request);
    }

}
