
package com.teachmeskills.dating_app.model.user;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user_account")
@ToString
@EqualsAndHashCode
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private int id;
    @Column(name = "first_name")
    @Getter
    @Setter
    private String userAccountFirstName;
    @Column(name = "last_name")
    @Getter
    @Setter
    private String userAccountLastName;
    @Column(name = "email")
    @Getter
    @Setter
    private String userAccountEmail;
    @Column(name = "gender_id")
    @Getter
    @Setter
    private int userAccountGenderId;
    @Column(name = "about_me")
    @Getter
    @Setter
    private String userAccountAboutMe;
    @Column(name = "grade")
    @Getter
    @Setter
    private double userAccountGrade;
    @Column(name = "confirmed")
    @Getter
    @Setter
    private boolean isUserAccountConfirmed;
    @Column(name = "username")
    @Getter
    @Setter
    private String username;
    @Column(name = "password")
    @Getter
    @Setter
    private String password;
    @Column(name = "role")
    @Getter
    @Setter
    private String role;
}
