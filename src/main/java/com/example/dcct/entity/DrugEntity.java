package com.example.dcct.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dcct_drug")
public class DrugEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long mid;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "drugCode", referencedColumnName = "id")
    DrugCodeEntity drugCodeEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "drugName", referencedColumnName = "id")
    DrugNameEntity drugNameEntity;

    @Column(nullable = false,columnDefinition = "MEDIUMTEXT")
    String result;

    Double score;
}
