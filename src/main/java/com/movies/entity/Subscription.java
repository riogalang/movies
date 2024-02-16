package com.movies.entity;

import com.movies.entity.Participant;
import com.movies.entity.ServiceType;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "participant_id", nullable = false)
    private Participant participant;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ServiceType serviceType;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date endDate;

    @Column(nullable = false)
    private boolean active;

    public void extendDuration(int additionalSessions) {
    }

    // Constructors, getters and setters, and other methods (omitted for brevity)
}
