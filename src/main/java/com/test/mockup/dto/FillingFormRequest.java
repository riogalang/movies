package com.test.mockup.dto;

import com.test.mockup.entity.JobExperiences;
import com.test.mockup.entity.LatestEducation;
import com.test.mockup.entity.TrainingHistory;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FillingFormRequest {

    private String position;
    private Long biodataId;
    private String name;
    private String idCard;
    private String birthDateAndPlace;
    private String gender;
    private String status;
    private String addressByIdCard;
    private String addressName;
    private String email;
    private String mobilePhone;
    private String religion;
    private String bloodGroup;
    private String closestPerson;
    private Long userAuthenticationId;
    private List<LatestEducation> latestEducation;
    private List<JobExperiences> jobExperiences;
    private List<TrainingHistory> trainingHistory;
    private String skill;
    private Boolean willingToBePlacedInAllOffices;
    private BigDecimal expectedIncome;
}
