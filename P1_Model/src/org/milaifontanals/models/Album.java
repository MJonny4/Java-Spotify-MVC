/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.models;

import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Ion
 */
public class Album extends Producte {
  private int album_any_creacio;
  private int album_durada;
  private int posicio;
  private List<Canco> cancons;

  public Album() {
  }

  public Album(long producte_id, String producte_titol, char producte_actiu, TipusProducteEnum producte_tipus,
      Estil producte_estil, int album_any_creacio, List<Canco> cancons) {
    super(producte_id, producte_titol, producte_actiu, producte_tipus, producte_estil);
    this.album_any_creacio = album_any_creacio;
    this.cancons = cancons;
  }

  public int getAlbum_any_creacio() {
    return album_any_creacio;
  }

  public void setAlbum_any_creacio(int album_any_creacio) {
    this.album_any_creacio = album_any_creacio;
  }

  public Iterator<Canco> getCancons() {
    Iterator<Canco> it = new Iterator<Canco>() {
      private int index = 0;

      @Override
      public boolean hasNext() {
        return index < cancons.size();
      }

      @Override
      public Canco next() {
        return cancons.get(index++);
      }
    };
    return it;
  }

  public void setCancons(List<Canco> cancons) {
    this.cancons = cancons;
  }

  @Override
  public int getDurada() {
    int durada = 0;
    for (Canco canco : cancons) {
      durada += canco.getDurada();
    }
    return durada;
  }

  public int getNumCancons() {
    return cancons.size();
  }

  public void getCanco(int idx) {
    cancons.get(idx);
  }

  public void removeCanco(int idx) {
    cancons.remove(idx);
  }

  public void addCanco(Canco canco) {
    cancons.add(canco);
  }

  public int getAlbum_durada() {
    return album_durada;
  }

  public void setAlbum_durada(int album_durada) {
    this.album_durada = album_durada;
  }

  public int getPosicio() {
    return posicio;
  }

  public void setPosicio(int posicio) {
    this.posicio = posicio;
  }

}
