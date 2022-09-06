package br.com.compass.history.builder;

import br.com.compass.history.dto.request.RequestAllocation;
import br.com.compass.history.entities.AllocationEntity;
import br.com.compass.history.entities.AllocationMovieEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AllocationEntityBuilder {

    private AllocationEntity allocation;
    public AllocationEntityBuilder(){

    }

    public static AllocationEntityBuilder one(){
        AllocationEntityBuilder builder = new AllocationEntityBuilder();
        builder.allocation = new AllocationEntity();

        builder.allocation.setUserId("1");
        builder.allocation.setCardNumber("438184557");
        builder.allocation.setPaymentStatus("APPROVED");

        List<AllocationMovieEntity> movies = new ArrayList<>();

        AllocationMovieEntity movie1 = new AllocationMovieEntity();
        movie1.setId(1l);
        movie1.setName("teste1");
        movies.add(movie1);

        AllocationMovieEntity movie2 = new AllocationMovieEntity();
        movie2.setId(2l);
        movie2.setName("teste2");
        movies.add(movie2);

        builder.allocation.setMovies(movies);
        return builder;

    }

    public AllocationEntityBuilder withRequestAllocation(RequestAllocation allocation){
        this.allocation.setId("1");
        this.allocation.setUserId(allocation.getUserId());
        this.allocation.setCardNumber(allocation.getCardNumber());
        this.allocation.setPaymentStatus(allocation.getPaymentStatus());
        this.allocation.setMovies(allocation.getMovies().stream().map(requestAllocationMovie ->
                AllocationMovieEntityBuilder.one().withRequest(requestAllocationMovie).now()).collect(Collectors.toList()));
        return this;
    }

    public AllocationEntity now(){
        return this.allocation;
    }
}
