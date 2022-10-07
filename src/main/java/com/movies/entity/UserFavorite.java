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
@Table(name = "user_favorite")
@Builder
public class UserFavorite {
    @Id
    @GeneratedValue(generator="user_favorite_seq")
    @SequenceGenerator(name="user_favorite_seq",sequenceName="user_favorite_seq", allocationSize=1)
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

}
