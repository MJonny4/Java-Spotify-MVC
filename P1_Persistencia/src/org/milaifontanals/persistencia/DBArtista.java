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
import org.milaifontanals.models.Artista;

/**
 *
 * @author Ion
 */
public class DBArtista {
  public static String getInterpret(long producte_id) throws PersistenciaException {
    String artista = "";
    PreparedStatement ps = null;
    try {
      String sql = "SELECT a.artista_nom "
          + "FROM ARTISTA a, CANCO c "
          + "WHERE a.artista_id = c.canco_interpet "
          + "AND c.canco_id = ?";
      ps = Persistencia.getConnection().prepareStatement(sql);
      ps.setLong(1, producte_id);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        artista = rs.getString("artista_nom");
      }
      rs.close();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new PersistenciaException("Error al obtenir els artistes.\n" + e.getMessage());
    } finally {
      if (ps != null) {
        try {
          ps.close();
        } catch (SQLException e) {
          e.printStackTrace();
          throw new PersistenciaException(
              "Error en tancar la consulta per a recuperar els artistes.\n" + e.getMessage());
        }
      }
    }
    return artista;
  }

  public static List<Artista> getInterprets() throws PersistenciaException {
    List<Artista> artistes = new ArrayList<Artista>();
    PreparedStatement ps = null;
    try {
      String sql = "SELECT a.artista_nom "
          + "FROM ARTISTA a, CANCO c "
          + "WHERE a.artista_id = c.canco_interpet "
          + "GROUP BY a.artista_nom";
      ps = Persistencia.getConnection().prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        Artista a = new Artista() {
        };
        a.setArtista_nom(rs.getString("artista_nom"));
        artistes.add(a);
      }
      rs.close();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new PersistenciaException("Error al obtenir els artistes.\n" + e.getMessage());
    } finally {
      if (ps != null) {
        try {
          ps.close();
        } catch (SQLException e) {
          e.printStackTrace();
          throw new PersistenciaException(
              "Error en tancar la consulta per a recuperar els artistes.\n" + e.getMessage());
        }
      }
    }
    return artistes;
  }
}