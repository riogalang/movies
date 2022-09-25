package com.test.mockup.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "latest_education")
@Builder
public class LatestEducation {
    @Id
    @GeneratedValue(generator="latest_education_seq")
    @SequenceGenerator(name="latest_education_seq",sequenceName="latest_education_seq", allocationSize=1)
    @Column(name = "id", length = 36, updatable = false, nullable = false)
    private Long id;

    @Column(name = "last_education")
    private String lastEducation;

    @Column(name = "name_institution")
    private String nameInstitution;

    @Column(name = "major")
    private String major;

    @Column(name = "graduation_year")
    private String graduationYear;

    @Column(name = "gpa")
    private String gpa;

    @NotNull
    @OneToOne
    @JoinColumn(name = "biodata_id", referencedColumnName = "id")
    private Biodata biodata;
}
