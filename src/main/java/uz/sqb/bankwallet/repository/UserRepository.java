package uz.sqb.bankwallet.repository;


import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
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

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select u from User u join fetch u.wallet w where w.walletNumber = :walletNumber")
    Optional<User> findByWalletNumber(@Param("walletNumber") String walletNumber);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select u from User u join fetch u.wallet where u.phoneNumber = :phoneNumber")
    Optional<User> findByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByUsername(String phoneNumber);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select u from User u join fetch u.wallet where u.serviceId = :serviceId")
    Optional<User> findByServiceId(@Param("serviceId") Long serviceId);
}
