package com.test.mockup.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "training_history")
@Builder
public class TrainingHistory {
    @Id
    @GeneratedValue(generator="training_history_seq")
    @SequenceGenerator(name="training_history_seq",sequenceName="training_history_seq", allocationSize=1)
    @Column(name = "id", length = 36, updatable = false, nullable = false)
    private Long id;

    @Column(name = "name_training")
    private String nameTraining;

    @Column(name = "is_any_certificate")
    private Boolean isAnyCertificate;

    @Column(name = "year")
    private String year;

    @NotNull
    @OneToOne
    @JoinColumn(name = "biodata_id", referencedColumnName = "id")
    private Biodata biodata;

}
