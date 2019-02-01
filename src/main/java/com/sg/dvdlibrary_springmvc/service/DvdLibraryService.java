/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary_springmvc.service;

import com.sg.dvdlibrary_springmvc.model.Director;
import com.sg.dvdlibrary_springmvc.model.Dvd;
import com.sg.dvdlibrary_springmvc.model.Studio;
import java.util.List;

/**
 *
 * @author Roger Brock
 */
public interface DvdLibraryService {

    //DVD
    void addDVD(Dvd dvd);

    void deleteDVD(int dvdId);

    void editDVD(Dvd dvd);

    List<Dvd> getAllDVDs();

    List<Dvd> getAllDVDsBySearch(String searchItem, String searchTerm);

    Dvd getDVDInfo(int dvdId);

    //Other methods
    String setSelectedSearchType(String searchType);

    void checkIfStudioExists(Dvd dvd);

    void checkIfDirectorExists(Dvd dvd);
}
