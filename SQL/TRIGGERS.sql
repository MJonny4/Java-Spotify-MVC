CREATE OR REPLACE TRIGGER trigger_grup_data_creacio
  BEFORE INSERT OR UPDATE ON ART_GRUP
  FOR EACH ROW
BEGIN
  IF (sysdate < :new.grup_data_creacio)
  THEN
    RAISE_APPLICATION_ERROR(-20001, 'La data de creacio no pot ser futura!');
  END IF;
END;
/ 
CREATE OR REPLACE TRIGGER trigger_ind_data_naixement
  BEFORE INSERT OR UPDATE ON ART_IND
  FOR EACH ROW
BEGIN
  IF (sysdate < :new.ind_data_naixement)
  THEN
    RAISE_APPLICATION_ERROR(-20002, 'La data de naixement no pot ser futura!');
  END IF;
END;
/
CREATE OR REPLACE TRIGGER trigger_canco_any_creacio
  BEFORE INSERT OR UPDATE ON CANCO
  FOR EACH ROW
BEGIN
  IF (to_number(to_char(sysdate, 'YYYY')) < :new.canco_any_creacio)
  THEN
    RAISE_APPLICATION_ERROR(-20003, 'L''any de creacio no pot ser futur!');
  END IF;
END;
/
CREATE OR REPLACE TRIGGER trigger_album_any_creacio
  BEFORE INSERT OR UPDATE ON ALBUM
  FOR EACH ROW
BEGIN
  IF (to_number(to_char(sysdate, 'YYYY')) < :new.album_any_creacio)
  THEN
    RAISE_APPLICATION_ERROR(-20004, 'L''any de creacio no pot ser futur!');
  END IF;
END;
/
CREATE OR REPLACE TRIGGER trigger_hgrup_data_inici
  BEFORE INSERT OR UPDATE ON H_GRUP
  FOR EACH ROW
BEGIN
  IF (sysdate < :new.hgrup_data_inici)
  THEN
    RAISE_APPLICATION_ERROR(-20005, 'La data d''inici no pot ser futura!');
  END IF;
END;
/
CREATE OR REPLACE TRIGGER trigger_canco_durada
  BEFORE INSERT OR UPDATE ON CANCO
  FOR EACH ROW
BEGIN
  IF (:new.canco_durada <= 0)
  THEN
    RAISE_APPLICATION_ERROR(-20006, 'La durada no pot ser 0 o negativa!');
  END IF;
END;
/
CREATE OR REPLACE TRIGGER trigger_llista_durada
  BEFORE INSERT OR UPDATE ON LLISTA
  FOR EACH ROW
BEGIN
  IF (:new.llista_durada < 0)
  THEN
    RAISE_APPLICATION_ERROR(-20007, 'La durada no pot ser 0 o negativa!');
  END IF;
END;
/
CREATE OR REPLACE TRIGGER trigger_album_durada
  BEFORE INSERT OR UPDATE ON ALBUM
  FOR EACH ROW
BEGIN
  IF (:new.album_durada < 0)
  THEN
    RAISE_APPLICATION_ERROR(-20008, 'La durada no pot ser 0 o negativa!');
  END IF;
END;
/
CREATE OR REPLACE TRIGGER trigger_client_virtual_delete
  BEFORE DELETE ON CLIENT
  FOR EACH ROW
BEGIN
  IF (:old.client_id = 0)
  THEN
    RAISE_APPLICATION_ERROR(-20009, 'No es pot esborrar un client virtual!');
  END IF;
END;
/
CREATE OR REPLACE TRIGGER trigger_llista_tipus
  BEFORE INSERT OR UPDATE ON LLISTA
  FOR EACH ROW
DECLARE
  v_tipus producte.producte_tipus%type;
BEGIN
  SELECT producte_tipus INTO v_tipus FROM producte WHERE producte_id = :new.llista_id;
  IF (v_tipus != 'L')
  THEN
    RAISE_APPLICATION_ERROR(-20010, 'No es pot inserir una llista dintre de un altre llista!');
  END IF;
END;
/
CREATE OR REPLACE TRIGGER trigger_delete_client
  BEFORE DELETE ON CLIENT
  FOR EACH ROW
DECLARE
  rep_count number;
BEGIN
  SELECT COUNT(*) INTO rep_count FROM REPRODUCCIO repro WHERE repro.rep_id_client = :old.client_id;
  IF (rep_count > 0) THEN
    UPDATE REPRODUCCIO set rep_id_client = 0 WHERE rep_id_client = :old.client_id; -- GOOD
  END IF;
END;
/
CREATE OR REPLACE TRIGGER trigger_delete_producte
  BEFORE DELETE ON PRODUCTE
  FOR EACH ROW
DECLARE
  rep_count number;
BEGIN
    -- MODIFICAR EN ARCHIVO
  SELECT COUNT(*) INTO rep_count FROM reproduccio where rep_id_producte = :old.producte_id;
  IF (rep_count > 0) THEN
    RAISE_APPLICATION_ERROR(-20011, 'No es pot esborrar un producte que ha estat reproduit!');
  END IF;
