package com.showing.backend.db.entity.performance;


import com.showing.backend.db.entity.BaseTimeEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "season")
public class Season extends BaseTimeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performance_id")
    private Performance performance;

    @Column
    private String interparkId;

    @Column(unique = true)
    private Long playdbId;

    @Column
    private String seasonImage;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @Column
    private String description;

    @Column
    private String location;

    @Column
    private String runningtime;

    @Column
    private String performanceAge;

    @Column
    private String detailType;

    @Column
    private int proceedFlag;

}