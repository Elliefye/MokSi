 DROP TABLE "mokykla" CASCADE;
 DROP TABLE "mokytojas" CASCADE;
 DROP TABLE "dalykas" CASCADE;
 DROP TABLE "klase" CASCADE;
 DROP TABLE "globejas" CASCADE;
 DROP TABLE "mokinys" CASCADE;
 DROP TABLE "pazymys" CASCADE;
 DROP TABLE "pastaba" CASCADE;

 DROP VIEW globeju_vaikai;
 DROP VIEW dalyku_mokytojai;
 DROP VIEW dalyku_pazymiu_vidurkiai;
 DROP VIEW mokiniu_pazymiu_vidurkiai;
 
 DROP MATERIALIZED VIEW mokiniu_pazymiai CASCADE;
 DROP MATERIALIZED VIEW klasiu_mokiniai CASCADE;
 
 DROP FUNCTION klases_talpa_func() CASCADE;
 DROP FUNCTION Mokytoju_sk_didinimas_func() CASCADE;
 DROP FUNCTION Mokinio_pastabos_func() CASCADE;
 DROP FUNCTION Globejo_istrinimas_func() CASCADE;

 /*
 Dropinant lenteles nusidropina ir VIEWS, ir MAT VIEWS, ir INDEXAI.
 Tai uztenka dropint lenteles, kad isvalyti savo schema
 */