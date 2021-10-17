package com.alkemy.disneydemo.rest;

import com.alkemy.disneydemo.model.Actor;
import com.alkemy.disneydemo.service.ActorService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.monitorjbl.json.JsonResult;
import com.monitorjbl.json.JsonView;
import com.monitorjbl.json.JsonViewModule;
import com.monitorjbl.json.Match;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.io.Serializable;
import java.util.List;

import static com.monitorjbl.json.Match.match;

@RestController
@RequestMapping("/characters")
public class ActorRestController implements Serializable {

    private ActorService actorService;

    @Autowired
    public ActorRestController(ActorService theActorService){ actorService = theActorService;}

    //expose "/actor" and return list of actors. ONLY SHOW NAME AND IMAGE
    @GetMapping
    public List<Actor> getAll(@QueryParam("name") String name,
                              @QueryParam("age") Integer age,
                              @QueryParam("movies") Integer movies){

        if(name != null && name.length() > 0 ){
            return actorService.getActorsByName(name);
        }
        if(age != null && age >= 0 ){
            return actorService.getActorsByAge(age);
        }
        if(movies != null && movies >= 0 ){
            return actorService.getActorsByMovieTVSerie(movies);
        }

        //getall sin filtros
        JsonResult json = JsonResult.instance();
        List<Actor> actors = actorService.getAll();
        String[] excludedProps = {"id","age","weight","bio","movieTVSeries"};

        return json.use(JsonView.with(actors)
                .onClass(Actor.class, Match.match()
                        .exclude(excludedProps)))
                .returnValue();
    }

    @GetMapping("/{actorId}")
    public Actor get(@PathVariable int actorId) {
        JsonResult json = JsonResult.instance();
        Actor theActor = actorService.get(actorId);
        String[] includedProps = {"movieTVSeries"};

        return json.use(JsonView.with(theActor)
                        .onClass(Actor.class, Match.match().include(includedProps)))
                        .returnValue();
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

