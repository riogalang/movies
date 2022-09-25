package com.test.mockup.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_authentication")
@Builder
public class UserAuthentication {
    @Id
    @GeneratedValue(generator="user_authentication_seq")
    @SequenceGenerator(name="user_authentication_seq",sequenceName="user_authentication_seq", allocationSize=1)
    @Column(name = "id", length = 36, updatable = false, nullable = false)
    private Long id;

    @Column(name = "email")

    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "is_filling_form")
    private Boolean isFillingForm;

    @Column(name = "is_user_admin")
    private Boolean isUserAdmin;


}
