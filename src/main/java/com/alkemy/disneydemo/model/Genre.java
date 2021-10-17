package com.alkemy.disneydemo.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * One Genre per movie
 */
@Entity(name="Genre")
@Table(name="genre")
public class Genre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="image")
    private String image;


    @Nullable
    @JsonIgnore
    //@JsonManagedReference
    @JsonIgnoreProperties({"genre","actors"})
    @OneToMany(mappedBy="genre",
            cascade= CascadeType.ALL //{CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH }
    )
    private Set<MovieTVSerie> movieTVSeries = new HashSet<>();

    //no arg const
    //arg const
    public Genre() {
    }

    public Genre(String name, String image, Set<MovieTVSerie> movieTVSeries) {
        this.name = name;
        this.image = image;
        this.movieTVSeries = movieTVSeries;
    }

    //getts and setts
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<MovieTVSerie> getMovieTVSeries() {
        return movieTVSeries;
    }

    public void setMovieTVSeries(Set<MovieTVSerie> movieTVSeries) {
        this.movieTVSeries = movieTVSeries;
    }

    //adding convenience methods for bi-directional relationship
    public void addMovieTVSerie(MovieTVSerie movieTVSerie){
        movieTVSeries.add(movieTVSerie);
        movieTVSerie.setGenre(this);
    }

    public void removeMovieTvSerie(MovieTVSerie movieTVSerie){
        movieTVSeries.remove(movieTVSerie);
    }

    public void removeMovieTvSeriesSet(){
            this.movieTVSeries = null;
        }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", movieTvSeries=" + movieTVSeries +
                '}';
    }

}
