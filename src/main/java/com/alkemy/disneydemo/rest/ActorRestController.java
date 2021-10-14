package com.alkemy.disneydemo.rest;

import com.alkemy.disneydemo.model.Actor;
import com.alkemy.disneydemo.service.ActorService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/characters")
public class ActorRestController implements Serializable {

    private ActorService actorService;

    @Autowired
    public ActorRestController(ActorService theActorService){ actorService = theActorService;}

    //expose "/actor" and return list of actors
    @GetMapping
    public List<Actor> getAll() {return actorService.getAll();}

    @GetMapping("/{actorId}")
    public Actor get(@PathVariable int actorId) {
        return actorService.get(actorId);
    }

     @PostMapping
     public Actor add(@RequestBody Actor theActor){
            theActor.setId(0);
            actorService.save(theActor);
            return theActor;
    }

    @PutMapping
    public Actor update(@RequestBody Actor theActor){

        actorService.save(theActor);
        return theActor;
    }

    @DeleteMapping("/{actorId}")
    public String delete(@PathVariable int actorId){
        Actor tempActor = actorService.get(actorId);
        /*getting movieTvSerieSet
        Set<MovieTVSerie> movieTvSerieSet = tempActor.getMovieTVSeries();
        for(MovieTVSerie mts : movieTvSerieSet){
            mts.
        }*/
        actorService.delete(actorId);
        return "The Actor deleted is: " + tempActor + "\nId: " + tempActor.getId();
    }


}

