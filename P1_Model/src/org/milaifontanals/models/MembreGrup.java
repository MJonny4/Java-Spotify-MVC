/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.models;

import java.util.Date;

/**
 *
 * @author Ion
 */
public class MembreGrup {
  private Date dataInici;
  private Date dataFi;
  private ArtistaIndividual membre;

  public MembreGrup(Date dataInici, Date dataFi, ArtistaIndividual membre) {
    this.dataInici = dataInici;
    this.dataFi = dataFi;
    this.membre = membre;
  }

  public Date getDataInici() {
    return dataInici;
  }

  public void setDataInici(Date dataInici) {
    this.dataInici = dataInici;
  }

  public Date getDataFi() {
    return dataFi;
  }

  public void setDataFi(Date dataFi) {
    this.dataFi = dataFi;
  }

  public ArtistaIndividual getMembre() {
    return membre;
  }

  public void setMembre(ArtistaIndividual membre) {
    this.membre = membre;
  }

}