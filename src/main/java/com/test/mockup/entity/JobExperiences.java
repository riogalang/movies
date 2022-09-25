package com.test.mockup.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "job_experiences")
@Builder
public class JobExperiences {
    @Id
    @GeneratedValue(generator="job_experiences_seq")
    @SequenceGenerator(name="job_experiences_seq",sequenceName="job_experiences_seq", allocationSize=1)
    @Column(name = "id", length = 36, updatable = false, nullable = false)
    private Long id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "last_position")
    private String lastPosition;

    @Column(name = "last_income")
    private BigDecimal lastIncome;

    @Column(name = "year")
    private String year;

    @NotNull
    @OneToOne
    @JoinColumn(name = "biodata_id", referencedColumnName = "id")
    private Biodata biodata;
}
