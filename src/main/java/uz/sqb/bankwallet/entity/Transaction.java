package uz.sqb.bankwallet.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.sqb.bankwallet.enums.TransactionStatus;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "transactions",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"transaction_id", "service_id"})}
)

@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transaction_id", nullable = false)
    private Long transactionId;

    @Column(name = "service_id", nullable = false)
    private Long serviceId;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @Column(name = "transaction_time", nullable = false)
    private LocalDateTime transactionTime;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "wallet_number", length = 20)
    private String walletNumber;

    @Column(name = "status", nullable = false)
    private TransactionStatus status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}
