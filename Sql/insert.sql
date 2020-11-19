-- mokykla: kodas, adresas, pavadinimas, direktorius, mokiniu skaicius (default 0)
INSERT INTO Mokykla VALUES(617283, 'Zariju g. 5', 'Liepu gimnazija', 'Aldona Aldoniene') ;

--dalykas: nr (generated), pavadinimas, mokytoju sk (default 0)
INSERT INTO Dalykas VALUES(DEFAULT, 'Lietuviu kalba') ;
INSERT INTO Dalykas VALUES(DEFAULT, 'Matematika') ;
INSERT INTO Dalykas VALUES(DEFAULT, 'Chemija') ;
INSERT INTO Dalykas VALUES(DEFAULT, 'Biologija') ;
INSERT INTO Dalykas VALUES(DEFAULT, 'Vokieciu kalba') ;
INSERT INTO Dalykas VALUES(DEFAULT, 'Kuno kultura') ;

--mokytojas: nr, mokykla, vardas, pavarde, dalykas
INSERT INTO Mokytojas VALUES(999999, 'Liepu gimnazija', 'Aurelija', 'Aurelioniene', 'Biologija') ;
INSERT INTO Mokytojas VALUES(999998, 'Liepu gimnazija', 'Jane', 'Janiniene', 'Chemija') ;
INSERT INTO Mokytojas VALUES(999997, 'Liepu gimnazija', 'Irena', 'Ireniene', 'Lietuviu kalba') ;
INSERT INTO Mokytojas VALUES(999996, 'Liepu gimnazija', 'Tomas', 'Tomauskas', 'Matematika') ;
INSERT INTO Mokytojas VALUES(999995, 'Liepu gimnazija', 'Aurelija', 'Aureliene', 'Matematika') ;
INSERT INTO Mokytojas VALUES(999994, 'Liepu gimnazija', 'Jonas', 'Jonienius', 'Vokieciu kalba') ;
INSERT INTO Mokytojas VALUES(999993, 'Liepu gimnazija', 'Paulius', 'Paulienius', 'Informatika') ;
INSERT INTO Mokytojas VALUES(999991, 'Liepu gimnazija', 'Janina', 'Janiene', 'Lietuviu kalba') ;
INSERT INTO Mokytojas VALUES(999990, 'Liepu gimnazija', 'Luka', 'Lukiene', 'Kuno kultura') ;

INSERT INTO Mokytojas VALUES(999000, 'Liepu gimnazija', 'Lukas', 'Lukauskas', 'Kuno kultura') ;

--klase: pavadinimas, mokyklos pavadinimas
INSERT INTO Klase VALUES('10A', 'Liepu gimnazija') ;
INSERT INTO Klase VALUES('11A', 'Liepu gimnazija') ;
INSERT INTO Klase VALUES('11F', 'Liepu gimnazija') ;
INSERT INTO Klase VALUES('5A', 'Liepu gimnazija') ;
INSERT INTO Klase VALUES('6A', 'Liepu gimnazija') ;
INSERT INTO Klase VALUES('7A', 'Liepu gimnazija') ;
INSERT INTO Klase VALUES('7B', 'Liepu gimnazija') ;

--globejas: ak, vardas, pavarde
INSERT INTO Globejas VALUES(44805197481, 'Aukse', 'Auksieniene') ;
INSERT INTO Globejas VALUES(45609137121, 'Gabija', 'Gabijeniene') ;
INSERT INTO Globejas VALUES(44812071200, 'Skaiste', 'Skaistiene') ;
INSERT INTO Globejas VALUES(38111171100, 'Julius', 'Julenas') ;
INSERT INTO Globejas VALUES(37911171162, 'Aurimas', 'Aurenas') ;
INSERT INTO Globejas VALUES(37608309173, 'Sarunas', 'Sarunenas') ;

INSERT INTO Globejas VALUES(65432123456, 'Sarunas', 'Sarunenas') ;


