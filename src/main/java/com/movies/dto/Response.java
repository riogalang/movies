package com.movies.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Response<T> {
    private LocalDateTime localDateTime;
    private T result;

    public static <T> Response<T> result(@Nullable T result) {
        return Response.<T>builder()
                .localDateTime(LocalDateTime.now())
                .result(result)
                .build();
    }
}