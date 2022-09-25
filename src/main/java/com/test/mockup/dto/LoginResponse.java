package com.test.mockup.dto;

import com.test.mockup.entity.Biodata;
import com.test.mockup.entity.JobExperiences;
import com.test.mockup.entity.LatestEducation;
import com.test.mockup.entity.TrainingHistory;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    private Biodata biodata;
    private List<BiodataDto> biodataDto;
    private List<JobExperiences> jobExperiences;
    private List<LatestEducation> latestEducations;
    private List<TrainingHistory> trainingHistories;

}
