package uz.sqb.bankwallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uz.sqb.bankwallet.data.TransactionStatementDto;
import uz.sqb.bankwallet.entity.Transaction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional
public interface TransactionRepository extends JpaRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction> {

    boolean existsByTransactionIdAndServiceId(Long transactionId, Long serviceId);

    Optional<Transaction> findByTransactionIdAndServiceId(Long transactionId, Long serviceId);


    @Query("select new uz.sqb.bankwallet.data.TransactionStatementDto(t.amount,t.id, t.transactionId,t.transactionTime) from Transaction t where t.serviceId=:serviceId and t.transactionTime>=:from and t.transactionTime<=:to")
    List<TransactionStatementDto> findAllStatementByServiceId(@Param("serviceId") Long serviceId, @Param("from") LocalDateTime from, @Param("to") LocalDateTime to);
}
