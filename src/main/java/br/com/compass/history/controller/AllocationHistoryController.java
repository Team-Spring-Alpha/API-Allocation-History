package br.com.compass.history.controller;

import br.com.compass.history.dto.response.ResponseAllocationDTO;
import br.com.compass.history.service.AllocationHistoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/allocation-history")
public class AllocationHistoryController {

    @Autowired
    private AllocationHistoryService service;

    @ApiOperation(value = "get all allocation history from user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @GetMapping("/{userId}")
    public ResponseEntity<List<ResponseAllocationDTO>> getByUser(@PathVariable String userId){
        List<ResponseAllocationDTO> byUserId = service.findByUserId(userId);
        return ResponseEntity.ok(byUserId);
    }
}
