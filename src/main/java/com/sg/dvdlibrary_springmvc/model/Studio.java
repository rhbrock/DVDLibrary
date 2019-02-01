/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary_springmvc.model;

import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Roger Brock
 */
public class Studio {

    private int studioId;
    @NotEmpty(message = "You must add Dvd Studio Name.")
    @Length(max = 50, message = "Name must be no more than 50 characters in length.")
    private String studioName;

    public int getStudioId() {
        return studioId;
    }

    public void setStudioId(int studioId) {
        this.studioId = studioId;
    }

    public String getStudioName() {
        return studioName;
    }

    public void setStudioName(String studioName) {
        this.studioName = studioName;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.studioId;
        hash = 71 * hash + Objects.hashCode(this.studioName);
        return hash;
    }

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
        final Studio other = (Studio) obj;
        if (this.studioId != other.studioId) {
            return false;
        }
        if (!Objects.equals(this.studioName, other.studioName)) {
            return false;
        }
        return true;
    }

}
