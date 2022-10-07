package com.movies.entity;

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
public class Movies {
    @Id
    @GeneratedValue(generator="biodata_seq")
    @SequenceGenerator(name="biodata_seq",sequenceName="biodata_seq", allocationSize=1)
    @Column(name = "id", length = 36, updatable = false, nullable = false)
    private Long id;

    @Column(name = "original_title")
    private String originalTitle;

    @Column(name = "backdrop_path")
    private String backdropPath;

    @Column(name = "poster_path")
    private String posterPath;

    @Column(name = "overview")
    private String overview;

    @Column(name = "popularity")
    private Double popularity;

    @Column(name = "release_date")
    private Date releaseDate;

    @Column(name = "vote_average")
    private Double voteAverage;

    @Column(name = "vote_count")
    private String voteCount;

    @Column(name = "trailer_link")
    private String trailerLink;

    @NotNull
    @OneToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;




}
