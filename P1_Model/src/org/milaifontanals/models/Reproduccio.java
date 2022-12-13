/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.models;

import java.sql.Timestamp;

/**
 *
 * @author Ion
 */
public class Reproduccio {
  private Timestamp d;
  private Producte p;
  private Client c;

  public Reproduccio() {

  }

  public Reproduccio(Client c, Producte p, Timestamp d) {
    this.c = c;
    this.p = p;
    this.d = d;
  }

  public Timestamp getD() {
    return d;
  }

  public void setD(Timestamp d) {
    this.d = d;
  }

  public Producte getP() {
    return p;
  }

  public void setP(Producte p) {
    this.p = p;
  }

  public Client getC() {
    return c;
  }

  public void setC(Client c) {
    this.c = c;
  }
}