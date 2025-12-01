package uz.sqb.bankwallet.service;

import org.springframework.stereotype.Service;
import uz.sqb.bankwallet.dto.request.ChangePasswordRequest;
import uz.sqb.bankwallet.dto.response.ChangePasswordResponse;

@Service
public class UserService {

    public ChangePasswordResponse changePassword(ChangePasswordRequest request) {
        ChangePasswordResponse result = new ChangePasswordResponse();
        return result;
    }

}
