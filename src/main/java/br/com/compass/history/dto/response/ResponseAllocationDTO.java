package br.com.compass.history.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ResponseAllocationDTO {
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("allocation_number")
    private String id;
    @JsonProperty("card_number")
    private String cardNumber;
    private List<ResponseAllocationMovieDTO> movies;
    @JsonProperty("payment_status")
    private String paymentStatus;
    @JsonProperty("allocation_date")
    private LocalDateTime date;
}
