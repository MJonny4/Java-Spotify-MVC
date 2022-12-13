/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.models;

import java.util.List;

/**
 *
 * @author Ion
 */
public abstract class Artista {
  private long artista_id;
  private String artista_nom;
  private TipusArtistaEnum artista_tipus;
  private List<Canco> cancons;

  public Artista() {
  }

  public Artista(long artista_id, String artista_nom, TipusArtistaEnum artista_tipus) {
    this.artista_id = artista_id;
    this.artista_nom = artista_nom;
    this.artista_tipus = artista_tipus;
  }

  public Artista(long artista_id, String artista_nom, TipusArtistaEnum artista_tipus, List<Canco> cancons) {
    this.artista_id = artista_id;
    this.artista_nom = artista_nom;
    this.artista_tipus = artista_tipus;
    this.cancons = cancons;
  }

  public long getArtista_id() {
    return artista_id;
  }

  public void setArtista_id(long artista_id) {
    this.artista_id = artista_id;
  }

  public String getArtista_nom() {
    return artista_nom;
  }

  public void setArtista_nom(String artista_nom) {
    this.artista_nom = artista_nom;
  }

  public TipusArtistaEnum getArtista_tipus() {
    return artista_tipus;
  }

  public void setArtista_tipus(TipusArtistaEnum artista_tipus) {
    this.artista_tipus = artista_tipus;
  }

  public List<Canco> getCanconsInterpredades() {
    return cancons;
  }

  public void setCancons(List<Canco> cancons) {
    this.cancons = cancons;
  }
}