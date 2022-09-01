package br.com.compass.history.controller;

import br.com.compass.history.dto.response.ResponseAllocation;
import br.com.compass.history.service.AllocationHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/allocation-history/history")
public class AllocationHistoryController {

    @Autowired
    private AllocationHistoryService service;


    @GetMapping("/{userId}")
    public ResponseEntity<List<ResponseAllocation>> getByUser(@PathVariable String userId){
        List<ResponseAllocation> byUserId = service.findByUserId(userId);
        return ResponseEntity.ok(byUserId);
    }
}
