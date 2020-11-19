CREATE TABLE Mokykla (
	mokyklos_kodas		INTEGER		NOT NULL,
	adresas				VARCHAR(32)	NOT NULL,
	pavadinimas			VARCHAR(50)	NOT NULL,
	direktorius			VARCHAR(40)	NOT NULL,
	mokiniu_skaicius	SMALLINT 	NOT NULL 	DEFAULT 0,
	UNIQUE (pavadinimas),
	PRIMARY KEY (mokyklos_kodas)
);

CREATE TABLE Dalykas (
	dalyko_nr			SERIAL		NOT NULL,
	pavadinimas			VARCHAR(30)	NOT NULL,
	mokytoju_skaicius	SMALLINT	NOT NULL	DEFAULT 0,
	UNIQUE (pavadinimas),
	PRIMARY KEY (dalyko_nr)
);

CREATE TABLE Mokytojas (
	mokytojo_nr		INTEGER		NOT NULL,
	mokykla			VARCHAR(50)	NOT NULL,
	vardas			CHAR(12)	NOT NULL,
	pavarde			VARCHAR(20)	NOT NULL,
	dalykas			VARCHAR(30)	NOT NULL,
	PRIMARY KEY (mokytojo_nr),
	CONSTRAINT iMokyklaMokytojas		FOREIGN KEY (mokykla)	REFERENCES Mokykla(pavadinimas)	ON DELETE RESTRICT ON UPDATE RESTRICT
);

ALTER TABLE Mokytojas ADD CONSTRAINT iDalykaMokytojas
FOREIGN KEY (dalykas)	REFERENCES Dalykas(pavadinimas) ON DELETE RESTRICT ON UPDATE RESTRICT;

