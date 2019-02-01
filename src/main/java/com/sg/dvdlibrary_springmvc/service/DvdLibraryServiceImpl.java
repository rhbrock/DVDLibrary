/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary_springmvc.service;

import com.sg.dvdlibrary_springmvc.dao.DvdLibraryDao;
import com.sg.dvdlibrary_springmvc.model.Director;
import com.sg.dvdlibrary_springmvc.model.Dvd;
import com.sg.dvdlibrary_springmvc.model.Studio;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Roger Brock
 */
public class DvdLibraryServiceImpl implements DvdLibraryService {

    private DvdLibraryDao dao;

    @Inject
    public DvdLibraryServiceImpl(DvdLibraryDao dao) {
        this.dao = dao;
    }

    @Override
    public void addDVD(Dvd dvd) {

        checkIfStudioExists(dvd);
        checkIfDirectorExists(dvd);

        dao.addDVD(dvd);
    }

    @Override
    public void deleteDVD(int dvdId) {
        dao.deleteDVD(dvdId);
    }

    @Override
    public void editDVD(Dvd dvd) {

        checkIfStudioExists(dvd);
        checkIfDirectorExists(dvd);

        dao.editDVD(dvd);
    }

    @Override
    public List<Dvd> getAllDVDs() {
        return dao.getAllDVDs();
    }

    @Override
    public List<Dvd> getAllDVDsBySearch(String searchType, String searchTerm) {

        if (searchType.equalsIgnoreCase("directorId")) {

            List<Dvd> dvds = dao.getAllDVDs();
            int foundId = 0;

            for (Dvd dvd : dvds) {
                if (dvd.getDirector().getDirectorName().equalsIgnoreCase(searchTerm)) {
                    foundId = dvd.getDirector().getDirectorId();
                    break;
                }
            }

            String foundIdString = Integer.toString(foundId);
            searchTerm = foundIdString;
        }

        return dao.getAllDVDsBySearch(searchType, searchTerm);
    }

    @Override
    public Dvd getDVDInfo(int dvdId) {
        return dao.getDVDInfo(dvdId);
    }

    @Override
    public String setSelectedSearchType(String searchType) {

        String selectedSearchType = "";

        switch (searchType) {
            case "dvdTitle":
                selectedSearchType = "Title";
                break;
            case "dvdReleaseYear":
                selectedSearchType = "Release Year";
                break;
            case "mpaaRating":
                selectedSearchType = "Rating";
                break;
            case "director":
                selectedSearchType = "Director";
                break;
        }

        return selectedSearchType;
    }

    @Override
    public void checkIfStudioExists(Dvd dvd) {
        List<Studio> studioList = dao.getAllStudios();
        String studioName = dvd.getStudio().getStudioName();

        Studio studio = new Studio();

        int studioCount = studioList.size();

        for (Studio s : studioList) {

            if (!s.getStudioName().equalsIgnoreCase(studioName)) {
                studioCount--;
            } else {
                studio = s;
                dvd.setStudio(studio);
                break;
            }
        }

        if (studioCount == 0) {
            studio.setStudioName(studioName);
            dao.addStudio(studio);
            dvd.setStudio(studio);
        }
    }

    @Override
    public void checkIfDirectorExists(Dvd dvd) {
        List<Director> directorList = dao.getAllDirectors();
        String directorName = dvd.getDirector().getDirectorName();

        Director director = new Director();

        int directorCount = directorList.size();

        for (Director d : directorList) {

            if (!d.getDirectorName().equalsIgnoreCase(directorName)) {
                directorCount--;
            } else {
                director = d;
                dvd.setDirector(director);
                break;
            }
        }

        if (directorCount == 0) {
            director.setDirectorName(directorName);
            dao.addDirector(director);
            dvd.setDirector(director);
        }
    }

}
