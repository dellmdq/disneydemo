package com.alkemy.disneydemo.service;

import com.alkemy.disneydemo.model.Actor;

import java.util.List;

public interface ActorService {
    public List<Actor> getAll();

    public Actor get(int theId);

    public void save(Actor theActor);//hibernate saveOrUpdate

    public void delete(int theId);
}
