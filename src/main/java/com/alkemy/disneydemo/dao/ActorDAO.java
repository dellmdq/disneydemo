package com.alkemy.disneydemo.dao;

import com.alkemy.disneydemo.model.Actor;
import com.alkemy.disneydemo.model.MovieTVSerie;

import java.util.List;

public interface ActorDAO {

    public List<Actor> getAll();

    public void save(Actor theActor);//with hibernate this add and save options depending on id

    public Actor get(int theId);

    public void delete(int theId);

    public List<Actor> getActorsByName(String name);

    public List<Actor> getActorsByAge(int age);

    public List<Actor> getActorsByMovieTVSerieId(int theMovieTVSerieId);

    public void update(Actor theActor);
}
