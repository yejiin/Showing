package com.showing.backend.db.entity.performance;


import com.showing.backend.db.entity.BaseTimeEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "performance")
public class Performance extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long lastSeasonId;

    @Column
    private String performanceName;

    @Column
    private String performanceImage;

    @Column
    private int performanceType;

}