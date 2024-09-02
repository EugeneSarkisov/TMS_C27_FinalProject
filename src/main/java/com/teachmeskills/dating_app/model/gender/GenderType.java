package com.teachmeskills.dating_app.model.gender;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "gender")
public class GenderType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;
    @Column(name = "gender_name")
    @Getter
    @Setter
    private String genderName;
}
