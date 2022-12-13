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
public abstract class Producte {
  private long producte_id;
  private String producte_titol;
  private char producte_actiu;
  private TipusProducteEnum producte_tipus;
  private Estil producte_estil;

  public Producte() {
  }

  public Producte(long producte_id, String producte_titol, char producte_actiu, TipusProducteEnum producte_tipus,
      Estil producte_estil) {
    this.producte_id = producte_id;
    this.producte_titol = producte_titol;
    this.producte_actiu = producte_actiu;
    this.producte_tipus = producte_tipus;
    this.producte_estil = producte_estil;
  }

  public long getProd_id() {
    return producte_id;
  }

  public void setProd_id(long producte_id) {
    this.producte_id = producte_id;
  }

  public String getProducte_titol() {
    return producte_titol;
  }

  public void setProducte_titol(String producte_titol) {
    this.producte_titol = producte_titol;
  }

  public char getProducte_actiu() {
    return producte_actiu;
  }

  public void setProducte_actiu(char producte_actiu) {
    this.producte_actiu = producte_actiu;
  }

  public TipusProducteEnum getProducte_tipus() {
    return producte_tipus;
  }

  public void setProducte_tipus(TipusProducteEnum producte_tipus) {
    this.producte_tipus = producte_tipus;
  }

  public Estil getProducte_estil() {
    return producte_estil;
  }

  public void setProducte_estil(Estil producte_estil) {
    this.producte_estil = producte_estil;
  }

  public abstract int getDurada();
}