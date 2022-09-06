package br.com.compass.history.listeners;

import br.com.compass.history.dto.request.RequestAllocation;
import br.com.compass.history.service.AllocationHistoryService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AllocationCreatedListener {

    @Autowired
    private AllocationHistoryService service;

    @RabbitListener(queues = "payment.status")
    public void allocationCreated(RequestAllocation allocation){
        service.createdAllocation(allocation);
    }

}
