package com.showing.backend.db.entity.performance;


import com.showing.backend.db.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "actor")
public class Actor extends BaseTimeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String actorName;

    @Column(unique = true)
    private Long playdbId;

    @Column
    private String actorImage;

}