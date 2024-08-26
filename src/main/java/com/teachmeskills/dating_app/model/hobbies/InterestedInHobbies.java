package com.teachmeskills.dating_app.model.hobbies;

import com.teachmeskills.dating_app.model.user.UserAccount;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_hobbies")
public class InterestedInHobbies {
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
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "hobbie_id")
    private HobbiesType hobbiesType;
}