--mokinys: nr, ak, vardas, pavarde, gimimo data, adresas, globejo ak, klases pavadinimas
INSERT INTO Mokinys VALUES(111001, '00000000001', 'Simas', 'Simaitis', '2001-09-01', 'Guobu g. 9-5', 44805197481, '11F') ;
INSERT INTO Mokinys VALUES(111002, '00000000002', 'Sima', 'Simaityte', '2001-09-01', 'Guobu g. 9-5', 44805197481, '11F') ;
INSERT INTO Mokinys VALUES(111003, '00000000003', 'Lukas', 'Lukaitis', '2001-10-01', 'Baltoji g. 99-10', 45609137121, '11F') ;
INSERT INTO Mokinys VALUES(111004, '00000000004', 'Ramunas', 'Julenas', '2001-11-01', 'Isiuteliu g. 68-71', 38111171100, '11F') ;
INSERT INTO Mokinys VALUES(111005, '00000000005', 'Rima', 'Rimaityte', '2001-08-01', 'Guobu g. 9-5', 44805197481, '11F') ;
INSERT INTO Mokinys VALUES(111006, '00000000006', 'Ela', 'Aurenaite', '2001-12-01', 'Kaimynu g. 88-31', 37911171162, '11F') ;
INSERT INTO Mokinys VALUES(111007, '00000000007', 'Daiva', 'Aurenaite', '2001-12-08', 'Kaimynu g. 88-31', 37911171162, '11F') ;
INSERT INTO Mokinys VALUES(111008, '00000000008', 'Dominykas', 'Aurenas', '2001-09-15', 'Kaimynu g. 88-31', 37911171162, '11F') ;
INSERT INTO Mokinys VALUES(111009, '00000000009', 'Jokubas', 'Aurenas', '2001-02-14', 'Kaimynu g. 88-31', 37911171162, '11F') ;
INSERT INTO Mokinys VALUES(111010, '00000000010', 'Matas', 'Mataitis', '2001-02-16', 'Guobu g. 9-5', 44805197481, '11F') ;
INSERT INTO Mokinys VALUES(111011, '00000000011', 'Leja', 'Sarunenaite', '2001-08-20', 'Lauzu g. 15-32', 37608309173, '11F') ;
INSERT INTO Mokinys VALUES(111012, '00000000012', 'Simona', 'Simonyte', '2001-09-30', 'Guobu g. 9-5', 44805197481, '11F') ;
INSERT INTO Mokinys VALUES(111013, '00000000013', 'Kleja', 'Klejyte', '2001-05-13', 'Guobu g. 9-5', 44805197481, '11F') ;
INSERT INTO Mokinys VALUES(111014, '00000000014', 'Juozas', 'Sarunenas', '2001-12-12', 'Lauzu g. 15-32', 37608309173, '11F') ;
INSERT INTO Mokinys VALUES(111015, '00000000015', 'Herkus', 'Julenas', '2001-09-12', 'Isiuteliu g. 68-71', 38111171100, '11F') ;
INSERT INTO Mokinys VALUES(111016, '00000000016', 'Joris', 'Jorauskas', '2001-09-18', 'Guobu g. 9-5', 44805197481, '11F') ;
INSERT INTO Mokinys VALUES(111017, '00000000017', 'Gabrielius', 'Julenas', '2001-09-25', 'Isiuteliu g. 68-71', 38111171100, '11F') ;
INSERT INTO Mokinys VALUES(111018, '00000000018', 'Gabriele', 'Gabriauskyte', '2001-12-31', 'Papilenu g. 79-1', 44812071200, '11F') ;
INSERT INTO Mokinys VALUES(111019, '00000000019', 'Patricija', 'Patricaite', '2001-01-01', 'Papilenu g. 79-1', 44812071200, '11F') ;
INSERT INTO Mokinys VALUES(111020, '00000000020', 'Austeja', 'Austyte', '2001-07-01', 'Baltoji g. 99-10', 45609137121, '11F') ;
INSERT INTO Mokinys VALUES(111021, '00000000021', 'Saule', 'Sauliaite', '2001-11-18', 'Papilenu g. 79-1', 44812071200, '11F') ;
INSERT INTO Mokinys VALUES(111022, '00000000022', 'Jorune', 'Jorunyte', '2009-11-11', 'Baltoji g. 99-10', 45609137121, '10A') ;
INSERT INTO Mokinys VALUES(111023, '00000000023', 'Lukne', 'Sarunenaite', '2007-05-30', 'Lauzu g. 15-32', 37608309173, '11A') ;

