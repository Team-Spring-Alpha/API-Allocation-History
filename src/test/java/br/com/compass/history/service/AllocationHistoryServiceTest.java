package br.com.compass.history.service;

import br.com.compass.history.dto.request.RequestAllocationDTO;
import br.com.compass.history.dto.request.RequestAllocationMovieDTO;
import br.com.compass.history.dto.response.ResponseAllocationDTO;
import br.com.compass.history.entities.AllocationEntity;
import br.com.compass.history.entities.AllocationMovieEntity;
import br.com.compass.history.repository.AllocationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.Sort;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;


@SpringBootTest(classes = AllocationHistoryService.class)
class AllocationHistoryServiceTest {

    @Autowired
    private AllocationHistoryService allocationHistoryService;
    @MockBean
    private AllocationRepository allocationRepository;

    @SpyBean
    private ModelMapper mapper;


    @Test
    @DisplayName("Should find a user by id")
    public void shouldFindAUserById() {
        RequestAllocationDTO requestAllocationDTO = buildRequestAllocation("user id", "card test", "movie test", "Approved");

        AllocationEntity allocationEntity = buildAllocationEntity("id", "user id", "card test", "movie test", "Approved");
        List<AllocationEntity> allocationEntityList = new ArrayList<>();
        allocationEntityList.add(allocationEntity);

        Mockito.when(allocationRepository.findByUserId(requestAllocationDTO.getUserId(), Sort.by(Sort.Direction.DESC, "date"))).thenReturn(Optional.of(allocationEntityList));

        List<ResponseAllocationDTO> responseAllocationDTOList = allocationHistoryService.findByUserId(requestAllocationDTO.getUserId());

        responseAllocationDTOList.forEach(responseAllocationDTO ->
                Assertions.assertEquals(requestAllocationDTO.getUserId(), responseAllocationDTO.getUserId())
        );

        responseAllocationDTOList.forEach(responseAllocationDTO ->
                Assertions.assertEquals(requestAllocationDTO.getCardNumber(), responseAllocationDTO.getCardNumber())
        );

        responseAllocationDTOList.forEach(responseAllocationDTO ->
                Assertions.assertEquals(requestAllocationDTO.getPaymentStatus(), responseAllocationDTO.getPaymentStatus())
        );

        responseAllocationDTOList.forEach(responseAllocationDTO ->
                Assertions.assertEquals(requestAllocationDTO.getMovies().get(0).getName(), responseAllocationDTO.getMovies().get(0).getName())
        );

        responseAllocationDTOList.forEach(responseAllocationDTO ->
                Assertions.assertEquals(requestAllocationDTO.getMovies().get(0).getId(), responseAllocationDTO.getMovies().get(0).getId())
        );

    }

    @Test
    @DisplayName("Should throw an exception when try find an nonexistent user ")
    public void shouldThrowAnExceptionWhenTryFindAnUserNonexistent() {
        Assertions.assertThrows(ResponseStatusException.class, () -> allocationHistoryService.findByUserId("teste"));
    }
    @Test
    @DisplayName("should save an allocation movie")
    public void shouldSaveAnAllocationMovie() {
        RequestAllocationDTO requestAllocationDTO = buildRequestAllocation("test", "test", "test", "test");

        allocationHistoryService.createdAllocation(requestAllocationDTO);

        Mockito.verify(allocationRepository).save(any());

    }

    private RequestAllocationDTO buildRequestAllocation(String userId, String cardNumber, String movieName, String paymentStatus) {
        RequestAllocationMovieDTO requestAllocationMovieDTO = RequestAllocationMovieDTO.builder()
                .id(1L)
                .name(movieName)
                .build();

        List<RequestAllocationMovieDTO> requestAllocationMovieDTOList = new ArrayList<>();
        requestAllocationMovieDTOList.add(requestAllocationMovieDTO);

        return RequestAllocationDTO.builder()
                .userId(userId)
                .cardNumber(cardNumber)
                .movies(requestAllocationMovieDTOList)
                .paymentStatus(paymentStatus)
                .build();
    }

    private AllocationEntity buildAllocationEntity(String id, String userId, String cardNumber, String movieName, String paymentStatus) {
        AllocationMovieEntity movieEntity = AllocationMovieEntity.builder()
                .id(1L)
                .name(movieName)
                .build();

        List<AllocationMovieEntity> movieEntityList = new ArrayList<>();
        movieEntityList.add(movieEntity);

        return AllocationEntity.builder()
                .id(id)
                .userId(userId)
                .cardNumber(cardNumber)
                .movies(movieEntityList)
                .paymentStatus(paymentStatus)
                .build();
    }
}