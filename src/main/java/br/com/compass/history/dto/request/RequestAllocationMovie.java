package br.com.compass.history.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RequestAllocationMovie {
    @NotNull
    private Long id;
    @NotBlank
    private String name;
}
