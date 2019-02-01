/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary_springmvc.dao;

import com.sg.dvdlibrary_springmvc.model.Director;
import com.sg.dvdlibrary_springmvc.model.Dvd;
import com.sg.dvdlibrary_springmvc.model.Studio;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Roger Brock
 */
public class DvdLibraryDaoDbImpl implements DvdLibraryDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //PREPARED STATEMENTS
    //DVD
    private static final String SQL_INSERT_DVD
            = "insert into dvd "
            + "(dvdTitle, dvdReleaseYear, mpaaRating, directorId, studioId, review) "
            + "values (?,?,?,?,?,?)";

    private static final String SQL_DELETE_DVD
            = "delete from dvd where dvdId = ?";

    private static final String SQL_UPDATE_DVD
            = "update dvd set dvdTitle = ?, dvdReleaseYear = ?, "
            + "mpaaRating = ?, directorId = ?, studioId = ?, review = ? "
            + "where dvdId = ?";

    private static final String SQL_SELECT_DVD
            = "select * from dvd where dvdId = ?";

    private static final String SQL_SELECT_ALL_DVDS
            = "select * from dvd order by dvdTitle";

    //DIRECTOR
    private static final String SQL_INSERT_DIRECTOR
            = "insert into director "
            + "(directorName) "
            + "values (?)";

    private static final String SQL_DELETE_DIRECTOR
            = "delete from director where directorId = ?";

    private static final String SQL_UPDATE_DIRECTOR
            = "update director set directorName = ? "
            + "where directorId = ?";

    private static final String SQL_SELECT_DIRECTOR
            = "select * from director where directorId = ?";

    private static final String SQL_SELECT_ALL_DIRECTOR
            = "select * from director";

    //Studio
    private static final String SQL_INSERT_STUDIO
            = "insert into studio "
            + "(studioName) "
            + "values (?)";

    private static final String SQL_DELETE_STUDIO
            = "delete from studio where studioId = ?";

    private static final String SQL_UPDATE_STUDIO
            = "update studio set studioName = ? "
            + "where studioId = ?";

    private static final String SQL_SELECT_STUDIO
            = "select * from studio where studioId = ?";

    private static final String SQL_SELECT_ALL_STUDIO
            = "select * from studio";

