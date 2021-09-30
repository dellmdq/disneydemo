package com.alkemy.disneydemo.service;

import java.util.List;

public interface MovieTVSerieService {
    public List<com.alkemy.disneydemo.model.MovieTVSerie> getAll();

    public com.alkemy.disneydemo.model.MovieTVSerie get(int theId);

    public void save(com.alkemy.disneydemo.model.MovieTVSerie theMovieTVSerie);

    public void delete(int theId);
}
