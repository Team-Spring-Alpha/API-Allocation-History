package br.com.compass.history.service;

import br.com.compass.history.builder.AllocationEntityBuilder;
import br.com.compass.history.builder.RequestAllocationBuilder;
import br.com.compass.history.dto.request.RequestAllocation;
import br.com.compass.history.entities.AllocationEntity;
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
        RequestAllocation requestAllocation = RequestAllocationBuilder.one().now();
        List<AllocationEntity> list = new ArrayList<>();
        AllocationEntity allocation = AllocationEntityBuilder.one().withRequestAllocation(requestAllocation).now();
        list.add(allocation);
        Mockito.when(allocationRepository.findByUserId(allocation.getUserId())).thenReturn(Optional.of(list));
        allocationHistoryService.findByUserId(allocation.getUserId());

        Mockito.verify(allocationRepository, Mockito.times(1)).findByUserId(allocation.getUserId());
        Assertions.assertEquals("1", allocation.getUserId());
    }

    @Test
    @DisplayName("Should throw an exception when try find an nonexistent user ")
    public void shouldThrowAnExceptionWhenTryFindAnUserNonexistent(){
        RequestAllocation requestAllocation = RequestAllocationBuilder.one().now();
        List<AllocationEntity> list = new ArrayList<>();
        AllocationEntity allocation = AllocationEntityBuilder.one().withRequestAllocation(requestAllocation).now();
        list.add(allocation);

        Assertions.assertThrows(ResponseStatusException.class, () -> allocationHistoryService.findByUserId("teste"));
    }
}