//    RETURNED LISTS BY IDS
//    DvdByDirectorId
//    private static final String SQL_SELECT_DIRECTOR_BY_DIRECTOR_ID
//            = "select d.directorId, d.directorName "
//            + "from director d join dvd dvd on d.directorId = dvd.directorId "
//            + "where d.directorId = ?";
//
//    //DvdByStudioId
//    private static final String SQL_SELECT_STUDIO_BY_STUDIO_ID
//            = "select s.studioId, s.studioName "
//            + "from studio d join dvd dvd on s.studioId = dvd.studioId "
//            + "where s.studioId = ?";
    // GET DIRECTOR AND STUDIO BY DVDID
    private static final String SQL_SELECT_DIRECTOR_BY_DVDID
            = "select dir.directorId, dir.directorName "
            + "from dvd d join director dir on d.directorId = dir.directorId "
            + "where d.dvdId = ?";

    private static final String SQL_SELECT_STUDIO_BY_DVDID
            = "select s.studioId, s.studioName "
            + "from dvd d join studio s on d.studioId = s.studioId "
            + "where d.dvdId = ?";

    //DVD METHODS
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addDVD(Dvd dvd) {
        jdbcTemplate.update(SQL_INSERT_DVD,
                dvd.getTitle(),
                dvd.getReleaseYear(),
                dvd.getRating(),
                dvd.getDirector().getDirectorId(),
                dvd.getStudio().getStudioId(),
                dvd.getReview());

        dvd.setDvdId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class));
    }

    @Override
    public void deleteDVD(int dvdId) {
        jdbcTemplate.update(SQL_DELETE_DVD, dvdId);
    }

    @Override
    public void editDVD(Dvd dvd) {
        jdbcTemplate.update(SQL_UPDATE_DVD,
                dvd.getTitle(),
                dvd.getReleaseYear(),
                dvd.getRating(),
                dvd.getDirector().getDirectorId(),
                dvd.getStudio().getStudioId(),
                dvd.getReview(),
                dvd.getDvdId());
    }

    @Override
    public List<Dvd> getAllDVDs() {
        List<Dvd> dvd
                = jdbcTemplate.query(SQL_SELECT_ALL_DVDS, new DvdMapper());

        for (Dvd d : dvd) {
            d.setDirector(findDirectorOfDvd(d.getDvdId()));
            d.setStudio(findStudioOfDvd(d.getDvdId()));
        }
        return dvd;
    }

    @Override
    public List<Dvd> getAllDVDsBySearch(String searchType, String searchTerm) {
        List<Dvd> dvd
                = jdbcTemplate.query("select * "
                        + "from dvd where " + searchType +  "= ?", new DvdMapper(),
                        searchTerm);

        for (Dvd d : dvd) {
            d.setDirector(findDirectorOfDvd(d.getDvdId()));
            d.setStudio(findStudioOfDvd(d.getDvdId()));
        }
        return dvd;
    }

    @Override
    public Dvd getDVDInfo(int dvdId) {
        try {
            Dvd d = jdbcTemplate.queryForObject(SQL_SELECT_DVD,
                    new DvdMapper(),
                    dvdId);

//            Director dir = jdbcTemplate.queryForObject(SQL_SELECT_DIRECTOR_BY_DVDID,
//                    new DirectorMapper(),
//                    d.getDvdId());
//            Studio stu = jdbcTemplate.queryForObject(SQL_SELECT_STUDIO_BY_DVDID,
//                    new StudioMapper(),
//                    d.getDvdId());
            d.setDirector(findDirectorOfDvd(dvdId));
            d.setStudio(findStudioOfDvd(dvdId));

            return d;

        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    //DIRECTOR METHODS
    @Override
    public void addDirector(Director director) {
        jdbcTemplate.update(SQL_INSERT_DIRECTOR,
                director.getDirectorName());

        director.setDirectorId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class));
    }

    @Override
    public void editDirector(Director director) {
        jdbcTemplate.update(SQL_UPDATE_DIRECTOR,
                director.getDirectorName());

    }

    @Override
    public void deleteDirector(int directorId) {
        jdbcTemplate.update(SQL_DELETE_DIRECTOR, directorId);
    }

    @Override
    public List<Director> getAllDirectors() {
        return jdbcTemplate.query(SQL_SELECT_ALL_DIRECTOR, new DirectorMapper());
    }

    @Override
    public Director getDirector(int directorId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_DIRECTOR,
                    new DirectorMapper(),
                    directorId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    //STUDIO METHODS
    @Override
    public void addStudio(Studio studio) {
        jdbcTemplate.update(SQL_INSERT_STUDIO,
                studio.getStudioName());

        studio.setStudioId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class));
    }

    @Override
    public void editStudio(Studio studio) {
        jdbcTemplate.update(SQL_UPDATE_STUDIO,
                studio.getStudioName());
    }

    @Override
    public void deleteStudio(int studioId) {
        jdbcTemplate.update(SQL_DELETE_STUDIO, studioId);
    }

    @Override
    public List<Studio> getAllStudios() {
        return jdbcTemplate.query(SQL_SELECT_ALL_STUDIO, new StudioMapper());
    }

    @Override
    public Studio getStudio(int studioId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_STUDIO,
                    new StudioMapper(),
                    studioId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    //HELPER METHODS
    private Director findDirectorOfDvd(int dvdId) {
        return jdbcTemplate.queryForObject(SQL_SELECT_DIRECTOR_BY_DVDID,
                new DirectorMapper(),
                dvdId);
    }

    private Studio findStudioOfDvd(int dvdId) {
        return jdbcTemplate.queryForObject(SQL_SELECT_STUDIO_BY_DVDID,
                new StudioMapper(),
                dvdId);
    }

    //MAPPING METHODS
    private static final class DvdMapper implements RowMapper<Dvd> {

        @Override
        public Dvd mapRow(ResultSet rs, int i) throws SQLException {

            Dvd d = new Dvd();
            d.setDvdId(rs.getInt("dvdId"));
            d.setTitle(rs.getString("dvdTitle"));

//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
//            String releaseDate = rs.getTimestamp("dvdReleaseYear").toString();
//            LocalDate date = LocalDate.parse(releaseDate, formatter);
            d.setReleaseYear(rs.getString("dvdReleaseYear"));

            d.setRating(rs.getString("mpaaRating"));
            d.setReview(rs.getString("review"));

            return d;
        }
    }

    private static final class DirectorMapper implements RowMapper<Director> {

        @Override
        public Director mapRow(ResultSet rs, int i) throws SQLException {
            Director d = new Director();
            d.setDirectorId(rs.getInt("directorId"));
            d.setDirectorName(rs.getString("directorName"));

            return d;

        }
    }

    private static final class StudioMapper implements RowMapper<Studio> {

        @Override
        public Studio mapRow(ResultSet rs, int i) throws SQLException {
            Studio s = new Studio();
            s.setStudioId(rs.getInt("studioId"));
            s.setStudioName(rs.getString("studioName"));

            return s;
        }
    }

}
