package uz.sqb.bankwallet.service;

import org.springframework.stereotype.Service;
import uz.sqb.bankwallet.dto.request.ChangePasswordRequest;
import uz.sqb.bankwallet.dto.request.RegisterUserRequest;
import uz.sqb.bankwallet.dto.response.ChangePasswordResponse;
import uz.sqb.bankwallet.dto.response.RegisterUserResponse;
import uz.sqb.bankwallet.entity.User;
import uz.sqb.bankwallet.entity.Wallet;
import uz.sqb.bankwallet.exception.ExceptionWithStatusCode;
import uz.sqb.bankwallet.repository.UserRepository;
import uz.sqb.bankwallet.repository.WalletRepository;
import uz.sqb.bankwallet.utils.Utils;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final WalletRepository walletRepository;

    public UserService(UserRepository userRepository, WalletRepository walletRepository) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
    }

    public ChangePasswordResponse changePassword(ChangePasswordRequest request) {
        ChangePasswordResponse result = new ChangePasswordResponse();
        return result;
    }


    public RegisterUserResponse registerUser(RegisterUserRequest request) {

        if (!Utils.isValidPhoneNumber(request.getPhoneNumber())) {
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

        user.setWallet(wallet);
        wallet.setUser(user);
        userRepository.save(user);




        RegisterUserResponse response = new RegisterUserResponse();
        response = response.from(user);
        return response;
    }


}
