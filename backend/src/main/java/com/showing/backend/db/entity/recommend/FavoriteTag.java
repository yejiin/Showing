package com.showing.backend.db.entity.recommend;

import com.showing.backend.db.entity.BaseTimeEntity;
import com.showing.backend.db.entity.Tag;
import com.showing.backend.db.entity.User;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    @ColumnDefault("0")
    private float weight;
}
