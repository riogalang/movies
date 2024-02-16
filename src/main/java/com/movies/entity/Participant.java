package com.movies.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean registered;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date lastActivity;

    private boolean active;

    public void addSubscription(Subscription subscription) {
    }

    public void removeSubscription(String serviceType) {
    }

    public Subscription getSubscription(String serviceType) {
    }

    // Getters and setters (omitted for brevity)

}
