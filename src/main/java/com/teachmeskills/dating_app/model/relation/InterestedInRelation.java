package com.teachmeskills.dating_app.model.relation;

import com.teachmeskills.dating_app.model.user.UserAccount;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "interested_in_relation")
public class InterestedInRelation {
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
    @JoinColumn(name = "relation_type_id")
    private RelationType relationType;
}
