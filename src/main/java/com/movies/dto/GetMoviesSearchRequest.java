package com.movies.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetMoviesSearchRequest {

    private String keyword;
}
