package com.alkemy.disneydemo.service;

import com.alkemy.disneydemo.dao.ActorDAO;
import com.alkemy.disneydemo.model.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ActorServiceImpl implements ActorService {

    private ActorDAO actorDAO;

    @Autowired
    public ActorServiceImpl(ActorDAO theActorDAO){ actorDAO = theActorDAO;}

    @Override
    @Transactional
    public List<Actor> getAll() {
        return actorDAO.getAll();
    }

    @Override
    @Transactional
    public Actor get(int theId) {
        return actorDAO.get(theId);
    }

    @Override
    @Transactional
    public void save(Actor theActor) {
        actorDAO.save(theActor);
    }

    @Override
    @Transactional
    public void delete(int theId) {
        actorDAO.delete(theId);
    }
}
