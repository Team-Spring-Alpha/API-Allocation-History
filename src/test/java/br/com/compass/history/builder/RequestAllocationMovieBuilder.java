package br.com.compass.history.builder;

import br.com.compass.history.dto.request.RequestAllocationMovie;

import java.util.ArrayList;
import java.util.List;

public class RequestAllocationMovieBuilder {

    private RequestAllocationMovie requestAllocationMovie;

    public RequestAllocationMovieBuilder(){

    }

    public static RequestAllocationMovieBuilder one(){
        RequestAllocationMovieBuilder builder = new RequestAllocationMovieBuilder();
        builder.requestAllocationMovie = new RequestAllocationMovie();

        builder.requestAllocationMovie.setId(1L);
        builder.requestAllocationMovie.setName("teste");

        return builder;
    }

    public RequestAllocationMovie now(){
        return this.requestAllocationMovie;
    }

    public List<RequestAllocationMovie> list(){
        List<RequestAllocationMovie> movieList = new ArrayList<>();
        movieList.add(this.requestAllocationMovie);
        movieList.add(this.requestAllocationMovie);
        return movieList;
    }
}
