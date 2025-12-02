package uz.sqb.bankwallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.sqb.bankwallet.entity.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    boolean existsByWalletNumber(String walletNumber);
}
