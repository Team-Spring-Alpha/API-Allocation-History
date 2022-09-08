package br.com.compass.history.service;

import br.com.compass.history.dto.request.RequestAllocationDTO;
import br.com.compass.history.dto.response.ResponseAllocationDTO;
import br.com.compass.history.entities.AllocationEntity;
import br.com.compass.history.repository.AllocationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AllocationHistoryService {

    @Autowired
    private AllocationRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public List<ResponseAllocationDTO> findByUserId(String userId){
        List<AllocationEntity> allocationEntities = repository.findByUserId(userId, Sort.by(Sort.Direction.DESC, "date"))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (allocationEntities.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return allocationEntities.stream().map(allocationEntity ->
                modelMapper.map(allocationEntity, ResponseAllocationDTO.class)).collect(Collectors.toList());
    }

    public void createdAllocation(@Valid RequestAllocationDTO allocation) {
        AllocationEntity entity = modelMapper.map(allocation, AllocationEntity.class);
        repository.save(entity);
    }
}
