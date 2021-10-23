package com.alkemy.disneydemo.model;


import com.fasterxml.jackson.annotation.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity(name="MovieTVSerie")
@Table(name="movietvserie")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class MovieTVSerie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Nullable
    @Column(name="image")
    private String image;

    @Nullable
    @Column(name="title")
    private String title;

    @Nullable
    @Column(name="release_date")
    private String date;

    @Nullable
    @Column(name="rating")
    private int rating;

    @Nullable
    @ManyToOne(cascade= {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name="genre_id")
    private Genre genre;

    @Nullable
    @JsonIgnoreProperties("movietvseries")
    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name="movietvserie_actor",
            joinColumns = {@JoinColumn(name="movietvserie_id")},
            inverseJoinColumns = {@JoinColumn(name="actor_id")})
    private Set<Actor> actors = new HashSet<Actor>();

    //constructors
    public MovieTVSerie() {
    }

    public MovieTVSerie(String image, String title, String date, int rating, Genre genre, Set<Actor> actors) {
        this.image = image;
        this.title = title;
        this.date = date;
        this.rating = rating;
        this.genre = genre;
        this.actors = actors;
    }

    //getters and setters
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Set<Actor> getActors() {
        return actors;
    }

   public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    //to string
    @Override
    public String toString() {
        return "Show{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", rating=" + rating +
                ", actorset=" + actors +
                ", genre=" + genre +
                '}';
    }


}
