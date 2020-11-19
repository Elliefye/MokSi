/*
EILISKUMAS LENTELIU KURIMUI:

mokykla
dalykas
mokytojas
klase
globejas
mokinys
pazymys
pastaba
*/

/*
EILISKUMAS VISKO KURIMUI:

lenteles
indexai
mat views
views
trigeriai
insertai
refresh mat views
*/

TABLES
\dt pane5605.*
VIEWS
\dv pane5605.*
MAT VIEWS
\dm pane5605.*
FUNCTIONS
\df pane5605.*
TRIGERS
select * from information_schema.triggers;

INDEXAI

SELECT
    tablename,
    indexname,
    indexdef
FROM
    pg_indexes
WHERE
    schemaname = 'pane5605';
	
REFRESH MATERIALIZED VIEW Mokiniu_pazymiai;
REFRESH MATERIALIZED VIEW Klasiu_mokiniai;