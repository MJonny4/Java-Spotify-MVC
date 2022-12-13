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
public class Client {
  private long client_id;
  private String client_email;
  private String client_nom;
  private String client_cognoms;
  private Date client_data_naixement;
  private String client_postalcode;
  private String client_domicili1;
  private String client_domicili2;
  private String client_poblacio;
  private Pais client_pais;

  public Client(String client_nom) {
    this.client_nom = client_nom;
  }

  public Client(long client_id, String client_email, String client_nom, String client_cognoms,
      Date client_data_naixement, String client_postalcode, String client_domicili1, String client_domicili2,
      String client_poblacio) {
    this.client_id = client_id;
    this.client_email = client_email;
    this.client_nom = client_nom;
    this.client_cognoms = client_cognoms;
    this.client_data_naixement = client_data_naixement;
    this.client_postalcode = client_postalcode;
    this.client_domicili1 = client_domicili1;
    this.client_domicili2 = client_domicili2;
    this.client_poblacio = client_poblacio;
  }

  public Client(long client_id, String client_email, String client_nom, String client_cognoms,
      Date client_data_naixement, String client_postalcode, String client_domicili1, String client_domicili2,
      String client_poblacio, Pais client_pais) {
    this.client_id = client_id;
    this.client_email = client_email;
    this.client_nom = client_nom;
    this.client_cognoms = client_cognoms;
    this.client_data_naixement = client_data_naixement;
    this.client_postalcode = client_postalcode;
    this.client_domicili1 = client_domicili1;
    this.client_domicili2 = client_domicili2;
    this.client_poblacio = client_poblacio;
    this.client_pais = client_pais;
  }

  public long getClient_id() {
    return client_id;
  }

  public void setClient_id(long client_id) {
    this.client_id = client_id;
  }

  public String getClient_email() {
    return client_email;
  }

  public void setClient_email(String client_email) {
    this.client_email = client_email;
  }

  public String getClient_nom() {
    return client_nom;
  }

  public void setClient_nom(String client_nom) {
    this.client_nom = client_nom;
  }

  public String getClient_cognoms() {
    return client_cognoms;
  }

  public void setClient_cognoms(String client_cognoms) {
    this.client_cognoms = client_cognoms;
  }

  public Date getClient_data_naixement() {
    return client_data_naixement;
  }

  public void setClient_data_naixement(Date client_data_naixement) {
    this.client_data_naixement = client_data_naixement;
  }

  public String getClient_postalcode() {
    return client_postalcode;
  }

  public void setClient_postalcode(String client_postalcode) {
    this.client_postalcode = client_postalcode;
  }

  public String getclient_domicili1() {
    return client_domicili1;
  }

  public void setclient_domicili1(String client_domicili1) {
    this.client_domicili1 = client_domicili1;
  }

  public String getclient_domicili2() {
    return client_domicili2;
  }

  public void setclient_domicili2(String client_domicili2) {
    this.client_domicili2 = client_domicili2;
  }

  public String getClient_poblacio() {
    return client_poblacio;
  }

  public void setClient_poblacio(String client_poblacio) {
    this.client_poblacio = client_poblacio;
  }

  public Pais getClient_pais() {
    return client_pais;
  }

  public void setClient_pais(Pais client_pais) {
    this.client_pais = client_pais;
  }

}