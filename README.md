CREATE DATABASE BTL;

USE BTL;

CREATE TABLE Participant (
	Participant_ID integer NOT NULL,
    Name varchar(100),
    DOB varchar (10),
    CONSTRAINT PRIMARY KEY(Participant_ID)
);

CREATE TABLE Participant_awards (
	Participant_ID integer NOT NULL REFERENCES Participant(Participant_ID) ,
    Awards varchar(200) NOT NULL,
    CONSTRAINT PRIMARY KEY (Participant_ID,Awards)
);

CREATE TABLE Participant_film_List (
	Participant_ID integer NOT NULL REFERENCES Participant(Participant_ID),
	Film varchar(200) NOT NULL,
    CONSTRAINT PRIMARY KEY (Participant_ID,Film)
);

CREATE TABLE Employee (
	Employee_ID integer NOT NULL,
    E_Name varchar(100),
    DOB varchar(10),
    CONSTRAINT PRIMARY KEY (Employee_ID)
);


CREATE TABLE Movie_Manager (
  M_Employee_ID integer NOT NULL REFERENCES Employee(Employee_ID),
  Film_ID integer NOT NULL,
  Depot_Location varchar(200),
  PRIMARY KEY (M_Employee_ID)
);

CREATE TABLE Rent_Manager (
	R_Employee_ID integer NOT NULL REFERENCES Employee(Employee_ID),
    Rent_ID integer,
    Store_Location varchar(200),
    PRIMARY KEY (R_Employee_ID)
);

CREATE TABLE Customer_Service (
	C_Employee_ID integer NOT NULL REFERENCES Employee(Employee_ID),
    Citizen_ID integer,
    Website varchar(200),
    Hotline varchar(11),
    PRIMARY KEY (C_Employee_ID)

);

CREATE TABLE Movie(
	Film_ID integer NOT NULL,
    M_Employee_ID integer,
    IMDB_rating double,
    Realease_year integer,
    Movie_name varchar(100),
    PRIMARY KEY (Film_ID)
   
);

CREATE TABLE Movie_genre(
	Film_ID integer NOT NULL REFERENCES Movie(Film_ID),
    Genre varchar(20) NOT NULL,
    CONSTRAINT PRIMARY KEY(Film_ID,Genre)
);

CREATE TABLE Play_in(
	Participant_ID integer NOT NULL REFERENCES Participant(Participant_ID),
    Film_ID integer NOT NULL REFERENCES Movie(Film_ID),
    Role varchar (20),
    CONSTRAINT PRIMARY KEY (Participant_ID,Film_ID)
);

CREATE TABLE Customer (
	Citizen_ID integer NOT NULL,
    C_Employee_ID integer,
    C_Name varchar (100),
    DOB varchar(10),
    C_Number int,
    C_Street int,
    C_District int,
    C_City int,
    PRIMARY KEY (Citizen_ID)

);

CREATE TABLE Rents(
	Rent_ID integer NOT NULL,
    Citizen_ID integer,
    Film_ID integer,
    R_Employee_ID integer,
    R_TimeDay varchar(10),
    Fee integer,
    PRIMARY KEY (Rent_ID)
);


INSERT INTO Participant (Participant_ID, Name, DOB)
VALUES
(1, 'Tom Hanks', '1956-07-09'),
(2, 'Meryl Streep', '1949-06-22'),
(3, 'Leonardo DiCaprio', '1974-11-11'),
(4, 'Jennifer Lawrence', '1990-08-04'),
(5, 'Robert Downey Jr.', '1965-04-04');

INSERT INTO Participant_awards (Participant_ID, Awards)
VALUES
(1, 'Academy Award for Best Actor (1994)'),
(1, 'Academy Award for Best Actor (1999)'),
(1, 'Golden Globe Award for Best Actor in a Motion Picture - Drama (1994)'),
(2, 'Academy Award for Best Actress (1980)'),
(2, 'Academy Award for Best Actress (1982)'),
(2, 'Academy Award for Best Actress (2012)'),
(2, 'Golden Globe Award for Best Actress in a Motion Picture - Drama (1980)'),
(2, 'Golden Globe Award for Best Actress in a Motion Picture - Drama (1983)'),
(2, 'Golden Globe Award for Best Actress in a Motion Picture - Comedy or Musical (2003)');

INSERT INTO Participant_film_List (Participant_ID, Film)
VALUES
(1, 'Forrest Gump (1994)'),
(1, 'Saving Private Ryan (1998)'),
(1, 'Cast Away (2000)'),
(2, 'Kramer vs. Kramer (1979)'),
(2, 'Sophie\'s Choice (1982)'),
(2, 'The Devil Wears Prada (2006)'),
(3, 'Titanic (1997)'),
(3, 'The Departed (2006)'),
(3, 'The Wolf of Wall Street (2013)'),
(4, 'Silver Linings Playbook (2012)'),
(4, 'American Hustle (2013)'),
(4, 'Joy (2015)');