CREATE TABLE Klase (
    pavadinimas					CHAR(3)    	NOT NULL		CONSTRAINT KlasesPavadinimas
															CHECK (pavadinimas ~ '^([1-9]|1[0-2])[A-Z]$'),
    mokyklos_pavadinimas		VARCHAR(50) NOT NULL,
    PRIMARY KEY (pavadinimas),
	CONSTRAINT iMokyklaKlase			FOREIGN KEY (mokyklos_pavadinimas)		REFERENCES Mokykla(pavadinimas)		ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE Globejas (
    AK					CHAR(11)	NOT NULL 	CONSTRAINT GlobejoAsmensKodas
												CHECK (AK ~ '^\d{11}$'),
	vardas				CHAR(12)    NOT NULL,
    pavarde     		VARCHAR(20) NOT NULL,
    PRIMARY KEY (AK)
);

CREATE TABLE Mokinys (
	mokinio_nr			INTEGER		NOT NULL,
	AK   				CHAR(11)	NOT NULL 	CONSTRAINT MokinioAsmensKodas
												CHECK (AK ~ '^\d{11}$'),
    vardas				CHAR(12)	NOT NULL,
    pavarde				VARCHAR(20) NOT NULL,
    gimimo_data			DATE    	NOT NULL 	CONSTRAINT GimimoData 
												CHECK(gimimo_data > '2000-01-01' AND gimimo_data < CURRENT_DATE + INTERVAL '4 years'),
    adresas   			VARCHAR(32) NOT NULL,
    globejo_AK       	CHAR(11)	NOT NULL 	CONSTRAINT AsmensKodas
												CHECK (globejo_AK ~ '^\d{11}$'),						
	klases_pavadinimas	CHAR(3) 	NOT NULL,
    PRIMARY KEY (mokinio_nr),
	CONSTRAINT iKlaseMokinys	FOREIGN KEY (klases_pavadinimas)	REFERENCES Klase(pavadinimas)	ON DELETE RESTRICT ON UPDATE RESTRICT
);

ALTER TABLE Mokinys ADD CONSTRAINT iGlobejaMokinys
FOREIGN KEY (globejo_AK)			REFERENCES Globejas(AK)			ON DELETE RESTRICT ON UPDATE RESTRICT;

CREATE TABLE Pazymys (
	pazymio_nr		SERIAL		NOT NULL,
	pazymys			SMALLINT	NOT NULL	CONSTRAINT PazymioDydis
											CHECK (pazymys BETWEEN 1 AND 10),
	mokinys			INTEGER		NOT NULL,
	dalykas			VARCHAR(30)	NOT NULL,
	data 			DATE 		NOT NULL	DEFAULT CURRENT_DATE,
	laikas 			TIME 		NOT NULL	DEFAULT LOCALTIME(0),
	PRIMARY KEY (pazymio_nr),
	CONSTRAINT iMokiniPazymys	FOREIGN KEY (mokinys) 	REFERENCES Mokinys(mokinio_nr)	ON DELETE CASCADE ON UPDATE CASCADE
);

ALTER TABLE Pazymys ADD CONSTRAINT iDalykaPazymys
FOREIGN KEY (dalykas)	REFERENCES Dalykas(pavadinimas) ON DELETE RESTRICT ON UPDATE RESTRICT;

CREATE TABLE Pastaba (
    pastabos_nr			INTEGER     NOT NULL,
    mokinys				INTEGER    	NOT NULL,
    data 				DATE 		NOT NULL	DEFAULT CURRENT_DATE,
	laikas 				TIME 		NOT NULL	DEFAULT LOCALTIME(0),
    priezastis			VARCHAR(50),
    PRIMARY KEY (pastabos_nr),
    CONSTRAINT iMokiniPastaba 			FOREIGN KEY	(mokinys)	REFERENCES Mokinys(mokinio_nr)	ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE UNIQUE INDEX Mokinys_AK_index 			ON Mokinys(AK);
CREATE INDEX Mokinys_klases_pavadinimas_index 	ON Mokinys(klases_pavadinimas);
CREATE UNIQUE INDEX Globejas_AK_index			ON Globejas(AK);
CREATE INDEX Pazymys_mokinys_index 				ON Pazymys(mokinys);


CREATE MATERIALIZED VIEW Mokiniu_pazymiai
AS SELECT vardas, pavarde, dalykas, pazymys
	FROM Mokinys 	JOIN Pazymys 
	ON Mokinys.mokinio_nr = Pazymys.mokinys
	WITH NO DATA;
	
CREATE MATERIALIZED VIEW Klasiu_mokiniai
AS SELECT Klase.pavadinimas AS klase,
	CONCAT(Mokinys.vardas, ' ', Mokinys.pavarde) AS mokinys
	FROM Klase 	JOIN Mokinys 
	ON Klase.pavadinimas = Mokinys.klases_pavadinimas
	WITH NO DATA;
	

CREATE VIEW Globeju_vaikai
AS SELECT Globejas.vardas AS globejo_vardas, Globejas.pavarde AS globejo_pavarde,
	Mokinys.vardas AS mokinio_vardas, Mokinys.pavarde AS mokinio_pavarde
	FROM Globejas JOIN Mokinys
	ON Globejas.AK = Mokinys.globejo_AK;
	
CREATE VIEW Dalyku_mokytojai
AS SELECT Dalykas.pavadinimas AS dalykas, 
	CONCAT(Mokytojas.vardas, ' ', Mokytojas.pavarde) AS mokytojas
	FROM Mokytojas JOIN Dalykas
	ON Mokytojas.dalykas = Dalykas.pavadinimas;
	
CREATE VIEW Dalyku_pazymiu_vidurkiai
AS SELECT Dalykas.pavadinimas AS dalykas, ROUND(AVG(Pazymys.pazymys)::NUMERIC, 2) AS vidurkis
	FROM Dalykas JOIN Pazymys
	ON Dalykas.pavadinimas = Pazymys.dalykas
	GROUP BY Dalykas.pavadinimas;
	
CREATE VIEW Mokiniu_pazymiu_vidurkiai
AS SELECT CONCAT(Mokinys.vardas, ' ', Mokinys.pavarde) AS mokinys,
	ROUND(AVG(Pazymys.pazymys)::NUMERIC, 2) AS vidurkis
	FROM Mokinys JOIN Pazymys
	ON Mokinys.mokinio_nr = Pazymys.mokinys
	GROUP BY CONCAT(Mokinys.vardas, ' ', Mokinys.pavarde);