drop database if exists dvd_library_test;

create database dvd_library_test;

use dvd_library_test;

CREATE TABLE IF NOT EXISTS dvd (
 dvdId int NOT NULL AUTO_INCREMENT,
 dvdTitle varChar(50) not null,
 dvdReleaseDate date not null,
 mpaaRating enum('G', 'PG', 'PG13', 'R', 'NC17') null,
 directorId int not null,
 studioId int not null,
 PRIMARY KEY (dvdId)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS director (
 directorId int NOT NULL AUTO_INCREMENT,
 directorName varChar(30) not null,
 PRIMARY KEY (directorId)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS studio (
 studioId int NOT NULL AUTO_INCREMENT,
 studioName varChar(50) not null,
 PRIMARY KEY (studioId)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS dvdreview (
 dvdReviewId int NOT NULL AUTO_INCREMENT,
 dvdReview text null,
 dvdId int not null,
 PRIMARY KEY (dvdReviewId)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Constraints for table dvd
--

 ALTER TABLE dvd ADD CONSTRAINT fk_dvd_director FOREIGN KEY (directorId) REFERENCES director(directorId) ON DELETE NO ACTION;
 ALTER TABLE dvd ADD CONSTRAINT fk_dvd_studio FOREIGN KEY (studioId) REFERENCES studio(studioId) ON DELETE NO ACTION;
 
 --
-- Constraints for dvdreview table
--
ALTER TABLE dvdreview ADD CONSTRAINT fk_dvdreview_dvd FOREIGN KEY (dvdId) REFERENCES dvd(dvdId) ON DELETE NO ACTION;


