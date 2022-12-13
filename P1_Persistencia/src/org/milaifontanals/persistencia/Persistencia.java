/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.persistencia;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Ion
 */
public class Persistencia {
  private static Connection con;

  private Persistencia() throws PersistenciaException {
    this("keys.properties");
  }

  private Persistencia(String propsFitxer) throws PersistenciaException {
    try {
      Properties props = new Properties();
      props.load(new FileInputStream(propsFitxer));
      String[] claus = { "url", "user", "pass" };
      String[] valors = new String[3];
      for (int i = 0; i < claus.length; i++) {
        valors[i] = props.getProperty(claus[i]);
        if (valors[i] == null || valors[i].isEmpty()) {
          throw new PersistenciaException("No s'ha trobat la clau " + claus[i] + " al fitxer " + propsFitxer);
        }
      }
      con = DriverManager.getConnection(valors[0], valors[1], valors[2]);
      con.setAutoCommit(false);
    } catch (IOException ex) {
      throw new PersistenciaException("No s'ha trobat el fitxer " + propsFitxer + "\n" + ex.getMessage());
    } catch (SQLException ex) {
      throw new PersistenciaException("No s'ha pogut connectar a la base de dades.\n" + ex.getMessage());
    }
  }

  public static Connection getConnection() throws PersistenciaException {
    if (con == null) {
      new Persistencia();
    }
    return con;
  }

  public static void close() throws PersistenciaException {
    if (con != null) {
      try {
        con.rollback();
      } catch (SQLException ex) {
        throw new PersistenciaException("No s'ha pogut fer el rollback final.\n" + ex.getMessage());
      }
      try {
        con.close();
      } catch (SQLException ex) {
        throw new PersistenciaException("No s'ha pogut tancar la connexió.\n" + ex.getMessage());
      }
    }
  }

  public static void commit() throws PersistenciaException {
    try {
      con.commit();
    } catch (SQLException ex) {
      throw new PersistenciaException("No s'ha pogut fer el commit.\n" + ex.getMessage());
    }
  }

  public static void rollback() throws PersistenciaException {
    try {
      con.rollback();
    } catch (SQLException ex) {
      throw new PersistenciaException("No s'ha pogut fer el rollback.\n" + ex.getMessage());
    }
  }
}