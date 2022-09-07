package br.com.compass.history.controller;

import br.com.compass.history.dto.response.ResponseAllocationDTO;
import br.com.compass.history.service.AllocationHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/allocation-history")
public class AllocationHistoryController {

    @Autowired
    private AllocationHistoryService service;

    @GetMapping("/{userId}")
    public ResponseEntity<List<ResponseAllocationDTO>> getByUser(@PathVariable String userId){
        List<ResponseAllocationDTO> byUserId = service.findByUserId(userId);
        return ResponseEntity.ok(byUserId);
    }
}
