/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.vistes;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ion
 */
public class VistaProducte extends JPanel {
  JLabel labelTitolProductes = new JLabel("Productes");
  JLabel labelFiltrar = new JLabel("Filtrar per");
  JLabel labelTipusFiltrar = new JLabel("Tipus:");
  JCheckBox cbCancoFiltra = new JCheckBox("Cançó");
  JCheckBox cbAlbumFiltra = new JCheckBox("Àlbum");
  JCheckBox cbLlistaFiltra = new JCheckBox("Llista");
  JLabel labelActiuFiltrar = new JLabel("Actiu:");
  JRadioButton rbSiActiuFiltra = new JRadioButton("Sí");
  JRadioButton rbNoActiuFiltra = new JRadioButton("No");
  JRadioButton rbTotsActiuFiltra = new JRadioButton("Tots");
  JLabel labelTitolFiltrar = new JLabel("Títol:");
  JTextField textTitolFiltra = new JTextField();
  JButton buttonFiltrar = new JButton("Filtrar");
  JTable tableProductes = new JTable();
  DefaultTableModel modelProductes = new DefaultTableModel();
  JButton buttonAfegir = new JButton("Afegir");
  JButton buttonModificar = new JButton("Modificar");
  JButton buttonNetejar = new JButton("Netejar");
  JButton buttonImprimir = new JButton("Imprimir");
  JButton buttonEliminar = new JButton("Eliminar");

  JLabel labelTitolModProductes = new JLabel("Afegir o Modificar Productes");
  JTextField textIdHiddenMod = new JTextField();
  JLabel labelTipusMod = new JLabel("Tipus:");
  JComboBox<String> cbTipusMod = new JComboBox<>();
  JLabel labelTitolMod = new JLabel("Títol:");
  JTextField textTitolMod = new JTextField();
  JLabel labelEstilMod = new JLabel("Estil:");
  JComboBox<String> cbEstilMod = new JComboBox<>();
  JLabel labelActiuMod = new JLabel("Actiu:");
  JRadioButton rbSiActiuMod = new JRadioButton("Sí");
  JRadioButton rbNoActiuMod = new JRadioButton("No");
  JLabel labelDuradaMod = new JLabel("Durada:");
  JTextField textDuradaMod = new JTextField();
  JLabel labelAnyCreacioMod = new JLabel("Any de Creació:");
  JTextField textAnyCreacioMod = new JTextField();
  JLabel labelInterpretsMod = new JLabel("Intèrprets:");
  JComboBox<String> cbInterpretsMod = new JComboBox<>();
  JButton buttonCancelarMod = new JButton("Cancelar");
  JButton buttonGuardarMod = new JButton("Guardar");
  JLabel labelErrorMod = new JLabel();

  JTable tableAlbumOLlista = new JTable();
  DefaultTableModel modelAlbumOLlista = new DefaultTableModel();
  JLabel labelDisponibles = new JLabel("Productes disponibles");
  JComboBox<String> cbDisponibles = new JComboBox<>();
  JButton buttonAfegirAlbumOLlista = new JButton("Afegir");
  JLabel labelNoDisponibles = new JLabel("Productes Utilitzats");
  JComboBox<String> cbNoDisponibles = new JComboBox<>();
  JButton buttonEliminarAlbumOLlista = new JButton("Eliminar");

