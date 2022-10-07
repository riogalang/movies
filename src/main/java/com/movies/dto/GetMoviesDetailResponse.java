package com.movies.dto;

import com.movies.entity.Movies;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetMoviesDetailResponse {

    private Movies movies;
    private List<Movies> recommendation;
}
