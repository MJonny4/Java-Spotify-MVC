/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.milaifontanals.models.Album;
import org.milaifontanals.models.Canco;
import org.milaifontanals.models.Estil;
import org.milaifontanals.models.ItemsLlista;
import org.milaifontanals.models.Producte;
import org.milaifontanals.models.TipusProducteEnum;

/**
 *
 * @author Ion
 */
public class DBProducte {
  public static long getProducte(String nom) throws PersistenciaException {
    long id = 0;
    PreparedStatement ps = null;
    try {
      String sql = "SELECT producte_id FROM producte WHERE producte_titol = ?";
      ps = Persistencia.getConnection().prepareStatement(sql);
      ps.setString(1, nom);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        id = rs.getLong("producte_id");
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw new PersistenciaException("Error al obtenir el producte: " + nom + ".\n" + e.getMessage());
    } finally {
      if (ps != null) {
        try {
          ps.close();
        } catch (SQLException e) {
          e.printStackTrace();
          throw new PersistenciaException("Error al tencar la consulta del producte: " + nom + ".\n" + e.getMessage());
        }
      }
    }
    return id;
  }

  public static List<Canco> getCanconcsInterpretades(long idArtista) throws PersistenciaException {
    List<Canco> cancons = new ArrayList<Canco>();
    PreparedStatement ps = null;
    try {
      String sql = "SELECT c.canco_any_creacio, c.canco_durada FROM CANCO c, PRODUCTE p WHERE c.canco_id = p.producte_id AND p.producte_id = ?";
      ps = Persistencia.getConnection().prepareStatement(sql);
      ps.setLong(1, idArtista);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        Canco canco = new Canco();
        canco.setCanco_any_creacio(rs.getInt("canco_any_creacio"));
        canco.setDurada(rs.getInt("canco_durada"));
        cancons.add(canco);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw new PersistenciaException(
          "Error al obtenir les cancons interpretades per l'artista: " + idArtista + ".\n" + e.getMessage());
    } finally {
      if (ps != null) {
        try {
          ps.close();
        } catch (SQLException e) {
          e.printStackTrace();
          throw new PersistenciaException("Error al tencar la consulta de les cancons interpretades per l'artista: "
              + idArtista + ".\n" + e.getMessage());
        }
      }
    }
    return cancons;
  }