  public void afegirComponents() {
    //  * labelTitolProductes
    labelTitolProductes.setBounds(10, 10, 100, 30);
    labelTitolProductes.setFont(new java.awt.Font("Tahoma", 1, 18));
    labelTitolProductes.setPreferredSize(new java.awt.Dimension(100, 30));
    this.add(labelTitolProductes);
    //  * labelFiltrar
    labelFiltrar.setBounds(10, 50, 100, 30);
    labelFiltrar.setFont(new java.awt.Font("Tahoma", 1, 14));
    labelFiltrar.setPreferredSize(new java.awt.Dimension(100, 30));
    this.add(labelFiltrar);
    //  * labelTipusFiltrar
    labelTipusFiltrar.setBounds(10, 90, 100, 30);
    labelTipusFiltrar.setFont(new java.awt.Font("Tahoma", 1, 12));
    labelTipusFiltrar.setPreferredSize(new java.awt.Dimension(100, 30));
    this.add(labelTipusFiltrar);
    //  * cbCancoFiltra
    cbCancoFiltra.setBounds(10, 120, 100, 30);
    cbCancoFiltra.setFont(new java.awt.Font("Tahoma", 1, 12));
    cbCancoFiltra.setPreferredSize(new java.awt.Dimension(100, 30));
    this.add(cbCancoFiltra);
    //  * cbAlbumFiltra
    cbAlbumFiltra.setBounds(10, 150, 100, 30);
    cbAlbumFiltra.setFont(new java.awt.Font("Tahoma", 1, 12));
    cbAlbumFiltra.setPreferredSize(new java.awt.Dimension(100, 30));
    this.add(cbAlbumFiltra);
    //  * cbLlistaFiltra
    cbLlistaFiltra.setBounds(10, 180, 100, 30);
    cbLlistaFiltra.setFont(new java.awt.Font("Tahoma", 1, 12));
    cbLlistaFiltra.setPreferredSize(new java.awt.Dimension(100, 30));
    this.add(cbLlistaFiltra);
    //  * labelActiuFiltrar
    labelActiuFiltrar.setBounds(10, 220, 100, 30);
    labelActiuFiltrar.setFont(new java.awt.Font("Tahoma", 1, 12));
    labelActiuFiltrar.setPreferredSize(new java.awt.Dimension(100, 30));
    this.add(labelActiuFiltrar);
    //  * rbSiActiuFiltra
    rbSiActiuFiltra.setBounds(10, 250, 100, 30);
    rbSiActiuFiltra.setFont(new java.awt.Font("Tahoma", 1, 12));
    rbSiActiuFiltra.setPreferredSize(new java.awt.Dimension(100, 30));
    this.add(rbSiActiuFiltra);
    //  * rbNoActiuFiltra
    rbNoActiuFiltra.setBounds(10, 280, 100, 30);
    rbNoActiuFiltra.setFont(new java.awt.Font("Tahoma", 1, 12));
    rbNoActiuFiltra.setPreferredSize(new java.awt.Dimension(100, 30));
    this.add(rbNoActiuFiltra);
    //  * rbTotsActiuFiltra
    rbTotsActiuFiltra.setBounds(10, 310, 100, 30);
    rbTotsActiuFiltra.setFont(new java.awt.Font("Tahoma", 1, 12));
    rbTotsActiuFiltra.setPreferredSize(new java.awt.Dimension(100, 30));
    this.add(rbTotsActiuFiltra);
    //  * labelTitolFiltrar
    labelTitolFiltrar.setBounds(10, 350, 100, 30);
    labelTitolFiltrar.setFont(new java.awt.Font("Tahoma", 1, 12));
    labelTitolFiltrar.setPreferredSize(new java.awt.Dimension(100, 30));
    this.add(labelTitolFiltrar);
    //  * textTitolFiltra
    textTitolFiltra.setBounds(10, 380, 100, 30);
    textTitolFiltra.setFont(new java.awt.Font("Tahoma", 1, 12));
    textTitolFiltra.setPreferredSize(new java.awt.Dimension(100, 30));
    this.add(textTitolFiltra);
    //  * buttonFiltrar
    buttonFiltrar.setBounds(10, 420, 100, 30);
    buttonFiltrar.setFont(new java.awt.Font("Tahoma", 1, 12));
    buttonFiltrar.setPreferredSize(new java.awt.Dimension(100, 30));
    this.add(buttonFiltrar);
    //  ************************************************************
    //  * tableProductes
    tableProductes.setBounds(120, 50, 500, 400);
    tableProductes.setFont(new java.awt.Font("Tahoma", 1, 12));
    tableProductes.setPreferredSize(new java.awt.Dimension(500, 400));
    this.add(tableProductes);
    //  * modelProductes
    modelProductes.addColumn("Titol");
    modelProductes.addColumn("Estil");
    modelProductes.addColumn("Tipus");
    modelProductes.addColumn("Actiu");
    //  * buttonAfegir
    buttonAfegir.setBounds(120, 460, 100, 30);
    buttonAfegir.setFont(new java.awt.Font("Tahoma", 1, 12));
    buttonAfegir.setPreferredSize(new java.awt.Dimension(100, 30));
    this.add(buttonAfegir);
    //  * buttonModificar
    buttonModificar.setBounds(230, 460, 100, 30);
    buttonModificar.setFont(new java.awt.Font("Tahoma", 1, 12));
    buttonModificar.setPreferredSize(new java.awt.Dimension(100, 30));
    this.add(buttonModificar);
    //  * buttonNetejar
    buttonNetejar.setBounds(230, 500, 100, 30);
    buttonNetejar.setFont(new java.awt.Font("Tahoma", 1, 12));
    buttonNetejar.setPreferredSize(new java.awt.Dimension(100, 30));
    this.add(buttonNetejar);
    //  * buttonImprimir
    buttonImprimir.setBounds(340, 460, 100, 30);
    buttonImprimir.setFont(new java.awt.Font("Tahoma", 1, 12));
    buttonImprimir.setPreferredSize(new java.awt.Dimension(100, 30));
    this.add(buttonImprimir);
    //  * buttonEliminar
    buttonEliminar.setBounds(520, 460, 100, 30);
    buttonEliminar.setFont(new java.awt.Font("Tahoma", 1, 12));
    buttonEliminar.setPreferredSize(new java.awt.Dimension(100, 30));
    this.add(buttonEliminar);

    //  ************************************************************
    //  * labelTitolModProductes
    labelTitolModProductes.setBounds(680, 10, 300, 30);
    labelTitolModProductes.setFont(new java.awt.Font("Tahoma", 1, 14));
    labelTitolModProductes.setPreferredSize(new java.awt.Dimension(300, 30));
    this.add(labelTitolModProductes);
    //  * textIdHiddenMod
    textIdHiddenMod.setBounds(680, 40, 200, 30);
    textIdHiddenMod.setFont(new java.awt.Font("Tahoma", 1, 12));
    textIdHiddenMod.setPreferredSize(new java.awt.Dimension(200, 30));
    this.add(textIdHiddenMod);
    //  * labelTipusMod
    labelTipusMod.setBounds(680, 50, 200, 30);
    labelTipusMod.setFont(new java.awt.Font("Tahoma", 1, 12));
    labelTipusMod.setPreferredSize(new java.awt.Dimension(200, 30));
    this.add(labelTipusMod);
    //  * cbTipusMod
    cbTipusMod.setBounds(680, 80, 200, 30);
    cbTipusMod.setFont(new java.awt.Font("Tahoma", 1, 12));
    cbTipusMod.setPreferredSize(new java.awt.Dimension(200, 30));
    this.add(cbTipusMod);
    //  * labelTitolMod
    labelTitolMod.setBounds(680, 110, 200, 30);
    labelTitolMod.setFont(new java.awt.Font("Tahoma", 1, 12));
    labelTitolMod.setPreferredSize(new java.awt.Dimension(200, 30));
    this.add(labelTitolMod);
    //  * textTitolMod
    textTitolMod.setBounds(680, 140, 200, 30);
    textTitolMod.setFont(new java.awt.Font("Tahoma", 1, 12));
    textTitolMod.setPreferredSize(new java.awt.Dimension(200, 30));
    this.add(textTitolMod);
    //  * labelEstilMod
    labelEstilMod.setBounds(680, 170, 200, 30);
    labelEstilMod.setFont(new java.awt.Font("Tahoma", 1, 12));  
    labelEstilMod.setPreferredSize(new java.awt.Dimension(200, 30));
    this.add(labelEstilMod);
    //  * cbEstilMod
    cbEstilMod.setBounds(680, 200, 200, 30);
    cbEstilMod.setFont(new java.awt.Font("Tahoma", 1, 12));
    cbEstilMod.setPreferredSize(new java.awt.Dimension(200, 30));
    this.add(cbEstilMod);
    //  * labelActiuMod
    labelActiuMod.setBounds(680, 230, 200, 30);
    labelActiuMod.setFont(new java.awt.Font("Tahoma", 1, 12));
    labelActiuMod.setPreferredSize(new java.awt.Dimension(200, 30));
    this.add(labelActiuMod);
    //  * rbSiActiuMod
    rbSiActiuMod.setBounds(680, 260, 100, 30);
    rbSiActiuMod.setFont(new java.awt.Font("Tahoma", 1, 12));
    rbSiActiuMod.setPreferredSize(new java.awt.Dimension(100, 30));
    this.add(rbSiActiuMod);
    //  * rbNoActiuMod
    rbNoActiuMod.setBounds(680, 290, 100, 30);
    rbNoActiuMod.setFont(new java.awt.Font("Tahoma", 1, 12));
    rbNoActiuMod.setPreferredSize(new java.awt.Dimension(100, 30));
    this.add(rbNoActiuMod);
    //  * labelDuradaMod
    labelDuradaMod.setBounds(680, 320, 200, 30);
    labelDuradaMod.setFont(new java.awt.Font("Tahoma", 1, 12));
    labelDuradaMod.setPreferredSize(new java.awt.Dimension(200, 30));
    this.add(labelDuradaMod);
    //  * textDuradaMod
    textDuradaMod.setBounds(680, 350, 200, 30);
    textDuradaMod.setFont(new java.awt.Font("Tahoma", 1, 12));
    textDuradaMod.setPreferredSize(new java.awt.Dimension(200, 30));
    this.add(textDuradaMod);
    //  * labelAnyCreacioMod
    labelAnyCreacioMod.setBounds(680, 380, 200, 30);
    labelAnyCreacioMod.setFont(new java.awt.Font("Tahoma", 1, 12));
    labelAnyCreacioMod.setPreferredSize(new java.awt.Dimension(200, 30));
    this.add(labelAnyCreacioMod);
    //  * textAnyCreacioMod
    textAnyCreacioMod.setBounds(680, 410, 200, 30);
    textAnyCreacioMod.setFont(new java.awt.Font("Tahoma", 1, 12));
    textAnyCreacioMod.setPreferredSize(new java.awt.Dimension(200, 30));
    this.add(textAnyCreacioMod);
    //  * labelInterpretsMod
    labelInterpretsMod.setBounds(680, 440, 200, 30);
    labelInterpretsMod.setFont(new java.awt.Font("Tahoma", 1, 12));
    labelInterpretsMod.setPreferredSize(new java.awt.Dimension(200, 30));
    this.add(labelInterpretsMod);
    //  * cbInterpretsMod
    cbInterpretsMod.setBounds(680, 470, 200, 30);
    cbInterpretsMod.setFont(new java.awt.Font("Tahoma", 1, 12));
    cbInterpretsMod.setPreferredSize(new java.awt.Dimension(200, 30));
    this.add(cbInterpretsMod);
    //  * buttonGuardarMod
    buttonCancelarMod.setBounds(680, 530, 100, 30);
    buttonCancelarMod.setFont(new java.awt.Font("Tahoma", 1, 12));
    buttonCancelarMod.setPreferredSize(new java.awt.Dimension(100, 30));
    this.add(buttonCancelarMod);
    //  * buttonCancelarMod
    buttonGuardarMod.setBounds(780, 530, 100, 30);
    buttonGuardarMod.setFont(new java.awt.Font("Tahoma", 1, 12));
    buttonGuardarMod.setPreferredSize(new java.awt.Dimension(100, 30));
    this.add(buttonGuardarMod);
    //  * labelErrorMod
    labelErrorMod.setBounds(680, 560, 300, 300);
    labelErrorMod.setFont(new java.awt.Font("Tahoma", 1, 12));
    labelErrorMod.setPreferredSize(new java.awt.Dimension(300, 300));
    this.add(labelErrorMod);
    //  ************************************************************
    //  * tableAlbumOLlista
      tableAlbumOLlista.setBounds(1000, 50, 500, 300);
      tableAlbumOLlista.setFont(new java.awt.Font("Tahoma", 1, 12));
      tableAlbumOLlista.setPreferredSize(new java.awt.Dimension(500, 300));
      this.add(tableAlbumOLlista);
    //  * modelAlbumOLlista

    //  * labelDisponibles
    labelDisponibles.setBounds(1000, 390, 200, 30);
    labelDisponibles.setFont(new java.awt.Font("Tahoma", 1, 12));
    labelDisponibles.setPreferredSize(new java.awt.Dimension(200, 30));
    this.add(labelDisponibles);
    //  * cbDisponibles
    cbDisponibles.setBounds(1000, 420, 125, 30);
    cbDisponibles.setFont(new java.awt.Font("Tahoma", 1, 12));
    cbDisponibles.setPreferredSize(new java.awt.Dimension(125, 30));
    this.add(cbDisponibles);
    //  * buttonAfegirAlbumOLlista
    buttonAfegirAlbumOLlista.setBounds(1130, 420, 125, 30);
    buttonAfegirAlbumOLlista.setFont(new java.awt.Font("Tahoma", 1, 12));
    buttonAfegirAlbumOLlista.setPreferredSize(new java.awt.Dimension(125, 30));
    this.add(buttonAfegirAlbumOLlista);
    //  * labelNoDisponibles
    labelNoDisponibles.setBounds(1000, 460, 125, 30);
    labelNoDisponibles.setFont(new java.awt.Font("Tahoma", 1, 12));
    labelNoDisponibles.setPreferredSize(new java.awt.Dimension(125, 30));
    this.add(labelNoDisponibles);
    //  * cbNoDisponibles
    cbNoDisponibles.setBounds(1000, 490, 125, 30);
    cbNoDisponibles.setFont(new java.awt.Font("Tahoma", 1, 12));
    cbNoDisponibles.setPreferredSize(new java.awt.Dimension(125, 30));
    this.add(cbNoDisponibles);
    //  * buttonEliminarAlbumOLlista
    buttonEliminarAlbumOLlista.setBounds(1130, 490, 125, 30);
    buttonEliminarAlbumOLlista.setFont(new java.awt.Font("Tahoma", 1, 12));
    buttonEliminarAlbumOLlista.setPreferredSize(new java.awt.Dimension(125, 30));
    this.add(buttonEliminarAlbumOLlista);
  }

  public void natejar() {
    // Netejem primer
    textIdHiddenMod.setText("");
    cbTipusMod.removeAllItems();
    textTitolMod.setText("");
    cbEstilMod.removeAllItems();
    rbSiActiuMod.setSelected(false);
    rbNoActiuMod.setSelected(false);
    textDuradaMod.setText("");
    textAnyCreacioMod.setText("");
    cbInterpretsMod.removeAllItems();
    labelErrorMod.setText("");
  }
  public VistaProducte() {
    setLayout(null);
    setBounds(0, 0, 1888, 788);
    setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 140, 0)));
    afegirComponents();
    setOpaque(false);
    setVisible(true);
    textIdHiddenMod.setVisible(false);
  }

}
