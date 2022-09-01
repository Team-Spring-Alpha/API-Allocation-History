package br.com.compass.history.service;

import br.com.compass.history.dto.request.RequestAllocation;
import br.com.compass.history.dto.response.ResponseAllocation;
import br.com.compass.history.entities.AllocationEntity;
import br.com.compass.history.repository.AllocationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AllocationHistoryService {

    @Autowired
    private AllocationRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public List<ResponseAllocation> findByUserId(String userId){
        List<AllocationEntity> allocationEntities = repository.findByUserId(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<ResponseAllocation> collect = allocationEntities.stream().map(allocationEntity ->
                modelMapper.map(allocationEntity, ResponseAllocation.class)).collect(Collectors.toList());
        return collect;
    }

    public void createdAllocation(RequestAllocation allocation) {
        AllocationEntity entity = modelMapper.map(allocation, AllocationEntity.class);
        repository.save(entity);
    }
}
