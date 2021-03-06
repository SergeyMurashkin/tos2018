DROP DATABASE IF EXISTS ttschool;
CREATE DATABASE ttschool;
USE ttschool;

CREATE TABLE subjects(
id INT NOT NULL AUTO_INCREMENT,
name VARCHAR(250) NOT NULL,
PRIMARY KEY (id),
UNIQUE KEY name (name)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE schools(
id INT NOT NULL AUTO_INCREMENT,
name VARCHAR(250) NOT NULL,
year INT NOT NULL,
PRIMARY KEY (id),
UNIQUE KEY name_year(name,year)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE groups(
id INT NOT NULL AUTO_INCREMENT,
schoolId INT NOT NULL,
name VARCHAR(250) NOT NULL,
room VARCHAR(250) NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (schoolId) REFERENCES schools(id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE trainees (
id INT NOT NULL AUTO_INCREMENT,
groupId INT DEFAULT NULL,
firstName VARCHAR(250) NOT NULL,
lastName VARCHAR(250) NOT NULL,
rating INT NOT NULL,
PRIMARY KEY (id),
KEY firstName(firstName),
KEY lastName(lastName),
KEY rating(rating),
FOREIGN KEY (groupId) REFERENCES groups(id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE group_subject (
id INT NOT NULL AUTO_INCREMENT,
groupId INT NOT NULL,
subjectId INT NOT NULL,
PRIMARY KEY (id),
KEY groupId(groupId),
KEY subjectId(subjectId),
UNIQUE KEY groupId_subjectId(groupId,subjectId),
FOREIGN KEY (groupId) REFERENCES groups(id) ON DELETE CASCADE,
FOREIGN KEY (subjectId) REFERENCES subjects(id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8;