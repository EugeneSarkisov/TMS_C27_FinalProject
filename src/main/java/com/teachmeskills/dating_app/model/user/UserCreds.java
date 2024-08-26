package com.teachmeskills.dating_app.model.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_creds")
public class UserCreds{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;
    @Column(name = "user_username")
    @Getter
    @Setter
    private String username;
    @Column(name = "user_password")
    @Getter
    @Setter
    private String password;

}
