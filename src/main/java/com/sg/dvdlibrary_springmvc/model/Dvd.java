/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary_springmvc.model;

import java.util.Objects;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Roger Brock
 */
public class Dvd {

    private int dvdId;
    @NotEmpty(message = "You must add Dvd Title.")
    @Length(max = 50, message = "Title must be no more than 50 characters in length.")
    private String title;
    @NotEmpty(message = "You must add Dvd Release Year.")
    @Length(min=4, max =4, message = "Must be a valid 4 digit year")
    private String releaseYear;
    @NotEmpty(message = "You must add Dvd Rating.")
    @Length(max = 50, message = "Rating must be no more than 50 characters in length.")
    private String rating;
    @NotNull
    @Valid
    private Director director;
    @NotNull
    @Valid
    private Studio studio;
    @NotEmpty(message = "You must add Dvd Review.")
    @Length(max = 200, message = "Review must be no more than 200 characters in length.")
    private String review;

    public int getDvdId() {
        return dvdId;
    }

    public void setDvdId(int dvdId) {
        this.dvdId = dvdId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public Studio getStudio() {
        return studio;
    }

    public void setStudio(Studio studio) {
        this.studio = studio;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.dvdId;
        hash = 89 * hash + Objects.hashCode(this.title);
        hash = 89 * hash + Objects.hashCode(this.releaseYear);
        hash = 89 * hash + Objects.hashCode(this.rating);
        hash = 89 * hash + Objects.hashCode(this.director);
        hash = 89 * hash + Objects.hashCode(this.studio);
        hash = 89 * hash + Objects.hashCode(this.review);
        return hash;
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final Dvd other = (Dvd) obj;
//        if (this.dvdId != other.dvdId) {
//            return false;
//        }
//        if (!Objects.equals(this.title, other.title)) {
//            return false;
//        }
//        if (!Objects.equals(this.releaseYear, other.releaseYear)) {
//            return false;
//        }
//        if (!this.rating.equals(other.rating)) {
//            return false;
//        }
//        if (!Objects.equals(this.director, other.director)) {
//            return false;
//        }
//        if (!Objects.equals(this.studio, other.studio)) {
//            return false;
//        }
//        if (!Objects.equals(this.review, other.review)) {
//            return false;
//        }
//        return true;
//    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dvd other = (Dvd) obj;
        if (this.dvdId != other.dvdId) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.releaseYear, other.releaseYear)) {
            return false;
        }
        if (!Objects.equals(this.rating, other.rating)) {
            return false;
        }
        if (!Objects.equals(this.review, other.review)) {
            return false;
        }
        if (!Objects.equals(this.director, other.director)) {
            return false;
        }
        if (!Objects.equals(this.studio, other.studio)) {
            return false;
        }
        return true;
    }
}
