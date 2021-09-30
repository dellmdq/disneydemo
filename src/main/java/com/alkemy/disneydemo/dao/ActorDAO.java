package com.alkemy.disneydemo.dao;

import com.alkemy.disneydemo.model.Actor;

import java.util.List;

public interface ActorDAO {

    public List<Actor> getAll();

    public void save(Actor theActor);//with hibernate this add and save options depending on id

    public Actor get(int theId);

    public void delete(int theId);

}
