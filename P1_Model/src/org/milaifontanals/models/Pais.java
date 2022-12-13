/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.models;

/**
 *
 * @author Ion
 */
public class Pais {
  private String pais_iso;
  private String pais_nom;

  public Pais() {

  }

  public Pais(String pais_iso, String pais_nom) {
    this.pais_iso = pais_iso;
    this.pais_nom = pais_nom;
  }

  public String getPais_iso() {
    return pais_iso;
  }

  public void setPais_iso(String pais_iso) {
    this.pais_iso = pais_iso;
  }

  public String getPais_nom() {
    return pais_nom;
  }

  public void setPais_nom(String pais_nom) {
    this.pais_nom = pais_nom;
  }
}