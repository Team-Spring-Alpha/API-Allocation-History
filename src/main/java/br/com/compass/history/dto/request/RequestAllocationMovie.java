package br.com.compass.history.dto.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class RequestAllocationMovie {
    @NotNull
    private Long id;
    @NotBlank
    private String name;
}
