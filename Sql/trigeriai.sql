CREATE FUNCTION Mokytoju_sk_didinimas_func()
RETURNS TRIGGER AS 
$$BEGIN 
UPDATE Dalykas
SET mokytoju_skaicius = 
	(SELECT mokytoju_skaicius FROM Dalykas WHERE pavadinimas = NEW.dalykas) + 1 
	WHERE pavadinimas = NEW.dalykas;
RETURN NEW;
END;$$
LANGUAGE plpgsql;	
	
CREATE TRIGGER Mokytoju_sk_didinimas
AFTER INSERT ON Mokytojas
FOR EACH ROW
EXECUTE PROCEDURE Mokytoju_sk_didinimas_func();



CREATE FUNCTION Klases_talpa_func()
RETURNS TRIGGER AS 
$$BEGIN 
IF(SELECT COUNT(*) FROM Mokinys  
WHERE Mokinys.klases_pavadinimas = NEW.klases_pavadinimas) >= 20
THEN   
	RAISE EXCEPTION 'Virsytas klases mokiniu skaicius';
END IF;
RETURN NEW;
END;$$
LANGUAGE plpgsql;

CREATE TRIGGER Klases_talpa
BEFORE INSERT OR UPDATE ON Mokinys
FOR EACH ROW 
EXECUTE PROCEDURE Klases_talpa_func();



CREATE FUNCTION Mokinio_pastabos_func()
RETURNS TRIGGER AS 
$$BEGIN 
IF(SELECT COUNT(*) FROM Pastaba  
WHERE Pastaba.mokinys = NEW.mokinys) >= 3
THEN   
	DELETE FROM Mokinys
	WHERE Mokinys.mokinio_nr = NEW.mokinys;
END IF;
RETURN NEW;
END;$$
LANGUAGE plpgsql;	
	
CREATE TRIGGER Mokinio_pastabos
AFTER INSERT ON Pastaba
FOR EACH ROW
EXECUTE PROCEDURE Mokinio_pastabos_func();



CREATE FUNCTION Globejo_istrinimas_func()
RETURNS TRIGGER AS 
$$BEGIN 
IF(SELECT COUNT(*) FROM Mokinys  
WHERE Mokinys.globejo_AK = OLD.globejo_AK) = 0
THEN   
	DELETE FROM Globejas
	WHERE Globejas.Ak = OLD.globejo_AK;
END IF;
RETURN NEW;
END;$$
LANGUAGE plpgsql;	
	
CREATE TRIGGER Globejo_istrinimas
AFTER DELETE ON Mokinys
FOR EACH ROW
EXECUTE PROCEDURE Globejo_istrinimas_func();
