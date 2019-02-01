/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary_springmvc.dao;

import com.sg.dvdlibrary_springmvc.model.Director;
import com.sg.dvdlibrary_springmvc.model.Dvd;
import com.sg.dvdlibrary_springmvc.model.Studio;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Roger Brock
 */
public class DvdLibraryDaoTest {

    private DvdLibraryDao dao;

    public DvdLibraryDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        dao = ctx.getBean("dvdLibraryDao", DvdLibraryDao.class);

        List<Dvd> dvd = dao.getAllDVDs();
        for (Dvd d : dvd) {
            dao.deleteDVD(d.getDvdId());
        }

        List<Director> dir = dao.getAllDirectors();
        for (Director d : dir) {
            dao.deleteDirector(d.getDirectorId());
        }

        List<Studio> st = dao.getAllStudios();
        for (Studio s : st) {
            dao.deleteStudio(s.getStudioId());
        }

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAddGetDirector() {
        Director dir = new Director();
        dir.setDirectorName("Spielburg");
        dao.addDirector(dir);

        Director fromDao = dao.getDirector(dir.getDirectorId());

        assertEquals(dir, fromDao);
    }

    @Test
    public void deleteDirector() {
        Director dir = new Director();
        dir.setDirectorName("Spielburg");
        dao.addDirector(dir);

        Director fromDao = dao.getDirector(dir.getDirectorId());

        assertEquals(dir, fromDao);

        dao.deleteDirector(dir.getDirectorId());

        assertNull(dao.getDirector(dir.getDirectorId()));
    }

    @Test
    public void testAddGetStudio() {
        Studio st = new Studio();
        st.setStudioName("Pinewood");
        dao.addStudio(st);

        Studio fromDao = dao.getStudio(st.getStudioId());

        assertEquals(st, fromDao);
    }

    @Test
    public void deleteStudio() {
        Studio st = new Studio();
        st.setStudioName("Pinewood");
        dao.addStudio(st);

        Studio fromDao = dao.getStudio(st.getStudioId());

        assertEquals(st, fromDao);

        dao.deleteStudio(st.getStudioId());

        assertNull(dao.getStudio(st.getStudioId()));
    }

    @Test
    public void testAddGetDvd() {
        Director dir = new Director();
        dir.setDirectorName("Spielburg");
        dao.addDirector(dir);

        Studio st = new Studio();
        st.setStudioName("Pinewood");
        dao.addStudio(st);

        Dvd d = new Dvd();
        d.setTitle("TestTitle");
//        d.setReleaseYear(LocalDate.parse("2018-09-18",
//                DateTimeFormatter.ISO_DATE));
        d.setReleaseYear("2018");
        d.setRating("G");
        d.setDirector(dir);
        d.setStudio(st);
        d.setReview("This is a review of this Dvd.");

        dao.addDVD(d);

        Dvd fromDao = dao.getDVDInfo(d.getDvdId());

        assertEquals(d, fromDao);
    }

    @Test
    public void deleteDvd() {
        Director dir = new Director();
        dir.setDirectorName("Spielburg");
        dao.addDirector(dir);

        Studio st = new Studio();
        st.setStudioName("Pinewood");
        dao.addStudio(st);

        Dvd d = new Dvd();
        d.setTitle("TestTitle");
//        d.setReleaseDate(LocalDate.parse("2018-09-18",
//                DateTimeFormatter.ISO_DATE));
        d.setReleaseYear("2018");
        d.setRating("G");
        d.setDirector(dir);
        d.setStudio(st);
        d.setReview("This is a review of this Dvd.");

        dao.addDVD(d);

        Dvd fromDao = dao.getDVDInfo(d.getDvdId());

        assertEquals(d, fromDao);

        dao.deleteDVD(d.getDvdId());

        assertNull(dao.getDVDInfo(d.getDvdId()));
    }

    @Test
    public void testGetAllDvds() {
        Director dir = new Director();
        dir.setDirectorName("Spielburg");
        dao.addDirector(dir);

        Studio st = new Studio();
        st.setStudioName("Pinewood");
        dao.addStudio(st);

        Dvd d = new Dvd();
        d.setTitle("TestTitle");
//        d.setReleaseDate(LocalDate.parse("2018-09-18",
//                DateTimeFormatter.ISO_DATE));
        d.setReleaseYear("2018");
        d.setRating("G");
        d.setDirector(dir);
        d.setStudio(st);
        d.setReview("This is a review of this Dvd.");

        dao.addDVD(d);

        Dvd fromDao = dao.getDVDInfo(d.getDvdId());

        assertEquals(d, fromDao);

        List<Dvd> dvds = dao.getAllDVDs();
        
        int x = dvds.size();
        
        assertEquals(1, x);

    }
}
