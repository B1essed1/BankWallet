package uz.sqb.bankwallet.entity;


import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlTransient;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sqb_user")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "wallet_id")
    @XmlTransient
    private Wallet wallet;

    @Column(name = "service_id")
    private Long serviceId;

}
