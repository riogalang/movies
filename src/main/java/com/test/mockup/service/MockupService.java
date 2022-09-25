package com.test.mockup.service;

import com.test.mockup.dto.*;
import com.test.mockup.entity.*;
import com.test.mockup.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Slf4j
@Service
public class MockupService {

    @Autowired
    private UserAuthenticationRepository userAuthenticationRepository;

    @Autowired
    private JobExperiencesRepository jobExperiencesRepository;

    @Autowired
    private TrainingHistoryRepository trainingHistoryRepository;

    @Autowired
    private BiodataRepository biodataRepository;

    @Autowired
    private LatestEducationRepository latestEducationRepository;

    public UserAuthentication signUp(AuthenticationRequestDto employeeRequestSaveDto) {

        if (userAuthenticationRepository.findByEmail(employeeRequestSaveDto.getEmail()).isPresent()) throw new RuntimeException();

        return userAuthenticationRepository.save(
                UserAuthentication.builder()
                        .email(employeeRequestSaveDto.getEmail())
                        .password(employeeRequestSaveDto.getPassword())
                        .isFillingForm(false)
                        .isUserAdmin(false)
                        .build());
    }

    public LoginResponse login(AuthenticationRequestDto employeeRequestSaveDto) {

        List<BiodataDto> biodataDtos = new ArrayList<>();
        var userAuthentication = userAuthenticationRepository.findByEmail(employeeRequestSaveDto.getEmail());

        if (userAuthentication.isEmpty())
            throw new RuntimeException();
        else if (!Objects.equals(userAuthentication.get().getPassword(), employeeRequestSaveDto.getPassword()))
            throw new RuntimeException();




        if (userAuthentication.get().getIsUserAdmin().equals(false)){
            if (biodataRepository.findByUserAuthentication(userAuthentication.get()).isEmpty()) throw new RuntimeException();
            Biodata biodata = biodataRepository.findByUserAuthentication(userAuthentication.get()).get();
            biodataDtos.add(BiodataDto.builder()
                    .biodata(biodataRepository.findByUserAuthentication(userAuthentication.get()).get())
                    .latestEducations(latestEducationRepository.findByBiodata(biodata))
                    .jobExperiences(jobExperiencesRepository.findByBiodata(biodata))
                    .trainingHistories(trainingHistoryRepository.findByBiodata(biodata))
                    .build());
        }else{

            List<Biodata> biodata = biodataRepository.findAll();
            for (var bio : biodata){
                biodataDtos.add(BiodataDto.builder()
                        .biodata(bio)
                        .trainingHistories(trainingHistoryRepository.findByBiodata(bio))
                        .jobExperiences(jobExperiencesRepository.findByBiodata(bio))
                        .latestEducations(latestEducationRepository.findByBiodata(bio))
                        .build());
            }
        }


        return LoginResponse.builder()
                        .biodataDto(biodataDtos)
                        .build();
    }

    @Transactional
    public BiodataDto fillingForm(FillingFormRequest fillingFormRequest) {

        List<LatestEducation> latestEducations = new ArrayList<>();
        List<TrainingHistory> trainingHistories = new ArrayList<>();
        List<JobExperiences> jobExperiences = new ArrayList<>();
        UserAuthentication userAuthentication = userAuthenticationRepository.getById(fillingFormRequest.getUserAuthenticationId());


        Biodata biodata = biodataRepository.save(Biodata.builder()
                .position(fillingFormRequest.getPosition())
                .createdDate(new Date())
                .name(fillingFormRequest.getName())
                .idCard(fillingFormRequest.getIdCard())
                .birthDateAndPlace(fillingFormRequest.getBirthDateAndPlace())
                .gender(fillingFormRequest.getGender())
                .religion(fillingFormRequest.getReligion())
                .bloodGroup(fillingFormRequest.getBloodGroup())
                .status(fillingFormRequest.getStatus())
                .addressByIdCard(fillingFormRequest.getAddressByIdCard())
                .addressName(fillingFormRequest.getAddressName())
                .email(fillingFormRequest.getEmail())
                .mobilePhone(fillingFormRequest.getMobilePhone())
                .closestPerson(fillingFormRequest.getClosestPerson())
                .userAuthentication(userAuthentication)
                .skill(fillingFormRequest.getSkill())
                .willingToBePlacedInAllOffices(fillingFormRequest.getWillingToBePlacedInAllOffices())
                .expectedIncome(fillingFormRequest.getExpectedIncome())
                .build());


        for (var latestEducation : fillingFormRequest.getLatestEducation()){
            latestEducations.add(latestEducationRepository.save(LatestEducation.builder()
                    .lastEducation(latestEducation.getLastEducation())
                    .nameInstitution(latestEducation.getNameInstitution())
                    .major(latestEducation.getMajor())
                    .graduationYear(latestEducation.getGraduationYear())
                    .gpa(latestEducation.getGpa())
                    .biodata(biodata)
                    .build()));
        }

        for (var trainingHistory : fillingFormRequest.getTrainingHistory()){

            trainingHistories.add(trainingHistoryRepository.save(TrainingHistory.builder()
                    .nameTraining(trainingHistory.getNameTraining())
                    .isAnyCertificate(trainingHistory.getIsAnyCertificate())
                    .year(trainingHistory.getYear())
                    .biodata(biodata)
                    .build()));
        }

        for (var jobExperience : fillingFormRequest.getJobExperiences()){
            jobExperiences.add(jobExperiencesRepository.save(JobExperiences.builder()
                    .companyName(jobExperience.getCompanyName())
                    .lastPosition(jobExperience.getLastPosition())
                    .lastIncome(jobExperience.getLastIncome())
                    .year(jobExperience.getYear())
                    .biodata(biodata)
                    .build()));
        }

        userAuthentication.setIsFillingForm(true);
        userAuthenticationRepository.save(userAuthentication);

        return BiodataDto.builder()
                        .biodata(biodata)
                        .jobExperiences(jobExperiences)
                        .trainingHistories(trainingHistories)
                        .latestEducations(latestEducations)
                .build();
    }

