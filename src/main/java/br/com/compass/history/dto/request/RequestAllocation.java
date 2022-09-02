package br.com.compass.history.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class RequestAllocation {
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("card_number")
    private String cardNumber;
    private List<RequestAllocationMovie> movies;
    @JsonProperty("payment_status")
    private String paymentStatus;
}
