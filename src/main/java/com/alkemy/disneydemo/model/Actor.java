package com.alkemy.disneydemo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

import java.util.List;

@Entity
@Table(name="actor")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Actor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="image")
    private String image; //ver API the movie database para traer link imagen

    @Column(name="name")
    private String name;

    @Column(name="age")
    private int age;

    @Column(name="weight")
    private float weight;

    @Column(name="bio")
    private String bio;


    //muchos a muchos //todo ver tipos de cascade
    @JsonBackReference
    @ManyToMany(mappedBy = "actors",fetch=FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    /*@JoinTable(name="actor_show",
            joinColumns = @JoinColumn(name="actor_id"),//todo cargar actors and show para testear esto
            inverseJoinColumns =@JoinColumn(name="show_id")
    )*/
    private List<MovieTVSerie> movieTVSeries = new ArrayList<MovieTVSerie>();

    public Actor() {
    }

    public Actor(String image, String name, int age, float weight, String bio, List<MovieTVSerie> movieTVSeries) {
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

    public List<MovieTVSerie> getShows() {
        return movieTVSeries;
    }

    public void setShows(List<MovieTVSerie> movieTVSeries) {
        this.movieTVSeries = movieTVSeries;
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

    /*@Override
    public boolean equals(Object obj) {
        if(obj instanceof Actor){
            Actor theActor = (Actor) obj;
            return this.getId() == theActor.getId();
        }
        return false;
    }*/

}
