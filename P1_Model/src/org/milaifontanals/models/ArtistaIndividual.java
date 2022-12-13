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
public class ArtistaIndividual extends Artista {

  private Date ind_data_naixement;
  private Pais ind_pais_naixement;

  public ArtistaIndividual(long artista_id, String artista_nom, TipusArtistaEnum artista_tipus, Date ind_data_naixement,
      Pais ind_pais_naixement) {
    super(artista_id, artista_nom, artista_tipus);
    this.ind_data_naixement = ind_data_naixement;
    this.ind_pais_naixement = ind_pais_naixement;
  }

  public Date getInd_data_naixement() {
    return ind_data_naixement;
  }

  public void setInd_data_naixement(Date ind_data_naixement) {
    this.ind_data_naixement = ind_data_naixement;
  }

  public Pais getInd_pais_naixement() {
    return ind_pais_naixement;
  }

  public void setInd_pais_naixement(Pais ind_pais_naixement) {
    this.ind_pais_naixement = ind_pais_naixement;
  }

}