    @Transactional
    public BiodataDto update(FillingFormRequest fillingFormRequest) {

        List<LatestEducation> latestEducations = new ArrayList<>();
        List<TrainingHistory> trainingHistories = new ArrayList<>();
        List<JobExperiences> jobExperiences = new ArrayList<>();

        Biodata biodata1 = biodataRepository.findById(fillingFormRequest.getBiodataId()).get();


        Biodata biodata = biodataRepository.save(Biodata.builder()
                .id(fillingFormRequest.getBiodataId())
                .createdDate(biodata1.getCreatedDate())
                .position(fillingFormRequest.getPosition())
                .name(fillingFormRequest.getName())
                .idCard(fillingFormRequest.getIdCard())
                .birthDateAndPlace(fillingFormRequest.getBirthDateAndPlace())
                .gender(fillingFormRequest.getGender())
                .religion(fillingFormRequest.getReligion())
                .bloodGroup(fillingFormRequest.getBloodGroup())
                .status(fillingFormRequest.getStatus())
                .addressByIdCard(fillingFormRequest.getAddressByIdCard())
                .addressName(fillingFormRequest.getAddressName())
                .email(fillingFormRequest.getEmail())
                .mobilePhone(fillingFormRequest.getMobilePhone())
                .closestPerson(fillingFormRequest.getClosestPerson())
                .userAuthentication(biodata1.getUserAuthentication())
                .skill(fillingFormRequest.getSkill())
                .willingToBePlacedInAllOffices(fillingFormRequest.getWillingToBePlacedInAllOffices())
                .expectedIncome(fillingFormRequest.getExpectedIncome())
                .build());

        for (var trainHist : trainingHistoryRepository.findByBiodata(biodata)){
            trainingHistoryRepository.delete(trainHist);
        }

        for (var jobExp : jobExperiencesRepository.findByBiodata(biodata)){
            jobExperiencesRepository.delete(jobExp);
        }

        for (var latestEd : latestEducationRepository.findByBiodata(biodata)){
            latestEducationRepository.delete(latestEd);
        }




        for (var latestEducation : fillingFormRequest.getLatestEducation()){

            latestEducations.add(latestEducationRepository.save(LatestEducation.builder()
                    .lastEducation(latestEducation.getLastEducation())
                    .nameInstitution(latestEducation.getNameInstitution())
                    .major(latestEducation.getMajor())
                    .graduationYear(latestEducation.getGraduationYear())
                    .gpa(latestEducation.getGpa())
                    .biodata(biodata)
                    .build()));
        }

        for (var trainingHistory : fillingFormRequest.getTrainingHistory()){

            trainingHistories.add(trainingHistoryRepository.save(TrainingHistory.builder()
                    .nameTraining(trainingHistory.getNameTraining())
                    .isAnyCertificate(trainingHistory.getIsAnyCertificate())
                    .year(trainingHistory.getYear())
                    .biodata(biodata)
                    .build()));
        }

        for (var jobExperience : fillingFormRequest.getJobExperiences()){
            jobExperiences.add(jobExperiencesRepository.save(JobExperiences.builder()
                    .companyName(jobExperience.getCompanyName())
                    .lastPosition(jobExperience.getLastPosition())
                    .lastIncome(jobExperience.getLastIncome())
                    .year(jobExperience.getYear())
                    .biodata(biodata)
                    .build()));
        }


        return BiodataDto.builder()
                .biodata(biodata)
                .jobExperiences(jobExperiences)
                .trainingHistories(trainingHistories)
                .latestEducations(latestEducations)
                .build();
    }

    @Transactional
    public String delete(FillingFormRequest fillingFormRequest) {

        UserAuthentication userAuthentication = userAuthenticationRepository.findByEmail(fillingFormRequest.getEmail()).get();

        if (userAuthentication.getIsUserAdmin().equals(false)) throw new RuntimeException();

        var biodata = biodataRepository.findById(fillingFormRequest.getBiodataId());
        if (biodata.isPresent()) {
            for (var trainHist : trainingHistoryRepository.findByBiodata(biodata.get())){
                trainingHistoryRepository.delete(trainHist);
            }

            for (var jobExp : jobExperiencesRepository.findByBiodata(biodata.get())){
                jobExperiencesRepository.delete(jobExp);
            }

            for (var latestEd : latestEducationRepository.findByBiodata(biodata.get())){
                latestEducationRepository.delete(latestEd);
            }

            biodataRepository.delete(biodata.get());
            return "Success";
        }
        return "Not Found";

    }


}
