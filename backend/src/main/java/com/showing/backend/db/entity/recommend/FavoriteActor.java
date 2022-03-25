package com.showing.backend.db.entity.recommend;

import com.showing.backend.db.entity.BaseTimeEntity;
import com.showing.backend.db.entity.User;
import com.showing.backend.db.entity.performance.Actor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "favorite_actor")
public class FavoriteActor extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actor_id")
    private Actor actor;

    @Column
    private int weight;
}
