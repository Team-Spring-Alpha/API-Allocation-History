package br.com.compass.history.builder;

import br.com.compass.history.dto.request.RequestAllocation;
import br.com.compass.history.dto.request.RequestAllocationMovie;

import java.util.ArrayList;
import java.util.List;

public class RequestAllocationBuilder {

    private RequestAllocation allocation;
    public RequestAllocationBuilder(){

    }

    public static RequestAllocationBuilder one(){
        RequestAllocationBuilder builder = new RequestAllocationBuilder();
        builder.allocation = new RequestAllocation();

        builder.allocation.setUserId("1");
        builder.allocation.setCardNumber(438184557);
        builder.allocation.setPaymentStatus("APPROVED");

        List<RequestAllocationMovie> movies = new ArrayList<>();

        RequestAllocationMovie movie1 = new RequestAllocationMovie();
        movie1.setId(1l);
        movie1.setName("teste1");
        movies.add(movie1);

        RequestAllocationMovie movie2 = new RequestAllocationMovie();
        movie2.setId(2l);
        movie2.setName("teste2");
        movies.add(movie2);

        builder.allocation.setMovies(movies);
        return builder;
    }


    public RequestAllocationBuilder reproved(){
        this.allocation.setPaymentStatus("REPROVED");
        return reproved();
    }

    public RequestAllocation now(){
        return this.allocation;
    }

}
