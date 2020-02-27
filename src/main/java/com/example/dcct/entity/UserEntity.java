package com.example.dcct.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dcct_user")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long uid;

    @Column(nullable = false, unique = true)
    String email;

    String password;
    String nickname;

    @Column(nullable=false,name="state",columnDefinition="int default 0")
    int state;

    @OneToMany(mappedBy = "userEntity",cascade=CascadeType.MERGE,fetch=FetchType.EAGER)
    List<QueryRecordEntity> recordEntityList;


}
