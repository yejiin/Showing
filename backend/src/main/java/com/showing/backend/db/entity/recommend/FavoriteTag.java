package com.showing.backend.db.entity.recommend;

import com.showing.backend.db.entity.BaseTimeEntity;
import com.showing.backend.db.entity.Tag;
import com.showing.backend.db.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "favorite_tag")
public class FavoriteTag extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @Column
    private int weight;
}
