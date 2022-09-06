package br.com.compass.history.builder;

import br.com.compass.history.dto.request.RequestAllocationMovie;
import br.com.compass.history.entities.AllocationMovieEntity;

import java.util.ArrayList;
import java.util.List;

public class AllocationMovieEntityBuilder {

    private AllocationMovieEntity allocationMovie;

    public AllocationMovieEntityBuilder(){

    }

    public static AllocationMovieEntityBuilder one(){
        AllocationMovieEntityBuilder builder = new AllocationMovieEntityBuilder();
        builder.allocationMovie = new AllocationMovieEntity();

        builder.allocationMovie.setId(1l);
        builder.allocationMovie.setName("teste");

        return builder;
    }

    public AllocationMovieEntityBuilder withRequest(RequestAllocationMovie request){
        this.allocationMovie.setId(request.getId());
        this.allocationMovie.setName(request.getName());
        return this;
    }

    public AllocationMovieEntity now(){
        return this.allocationMovie;
    }

    public List<AllocationMovieEntity> list(){
        List<AllocationMovieEntity> movieList = new ArrayList<>();
        movieList.add(this.allocationMovie);
        movieList.add(this.allocationMovie);
        return movieList;
    }
}
