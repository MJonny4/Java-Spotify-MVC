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
public class Canco extends Producte {
  private int canco_any_creacio;
  private int durada;
  private Artista interpret;
  private List<Artista> autors;

  public Canco() {
  }

  public Canco(long producte_id, String producte_titol, char producte_actiu, TipusProducteEnum producte_tipus,
      Estil producte_estil, int canco_any_creacio, int durada, Artista interpret, List<Artista> autors) {
    super(producte_id, producte_titol, producte_actiu, producte_tipus, producte_estil);
    this.canco_any_creacio = canco_any_creacio;
    this.durada = durada;
    this.interpret = interpret;
    this.autors = autors;
  }

  public int getCanco_any_creacio() {
    return canco_any_creacio;
  }

  public void setCanco_any_creacio(int canco_any_creacio) {
    this.canco_any_creacio = canco_any_creacio;
  }

  public void setInterpret(Artista interpret) {
    this.interpret = interpret;
  }

  public void setAutors(List<Artista> autors) {
    this.autors = autors;
  }

  public void setDurada(int durada) {
    this.durada = durada;
  }

  @Override
  public int getDurada() {
    return (int) durada;
  }

  public Artista getInterpret() {
    return interpret;
  }

  public Iterator<Artista> getAutors() {
    Iterator<Artista> it = new Iterator<Artista>() {
      private int index = 0;

      @Override
      public boolean hasNext() {
        return index < autors.size();
      }

      @Override
      public Artista next() {
        return autors.get(index++);
      }
    };
    return it;
  }
}