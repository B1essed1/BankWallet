package uz.sqb.bankwallet.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import uz.sqb.bankwallet.entity.User;

import java.util.Optional;

@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query("select u from User  u where u.wallet.walletNumber =:walletNumber")
    Optional<User> findByWalletNumber(@Param("walletNumber") String walletNumber);

    Optional<User> findByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByUsername(String phoneNumber);
}
