package uz.sqb.bankwallet.service;

import org.springframework.stereotype.Service;
import uz.sqb.bankwallet.entity.User;
import uz.sqb.bankwallet.entity.Wallet;
import uz.sqb.bankwallet.exception.ExceptionWithStatusCode;
import uz.sqb.bankwallet.generated.*;
import uz.sqb.bankwallet.repository.UserRepository;
import uz.sqb.bankwallet.repository.WalletRepository;
import uz.sqb.bankwallet.utils.Utils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final WalletRepository walletRepository;

    public UserService(UserRepository userRepository, WalletRepository walletRepository) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
    }

    public ChangePasswordResponse changePassword(ChangePasswordRequest request) {
        // it is there checked in interception
        User user = userRepository.findByUsername(request.getUsername()).get();
        user.setPassword(Utils.encode(request.getNewPassword()));
        userRepository.save(user);

        // Create success response using XSD-generated class
        ChangePasswordResponse response = new ChangePasswordResponse();
        response.setStatus(0);
        response.setErrorMsg("success");
        response.setTimeStamp(String.valueOf(System.currentTimeMillis()));
        return response;
    }


    public RegisterUserResponse registerUser(RegisterUserRequest request) {

        if (!Utils.isValidPhoneNumber(request.getPhoneNumber().trim())) {
            throw ExceptionWithStatusCode.error("wrong.phone.number.format");
        }

        if (userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw ExceptionWithStatusCode.error("phone.number.exist");
        }

        if (userRepository.existsByUsername(request.getUsername())) {
            throw ExceptionWithStatusCode.error("username.exist");
        }


        String walletNumber;
        do {
            walletNumber = Utils.generateWalletNumber();
        } while (walletRepository.existsByWalletNumber(walletNumber));

        //should do password requirement

        Wallet wallet = new Wallet();
        wallet.setWalletNumber(walletNumber);


        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(Utils.encode(request.getPassword()));
        user.setPhoneNumber(request.getPhoneNumber());
        user.setServiceId(System.currentTimeMillis());

        user.setWallet(wallet);
        wallet.setUser(user);
        userRepository.save(user);

        List<Parameter>  parameters = new ArrayList<>();
        parameters.add(createParameter("serviceId", String.valueOf(user.getServiceId())));

        // Create response using XSD-generated class
        RegisterUserResponse response = new RegisterUserResponse();
        response.setUsername(user.getUsername());
        response.setPhoneNumber(user.getPhoneNumber());
        response.getParameters().addAll(parameters);
        response.setWalletNumber(user.getWallet().getWalletNumber());
        response.setStatus(0);
        response.setErrorMsg("success");
        response.setTimeStamp(String.valueOf(System.currentTimeMillis()));
        return response;
    }




    private Parameter createParameter(String key, String value) {
        Parameter parameter = new Parameter();
        parameter.setParamKey(key);
        parameter.setParamValue(value);
        return parameter;
    }
}
