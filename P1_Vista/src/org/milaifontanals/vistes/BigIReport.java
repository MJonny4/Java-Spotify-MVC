/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.vistes;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author isard
 */
public class BigIReport {
  private static String nomFitxer;
  private static final int BUFFER_SIZE = 4096;

//  private BigIReport() throws Exception {
//    nomFitxer = "ireport.properties";
//  }
//
//  private BigIReport(String nomFitxer) throws Exception {
//    try {
//      Properties props = new Properties();
//      props.load(new FileInputStream(nomFitxer));
//      String claus[] = { "urlReport", "userReport", "passwordReport" };
//      String valors[] = new String[3];
//      for (int i = 0; i < claus.length; i++) {
//        valors[i] = props.getProperty(claus[i]);
//        if (valors[i] == null || valors[i].isEmpty()) {
//          throw new Exception("No s'ha trobat la clau " + claus[i] + " al fitxer " + nomFitxer);
//        }
//      }
//    } catch (Exception ex) {
//      throw new Exception("No s'ha trobat el fitxer " + nomFitxer + "\n" + ex.getMessage());
//    }
//  }

  public static void generarInforme(char[] tipus, String actiu, String titol) {
    try {
      Properties propietats = new Properties();
      propietats.load(new FileInputStream("ireport.properties"));
      String[] claus = { "urlReport", "userReport", "passwordReport" };
      String[] valors = new String[3];
      for (int i = 0; i < claus.length; i++) {
        valors[i] = propietats.getProperty(claus[i]);
        if (valors[i] == null || valors[i].isEmpty()) {
          throw new Exception("No s'ha trobat la clau " + claus[i] + " al fitxer " + nomFitxer);
        }
      }
      String filtresProducte = "";

      if (tipus != null && tipus.length > 0) {
        for (int i = 0; i < tipus.length; i++) {
          filtresProducte += "&ionTipus=" + tipus[i];
        }
      }

      if (actiu != null && actiu.length() > 0) {
        filtresProducte += "&ionActiu=" + actiu;
      }

      if (titol != null && !titol.isEmpty()) {
        titol = titol.replaceAll("\\s", "");
        filtresProducte += "&ionTitol=" + titol;
      }

      String url = valors[0] + "?" + filtresProducte;
      URL obj = new URL(url);
      HttpURLConnection con = (HttpURLConnection) obj.openConnection();
      con.setRequestMethod("GET");
      String auth = Base64.getEncoder().encodeToString((valors[1] + ":" + valors[2]).getBytes());
      con.setRequestProperty("Authorization", "Basic " + auth);
      int responseCode = con.getResponseCode();
      if (responseCode == HttpURLConnection.HTTP_OK) {
        String fileName = "";
        String disposition = con.getHeaderField("Content-Disposition");
        String contentType = con.getContentType();
        int contentLength = con.getContentLength();

        if (disposition != null) {
          int index = disposition.indexOf("filename=");
          if (index > 0) {
            fileName = disposition.substring(index + 10, disposition.length() - 1);
          }
        } else {
          int posArguments = url.indexOf("?");
          if (posArguments == -1) {
            fileName = url.substring(url.lastIndexOf("/") + 1, url.length());
          } else {
            fileName = url.substring(url.lastIndexOf("/") + 1, posArguments);
          }
        }

        System.out.println("Content-Type = " + contentType);
        System.out.println("Content-Disposition = " + disposition);
        System.out.println("Content-Length = " + contentLength);
        System.out.println("fileName = " + fileName);

        InputStream inputStream = con.getInputStream();
        FileOutputStream outputStream = new FileOutputStream(fileName);
        int bytesRead = -1;
        byte[] buffer = new byte[BUFFER_SIZE];
        while ((bytesRead = inputStream.read(buffer)) != -1) {
          outputStream.write(buffer, 0, bytesRead);
        }
        outputStream.close();
        inputStream.close();

        JOptionPane.showMessageDialog(null, "Fitxer descarregat");

        if (Desktop.isDesktopSupported()) {
          try {
            Desktop.getDesktop().open(new File(fileName));
          } catch (IOException ex) {
            System.out.println("No hi ha aplicacions disponibles per obrir el fitxer.º\n" + ex.getMessage());
          }
        }
      } else {
        System.out.println("Mètode GET no ha funcionat!" + url);
        System.out.println("Codi de resposta: " + responseCode);
        System.out.println("Missatge de resposta: " + con.getResponseMessage());
      }
      con.disconnect();
    } catch (Exception ex) {
      System.out.println("Error al generar l'informe: " + ex.getMessage());
    }
  }
}