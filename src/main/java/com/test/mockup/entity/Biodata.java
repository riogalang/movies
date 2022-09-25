package com.test.mockup.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "biodata")
@Builder
public class Biodata {
    @Id
    @GeneratedValue(generator="biodata_seq")
    @SequenceGenerator(name="biodata_seq",sequenceName="biodata_seq", allocationSize=1)
    @Column(name = "id", length = 36, updatable = false, nullable = false)
    private Long id;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "position")
    private String position;

    @Column(name = "name")
    private String name;

    @Column(name = "id_card")
    private String idCard;

    @Column(name = "birth_date_and_place")
    private String birthDateAndPlace;

    @Column(name = "gender")
    private String gender;

    @Column(name = "blood_group")
    private String bloodGroup;

    @Column(name = "status")
    private String status;

    @Column(name = "address_by_id_card")
    private String addressByIdCard;

    @Column(name = "address_now")
    private String addressName;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile_phone")
    private String mobilePhone;

    @Column(name = "closest_person")
    private String closestPerson;

    @Column(name = "religion")
    private String religion;

    @NotNull
    @OneToOne
    @JoinColumn(name = "user_authentication_id", referencedColumnName = "id")
    private UserAuthentication userAuthentication;

    @Column(name = "skill")
    private String skill;

    @Column(name = "willing_to_be_placed_in_all_offices")
    private Boolean willingToBePlacedInAllOffices;

    @Column(name = "expected_income")
    private BigDecimal expectedIncome;


}
