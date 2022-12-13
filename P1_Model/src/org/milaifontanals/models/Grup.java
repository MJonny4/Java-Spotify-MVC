/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ion
 */
public class Grup extends Artista {

  private String grup_data_creacio;
  private List<MembreGrup> membres;

  public Grup(long artista_id, String artista_nom, TipusArtistaEnum artista_tipus, String grup_data_creacio,
      List<MembreGrup> membres) {
    super(artista_id, artista_nom, artista_tipus);
    this.grup_data_creacio = grup_data_creacio;
    this.membres = membres;
  }

  public String getGrup_data_creacio() {
    return grup_data_creacio;
  }

  public void setGrup_data_creacio(String grup_data_creacio) {
    this.grup_data_creacio = grup_data_creacio;
  }

  public List<ArtistaIndividual> getMembres() {
    List<ArtistaIndividual> listArtistaIndividual = new ArrayList<ArtistaIndividual>();
    for (MembreGrup membreGrup : membres) {
      listArtistaIndividual.add(membreGrup.getMembre());
    }
    return listArtistaIndividual;
  }

  public void setMembres(List<MembreGrup> membres) {
    this.membres = membres;
  }

  public void addMembre(ArtistaIndividual artista, Date dataInici, Date dataFi) {
    membres.add(new MembreGrup(dataInici, dataFi, artista));
  }

  public void removeMembre(int id) {
    for (MembreGrup membreGrup : membres) {
      if (membreGrup.getMembre().getArtista_id() == id) {
        membres.remove(membreGrup);
      }
    }
  }
}