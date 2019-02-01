/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary_springmvc.dao;

import com.sg.dvdlibrary_springmvc.model.Dvd;
import com.sg.dvdlibrary_springmvc.model.Director;
import com.sg.dvdlibrary_springmvc.model.Studio;
import java.util.List;

/**
 *
 * @author Roger
 */
public interface DvdLibraryDao {

    //DVD
    void addDVD(Dvd dvd);

    void deleteDVD(int dvdId);

    void editDVD(Dvd dvd);

    List<Dvd> getAllDVDs();
    
    List<Dvd> getAllDVDsBySearch(String searchItem, String searchTerm);

    Dvd getDVDInfo(int dvdId);

    //Director
    void addDirector(Director director);

    void editDirector(Director director);

    void deleteDirector(int directorId);

    List<Director> getAllDirectors();
    
    Director getDirector(int directorId);

    //Studio
    void addStudio(Studio studio);

    void editStudio(Studio studio);

    void deleteStudio(int studioId);

    List<Studio> getAllStudios();
    
    Studio getStudio(int studioId);

}
