package com.teachmeskills.dating_app.model.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_photo")
public class UserPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;
    @Column(name = "link")
    @Getter
    @Setter
    private String photoLink;
}
