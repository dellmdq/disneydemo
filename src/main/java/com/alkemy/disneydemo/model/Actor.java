package com.alkemy.disneydemo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;

import java.util.HashSet;
import java.util.Set;

@Entity(name="Actor")
@Table(name="actor")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Actor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    @Column(name="id")
    private int id;

    @Nullable
    @Column(name="image")
    private String image; //ver API the movie database para traer link imagen

    @Nullable
    @Column(name="name")
    private String name;

    @Nullable
    @Column(name="age")
    private int age;

    @Nullable
    @Column(name="weight")
    private float weight;

    @Nullable
    @Column(name="bio")
    private String bio;


    //muchos a muchos //
    @Nullable
    @JsonBackReference
    @JsonIgnoreProperties({"actors","genres"})
    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name="movietvserie_actor",
            joinColumns = @JoinColumn(name="actor_id"),
            inverseJoinColumns =@JoinColumn(name="movietvserie_id")
    )
    private Set<MovieTVSerie> movieTVSeries = new HashSet<MovieTVSerie>();

    public Actor() {
    }

    public Actor(String image, String name, int age, float weight, String bio, Set<MovieTVSerie> movieTVSeries) {
        this.image = image;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.bio = bio;
        this.movieTVSeries = movieTVSeries;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Set<MovieTVSerie> getMovieTVSeries() {
        return movieTVSeries;
    }

    public void setMovieTVSeries(Set<MovieTVSerie> movieTVSeries) {
        this.movieTVSeries = movieTVSeries;
    }

    //utility methods to handle manytomany operations
     public void addMovieTvSerie(MovieTVSerie movieTVSerie){
        this.movieTVSeries.add(movieTVSerie);
        movieTVSerie.getActors().add(this);
     }

     public void removeMovieTvSerie(MovieTVSerie movieTVSerie){
        this.movieTVSeries.remove(movieTVSerie);
        movieTVSerie.getActors().remove(this);
     }

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", bio='" + bio + '\'' +
                ", showList=" + movieTVSeries +
                '}';
    }


}
