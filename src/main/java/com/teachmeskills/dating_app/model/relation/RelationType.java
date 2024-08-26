package com.teachmeskills.dating_app.model.relation;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "relation_type")
public class RelationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;
    @Column(name = "name")
    @Getter
    @Setter
    private String relationTypeName;
}