  public static List<Producte> getProductes(char[] tipus, char[] actiu, String titol) throws PersistenciaException {
    List<Producte> productes = new ArrayList<Producte>();
    PreparedStatement ps = null;
    try {
      String sql = "SELECT p.producte_titol, e.estil_nom, p.producte_actiu, p.producte_tipus "
          + "FROM producte p, estil e "
          + "WHERE p.producte_estil = e.estil_id ";
      if (tipus != null && tipus.length > 0) {
        sql += "AND p.producte_tipus IN (";
        for (int i = 0; i < tipus.length; i++) {
          sql += "'" + tipus[i] + "'";
          if (i < tipus.length - 1) {
            sql += ",";
          }
        }
        sql += ") ";
      }

      if (actiu != null && actiu.length > 0) {
        sql += "AND p.producte_actiu IN (";
        for (int i = 0; i < actiu.length; i++) {
          sql += "'" + actiu[i] + "'";
          if (i < actiu.length - 1) {
            sql += ",";
          }
        }
        sql += ") ";
      }

      if (titol != null && titol.length() > 0) {
        sql += "AND p.producte_titol LIKE ? ";
      }

      sql += "ORDER BY p.producte_titol";

      ps = Persistencia.getConnection().prepareStatement(sql);
      if (titol != null && titol.length() > 0) {
        ps.setString(1, "%" + titol + "%");
      }

      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        Producte p = new Producte() {
          @Override
          public int getDurada() {
            return 0;
          }
        };
        p.setProducte_titol(rs.getString("producte_titol"));
        p.setProducte_actiu(rs.getNString("producte_actiu").charAt(0));
        Estil estil = new Estil();
        estil.setEstil_nom(rs.getString("estil_nom"));
        p.setProducte_estil(estil);
        if (rs.getString("producte_tipus").equals("C")) {
          p.setProducte_tipus(TipusProducteEnum.CANCO);
        } else if (rs.getString("producte_tipus").equals("A")) {
          p.setProducte_tipus(TipusProducteEnum.ALBUM);
        } else if (rs.getString("producte_tipus").equals("L")) {
          p.setProducte_tipus(TipusProducteEnum.LLISTA);
        }
        productes.add(p);
      }
      rs.close();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new PersistenciaException("Error al obtenir els productes.\n" + e.getMessage());
    } finally {
      if (ps != null) {
        try {
          ps.close();
        } catch (SQLException e) {
          e.printStackTrace();
          throw new PersistenciaException(
              "Error en tancar la consulta per a recuperar els productes.\n" + e.getMessage());
        }
      }
    }
    return productes;
  }

  public static List<Album> getAlbum(long idProducte) throws PersistenciaException {
    List<Album> albums = new ArrayList<Album>();
    PreparedStatement ps = null;
    try {
      String sql = "SELECT a.ALBUM_ANY_CREACIO, a.ALBUM_DURADA FROM ALBUM a, PRODUCTE p WHERE a.ALBUM_ID = p.PRODUCTE_ID AND p.PRODUCTE_ID = ?";
      ps = Persistencia.getConnection().prepareStatement(sql);
      ps.setLong(1, idProducte);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        Album album = new Album();
        album.setAlbum_any_creacio(rs.getInt("ALBUM_ANY_CREACIO"));
        album.setAlbum_durada(rs.getInt("ALBUM_DURADA"));
        albums.add(album);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw new PersistenciaException("Error al obtenir els albums.\n" + e.getMessage());
    } finally {
      if (ps != null) {
        try {
          ps.close();
        } catch (SQLException e) {
          e.printStackTrace();
          throw new PersistenciaException("Error en tancar la consulta per a recuperar els albums.\n" + e.getMessage());
        }
      }
    }
    return albums;
  }

  public static List<Album> getAlbumCancons(long idProducte) throws PersistenciaException {
    List<Album> albums = new ArrayList<Album>();
    PreparedStatement ps = null;
    try {
      String sql = "SELECT p.producte_titol, ac.album_con_posicio, c.canco_durada "
          + "FROM PRODUCTE p, ALBUM_CONTINGUT ac, CANCO c "
          + "WHERE p.producte_id = ac.album_con_id_canco "
          + "AND ac.album_con_id_canco = c.canco_id "
          + "AND ac.album_con_id_album = ? "
          + "ORDER BY ac.album_con_posicio";
      ps = Persistencia.getConnection().prepareStatement(sql);
      ps.setLong(1, idProducte);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        Album album = new Album();
        album.setPosicio(rs.getInt("album_con_posicio"));
        List<Canco> cancons = new ArrayList<Canco>();
        Canco canco = new Canco();
        canco.setProducte_titol(rs.getString("producte_titol"));
        canco.setDurada(rs.getInt("canco_durada"));
        cancons.add(canco);
        album.setCancons(cancons);
        albums.add(album);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw new PersistenciaException("Error al obtenir els albums.\n" + e.getMessage());
    } finally {
      if (ps != null) {
        try {
          ps.close();
        } catch (SQLException e) {
          e.printStackTrace();
          throw new PersistenciaException("Error en tancar la consulta per a recuperar els albums.\n" + e.getMessage());
        }
      }
    }
    return albums;
  }

  public static int getLlistaDuarada(long idProducte) throws PersistenciaException {
    PreparedStatement ps = null;
    int durada = 0;
    try {
      String sql = "SELECT l.llista_durada FROM LLISTA l, PRODUCTE p WHERE l.LLISTA_ID = p.PRODUCTE_ID AND p.PRODUCTE_ID = ?";
      ps = Persistencia.getConnection().prepareStatement(sql);
      ps.setLong(1, idProducte);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        durada = rs.getInt("llista_durada");
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw new PersistenciaException("Error al obtenir els albums.\n" + e.getMessage());
    } finally {
      if (ps != null) {
        try {
          ps.close();
        } catch (SQLException e) {
          e.printStackTrace();
          throw new PersistenciaException("Error en tancar la consulta per a recuperar els albums.\n" + e.getMessage());
        }
      }
    }
    return durada;
  }

  public static List<ItemsLlista> getLlistaProductes(long idProducte) throws PersistenciaException {
    List<ItemsLlista> items = new ArrayList<ItemsLlista>();
    PreparedStatement ps = null;
    try {
      String sql = "SELECT p.producte_titol, p.producte_tipus, lc.llista_con_posicio "
          + "FROM PRODUCTE p "
          + "JOIN LLISTA_CONTINGUT lc ON p.producte_id = lc.llista_con_id_producte "
          + "WHERE lc.llista_con_id_llista = ? "
          + "ORDER BY lc.llista_con_posicio";
      ps = Persistencia.getConnection().prepareStatement(sql);
      ps.setLong(1, idProducte);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        ItemsLlista item = new ItemsLlista();
        Producte producte = new Producte() {
          @Override
          public int getDurada() {
            return 0;
          }
        };
        producte.setProducte_titol(rs.getString("producte_titol"));
        if (rs.getString("producte_tipus").equals("A")) {
          producte.setProducte_tipus(TipusProducteEnum.ALBUM);
        } else if (rs.getString("producte_tipus").equals("C")) {
          producte.setProducte_tipus(TipusProducteEnum.CANCO);
        } else if (rs.getString("producte_tipus").equals("L")) {
          producte.setProducte_tipus(TipusProducteEnum.LLISTA);
        }
        item.setProducte(producte);
        item.setLlista_posicio(rs.getInt("llista_con_posicio"));
        items.add(item);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw new PersistenciaException("Error al obtenir els albums.\n" + e.getMessage());
    } finally {
      if (ps != null) {
        try {
          ps.close();
        } catch (SQLException e) {
          e.printStackTrace();
          throw new PersistenciaException("Error en tancar la consulta per a recuperar els albums.\n" + e.getMessage());
        }
      }
    }
    return items;
  }

  public static void updateCanco(int producteId, String titolModificat, String estilModificat, char actiuModificat,
      int anyCreacioModificat,
      String interpretModificat, int duradaModificat) throws PersistenciaException {
    PreparedStatement ps = null;
    PreparedStatement ps2 = null;

    try {
      String sql = "UPDATE PRODUCTE SET producte_titol = ?, producte_actiu = ? , producte_estil = (SELECT estil_id FROM ESTIL WHERE estil_nom = ?) WHERE producte_id = ?";
      ps = Persistencia.getConnection().prepareStatement(sql);
      ps.setString(1, titolModificat);
      ps.setString(2, String.valueOf(actiuModificat));
      ps.setString(3, estilModificat);
      ps.setInt(4, producteId);
      ps.executeUpdate();
      ps.close();
      String sql2 = "UPDATE CANCO SET canco_any_creacio = ?, canco_interpet = (SELECT artista_id FROM ARTISTA WHERE artista_nom = ?), canco_durada = ? WHERE canco_id = ?";
      ps2 = Persistencia.getConnection().prepareStatement(sql2);
      ps2.setInt(1, anyCreacioModificat);
      ps2.setString(2, interpretModificat);
      ps2.setInt(3, duradaModificat);
      ps2.setInt(4, producteId);
      ps2.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new PersistenciaException("Error al modificar la canco.\n" + e.getMessage());
    } finally {
      if (ps != null) {
        try {
          ps.close();
        } catch (SQLException e) {
          e.printStackTrace();
          throw new PersistenciaException("Error en tancar la consulta per a modificar la canco.\n" + e.getMessage());
        }
      }
    }
  }

  public static void updateAlbum(int producteId, String titolModificat, String estilModificat, char ctiuModificat,
      int newAnyCreacioModificat) throws PersistenciaException {
    PreparedStatement ps = null;
    PreparedStatement ps2 = null;

    try {
      String sql = "UPDATE PRODUCTE SET producte_titol = ?, producte_actiu = ? , producte_estil = (SELECT estil_id FROM ESTIL WHERE estil_nom = ?) WHERE producte_id = ?";
      ps = Persistencia.getConnection().prepareStatement(sql);
      ps.setString(1, titolModificat);
      ps.setString(2, String.valueOf(ctiuModificat));
      ps.setString(3, estilModificat);
      ps.setInt(4, producteId);
      ps.executeUpdate();
      ps.close();
      String sql2 = "UPDATE ALBUM SET album_any_creacio = ? WHERE album_id = ?";
      ps2 = Persistencia.getConnection().prepareStatement(sql2);
      ps2.setInt(1, newAnyCreacioModificat);
      ps2.setInt(2, producteId);
      ps2.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new PersistenciaException("Error al modificar l'album.\n" + e.getMessage());
    } finally {
      if (ps != null) {
        try {
          ps.close();
        } catch (SQLException e) {
          e.printStackTrace();
          throw new PersistenciaException("Error en tancar la consulta per a modificar l'album.\n" + e.getMessage());
        }
      }
    }
  }

  public static List<Producte> getCanconsDisponibles() throws PersistenciaException {
    List<Producte> cancons = new ArrayList<>();
    PreparedStatement ps = null;
    try {
      String sql = "SELECT p.producte_titol FROM CANCO c INNER JOIN PRODUCTE p ON p.producte_id = c.canco_id WHERE c.canco_id NOT IN (SELECT album_con_id_canco FROM ALBUM_CONTINGUT)";
      ps = Persistencia.getConnection().prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        Producte canco = new Producte() {
          @Override
          public int getDurada() {
            return 0;
          }
        };
        canco.setProducte_titol(rs.getString("producte_titol"));
        cancons.add(canco);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw new PersistenciaException("Error al obtenir les cancons.\n" + e.getMessage());
    } finally {
      if (ps != null) {
        try {
          ps.close();
        } catch (SQLException e) {
          e.printStackTrace();
          throw new PersistenciaException(
              "Error en tancar la consulta per a recuperar les cancons.\n" + e.getMessage());
        }
      }
    }
    return cancons;
  }

  public static List<Producte> getCanconsAlbum(int idAlbum) throws PersistenciaException {
    List<Producte> cancons = new ArrayList<>();
    PreparedStatement ps = null;
    try {
      String sql = "SELECT p.producte_titol FROM CANCO c INNER JOIN PRODUCTE p ON p.producte_id = c.canco_id INNER JOIN ALBUM_CONTINGUT ac ON ac.album_con_id_canco = c.canco_id WHERE ac.album_con_id_album = ?";
      ps = Persistencia.getConnection().prepareStatement(sql);
      ps.setInt(1, idAlbum);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        Producte canco = new Producte() {
          @Override
          public int getDurada() {
            return 0;
          }
        };
        canco.setProducte_titol(rs.getString("producte_titol"));
        cancons.add(canco);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw new PersistenciaException("Error al obtenir les cancons.\n" + e.getMessage());
    } finally {
      if (ps != null) {
        try {
          ps.close();
        } catch (SQLException e) {
          e.printStackTrace();
          throw new PersistenciaException(
              "Error en tancar la consulta per a recuperar les cancons.\n" + e.getMessage());
        }
      }
    }
    return cancons;
  }

  public static void insertCancoAlbum(int idAlbum, String titolCanco, int posicio) throws PersistenciaException {
    PreparedStatement ps = null;
    try {
      String sql = "INSERT INTO ALBUM_CONTINGUT (album_con_id_album, album_con_id_canco, album_con_posicio) VALUES (?, (SELECT producte_id FROM Producte WHERE producte_titol = ?), ?)";
      ps = Persistencia.getConnection().prepareStatement(sql);
      ps.setInt(1, idAlbum);
      ps.setString(2, titolCanco);
      ps.setInt(3, posicio);
      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new PersistenciaException("Error al insertar la canco a l'album.\n" + e.getMessage());
    } finally {
      if (ps != null) {
        try {
          ps.close();
        } catch (SQLException e) {
          e.printStackTrace();
          throw new PersistenciaException(
              "Error en tancar la consulta per a insertar la canco a l'album.\n" + e.getMessage());
        }
      }
    }
  }

  public static void deleteCancoAlbum(int idAlbum, String titolCanco) throws PersistenciaException {
    PreparedStatement ps = null;
    try {
      String sql = "DELETE FROM ALBUM_CONTINGUT WHERE album_con_id_album = ? AND album_con_id_canco = (SELECT producte_id FROM Producte WHERE producte_titol = ?)";
      ps = Persistencia.getConnection().prepareStatement(sql);
      ps.setInt(1, idAlbum);
      ps.setString(2, titolCanco);
      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new PersistenciaException("Error al eliminar la canco de l'album.\n" + e.getMessage());
    } finally {
      if (ps != null) {
        try {
          ps.close();
        } catch (SQLException e) {
          e.printStackTrace();
          throw new PersistenciaException(
              "Error en tancar la consulta per a eliminar la canco de l'album.\n" + e.getMessage());
        }
      }
    }
  }

  public static void updateLlista(int producteId, String titolModificat, String estilModificat, char actiuModificat)
      throws PersistenciaException {
    PreparedStatement ps = null;
    try {
      String sql = "UPDATE PRODUCTE SET producte_titol = ?, producte_estil = (SELECT estil_id FROM ESTIL WHERE estil_nom = ?), producte_actiu = ? WHERE producte_id = ?";
      ps = Persistencia.getConnection().prepareStatement(sql);
      ps.setString(1, titolModificat);
      ps.setString(2, estilModificat);
      ps.setString(3, String.valueOf(actiuModificat));
      ps.setInt(4, producteId);
      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new PersistenciaException("Error al modificar la llista.\n" + e.getMessage());
    } finally {
      if (ps != null) {
        try {
          ps.close();
        } catch (SQLException e) {
          e.printStackTrace();
          throw new PersistenciaException(
              "Error en tancar la consulta per a modificar la llista.\n" + e.getMessage());
        }
      }
    }
  }

  public static List<Producte> getProductesDisponiblesLlista() throws PersistenciaException {
    List<Producte> productes = new ArrayList<>();
    PreparedStatement ps = null;
    try {
      String sql = "SELECT producte_titol FROM producte WHERE producte_id NOT IN (SELECT llista_con_id_producte FROM llista_contingut) AND producte.producte_tipus != 'L'";
      ps = Persistencia.getConnection().prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        Producte producte = new Producte() {
          @Override
          public int getDurada() {
            return 0;
          }
        };
        producte.setProducte_titol(rs.getString("producte_titol"));
        productes.add(producte);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw new PersistenciaException("Error al obtenir els productes disponibles.\n" + e.getMessage());
    } finally {
      if (ps != null) {
        try {
          ps.close();
        } catch (SQLException e) {
          e.printStackTrace();
          throw new PersistenciaException(
              "Error en tancar la consulta per a recuperar els productes disponibles.\n" + e.getMessage());
        }
      }
    }
    return productes;
  }

  public static List<Producte> getProductesEnLlista(int Llista) throws PersistenciaException {
    List<Producte> productes = new ArrayList<>();
    PreparedStatement ps = null;
    try {
      String sql = "SELECT producte_titol FROM producte WHERE producte_id IN (SELECT llista_con_id_producte FROM llista_contingut WHERE llista_con_id_llista = ?)";
      ps = Persistencia.getConnection().prepareStatement(sql);
      ps.setInt(1, Llista);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        Producte producte = new Producte() {
          @Override
          public int getDurada() {
            return 0;
          }
        };
        producte.setProducte_titol(rs.getString("producte_titol"));
        productes.add(producte);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw new PersistenciaException("Error al obtenir els productes de la llista.\n" + e.getMessage());
    } finally {
      if (ps != null) {
        try {
          ps.close();
        } catch (SQLException e) {
          e.printStackTrace();
          throw new PersistenciaException(
              "Error en tancar la consulta per a recuperar els productes de la llista.\n" + e.getMessage());
        }
      }
    }
    return productes;
  }

  public static void insertProducteLlista(int idLlista, String titolProducte, int posicion)
      throws PersistenciaException {
    PreparedStatement ps = null;
    try {
      String sql = "INSERT INTO LLISTA_CONTINGUT (llista_con_id_llista, llista_con_id_producte, llista_con_posicio) VALUES (?, (SELECT producte_id FROM producte WHERE producte_titol = ?), ?)";
      ps = Persistencia.getConnection().prepareStatement(sql);
      ps.setInt(1, idLlista);
      ps.setString(2, titolProducte);
      ps.setInt(3, posicion);
      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new PersistenciaException("Error al insertar el producte a la llista.\n" + e.getMessage());
    } finally {
      if (ps != null) {
        try {
          ps.close();
        } catch (SQLException e) {
          e.printStackTrace();
          throw new PersistenciaException(
              "Error en tancar la consulta per a insertar el producte a la llista.\n" + e.getMessage());
        }
      }
    }
  }

  public static void deleteProducteLlista(int idLlista, String titolProducte) throws PersistenciaException {
    PreparedStatement ps = null;
    try {
      String sql = "DELETE FROM LLISTA_CONTINGUT WHERE llista_con_id_llista = ? AND llista_con_id_producte = (SELECT producte_id FROM producte WHERE producte_titol = ?)";
      ps = Persistencia.getConnection().prepareStatement(sql);
      ps.setInt(1, idLlista);
      ps.setString(2, titolProducte);
      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new PersistenciaException("Error al eliminar el producte de la llista.\n" + e.getMessage());
    } finally {
      if (ps != null) {
        try {
          ps.close();
        } catch (SQLException e) {
          e.printStackTrace();
          throw new PersistenciaException(
              "Error en tancar la consulta per a eliminar el producte de la llista.\n" + e.getMessage());
        }
      }
    }
  }

  public static void deleteProducte(int idProducte) throws PersistenciaException {
    PreparedStatement ps = null;
    try {
      String sql = "DELETE FROM producte WHERE producte_id = ?";
      ps = Persistencia.getConnection().prepareStatement(sql);
      ps.setInt(1, idProducte);
      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new PersistenciaException("Error al eliminar el producte.\n" + e.getMessage());
    } finally {
      if (ps != null) {
        try {
          ps.close();
        } catch (SQLException e) {
          e.printStackTrace();
          throw new PersistenciaException(
              "Error en tancar la consulta per a eliminar el producte.\n" + e.getMessage());
        }
      }
    }
  }

  public static void insertCanco(String producte_titol, String estil_nom, char producte_actiu, char producte_tipus,
      int any_creacio_canco,
      String artista_nom, int canco_durada) throws PersistenciaException {
    PreparedStatement ps = null;
    PreparedStatement ps2 = null;
    try {
      String sql = "INSERT INTO producte (producte_titol, producte_actiu, producte_estil, producte_tipus) "
          + "VALUES (?, ?, (SELECT estil_id FROM estil WHERE estil_nom = ?), ?)";
      ps = Persistencia.getConnection().prepareStatement(sql);
      ps.setString(1, producte_titol);
      ps.setString(2, String.valueOf(producte_actiu));
      ps.setString(3, estil_nom);
      ps.setString(4, String.valueOf(producte_tipus));
      ps.executeUpdate();

      String sql2 = "INSERT INTO canco (canco_id, canco_durada, canco_any_creacio, CANCO_INTERPET) "
          + "VALUES ((SELECT producte_id FROM producte WHERE producte_titol = ?), ?, ?, (SELECT artista_id FROM artista WHERE artista_nom = ?))";
      ps2 = Persistencia.getConnection().prepareStatement(sql2);
      ps2.setString(1, producte_titol);
      ps2.setInt(2, canco_durada);
      ps2.setInt(3, any_creacio_canco);
      ps2.setString(4, artista_nom);
      ps2.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new PersistenciaException("Error al insertar la canco.\n" + e.getMessage());
    } finally {
      if (ps != null) {
        try {
          ps.close();
        } catch (SQLException e) {
          e.printStackTrace();
          throw new PersistenciaException(
              "Error en tancar la consulta per a insertar la canco/producte.\n" + e.getMessage());
        }
      }
      if (ps2 != null) {
        try {
          ps2.close();
        } catch (SQLException e) {
          e.printStackTrace();
          throw new PersistenciaException(
              "Error en tancar la consulta per a insertar la canco.\n" + e.getMessage());
        }
      }
    }
  }

  public static void insertAlbum(String producte_titol, String estil_nom, char producte_actiu, char producte_tipus,
      int any_creacio_canco) throws PersistenciaException {
    PreparedStatement ps = null;
    PreparedStatement ps2 = null;
    try {
      String sql = "INSERT INTO producte (producte_titol, producte_actiu, producte_estil, producte_tipus) "
          + "VALUES (?, ?, (SELECT estil_id FROM estil WHERE estil_nom = ?), ?)";
      ps = Persistencia.getConnection().prepareStatement(sql);
      ps.setString(1, producte_titol);
      ps.setString(2, String.valueOf(producte_actiu));
      ps.setString(3, estil_nom);
      ps.setString(4, String.valueOf(producte_tipus));
      ps.executeUpdate();
      String sql2 = "INSERT INTO album (album_id, album_any_creacio) "
          + "VALUES ((SELECT producte_id FROM producte WHERE producte_titol = ?), ?)";
      ps2 = Persistencia.getConnection().prepareStatement(sql2);
      ps2.setString(1, producte_titol);
      ps2.setInt(2, any_creacio_canco);
      ps2.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new PersistenciaException("Error al insertar l'album.\n" + e.getMessage());
    } finally {
      if (ps != null) {
        try {
          ps.close();
        } catch (SQLException e) {
          e.printStackTrace();
          throw new PersistenciaException(
              "Error en tancar la consulta per a insertar l'album/producte.\n" + e.getMessage());
        }
      }
    }
  }

  public static void insertLlista(String producte_titol, String estil_nom, char producte_actiu, char producte_tipus)
      throws PersistenciaException {
    PreparedStatement ps = null;
    PreparedStatement ps2 = null;
    try {
      String sql = "INSERT INTO producte (producte_titol, producte_actiu, producte_estil, producte_tipus) "
          + "VALUES (?, ?, (SELECT estil_id FROM estil WHERE estil_nom = ?), ?)";
      ps = Persistencia.getConnection().prepareStatement(sql);
      ps.setString(1, producte_titol);
      ps.setString(2, String.valueOf(producte_actiu));
      ps.setString(3, estil_nom);
      ps.setString(4, String.valueOf(producte_tipus));
      ps.executeUpdate();
      String sql2 = "INSERT INTO llista (llista_id) "
          + "VALUES ((SELECT producte_id FROM producte WHERE producte_titol = ?))";
      ps2 = Persistencia.getConnection().prepareStatement(sql2);
      ps2.setString(1, producte_titol);
      ps2.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new PersistenciaException("Error al insertar la llista.\n" + e.getMessage());
    } finally {
      if (ps != null) {
        try {
          ps.close();
        } catch (SQLException e) {
          e.printStackTrace();
          throw new PersistenciaException(
              "Error en tancar la consulta per a insertar la llista/producte.\n" + e.getMessage());
        }
      }
    }
  }
}