END;
/
CREATE OR REPLACE TRIGGER trigger_delete_artista
  BEFORE DELETE ON ARTISTA
  FOR EACH ROW
DECLARE
  count1 number;
  count2 number;
begin
  SELECT COUNT (*) INTO count1 FROM CANCO can where can.canco_interpet = :old.artista_id;
  SELECT COUNT (*) INTO count2 FROM AUTORIA aut where aut.autoria_id_art_ind = :old.artista_id;
  IF (count1 > 0 or count2 > 0) THEN
    RAISE_APPLICATION_ERROR(-20012, 'No es pot esborrar un artista que ha estat utilitzat com autor!');
  END IF;
END;
/

CREATE OR REPLACE TRIGGER trigger_durada_album_contingut
  AFTER INSERT OR DELETE OR UPDATE OF album_con_id_canco
  ON ALBUM_CONTINGUT
  FOR EACH ROW
DECLARE
  v_durada number;
  v_tipus char(1);
BEGIN
  IF inserting or updating THEN
    SELECT producte_tipus INTO v_tipus FROM producte WHERE producte_id = :new.album_con_id_canco;
    IF v_tipus = 'C' THEN
      SELECT canco_durada INTO v_durada FROM CANCO where canco_id = :new.album_con_id_canco;
      UPDATE ALBUM SET album_durada = album_durada + v_durada WHERE album_id = :new.album_con_id_album;
    END IF;
  END IF;

  IF deleting or updating THEN
    select producte_tipus into v_tipus from producte where producte_id = :old.album_con_id_canco;
    IF v_tipus = 'C' THEN
      select canco_durada into v_durada from canco where canco_id = :old.album_con_id_canco;
      update album set album_durada = album_durada - v_durada where album_id = :old.album_con_id_album;
    end IF;
  end IF;
END;
/
CREATE OR REPLACE TRIGGER trigger_durada_album_after_update_durada_canco
  AFTER UPDATE OF canco_durada
  ON CANCO
  FOR EACH ROW
DECLARE
BEGIN
  UPDATE ALBUM
  SET album_durada = album_durada - :old.canco_durada + :new.canco_durada
  WHERE album_id IN (SELECT album_con_id_album 
                     FROM ALBUM_CONTINGUT 
                     WHERE album_con_id_canco = :old.canco_id);
END;
/
CREATE OR REPLACE TRIGGER trigger_durada_llista_contingut
  AFTER INSERT OR DELETE OR UPDATE OF llista_con_id_producte
  ON LLISTA_CONTINGUT
  FOR EACH ROW
DECLARE
  v_durada number;
  v_tipus char(1);
BEGIN
  IF INSERTING OR UPDATING THEN
    select producte_tipus into v_tipus from PRODUCTE where producte_id = :new.llista_con_id_producte;
    IF v_tipus = 'C' THEN
      select canco_durada into v_durada from CANCO where canco_id = :new.llista_con_id_producte;
      update LLISTA set llista_durada = llista_durada + v_durada where llista_id = :new.llista_con_id_llista;
    END IF;
    IF v_tipus = 'A' THEN
      select album_durada into v_durada from ALBUM where album_id = :new.llista_con_id_producte;
      update LLISTA set llista_durada = llista_durada + v_durada where llista_id = :new.llista_con_id_llista;
    END IF;
  END IF;

  IF DELETING OR UPDATING THEN
    select producte_tipus into v_tipus from PRODUCTE where producte_id = :old.llista_con_id_producte;
    IF v_tipus = 'C' THEN
      select canco_durada into v_durada from CANCO where canco_id = :old.llista_con_id_producte;
      update LLISTA set llista_durada = llista_durada - v_durada where llista_id = :old.llista_con_id_llista;
    end IF;
    IF v_tipus = 'A' THEN
      select album_durada into v_durada from ALBUM where album_id = :old.llista_con_id_producte;
      update LLISTA set llista_durada = llista_durada - v_durada where llista_id = :old.llista_con_id_llista;
    end IF;
  end IF;
END;
/
CREATE OR REPLACE TRIGGER trigger_durada_llista_after_update_durada_canco
  AFTER UPDATE OF canco_durada
  ON CANCO
  FOR EACH ROW
DECLARE
BEGIN
  UPDATE LLISTA
  SET llista_durada = llista_durada - :old.canco_durada + :new.canco_durada
  WHERE llista_id IN (SELECT llista_con_id_llista 
                      FROM LLISTA_CONTINGUT 
                      WHERE llista_con_id_producte = :old.canco_id);
END;
/
CREATE OR REPLACE TRIGGER trigger_durada_llista_after_update_durada_album
  AFTER UPDATE OF album_durada
  ON ALBUM
  FOR EACH ROW
DECLARE
BEGIN
  UPDATE LLISTA
  SET llista_durada = llista_durada - :old.album_durada + :new.album_durada
  WHERE llista_id IN (SELECT llista_con_id_llista 
                      FROM LLISTA_CONTINGUT 
                      WHERE llista_con_id_producte = :old.album_id);
END;
/
