package com.alkemy.disneydemo.model;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * One Genre per movie
 */
@Entity
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

    @JsonManagedReference
    @OneToMany(mappedBy="genre",
            cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH }
    )
    private List<MovieTVSerie> movieTVSeries = new ArrayList<>();

    //no arg const
    //arg const
    public Genre() {
    }

    public Genre(String name, String image, List<MovieTVSerie> movieTVSeries) {
        this.name = name;
        this.image = image;
        this.movieTVSeries = movieTVSeries;
    }

    //adding convenience methods for bi-directional relationship
   /* public void add(Show theShow){
        if(shows == null) shows = new ArrayList<>();

        shows.add(theShow);
        theShow.setGenre(this);
    }*/

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

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                /*", shows=" + shows +*/
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Genre) {
            Genre theGenre = (Genre) obj;
            return this.getId() == theGenre.getId();
        }
        return false;
    }
}
