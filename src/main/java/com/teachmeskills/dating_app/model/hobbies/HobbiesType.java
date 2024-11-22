package com.teachmeskills.dating_app.model.hobbies;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "hobbies")
public class HobbiesType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;
    @Column(name = "hobbie_name")
    @Getter
    @Setter
    private String hobbieName;
}
