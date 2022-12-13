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
public class Estil {
  private int estil_id;
  private String estil_nom;

  public Estil() {
  }

  public Estil(int estil_id, String estil_nom) {
    this.estil_id = estil_id;
    this.estil_nom = estil_nom;
  }

  public int getEstil_id() {
    return estil_id;
  }

  public void setEstil_id(int estil_id) {
    this.estil_id = estil_id;
  }

  public String getEstil_nom() {
    return estil_nom;
  }

  public void setEstil_nom(String estil_nom) {
    this.estil_nom = estil_nom;
  }
}
