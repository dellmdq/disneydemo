package com.alkemy.disneydemo.dao;

import com.alkemy.disneydemo.model.MovieTVSerie;

import java.util.List;

public interface MovieTVSerieDAO {

    public List<MovieTVSerie> getAll();

    public MovieTVSerie get(int theId);

    public void save(MovieTVSerie theMovieTVSerie);

    public void delete(int theId);

}
