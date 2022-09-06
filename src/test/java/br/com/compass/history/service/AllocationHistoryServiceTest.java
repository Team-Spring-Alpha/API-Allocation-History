package br.com.compass.history.service;

import br.com.compass.history.dto.request.RequestAllocation;
import br.com.compass.history.dto.request.RequestAllocationMovie;
import br.com.compass.history.dto.response.ResponseAllocation;
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
        RequestAllocation requestAllocation = buildRequestAllocation("user id", "card test", "movie test", "Approved");

        AllocationEntity allocationEntity = buildAllocationEntity("id", "user id", "card test", "movie test", "Approved");
        List<AllocationEntity> allocationEntityList = new ArrayList<>();
        allocationEntityList.add(allocationEntity);

        Mockito.when(allocationRepository.findByUserId(requestAllocation.getUserId())).thenReturn(Optional.of(allocationEntityList));

        List<ResponseAllocation> responseAllocationList = allocationHistoryService.findByUserId(requestAllocation.getUserId());

        responseAllocationList.forEach(responseAllocation ->
                Assertions.assertEquals(requestAllocation.getUserId(), responseAllocation.getUserId())
        );

        responseAllocationList.forEach(responseAllocation ->
                Assertions.assertEquals(requestAllocation.getCardNumber(), responseAllocation.getCardNumber())
        );

        responseAllocationList.forEach(responseAllocation ->
                Assertions.assertEquals(requestAllocation.getPaymentStatus(), responseAllocation.getPaymentStatus())
        );

        responseAllocationList.forEach(responseAllocation ->
                Assertions.assertEquals(requestAllocation.getMovies().get(0).getName(), responseAllocation.getMovies().get(0).getName())
        );

        responseAllocationList.forEach(responseAllocation ->
                Assertions.assertEquals(requestAllocation.getMovies().get(0).getId(), responseAllocation.getMovies().get(0).getId())
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
        RequestAllocation requestAllocation = RequestAllocation.builder()
                .cardNumber("test")
                .userId("user Test")
                .build();

        allocationHistoryService.createdAllocation(requestAllocation);

        Mockito.verify(allocationRepository).save(any());

    }

    private RequestAllocation buildRequestAllocation(String userId, String cardNumber, String movieName, String paymentStatus) {
        RequestAllocationMovie requestAllocationMovie = RequestAllocationMovie.builder()
                .id(1L)
                .name(movieName)
                .build();

        List<RequestAllocationMovie> requestAllocationMovieList = new ArrayList<>();
        requestAllocationMovieList.add(requestAllocationMovie);

        return RequestAllocation.builder()
                .userId(userId)
                .cardNumber(cardNumber)
                .movies(requestAllocationMovieList)
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