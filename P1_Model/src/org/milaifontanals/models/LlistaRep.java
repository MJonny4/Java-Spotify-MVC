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
public class LlistaRep extends Producte {
  private List<Producte> listRep;
  private int llistaDurada;

  public LlistaRep() {
  }

  public LlistaRep(long producte_id, String producte_titol, char producte_actiu, TipusProducteEnum producte_tipus,
      Estil producte_estil, List<Producte> listRep) {
    super(producte_id, producte_titol, producte_actiu, producte_tipus, producte_estil);
    this.listRep = listRep;
  }

  public List<Producte> getListRep() {
    return listRep;
  }

  public void setListRep(List<Producte> listRep) {
    this.listRep = listRep;
  }

  @Override
  public int getDurada() {
    int durada = 0;
    for (Producte producte : listRep) {
      durada += producte.getDurada();
    }
    return durada;
  }

  public Iterator<Producte> getItems() {
    Iterator<Producte> it = new Iterator<Producte>() {
      private int index = 0;

      @Override
      public boolean hasNext() {
        return index < listRep.size();
      }

      @Override
      public Producte next() {
        return listRep.get(index++);
      }
    };
    return it;
  }

  public int getNumItems() {
    return listRep.size();
  }

  public Producte getItem(int idx) {
    return listRep.get(idx);
  }

  public void addItem(Producte p) {
    listRep.add(p);
  }

  public Producte removeItem(int idx) {
    return listRep.remove(idx);
  }

  public int getLlistaDurada() {
    return llistaDurada;
  }

  public void setLlistaDurada(int llistaDurada) {
    this.llistaDurada = llistaDurada;
  }
}