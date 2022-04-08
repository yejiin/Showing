package com.showing.backend.db.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "user")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String uid;

    @Column
    private String nickname;

    @Column
    private String email;

    @Column
    private String userImage;

    @Column
    private String introduce;

    @Column
    private Boolean activeFlag;

    @Column
    private Boolean adminFlag;

    @Column
    private String refreshToken;

}
