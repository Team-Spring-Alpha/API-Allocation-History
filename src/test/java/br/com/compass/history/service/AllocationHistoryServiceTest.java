package br.com.compass.history.service;

import br.com.compass.history.dto.request.RequestAllocation;
import br.com.compass.history.entities.AllocationEntity;
import br.com.compass.history.repository.AllocationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(classes = AllocationHistoryService.class)
class AllocationHistoryServiceTest {

    @Autowired
    private AllocationHistoryService allocationHistoryService;

    @SpyBean
    private ModelMapper modelMapper;

    @MockBean
    private AllocationRepository repository;

    @Test
    @DisplayName("should save an allocation movie")
    void shouldSaveAnAllocationMovie() {
        RequestAllocation requestAllocation = RequestAllocation.builder()
                .cardNumber("test")
                .userId("user Test")
                .build();

        allocationHistoryService.createdAllocation(requestAllocation);

        Mockito.verify(repository).save(any());

    }
}