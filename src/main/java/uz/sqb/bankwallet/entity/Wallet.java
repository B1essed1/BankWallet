package uz.sqb.bankwallet.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "wallet")
@Getter
@Setter
public class Wallet {
    @Id
    private Long id;

    @OneToOne
    @JoinColumn
    private User wallet;

}
