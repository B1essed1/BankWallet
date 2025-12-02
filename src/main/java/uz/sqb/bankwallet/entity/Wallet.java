package uz.sqb.bankwallet.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.xml.bind.annotation.XmlTransient;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "wallet")
@Getter
@Setter
public class Wallet {
    @Id
    @Column(name = "wallet_number")
    private String walletNumber;

    @OneToOne(mappedBy = "wallet")
    @XmlTransient
    private User user;


    @Column(name = "balance", nullable = false)
    @Min(0)
    private Long balance = 0L;

}
