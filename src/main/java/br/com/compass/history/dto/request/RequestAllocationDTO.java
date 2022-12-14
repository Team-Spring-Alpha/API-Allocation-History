package br.com.compass.history.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RequestAllocationDTO {
    @NotBlank
    @JsonProperty("user_id")
    private String userId;
    @NotBlank
    @JsonProperty("card_number")
    @CreditCardNumber
    private String cardNumber;
    @NotEmpty
    private List<RequestAllocationMovieDTO> movies;
    @NotBlank
    @JsonProperty("payment_status")
    private String paymentStatus;
}
