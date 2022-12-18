/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.vistes;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author isard
 */
public class SmallIReport {
  public void LlistaReproduccionsProducte(int producte_id) throws Exception {
    Connection conn = null;
    try {
      Properties props = new Properties();
      props.load(new FileInputStream("keys.properties"));
      String[] claus = { "url", "user", "password" };
      String[] valors = new String[3];
      for (int i = 0; i < claus.length; i++) {
        valors[i] = props.getProperty(claus[i]);
        if (valors[i] == null || valors[i].isEmpty()) {
          throw new RuntimeException("L'arxiu " + "keys.properties" + " no troba la clau " + claus[i]);
        }
      }
      conn = DriverManager.getConnection(valors[0], valors[1], valors[2]);
      conn.setAutoCommit(false);

      JasperReport report = JasperCompileManager.compileReport("reportReproduccions.jrxml");
      System.out.println("Informe compilat");
      // Paràmetres pel report
      Map<String, Object> parameters = new HashMap<String, Object>();
      // Cal emplenar "parameters" amb els diferents paràmetres del Report
      parameters.put("paramProId", producte_id);
      // Genera el report (sense paràmetres)
      JasperPrint print = JasperFillManager.fillReport(report, parameters, conn);
      System.out.println("Informe generat");
      // Exporta informe a PDF (hi ha diverses opcions)
      JasperExportManager.exportReportToPdfFile(print, "ReproduccionsDelProducte.pdf");
      System.out.println("Informe exportat a PDF");
      // visualitza l'informe directament pel visor
      JasperViewer.viewReport(print, false);
      System.out.println("Informe visualitzat pel visor d'informes");
      conn.close();
    } catch (JRException | SQLException ex) {
      throw new Exception("Error al generar el report\n: " + ex.getMessage());
    } catch (IOException ex) {
      throw new Exception("Error , problemes en trobar el fitxer\n: " + ex.getMessage());
    }

  }
}
