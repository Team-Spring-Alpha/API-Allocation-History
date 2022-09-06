package br.com.compass.history.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "Allocation")
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AllocationEntity {
    @Id
    private String id;
    private String userId;
    private String cardNumber;
    private List<AllocationMovieEntity> movies;
    private String paymentStatus;
    private LocalDateTime date = LocalDateTime.now();
}
