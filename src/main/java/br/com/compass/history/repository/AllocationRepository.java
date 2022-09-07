package br.com.compass.history.repository;

import br.com.compass.history.entities.AllocationEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AllocationRepository extends MongoRepository<AllocationEntity, String> {

    public Optional<List<AllocationEntity>> findByUserId(String userId, Sort sort);
}
