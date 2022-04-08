package com.showing.backend.db.entity.performance;


import com.showing.backend.db.entity.BaseTimeEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "casting")
public class Casting extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_playdb_id", referencedColumnName = "playdbId")
    private Season season;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actor_playdb_id", referencedColumnName = "playdbId")
    private Actor actor;

    @Column
    private String role;

}