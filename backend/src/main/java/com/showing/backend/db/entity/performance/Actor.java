package com.showing.backend.db.entity.performance;


import com.showing.backend.db.entity.BaseTimeEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "actor")
public class Actor extends BaseTimeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String actorName;

    @Column(unique = true)
    private int playdbId;

    @Column
    private String actorImage;

}