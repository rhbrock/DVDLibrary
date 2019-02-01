/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary_springmvc.controller;

import com.sg.dvdlibrary_springmvc.model.Dvd;
import com.sg.dvdlibrary_springmvc.model.DvdSearch;
import com.sg.dvdlibrary_springmvc.service.DvdLibraryService;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Roger Brock
 */
@Controller
public class DvdLibraryController {

    private DvdLibraryService service;

    @Inject
    public DvdLibraryController(DvdLibraryService service) {
        this.service = service;
    }
    

    @RequestMapping(value = "/displayMainPage", method = RequestMethod.GET)
    public String getAllDvds(HttpServletRequest request, Model model) {

        //load items
        List<Dvd> dvds = service.getAllDVDs();
        model.addAttribute("dvds", dvds);

        DvdSearch dvdsearch = new DvdSearch();
        model.addAttribute("dvdsearch", dvdsearch);

        return "mainPage";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(@Valid @ModelAttribute("dvdsearch") DvdSearch search, BindingResult result,
            Model model, HttpServletRequest request) {

        String searchType = request.getParameter("searchType");
        String selectedSearchType = service.setSelectedSearchType(searchType);

        if (result.hasErrors()) {
            List<Dvd> dvds = service.getAllDVDs();
            model.addAttribute("dvds", dvds);

            model.addAttribute("searchType", searchType);
            model.addAttribute("selectedSearchType", selectedSearchType);

            return "searchDvds";
        }


        List<Dvd> dvds = service.getAllDVDsBySearch(search.getSearchType(), search.getSearchString());
        model.addAttribute("dvds", dvds);

        if (dvds.isEmpty()) {

            model.addAttribute("searchType", searchType);
            model.addAttribute("selectedSearchType", selectedSearchType);

            return "noSearchMatch";
        }

        selectedSearchType = "Search Type";
        model.addAttribute("selectedSearchType", selectedSearchType);

        return "searchDvds";
    }

    @RequestMapping(value = "/dvdInfo", method = RequestMethod.GET)
    public String getDvdInfo(HttpServletRequest request, Model model) {

        String id = request.getParameter("dvdId");
        int dvdId = Integer.parseInt(id);

        Dvd dvd = service.getDVDInfo(dvdId);

        model.addAttribute("dvd", dvd);

        return "dvdInfo";
    }

    @RequestMapping(value = "/displayCreateForm", method = RequestMethod.GET)
    public String displayCreateForm(Model model) {

        Dvd dvd = new Dvd();

        model.addAttribute("dvd", dvd);

        return "createDvd";

    }

    @RequestMapping(value = "/createDvd", method = RequestMethod.POST)
    public String createDvd(@Valid @ModelAttribute("dvd") Dvd dvd, BindingResult result) {

        if (result.hasErrors()) {
            return "createDvd";
        }

        dvd.setTitle(dvd.getTitle());
//        String releaseDate = request.getParameter("releaseYear");
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
//        LocalDate date = LocalDate.parse(releaseDate, formatter);
        dvd.setReleaseYear(dvd.getReleaseYear());
        dvd.setRating(dvd.getRating());
        dvd.setReview(dvd.getReview());

        // persist the new dvd.  Service will check for existing studio
        // and director
        service.addDVD(dvd);

        return "redirect:displayMainPage";
    }

    @RequestMapping(value = "/deleteDvd", method = RequestMethod.GET)
    public String deleteDvd(HttpServletRequest request) {

        String id = request.getParameter("dvdId");
        int dvdId = Integer.parseInt(id);

        service.deleteDVD(dvdId);

        return "redirect:displayMainPage";

    }

    @RequestMapping(value = "/displayEditForm", method = RequestMethod.GET)
    public String displayEditForm(HttpServletRequest request, Model model) {

        String id = request.getParameter("dvdId");
        int dvdId = Integer.parseInt(id);
        Dvd dvd = service.getDVDInfo(dvdId);
        model.addAttribute("dvd", dvd);

        return "editDvd";

    }

    @RequestMapping(value = "/editDvd", method = RequestMethod.POST)
    public String editDvd(@Valid @ModelAttribute("dvd") Dvd dvd, BindingResult result) {

        if (result.hasErrors()) {
            return "editDvd";
        }

        // persist the new dvd.  Service will check for existing studio
        // and director
        service.editDVD(dvd);

        return "redirect:displayMainPage";

    }
}
