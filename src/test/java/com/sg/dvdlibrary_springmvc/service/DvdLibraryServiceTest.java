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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Roger Brock
 */
public class DvdLibraryServiceTest {

    private DvdLibraryService service;

    public DvdLibraryServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        service = ctx.getBean("dvdLibraryService", DvdLibraryService.class);

        List<Dvd> dvd = service.getAllDVDs();
        for (Dvd d : dvd) {
            service.deleteDVD(d.getDvdId());
        }

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addDVD method, of class DvdLibraryService.
     */
    @Test
    public void testAddDVD() {

        Director director = new Director();
        director.setDirectorName("dirTest");

        Studio studio = new Studio();
        studio.setStudioName("stuTest");

        Dvd dvd = new Dvd();
        dvd.setTitle("Test Title");
        dvd.setReleaseYear("1998");
        dvd.setRating("R");
        dvd.setReview("test review");
        dvd.setDirector(director);
        dvd.setStudio(studio);

        service.addDVD(dvd);

        Dvd fromDao = service.getDVDInfo(dvd.getDvdId());

        assertEquals(dvd, fromDao);

    }

    /**
     * Test of deleteDVD method, of class DvdLibraryService.
     */
    @Test
    public void testDeleteDVD() {
        Director director = new Director();
        director.setDirectorName("dirTest");

        Studio studio = new Studio();
        studio.setStudioName("stuTest");

        Dvd dvd = new Dvd();
        dvd.setTitle("Test Title");
        dvd.setReleaseYear("1998");
        dvd.setRating("R");
        dvd.setReview("test review");
        dvd.setDirector(director);
        dvd.setStudio(studio);

        service.addDVD(dvd);

        Dvd fromDao = service.getDVDInfo(dvd.getDvdId());

        assertEquals(dvd, fromDao);

        service.deleteDVD(fromDao.getDvdId());

        assertNull(service.getDVDInfo(fromDao.getDvdId()));
    }

    /**
     * Test of editDVD method, of class DvdLibraryService.
     */
    @Test
    public void testEditDVD() {
        Director director = new Director();
        director.setDirectorName("dirTest");

        Studio studio = new Studio();
        studio.setStudioName("stuTest");

        Dvd dvd = new Dvd();
        dvd.setTitle("Test Title");
        dvd.setReleaseYear("1998");
        dvd.setRating("R");
        dvd.setReview("test review");
        dvd.setDirector(director);
        dvd.setStudio(studio);

        service.addDVD(dvd);

        Dvd fromDao = service.getDVDInfo(dvd.getDvdId());

        assertEquals(dvd, fromDao);

        fromDao.setTitle("editTest");

        assertNotEquals(fromDao, dvd);

    }

    /**
     * Test of getAllDVDs method, of class DvdLibraryService.
     */
    @Test
    public void testGetAllDVDs() {
        List<Dvd> dvdList = service.getAllDVDs();
        assertEquals(0, dvdList.size());
    }

    /**
     * Test of getAllDVDsBySearch method, of class DvdLibraryService.
     */
    @Test
    public void testGetAllDVDsBySearch() {

        Director director = new Director();
        director.setDirectorName("dirTest");

        Studio studio = new Studio();
        studio.setStudioName("stuTest");

        Dvd dvd = new Dvd();
        dvd.setTitle("Test Title");
        dvd.setReleaseYear("1998");
        dvd.setRating("R");
        dvd.setReview("test review");
        dvd.setDirector(director);
        dvd.setStudio(studio);

        service.addDVD(dvd);

        List<Dvd> fromService = service.getAllDVDsBySearch("mpaaRating", "R");

        String rating = fromService.get(0).getRating();

        assertEquals(rating, "R");

    }

    /**
     * Test of getDVDInfo method, of class DvdLibraryService.
     */
    @Test
    public void testGetDVDInfo() {

        //tested above
    }

    /**
     * Test of setSelectedSearchType method, of class DvdLibraryService.
     */
    @Test
    public void testSetSelectedSearchType() {

        //no test
    }

    /**
     * Test of checkIfStudioExists method, of class DvdLibraryService.
     */
    @Test
    public void testCheckIfStudioExists() {

        //no test
    }

    /**
     * Test of checkIfDirectorExists method, of class DvdLibraryService.
     */
    @Test
    public void testCheckIfDirectorExists() {

        //no test
    }

}
