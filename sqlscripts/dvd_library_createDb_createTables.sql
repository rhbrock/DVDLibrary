drop database if exists dvd_library;

create database dvd_library;

use dvd_library;

CREATE TABLE IF NOT EXISTS dvd (
 dvdId int NOT NULL AUTO_INCREMENT,
 dvdTitle varChar(50) not null,
 /*dvdReleaseYear year(4) not null,*/
 dvdReleaseYear varChar(10) not null,
 mpaaRating enum('G', 'PG', 'PG13', 'R', 'NC17') null,
 directorId int not null,
 studioId int not null,
 review text,
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


--
-- Constraints for table dvd
--

 ALTER TABLE dvd ADD CONSTRAINT fk_dvd_director FOREIGN KEY (directorId) REFERENCES director(directorId) ON DELETE NO ACTION;
 ALTER TABLE dvd ADD CONSTRAINT fk_dvd_studio FOREIGN KEY (studioId) REFERENCES studio(studioId) ON DELETE NO ACTION;
 
 
 insert into director (directorName) values ("Steven Speilburg"), ("Peter Jackson"), ("Steven King");
 insert into studio (studioName) values ("Pinewood"), ("Universal"), ("Horror");
 insert into dvd (dvdTitle, dvdReleaseYear, mpaaRating, directorId, StudioId, review)
 values ("Jurassic Park", '2000', "PG13", 1, 1, "Great movie!"), ("Jurrasic Park 2", '2005', "PG13", 1, 1, "Not as good as the first."), 
 ("Bad Taste", '1984', "R", 2, 2, "Classic!"), ("Good Taste", '1987', "R", 2, 2, "Not as good as the first."), ("It", '2017', "R", 2, 2, "Great."),
 ("It2", '2019', "R", 2, 1, "Not as good as the first.");


