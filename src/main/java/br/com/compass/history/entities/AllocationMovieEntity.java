package br.com.compass.history.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AllocationMovieEntity {
    private Long id;
    private String name;
}
