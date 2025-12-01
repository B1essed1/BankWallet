package uz.sqb.bankwallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;
import uz.sqb.bankwallet.entity.Transaction;

@Transactional
public interface TransactionRepository extends JpaRepository<Transaction,Long> , JpaSpecificationExecutor<Transaction> {


}
