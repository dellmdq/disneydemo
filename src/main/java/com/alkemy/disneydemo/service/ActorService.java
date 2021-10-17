package com.alkemy.disneydemo.service;

import com.alkemy.disneydemo.model.Actor;
import com.alkemy.disneydemo.model.MovieTVSerie;

import java.util.List;

public interface ActorService {
    public List<Actor> getAll();

    public Actor get(int theId);

    public void save(Actor theActor);//hibernate saveOrUpdate

    public void delete(int theId);

    public List<Actor> getActorsByName(String name);

    public List<Actor> getActorsByAge(int age);

    public List<Actor> getActorsByMovieTVSerie(int theMovieTVSerieId);
}
