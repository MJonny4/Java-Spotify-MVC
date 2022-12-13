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
public class ItemsLlista {
  private Producte producte;
  private int llista_posicio;

  public ItemsLlista() {
  }

  public ItemsLlista(Producte producte, int llista_posicio) {
    this.producte = producte;
    this.llista_posicio = llista_posicio;
  }

  public Producte getProducte() {
    return producte;
  }

  public void setProducte(Producte producte) {
    this.producte = producte;
  }

  public int getLlista_posicio() {
    return llista_posicio;
  }

  public void setLlista_posicio(int llista_posicio) {
    this.llista_posicio = llista_posicio;
  }

}
