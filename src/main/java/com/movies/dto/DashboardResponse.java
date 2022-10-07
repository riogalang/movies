package com.movies.dto;

import com.movies.entity.Movies;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponse {

    private List<Movies> popularMovies, userFavorite, latest;
}
