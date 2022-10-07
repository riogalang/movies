package com.movies.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category")
@Builder
public class Comments {
    @Id
    @GeneratedValue(generator="category_seq")
    @SequenceGenerator(name="category_seq",sequenceName="category_seq", allocationSize=1)
    @Column(name = "id", length = 36, updatable = false, nullable = false)
    private Long id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "user_authentication_id", referencedColumnName = "id")
    private UserAuthentication userAuthentication;

    @NotNull
    @OneToOne
    @JoinColumn(name = "movies_id", referencedColumnName = "id")
    private Movies movies;

    @Column(name = "review")
    private String review;

}