INSERT INTO Mokinys VALUES(555444, '00000000099', 'Saule', 'Sauliaite', '2001-11-18', 'Papilenu g. 79-1', 44812071200, '11F') ;

INSERT INTO Mokinys VALUES(123456, '00000099999', 'Lukne', 'Sarunenaite', '2007-05-30', 'Lauzu g. 15-32', 65432123456, '11A') ;

--pazymys: nr (generated), pazymys, mokinys, dalykas, data, laikas
INSERT INTO Pazymys VALUES(DEFAULT, 9, 111001, 'Matematika') ;
INSERT INTO Pazymys VALUES(DEFAULT, 6, 111001, 'Lietuviu kalba', '2019-09-21') ;
INSERT INTO Pazymys VALUES(DEFAULT, 4, 111001, 'Vokieciu kalba', '2019-09-29') ;
INSERT INTO Pazymys VALUES(DEFAULT, 10,111001,  'Biologija', '2019-10-03') ;
INSERT INTO Pazymys VALUES(DEFAULT, 10,111001,  'Kuno kultura', '2019-10-02') ;
INSERT INTO Pazymys VALUES(DEFAULT, 8, 111014,  'Biologija', '2019-10-03') ;
INSERT INTO Pazymys VALUES(DEFAULT, 9, 111013,  'Chemija', '2019-10-04') ;
INSERT INTO Pazymys VALUES(DEFAULT, 5, 111023,  'Lietuviu kalba', '2019-10-03') ;
INSERT INTO Pazymys VALUES(DEFAULT, 9, 111013,  'Lietuviu kalba') ;
INSERT INTO Pazymys VALUES(DEFAULT, 8, 111023,  'Matematika') ;
INSERT INTO Pazymys VALUES(DEFAULT, 5, 111002,  'Matematika') ;
INSERT INTO Pazymys VALUES(DEFAULT, 2, 111002,  'Chemija', '2019-10-04') ;
INSERT INTO Pazymys VALUES(DEFAULT, 3, 111002,  'Biologija', '2019-10-03') ;
INSERT INTO Pazymys VALUES(DEFAULT, 10,111003,  'Vokieciu kalba', '2019-10-02') ;
INSERT INTO Pazymys VALUES(DEFAULT, 6, 111003,  'Kuno kultura', '2019-10-01') ;
INSERT INTO Pazymys VALUES(DEFAULT, 7, 111010,  'Lietuviu kalba', '2019-11-10') ;
INSERT INTO Pazymys VALUES(DEFAULT, 8, 111006,  'Kuno kultura', '2019-11-11') ;
INSERT INTO Pazymys VALUES(DEFAULT, 8, 111005,  'Lietuviu kalba', '2019-11-12') ;
INSERT INTO Pazymys VALUES(DEFAULT, 9, 111007,  'Chemija', '2019-10-13') ;
INSERT INTO Pazymys VALUES(DEFAULT, 6, 111022,  'Matematika', '2019-10-15') ;
INSERT INTO Pazymys VALUES(DEFAULT, 6, 111020,  'Vokieciu kalba', '2019-11-16') ;
INSERT INTO Pazymys VALUES(DEFAULT, 4, 111022,  'Lietuviu kalba', '2019-11-17') ;

--pastaba: nr, mokinys, data (default), laikas (default), priezastis
INSERT INTO Pastaba VALUES(0, 111010, '2019-09-20', '13:30', 'Snekejo pamokos metu') ;
INSERT INTO Pastaba VALUES(2, 111015, '2019-10-01', '10:10', 'Nepadare namu darbu') ;
INSERT INTO Pastaba VALUES(1, 111023, '2019-09-12', '11:40', 'Nepasiruose projektui') ;
INSERT INTO Pastaba VALUES(3, 111023, '2019-09-12', '11:40', 'Nepasiruose projektui') ;

INSERT INTO Pastaba VALUES(4, 111023, '2019-09-12', '11:40', 'Nepasiruose projektui') ;