INSERT INTO Employee (Employee_ID, E_Name, DOB)
VALUES
(1, 'John Smith', '1970-01-01'),
(2, 'Jane Doe', '1975-01-01'),
(3, 'Peter Jones', '1980-01-01'),
(4, 'Mary Brown', '1985-01-01'),
(5, 'David Williams', '1990-01-01');

INSERT INTO Movie_Manager (M_Employee_ID, Film_ID, Depot_Location)
VALUES
(1, 1, 'Los Angeles, CA'),
(2, 2, 'New York, NY'),
(3, 3, 'Chicago, IL');

INSERT INTO Customer_Service (C_Employee_ID, Citizen_ID, Website, Hotline)
VALUES
(1, 1, 'http://www.namhehe.com', '1900 6600'),
(2, 2, 'http://www.namhihi.com', '1900 6868'),
(3, 3, 'http://www.namhaha.com', '1900 8866');

INSERT INTO Movie (Film_ID, M_Employee_ID, IMDB_rating, Realease_year, Movie_name)
VALUES
(1, 1, 8.8, 1994, 'Forrest Gump'),
(2, 2, 8.3, 1997, 'Titanic'),
(3, 3, 8.0, 1999, 'The Matrix');

INSERT INTO Play_in (Participant_ID, Film_ID, Role)
VALUES
(1, 1, 'Forrest Gump'),
(1, 2, 'Captain Miller'),
(1, 3, 'Chuck Noland'),
(2, 4, 'Joanna Kramer'),
(2, 5, 'Sophie Zawistowski'),
(2, 6, 'Miranda Priestly'),
(3, 7, 'Jack Dawson'),
(3, 8, 'Billy Costigan'),
(3, 9, 'Jordan Belfort'),
(4, 10, 'Tiffany Maxwell'),
(4, 11, 'Rosalyn Rosenfeld'),
(4, 12, 'Joy Mangano');

INSERT INTO Customer (Citizen_ID, C_Employee_ID, C_Name, DOB, C_Number, C_Street, C_District, C_City)
VALUES
(1, 1, 'John Doe', '1985-01-01', 123, 456, 789, 12345),
(2, 2, 'Jane Doe', '1980-01-01', 456, 789, 123, 54321),
(3, 3, 'Michael Jackson', '1995-01-01', 789, 123, 456, 43210);

INSERT INTO Movie_genre (Film_ID, Genre)
VALUES
(1, 'Drama'),
(1, 'Comedy'),
(2, 'Romance'),
(2, 'Drama'),
(3, 'Action'),
(3, 'Science Fiction');

INSERT INTO Rent_Manager (R_Employee_ID, Rent_ID, Store_Location)
VALUES
(1, 1, 'Los Angeles, CA'),
(2, 2, 'New York, NY'),
(3, 3, 'Chicago, IL');

INSERT INTO Rents (Rent_ID, Film_ID, Citizen_ID, R_Employee_ID, R_TimeDay, Fee)
VALUES 
(1, 1, 1, 1, '2023/10/12', 10),
(2, 2, 2, 2, '2023/09/11', 20),
(3, 3, 3, 3, '2022/10/12', 15),
(4, 1, 1, 1, '2023/01/23', 12),
(5, 2, 2, 2, '2023/09/30', 25),
(6, 3, 3, 3, '2023/02/28', 18),
(7, 1, 1, 1, '2023/10/12', 10);

ALTER TABLE Movie_Manager
ADD FOREIGN KEY (Film_ID) REFERENCES Movie(Film_ID) ON UPDATE CASCADE;

ALTER TABLE Rent_Manager
ADD FOREIGN KEY (Rent_ID) REFERENCES Rents(Rent_ID) ON UPDATE CASCADE;

ALTER TABLE customer_service
ADD FOREIGN KEY (Citizen_ID) REFERENCES Customer(Citizen_ID) ON UPDATE CASCADE;

ALTER TABLE Movie
ADD FOREIGN KEY (M_Employee_ID) REFERENCES Movie_manager(M_Employee_ID) ON UPDATE CASCADE;

ALTER TABLE Customer
ADD FOREIGN KEY (C_Employee_ID) REFERENCES Customer_Service(C_Employee_ID) ON UPDATE CASCADE;

ALTER TABLE Rents
ADD FOREIGN KEY (Film_ID) REFERENCES Movie(Film_ID) ON UPDATE CASCADE,
ADD FOREIGN KEY (Citizen_ID) REFERENCES Customer(Citizen_ID) ON UPDATE CASCADE,
ADD FOREIGN KEY (R_Employee_ID) REFERENCES rent_manager(R_Employee_ID) ON UPDATE CASCADE;
