package com.alkemy.disneydemo.rest;

import com.alkemy.disneydemo.model.Actor;
import com.alkemy.disneydemo.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ActorRestController implements Serializable {

    private ActorService actorService;

    @Autowired
    public ActorRestController(ActorService theActorService){ actorService = theActorService;}

    //expose "/actor" and return list of actors
    @GetMapping("/actors")
    public List<Actor> getAll() {return actorService.getAll();}

    //TODO HACER GET INDIVIDUAL falta exception
    @GetMapping("/actors/{actorId}")
    public Actor get(@PathVariable int actorId) {
        return actorService.get(actorId);
    }



}

