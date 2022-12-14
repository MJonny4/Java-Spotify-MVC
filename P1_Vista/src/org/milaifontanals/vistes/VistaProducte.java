/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.vistes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.milaifontanals.models.Album;
import org.milaifontanals.models.Artista;
import org.milaifontanals.models.Canco;
import org.milaifontanals.models.ItemsLlista;
import org.milaifontanals.models.Producte;
import org.milaifontanals.persistencia.DBArtista;
import org.milaifontanals.persistencia.DBProducte;
import org.milaifontanals.persistencia.Persistencia;
import org.milaifontanals.persistencia.PersistenciaException;

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

  JPanel panelProductes = new JPanel();
  JTable tableProductes = new JTable();
  JScrollPane scrollProductes = new JScrollPane(tableProductes);

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
  JScrollPane scrollAlbumOLlista = new JScrollPane(tableAlbumOLlista);

  DefaultTableModel modelAlbumOLlista = new DefaultTableModel();
  JLabel labelDisponibles = new JLabel("Productes disponibles");
  JComboBox<String> cbDisponibles = new JComboBox<>();
  JButton buttonAfegirAlbumOLlista = new JButton("Afegir");
  JLabel labelNoDisponibles = new JLabel("Productes Utilitzats");
  JComboBox<String> cbNoDisponibles = new JComboBox<>();
  JButton buttonEliminarAlbumOLlista = new JButton("Eliminar");

  public void afegirComponents() {
    // * labelTitolProductes
    labelTitolProductes.setBounds(10, 10, 100, 30);
    labelTitolProductes.setFont(new java.awt.Font("Tahoma", 1, 18));
    labelTitolProductes.setPreferredSize(new java.awt.Dimension(100, 30));
    this.add(labelTitolProductes);
    // * labelFiltrar
    labelFiltrar.setBounds(10, 50, 100, 30);
    labelFiltrar.setFont(new java.awt.Font("Tahoma", 1, 14));
    labelFiltrar.setPreferredSize(new java.awt.Dimension(100, 30));
    this.add(labelFiltrar);
    // * labelTipusFiltrar
    labelTipusFiltrar.setBounds(10, 90, 100, 30);
    labelTipusFiltrar.setFont(new java.awt.Font("Tahoma", 1, 12));
    labelTipusFiltrar.setPreferredSize(new java.awt.Dimension(100, 30));
    this.add(labelTipusFiltrar);
    // * cbCancoFiltra
    cbCancoFiltra.setBounds(10, 120, 100, 30);
    cbCancoFiltra.setFont(new java.awt.Font("Tahoma", 1, 12));
    cbCancoFiltra.setPreferredSize(new java.awt.Dimension(100, 30));
    this.add(cbCancoFiltra);
    // * cbAlbumFiltra
    cbAlbumFiltra.setBounds(10, 150, 100, 30);
    cbAlbumFiltra.setFont(new java.awt.Font("Tahoma", 1, 12));
    cbAlbumFiltra.setPreferredSize(new java.awt.Dimension(100, 30));
    this.add(cbAlbumFiltra);
    // * cbLlistaFiltra
    cbLlistaFiltra.setBounds(10, 180, 100, 30);
    cbLlistaFiltra.setFont(new java.awt.Font("Tahoma", 1, 12));
    cbLlistaFiltra.setPreferredSize(new java.awt.Dimension(100, 30));
    this.add(cbLlistaFiltra);
    // * labelActiuFiltrar
    labelActiuFiltrar.setBounds(10, 220, 100, 30);
    labelActiuFiltrar.setFont(new java.awt.Font("Tahoma", 1, 12));
    labelActiuFiltrar.setPreferredSize(new java.awt.Dimension(100, 30));
    this.add(labelActiuFiltrar);
    // * rbSiActiuFiltra
    rbSiActiuFiltra.setBounds(10, 250, 100, 30);
    rbSiActiuFiltra.setFont(new java.awt.Font("Tahoma", 1, 12));
    rbSiActiuFiltra.setPreferredSize(new java.awt.Dimension(100, 30));
    this.add(rbSiActiuFiltra);
    // * rbNoActiuFiltra
    rbNoActiuFiltra.setBounds(10, 280, 100, 30);
    rbNoActiuFiltra.setFont(new java.awt.Font("Tahoma", 1, 12));
    rbNoActiuFiltra.setPreferredSize(new java.awt.Dimension(100, 30));
    this.add(rbNoActiuFiltra);
    // * rbTotsActiuFiltra
    rbTotsActiuFiltra.setBounds(10, 310, 100, 30);
    rbTotsActiuFiltra.setFont(new java.awt.Font("Tahoma", 1, 12));
    rbTotsActiuFiltra.setPreferredSize(new java.awt.Dimension(100, 30));
    this.add(rbTotsActiuFiltra);
    // * labelTitolFiltrar
    labelTitolFiltrar.setBounds(10, 350, 100, 30);
    labelTitolFiltrar.setFont(new java.awt.Font("Tahoma", 1, 12));
    labelTitolFiltrar.setPreferredSize(new java.awt.Dimension(100, 30));
    this.add(labelTitolFiltrar);
    // * textTitolFiltra
    textTitolFiltra.setBounds(10, 380, 100, 30);
    textTitolFiltra.setFont(new java.awt.Font("Tahoma", 1, 12));
    textTitolFiltra.setPreferredSize(new java.awt.Dimension(100, 30));
    this.add(textTitolFiltra);
    // * buttonFiltrar
    buttonFiltrar.setBounds(10, 420, 100, 30);
    buttonFiltrar.setFont(new java.awt.Font("Tahoma", 1, 12));
    buttonFiltrar.setPreferredSize(new java.awt.Dimension(100, 30));
    this.add(buttonFiltrar);
    // ************************************************************
    // * panelProductes
    panelProductes.setBounds(120, 50, 500, 400);
    panelProductes.setLayout(null);
    // * tableProductes
    tableProductes.setBounds(0, 0, 500, 400);
    tableProductes.setPreferredScrollableViewportSize(new java.awt.Dimension(500, 400));
    tableProductes.setFillsViewportHeight(true);
    tableProductes.setDefaultEditor(Object.class, null);
    // * scrollProductes
    scrollProductes.setBounds(0, 0, 500, 400);
    scrollProductes.setViewportView(tableProductes);
    panelProductes.add(scrollProductes);
    this.add(panelProductes);
    // * modelProductes
    modelProductes.addColumn("Titol");
    modelProductes.addColumn("Estil");
    modelProductes.addColumn("Tipus");
    modelProductes.addColumn("Actiu");
    // * buttonAfegir
    buttonAfegir.setBounds(120, 460, 100, 30);
    buttonAfegir.setFont(new java.awt.Font("Tahoma", 1, 12));
    buttonAfegir.setPreferredSize(new java.awt.Dimension(100, 30));
    this.add(buttonAfegir);
    // * buttonModificar
    buttonModificar.setBounds(230, 460, 100, 30);
    buttonModificar.setFont(new java.awt.Font("Tahoma", 1, 12));
    buttonModificar.setPreferredSize(new java.awt.Dimension(100, 30));
    this.add(buttonModificar);
    // * buttonNetejar
    buttonNetejar.setBounds(230, 500, 100, 30);
    buttonNetejar.setFont(new java.awt.Font("Tahoma", 1, 12));
    buttonNetejar.setPreferredSize(new java.awt.Dimension(100, 30));
    this.add(buttonNetejar);
    // * buttonImprimir
    buttonImprimir.setBounds(340, 460, 100, 30);
    buttonImprimir.setFont(new java.awt.Font("Tahoma", 1, 12));
    buttonImprimir.setPreferredSize(new java.awt.Dimension(100, 30));
    this.add(buttonImprimir);
    // * buttonEliminar
    buttonEliminar.setBounds(520, 460, 100, 30);
    buttonEliminar.setFont(new java.awt.Font("Tahoma", 1, 12));
    buttonEliminar.setPreferredSize(new java.awt.Dimension(100, 30));
    this.add(buttonEliminar);

    // ************************************************************
    // * labelTitolModProductes
    labelTitolModProductes.setBounds(680, 10, 300, 30);
    labelTitolModProductes.setFont(new java.awt.Font("Tahoma", 1, 14));
    labelTitolModProductes.setPreferredSize(new java.awt.Dimension(300, 30));
    this.add(labelTitolModProductes);
    // * textIdHiddenMod
    textIdHiddenMod.setBounds(680, 40, 200, 30);
    textIdHiddenMod.setFont(new java.awt.Font("Tahoma", 1, 12));
    textIdHiddenMod.setPreferredSize(new java.awt.Dimension(200, 30));
    this.add(textIdHiddenMod);
    // * labelTipusMod
    labelTipusMod.setBounds(680, 50, 200, 30);
    labelTipusMod.setFont(new java.awt.Font("Tahoma", 1, 12));
    labelTipusMod.setPreferredSize(new java.awt.Dimension(200, 30));
    this.add(labelTipusMod);
    // * cbTipusMod
    cbTipusMod.setBounds(680, 80, 200, 30);
    cbTipusMod.setFont(new java.awt.Font("Tahoma", 1, 12));
    cbTipusMod.setPreferredSize(new java.awt.Dimension(200, 30));
    this.add(cbTipusMod);
    // * labelTitolMod
    labelTitolMod.setBounds(680, 110, 200, 30);
    labelTitolMod.setFont(new java.awt.Font("Tahoma", 1, 12));
    labelTitolMod.setPreferredSize(new java.awt.Dimension(200, 30));
    this.add(labelTitolMod);
    // * textTitolMod
    textTitolMod.setBounds(680, 140, 200, 30);
    textTitolMod.setFont(new java.awt.Font("Tahoma", 1, 12));
    textTitolMod.setPreferredSize(new java.awt.Dimension(200, 30));
    this.add(textTitolMod);
    // * labelEstilMod
    labelEstilMod.setBounds(680, 170, 200, 30);
    labelEstilMod.setFont(new java.awt.Font("Tahoma", 1, 12));
    labelEstilMod.setPreferredSize(new java.awt.Dimension(200, 30));
    this.add(labelEstilMod);
    // * cbEstilMod
    cbEstilMod.setBounds(680, 200, 200, 30);
    cbEstilMod.setFont(new java.awt.Font("Tahoma", 1, 12));
    cbEstilMod.setPreferredSize(new java.awt.Dimension(200, 30));
    this.add(cbEstilMod);
    // * labelActiuMod
    labelActiuMod.setBounds(680, 230, 200, 30);
    labelActiuMod.setFont(new java.awt.Font("Tahoma", 1, 12));
    labelActiuMod.setPreferredSize(new java.awt.Dimension(200, 30));
    this.add(labelActiuMod);
    // * rbSiActiuMod
    rbSiActiuMod.setBounds(680, 260, 100, 30);
    rbSiActiuMod.setFont(new java.awt.Font("Tahoma", 1, 12));
    rbSiActiuMod.setPreferredSize(new java.awt.Dimension(100, 30));
    this.add(rbSiActiuMod);
    // * rbNoActiuMod
    rbNoActiuMod.setBounds(680, 290, 100, 30);
    rbNoActiuMod.setFont(new java.awt.Font("Tahoma", 1, 12));
    rbNoActiuMod.setPreferredSize(new java.awt.Dimension(100, 30));
    this.add(rbNoActiuMod);
    // * labelDuradaMod
    labelDuradaMod.setBounds(680, 320, 200, 30);
    labelDuradaMod.setFont(new java.awt.Font("Tahoma", 1, 12));
    labelDuradaMod.setPreferredSize(new java.awt.Dimension(200, 30));
    this.add(labelDuradaMod);
    // * textDuradaMod
    textDuradaMod.setBounds(680, 350, 200, 30);
    textDuradaMod.setFont(new java.awt.Font("Tahoma", 1, 12));
    textDuradaMod.setPreferredSize(new java.awt.Dimension(200, 30));
    this.add(textDuradaMod);
    // * labelAnyCreacioMod
    labelAnyCreacioMod.setBounds(680, 380, 200, 30);
    labelAnyCreacioMod.setFont(new java.awt.Font("Tahoma", 1, 12));
    labelAnyCreacioMod.setPreferredSize(new java.awt.Dimension(200, 30));
    this.add(labelAnyCreacioMod);
    // * textAnyCreacioMod
    textAnyCreacioMod.setBounds(680, 410, 200, 30);
    textAnyCreacioMod.setFont(new java.awt.Font("Tahoma", 1, 12));
    textAnyCreacioMod.setPreferredSize(new java.awt.Dimension(200, 30));
    this.add(textAnyCreacioMod);
    // * labelInterpretsMod
    labelInterpretsMod.setBounds(680, 440, 200, 30);
    labelInterpretsMod.setFont(new java.awt.Font("Tahoma", 1, 12));
    labelInterpretsMod.setPreferredSize(new java.awt.Dimension(200, 30));
    this.add(labelInterpretsMod);
    // * cbInterpretsMod
    cbInterpretsMod.setBounds(680, 470, 200, 30);
    cbInterpretsMod.setFont(new java.awt.Font("Tahoma", 1, 12));
    cbInterpretsMod.setPreferredSize(new java.awt.Dimension(200, 30));
    this.add(cbInterpretsMod);
    // * buttonGuardarMod
    buttonCancelarMod.setBounds(680, 530, 100, 30);
    buttonCancelarMod.setFont(new java.awt.Font("Tahoma", 1, 12));
    buttonCancelarMod.setPreferredSize(new java.awt.Dimension(100, 30));
    this.add(buttonCancelarMod);
    // * buttonCancelarMod
    buttonGuardarMod.setBounds(780, 530, 100, 30);
    buttonGuardarMod.setFont(new java.awt.Font("Tahoma", 1, 12));
    buttonGuardarMod.setPreferredSize(new java.awt.Dimension(100, 30));
    this.add(buttonGuardarMod);
    // * labelErrorMod
    labelErrorMod.setBounds(680, 560, 300, 300);
    labelErrorMod.setFont(new java.awt.Font("Tahoma", Font.PLAIN, 14));
    labelErrorMod.setForeground(Color.RED);
    labelErrorMod.setPreferredSize(new java.awt.Dimension(300, 300));
    this.add(labelErrorMod);
    // ************************************************************
    // * tableAlbumOLlista
    tableAlbumOLlista.setBounds(1000, 50, 500, 300);
    tableAlbumOLlista.setPreferredScrollableViewportSize(new java.awt.Dimension(500, 400));
    tableAlbumOLlista.setFillsViewportHeight(true);
    tableAlbumOLlista.setDefaultEditor(Object.class, null);
    // * scrollAlbumOLlista
    scrollAlbumOLlista.setBounds(1000, 50, 500, 300);
    scrollAlbumOLlista.setViewportView(tableAlbumOLlista);
    this.add(scrollAlbumOLlista);
    // * modelAlbumOLlista

    // * labelDisponibles
    labelDisponibles.setBounds(1000, 390, 200, 30);
    labelDisponibles.setFont(new java.awt.Font("Tahoma", 1, 12));
    labelDisponibles.setPreferredSize(new java.awt.Dimension(200, 30));
    this.add(labelDisponibles);
    // * cbDisponibles
    cbDisponibles.setBounds(1000, 420, 125, 30);
    cbDisponibles.setFont(new java.awt.Font("Tahoma", 1, 12));
    cbDisponibles.setPreferredSize(new java.awt.Dimension(125, 30));
    this.add(cbDisponibles);
    // * buttonAfegirAlbumOLlista
    buttonAfegirAlbumOLlista.setBounds(1130, 420, 125, 30);
    buttonAfegirAlbumOLlista.setFont(new java.awt.Font("Tahoma", 1, 12));
    buttonAfegirAlbumOLlista.setPreferredSize(new java.awt.Dimension(125, 30));
    this.add(buttonAfegirAlbumOLlista);
    // * labelNoDisponibles
    labelNoDisponibles.setBounds(1000, 460, 125, 30);
    labelNoDisponibles.setFont(new java.awt.Font("Tahoma", 1, 12));
    labelNoDisponibles.setPreferredSize(new java.awt.Dimension(125, 30));
    this.add(labelNoDisponibles);
    // * cbNoDisponibles
    cbNoDisponibles.setBounds(1000, 490, 125, 30);
    cbNoDisponibles.setFont(new java.awt.Font("Tahoma", 1, 12));
    cbNoDisponibles.setPreferredSize(new java.awt.Dimension(125, 30));
    this.add(cbNoDisponibles);
    // * buttonEliminarAlbumOLlista
    buttonEliminarAlbumOLlista.setBounds(1130, 490, 125, 30);
    buttonEliminarAlbumOLlista.setFont(new java.awt.Font("Tahoma", 1, 12));
    buttonEliminarAlbumOLlista.setPreferredSize(new java.awt.Dimension(125, 30));
    this.add(buttonEliminarAlbumOLlista);
  }

  public void netejar() {
    labelTitolModProductes.setText("");
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

    // tableProductes.clearSelection();
    buttonModificar.setEnabled(false);
    buttonEliminar.setEnabled(false);
    buttonNetejar.setEnabled(false);
    buttonAfegir.setEnabled(true);

    cbDisponibles.removeAllItems();
    cbNoDisponibles.removeAllItems();

    labelTitolModProductes.setVisible(false);
    textIdHiddenMod.setVisible(false);
    labelTipusMod.setVisible(false);
    cbTipusMod.setVisible(false);
    labelTitolMod.setVisible(false);
    textTitolMod.setVisible(false);
    labelEstilMod.setVisible(false);
    cbEstilMod.setVisible(false);
    labelActiuMod.setVisible(false);
    rbSiActiuMod.setVisible(false);
    rbNoActiuMod.setVisible(false);
    labelDuradaMod.setVisible(false);
    textDuradaMod.setVisible(false);
    labelAnyCreacioMod.setVisible(false);
    textAnyCreacioMod.setVisible(false);
    labelInterpretsMod.setVisible(false);
    cbInterpretsMod.setVisible(false);
    buttonCancelarMod.setVisible(false);
    buttonGuardarMod.setVisible(false);
    labelErrorMod.setVisible(true);
    labelErrorMod.setText("");

    tableAlbumOLlista.setVisible(false);
    scrollAlbumOLlista.setVisible(false);
    labelDisponibles.setVisible(false);
    cbDisponibles.setVisible(false);
    buttonAfegirAlbumOLlista.setVisible(false);
    labelNoDisponibles.setVisible(false);
    cbNoDisponibles.setVisible(false);
    buttonEliminarAlbumOLlista.setVisible(false);
  }

  public void afegirEvents() {
    rbTotsActiuFiltra.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (rbTotsActiuFiltra.isSelected()) {
          rbSiActiuFiltra.setSelected(false);
          rbNoActiuFiltra.setSelected(false);
        }
      }
    });
    rbSiActiuFiltra.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (rbSiActiuFiltra.isSelected()) {
          rbTotsActiuFiltra.setSelected(false);
          rbNoActiuFiltra.setSelected(false);
        }
      }
    });
    rbNoActiuFiltra.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (rbNoActiuFiltra.isSelected()) {
          rbTotsActiuFiltra.setSelected(false);
          rbSiActiuFiltra.setSelected(false);
        }
      }
    });
    buttonNetejar.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        netejar();
        rbTotsActiuFiltra.setSelected(true);
        cbCancoFiltra.setSelected(true);
        cbAlbumFiltra.setSelected(true);
        cbLlistaFiltra.setSelected(true);
        tableProductes.clearSelection();
        buttonAfegir.doClick();
      }
    });
    buttonFiltrar.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        filtrar();
      }
    });
    tableProductes.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (tableProductes.getSelectedRowCount() > 1) {
          JOptionPane.showConfirmDialog(null, "Selecciona un únic producte", "Error", JOptionPane.DEFAULT_OPTION,
              JOptionPane.ERROR_MESSAGE);
          buttonNetejar.doClick();
        } else {
          veure();
          buttonModificar.setEnabled(true);
          buttonEliminar.setEnabled(true);
          buttonNetejar.setEnabled(true);
          buttonAfegir.setEnabled(false);
        }
      }
    });
    rbSiActiuMod.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (rbSiActiuMod.isSelected()) {
          rbNoActiuMod.setSelected(false);
        }
      }
    });
    rbNoActiuMod.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (rbNoActiuMod.isSelected()) {
          rbSiActiuMod.setSelected(false);
        }
      }
    });
    buttonAfegir.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        afegir();
      }
    });
    buttonGuardarMod.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        String tipus = cbTipusMod.getSelectedItem().toString();
        switch (tipus) {
          case "CANCO":
            if (buttonAfegir.isEnabled()) {
              afegirCancoButton();
            } else {
              // modificarCanco();
            }
            break;
          case "ALBUM":
            if (buttonAfegir.isEnabled()) {
              afegirAlbumButton();
            } else {
              // modificarAlbum();
            }
            break;
          case "LLISTA":
            if (buttonAfegir.isEnabled()) {
              afegirLlistaButton();
            } else {
              // modificarLlista();
            }
            break;
        }
      }
    });
    buttonCancelarMod.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        labelErrorMod.setText("");
        tableProductes.clearSelection();
        netejar();
      }
    });
    buttonModificar.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        modificar();
      }
    });
  }

  public VistaProducte() {
    setLayout(null);
    setBounds(0, 0, 1500, 788);
    setVisible(true);

    Connexio();
    afegirComponents();
    netejar();
    afegirEvents();

    rbTotsActiuFiltra.setSelected(true);
    cbCancoFiltra.setSelected(true);
    cbAlbumFiltra.setSelected(true);
    cbLlistaFiltra.setSelected(true);

    filtrar();
  }

  public void Connexio() {
    try {
      Persistencia.getConnection();
    } catch (PersistenciaException excep) {
      System.out.println("Error de connexió a la base de dades.\n" + excep.getMessage());
    }
  }

  public long getId(String producte_titol) {
    long returnedId;
    try {
      returnedId = DBProducte.getProducte(producte_titol);
    } catch (PersistenciaException excep) {
      System.out.println("Error al obtenir l'id del producte.\n" + excep.getMessage());
      returnedId = -1;
    }
    return returnedId;
  }

  public void filtrar() {
    netejar();
    buidarTaulaProductes();

    char[] tipus = null;
    char[] actiu = null;
    String titol = null;

    if (cbCancoFiltra.isSelected() && cbAlbumFiltra.isSelected() && cbLlistaFiltra.isSelected()) {
      tipus = new char[] { 'C', 'A', 'L' };
    } else if (cbCancoFiltra.isSelected() && cbAlbumFiltra.isSelected()) {
      tipus = new char[] { 'C', 'A' };
    } else if (cbCancoFiltra.isSelected() && cbLlistaFiltra.isSelected()) {
      tipus = new char[] { 'C', 'L' };
    } else if (cbAlbumFiltra.isSelected() && cbLlistaFiltra.isSelected()) {
      tipus = new char[] { 'A', 'L' };
    } else if (cbCancoFiltra.isSelected()) {
      tipus = new char[] { 'C' };
    } else if (cbAlbumFiltra.isSelected()) {
      tipus = new char[] { 'A' };
    } else if (cbLlistaFiltra.isSelected()) {
      tipus = new char[] { 'L' };
    } else {
      tipus = new char[] { 'Q' };
    }

    if (rbSiActiuFiltra.isSelected()) {
      actiu = new char[] { 'S' };
    } else if (rbNoActiuFiltra.isSelected()) {
      actiu = new char[] { 'N' };
    } else if (rbTotsActiuFiltra.isSelected()) {
      actiu = new char[] { 'S', 'N' };
    }

    if (textTitolFiltra.getText().equals("")) {
      titol = null;
    } else {
      titol = textTitolFiltra.getText();
    }

    if (tipus[0] == 'Q') {
      JOptionPane.showMessageDialog(null, "Has de seleccionar almenys un tipus de producte");
    } else {
      try {
        List<Producte> productesFiltrar = DBProducte.getProductes(tipus, actiu, titol);
        for (Producte p : productesFiltrar) {
          modelProductes.addRow(new Object[] { p.getProducte_titol(), p.getProducte_estil().getEstil_nom(),
              p.getProducte_tipus(), p.getProducte_actiu() });
        }
        tableProductes.setModel(modelProductes);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modelProductes);
        tableProductes.setRowSorter(sorter);
        List<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
        sorter.sort();
      } catch (PersistenciaException ex) {
        System.out.println("Error en filtrar.\n" + ex.getMessage());
      }
    }
  }

  public void buidarTaulaProductes() {
    modelProductes.setRowCount(0);
    modelProductes.setColumnCount(0);
    modelProductes.addColumn("Titol");
    modelProductes.addColumn("Estil");
    modelProductes.addColumn("Tipus");
    modelProductes.addColumn("Actiu");
  }

  public void veure() {
    netejar();
    int row = tableProductes.getSelectedRow();

    String titol = tableProductes.getValueAt(row, 0).toString();
    String estil = tableProductes.getValueAt(row, 1).toString();
    String tipus = tableProductes.getValueAt(row, 2).toString();
    String actiu = tableProductes.getValueAt(row, 3).toString();
    long id = getId(titol);

    mostrarTipus(tipus, titol, estil, actiu, id);
  }

  public void mostrarTipus(String producte_tipus, String producte_titol, String producte_estil, String producte_actiu,
      long producte_id) {
    switch (producte_tipus) {
      case "CANCO":
        mostrarCanco();
        textIdHiddenMod.setText(String.valueOf(producte_id));
        carregarCbTipusMod();
        cbTipusMod.setSelectedItem(producte_tipus);
        textTitolMod.setText(producte_titol);
        carregarCbEstilMod();
        cbEstilMod.setSelectedItem(producte_estil);
        carregarRbActiuMod();
        if (producte_actiu.equals("S")) {
          rbSiActiuMod.setSelected(true);
        } else {
          rbNoActiuMod.setSelected(true);
        }
        carregarCbInterpretsMod();
        try {
          String interpret = DBArtista.getInterpret(producte_id);
          cbInterpretsMod.setSelectedItem(interpret);
          List<Canco> cancons = DBProducte.getCanconcsInterpretades(producte_id);
          for (Canco c : cancons) {
            textDuradaMod.setText(String.valueOf(c.getDurada()));
            textAnyCreacioMod.setText(String.valueOf(c.getCanco_any_creacio()));
          }
        } catch (PersistenciaException ex) {
          System.out.println("Error en mostrar canço.\n" + ex.getMessage());
        }
        break;
      case "ALBUM":
        mostrarAlbum();
        textIdHiddenMod.setText(String.valueOf(producte_id));
        carregarCbTipusMod();
        cbTipusMod.setSelectedItem(producte_tipus);
        textTitolMod.setText(producte_titol);
        carregarCbEstilMod();
        cbEstilMod.setSelectedItem(producte_estil);
        carregarRbActiuMod();
        if (producte_actiu.equals("S")) {
          rbSiActiuMod.setSelected(true);
        } else {
          rbNoActiuMod.setSelected(true);
        }
        try {
          List<Album> albums = DBProducte.getAlbum(producte_id);
          for (Album a : albums) {
            textDuradaMod.setText(String.valueOf(a.getAlbum_durada()));
            textAnyCreacioMod.setText(String.valueOf(a.getAlbum_any_creacio()));
          }

          modelAlbumOLlista.setRowCount(0);
          modelAlbumOLlista.setColumnCount(0);
          modelAlbumOLlista.addColumn("Cançó");
          modelAlbumOLlista.addColumn("Durada");
          modelAlbumOLlista.addColumn("Posicio");

          List<Album> albumsCancons = DBProducte.getAlbumCancons(producte_id);
          for (Album ac : albumsCancons) {
            Iterator<Canco> it = ac.getCancons();
            while (it.hasNext()) {
              Canco c = it.next();
              modelAlbumOLlista.addRow(new Object[] { c.getProducte_titol(), c.getDurada(),
                  ac.getPosicio() });
            }
          }
          tableAlbumOLlista.setModel(modelAlbumOLlista);
        } catch (PersistenciaException ex) {
          System.out.println("Error en mostrar album.\n" + ex.getMessage());
        }
        break;
      case "LLISTA":
        mostrarLlista();
        textIdHiddenMod.setText(String.valueOf(producte_id));
        carregarCbTipusMod();
        cbTipusMod.setSelectedItem(producte_tipus);
        textTitolMod.setText(producte_titol);
        carregarCbEstilMod();
        cbEstilMod.setSelectedItem(producte_estil);
        carregarRbActiuMod();
        if (producte_actiu.equals("S")) {
          rbSiActiuMod.setSelected(true);
        } else {
          rbNoActiuMod.setSelected(true);
        }
        try {
          textDuradaMod.setText(String.valueOf(DBProducte.getLlistaDuarada(producte_id)));

          modelAlbumOLlista.setRowCount(0);
          modelAlbumOLlista.setColumnCount(0);
          modelAlbumOLlista.addColumn("Producte");
          modelAlbumOLlista.addColumn("Tipus");
          modelAlbumOLlista.addColumn("Posicio");

          List<ItemsLlista> items = DBProducte.getLlistaProductes(producte_id);
          for (ItemsLlista i : items) {
            modelAlbumOLlista
                .addRow(new Object[] { i.getProducte().getProducte_titol(), i.getProducte().getProducte_tipus(),
                    i.getLlista_posicio() });
          }
          tableAlbumOLlista.setModel(modelAlbumOLlista);
        } catch (PersistenciaException ex) {
          System.out.println("Error en mostrar llista.\n" + ex.getMessage());
        }
        break;
    }
  }

  public void carregarCbTipusMod() {
    cbTipusMod.removeAllItems();
    cbTipusMod.addItem("CANCO");
    cbTipusMod.addItem("ALBUM");
    cbTipusMod.addItem("LLISTA");
  }

  public void carregarCbEstilMod() {
    cbEstilMod.removeAllItems();
    cbEstilMod.addItem("Reggae");
    cbEstilMod.addItem("Rock");
    cbEstilMod.addItem("Trap");
  }

  public void carregarRbActiuMod() {
    rbSiActiuMod.setSelected(false);
    rbNoActiuMod.setSelected(false);
  }

  public void carregarCbInterpretsMod() {
    cbInterpretsMod.removeAllItems();
    try {
      List<Artista> interprets = DBArtista.getInterprets();
      for (Artista a : interprets) {
        cbInterpretsMod.addItem(a.getArtista_nom());
      }
    } catch (PersistenciaException ex) {
      System.out.println("Error en carregar interprets.\n" + ex.getMessage());
    }
  }

  public void mostrarCanco() {
    netejar();
    labelTitolModProductes.setVisible(true);
    labelTitolModProductes.setText("Veient canço");
    textIdHiddenMod.setVisible(false);
    labelTipusMod.setVisible(true);
    cbTipusMod.setVisible(true);
    cbTipusMod.setEnabled(false);
    labelTitolMod.setVisible(true);
    textTitolMod.setVisible(true);
    textTitolMod.setEnabled(false);
    labelEstilMod.setVisible(true);
    cbEstilMod.setVisible(true);
    cbEstilMod.setEnabled(false);
    labelActiuMod.setVisible(true);
    rbSiActiuMod.setVisible(true);
    rbSiActiuMod.setEnabled(false);
    rbNoActiuMod.setVisible(true);
    rbNoActiuMod.setEnabled(false);
    labelDuradaMod.setVisible(true);
    textDuradaMod.setVisible(true);
    textDuradaMod.setEnabled(false);
    labelAnyCreacioMod.setVisible(true);
    textAnyCreacioMod.setVisible(true);
    textAnyCreacioMod.setEnabled(false);
    labelInterpretsMod.setVisible(true);
    cbInterpretsMod.setVisible(true);
    cbInterpretsMod.setEnabled(false);
    buttonCancelarMod.setVisible(false);
    buttonGuardarMod.setVisible(false);
    labelErrorMod.setVisible(false);
  }

  public void mostrarAlbum() {
    netejar();
    labelTitolModProductes.setVisible(true);
    labelTitolModProductes.setText("Veient àlbum");
    textIdHiddenMod.setVisible(false);
    labelTipusMod.setVisible(true);
    cbTipusMod.setVisible(true);
    cbTipusMod.setEnabled(false);
    labelTitolMod.setVisible(true);
    textTitolMod.setVisible(true);
    textTitolMod.setEnabled(false);
    labelEstilMod.setVisible(true);
    cbEstilMod.setVisible(true);
    cbEstilMod.setEnabled(false);
    labelActiuMod.setVisible(true);
    rbSiActiuMod.setVisible(true);
    rbSiActiuMod.setEnabled(false);
    rbNoActiuMod.setVisible(true);
    rbNoActiuMod.setEnabled(false);
    labelDuradaMod.setVisible(true);
    textDuradaMod.setVisible(true);
    textDuradaMod.setEnabled(false);
    labelAnyCreacioMod.setVisible(true);
    textAnyCreacioMod.setVisible(true);
    textAnyCreacioMod.setEnabled(false);
    labelInterpretsMod.setVisible(false);
    cbInterpretsMod.setVisible(false);
    cbInterpretsMod.setEnabled(false);
    buttonCancelarMod.setVisible(false);
    buttonGuardarMod.setVisible(false);
    labelErrorMod.setVisible(false);

    tableAlbumOLlista.setVisible(true);
    scrollAlbumOLlista.setVisible(true);
    labelDisponibles.setVisible(false);
    cbDisponibles.setVisible(false);
    buttonAfegirAlbumOLlista.setVisible(false);
    labelNoDisponibles.setVisible(false);
    cbNoDisponibles.setVisible(false);
    buttonEliminarAlbumOLlista.setVisible(false);
  }

  public void mostrarLlista() {
    netejar();
    labelTitolModProductes.setVisible(true);
    labelTitolModProductes.setText("Veient llista");
    textIdHiddenMod.setVisible(false);
    labelTipusMod.setVisible(true);
    cbTipusMod.setVisible(true);
    cbTipusMod.setEnabled(false);
    labelTitolMod.setVisible(true);
    textTitolMod.setVisible(true);
    textTitolMod.setEnabled(false);
    labelEstilMod.setVisible(true);
    cbEstilMod.setVisible(true);
    cbEstilMod.setEnabled(false);
    labelActiuMod.setVisible(true);
    rbSiActiuMod.setVisible(true);
    rbSiActiuMod.setEnabled(false);
    rbNoActiuMod.setVisible(true);
    rbNoActiuMod.setEnabled(false);
    labelDuradaMod.setVisible(true);
    textDuradaMod.setVisible(true);
    textDuradaMod.setEnabled(false);
    labelAnyCreacioMod.setVisible(false);
    textAnyCreacioMod.setVisible(false);
    textAnyCreacioMod.setEnabled(false);
    labelInterpretsMod.setVisible(false);
    cbInterpretsMod.setVisible(false);
    cbInterpretsMod.setEnabled(false);
    buttonCancelarMod.setVisible(false);
    buttonGuardarMod.setVisible(false);
    labelErrorMod.setVisible(false);

    tableAlbumOLlista.setVisible(true);
    scrollAlbumOLlista.setVisible(true);
    labelDisponibles.setVisible(false);
    cbDisponibles.setVisible(false);
    buttonAfegirAlbumOLlista.setVisible(false);
    labelNoDisponibles.setVisible(false);
    cbNoDisponibles.setVisible(false);
    buttonEliminarAlbumOLlista.setVisible(false);
  }

  public void afegir() {
    netejar();
    labelTitolModProductes.setVisible(true);
    labelTitolModProductes.setText("Afegint Producte");
    labelTipusMod.setVisible(true);
    cbTipusMod.setVisible(true);
    cbTipusMod.setEnabled(true);
    carregarCbTipusMod();
    cbTipusMod.setSelectedIndex(-1);

    carregarCbEstilMod();
    carregarRbActiuMod();
    rbSiActiuMod.setSelected(true);
    carregarCbInterpretsMod();

    boolean afegint = true;

    while (afegint) {
      int opcio = -1;
      opcio = JOptionPane.showOptionDialog(null, "Quin tipus de producte vols afegir?", "Afegir Producte",
          JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
          new String[] { "Cançó", "Àlbum", "Llista" }, null);

      if (opcio == 0) {
        afegirCanco();
        cbTipusMod.setSelectedIndex(0);
        afegint = false;
      } else if (opcio == 1) {
        afegirAlbum();
        cbTipusMod.setSelectedIndex(1);
        afegint = false;
      } else if (opcio == 2) {
        afegirLlista();
        cbTipusMod.setSelectedIndex(2);
        afegint = false;
      } else if (opcio == -1) {
        netejar();
        afegint = false;
      }
    }
  }

  public void afegirCanco() {
    // labelTitolModProductes.setVisible(true);
    // textIdHiddenMod.setVisible(false);
    labelTipusMod.setVisible(true);
    cbTipusMod.setVisible(true);
    cbTipusMod.setEnabled(false);

    labelTitolMod.setVisible(true);
    textTitolMod.setVisible(true);
    textTitolMod.setEnabled(true);
    labelEstilMod.setVisible(true);
    cbEstilMod.setVisible(true);
    cbEstilMod.setEnabled(true);
    labelActiuMod.setVisible(true);
    rbSiActiuMod.setVisible(true);
    rbSiActiuMod.setEnabled(true);
    rbNoActiuMod.setVisible(true);
    rbNoActiuMod.setEnabled(true);
    labelDuradaMod.setVisible(true);
    textDuradaMod.setVisible(true);
    textDuradaMod.setEnabled(true);
    labelAnyCreacioMod.setVisible(true);
    textAnyCreacioMod.setVisible(true);
    textAnyCreacioMod.setEnabled(true);
    labelInterpretsMod.setVisible(true);
    cbInterpretsMod.setVisible(true);
    cbInterpretsMod.setEnabled(true);
    buttonCancelarMod.setVisible(true);
    buttonGuardarMod.setVisible(true);
    labelErrorMod.setVisible(true);
  }

  public void afegirCancoButton() {
    String errorAfegirCanco = "<html>";

    String producte_titol = textTitolMod.getText();
    String producte_estil = cbEstilMod.getSelectedItem().toString();
    char producte_actiu;
    if (rbSiActiuMod.isSelected()) {
      producte_actiu = 'S';
    } else {
      producte_actiu = 'N';
    }
    char producte_tipus = 'C';
    String canco_durada = textDuradaMod.getText();
    String canco_any_creacio = textAnyCreacioMod.getText();
    String canco_interpret = cbInterpretsMod.getSelectedItem().toString();

    if (producte_titol.equals("") || producte_titol == null || producte_titol.isEmpty()
        || producte_titol.length() > 100) {
      errorAfegirCanco += "El titol no pot ser buit i tenir mes de 100 caracters.<br>";
    }

    if (canco_durada.equals("") || canco_durada == null || canco_durada.isEmpty()) {
      errorAfegirCanco += "La durada no pot ser buida.<br>";
    } else if (canco_durada.contains(".") || canco_durada.contains(",")) {
      errorAfegirCanco += "La durada ha de ser un numero enter.<br>";
    } else if (canco_durada.matches(".*[a-zA-Z]+.*")) {
      errorAfegirCanco += "La durada ha de ser un numero enter.<br>";
    } else if (Integer.parseInt(canco_durada) < 1 || Integer.parseInt(canco_durada) > 600) {
      errorAfegirCanco += "La durada ha de ser un numero entre 1 i 600.<br>";
    }

    if (canco_any_creacio.equals("") || canco_any_creacio == null || canco_any_creacio.isEmpty()) {
      errorAfegirCanco += "L'any de creacio no pot ser buit.<br>";
    } else if (canco_any_creacio.contains(".") || canco_any_creacio.contains(",")) {
      errorAfegirCanco += "L'any de creacio ha de ser un numero enter.<br>";
    } else if (canco_any_creacio.matches(".*[a-zA-Z]+.*")) {
      errorAfegirCanco += "L'any de creacio ha de ser un numero enter.<br>";
    } else if (Integer.parseInt(canco_any_creacio) < 1900
        || Integer.parseInt(canco_any_creacio) > 2022) {
      errorAfegirCanco += "L'any de creacio ha de ser un numero entre 1900 i 2022.<br>";
    }

    errorAfegirCanco += "</html>";

    if (!errorAfegirCanco.equals("<html></html>")) {
      labelErrorMod.setVisible(true);
      labelErrorMod.setText(errorAfegirCanco);
    } else {
      try {
        DBProducte.insertCanco(producte_titol, producte_estil, producte_actiu, producte_tipus,
            Integer.parseInt(canco_any_creacio), canco_interpret, Integer.parseInt(canco_durada));
        labelErrorMod.setText("");
        tableProductes.clearSelection();
        filtrar();
        JOptionPane.showConfirmDialog(null, "Canco Afegida Correctament", "Canco afegida!",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE);
        // wait for JOptionPane to close and then netejar() and return;
        netejar();
      } catch (PersistenciaException error) {
        System.out.println(error.getMessage());
        labelErrorMod.setText("");
        tableProductes.clearSelection();
        filtrar();
        netejar();
      }
    }
  }

  public void afegirAlbum() {
    // labelTitolModProductes.setVisible(true);
    // textIdHiddenMod.setVisible(false);
    labelTipusMod.setVisible(true);
    cbTipusMod.setVisible(true);
    cbTipusMod.setEnabled(false);

    labelTitolMod.setVisible(true);
    textTitolMod.setVisible(true);
    textTitolMod.setEnabled(true);
    labelEstilMod.setVisible(true);
    cbEstilMod.setVisible(true);
    cbEstilMod.setEnabled(true);
    labelActiuMod.setVisible(true);
    rbSiActiuMod.setVisible(true);
    rbSiActiuMod.setEnabled(true);
    rbNoActiuMod.setVisible(true);
    rbNoActiuMod.setEnabled(true);
    labelDuradaMod.setVisible(false);
    textDuradaMod.setVisible(false);
    textDuradaMod.setEnabled(false);
    labelAnyCreacioMod.setVisible(true);
    textAnyCreacioMod.setVisible(true);
    textAnyCreacioMod.setEnabled(true);
    labelInterpretsMod.setVisible(false);
    cbInterpretsMod.setVisible(false);
    cbInterpretsMod.setEnabled(false);
    buttonCancelarMod.setVisible(true);
    buttonGuardarMod.setVisible(true);
    labelErrorMod.setVisible(true);
  }

  public void afegirAlbumButton() {
    String errorAfegirCanco = "<html>";

    String producte_titol = textTitolMod.getText();
    String producte_estil = cbEstilMod.getSelectedItem().toString();
    char producte_actiu;
    if (rbSiActiuMod.isSelected()) {
      producte_actiu = 'S';
    } else {
      producte_actiu = 'N';
    }
    char producte_tipus = 'A';
    String canco_any_creacio = textAnyCreacioMod.getText();

    if (producte_titol.equals("") || producte_titol == null || producte_titol.isEmpty()
        || producte_titol.length() > 100) {
      errorAfegirCanco += "El titol no pot ser buit i tenir mes de 100 caracters.<br>";
    }

    if (canco_any_creacio.equals("") || canco_any_creacio == null || canco_any_creacio.isEmpty()) {
      errorAfegirCanco += "L'any de creacio no pot ser buit.<br>";
    } else if (canco_any_creacio.contains(".") || canco_any_creacio.contains(",")) {
      errorAfegirCanco += "L'any de creacio ha de ser un numero enter.<br>";
    } else if (canco_any_creacio.matches(".*[a-zA-Z]+.*")) {
      errorAfegirCanco += "L'any de creacio ha de ser un numero enter.<br>";
    } else if (Integer.parseInt(canco_any_creacio) < 1900
        || Integer.parseInt(canco_any_creacio) > 2022) {
      errorAfegirCanco += "L'any de creacio ha de ser un numero entre 1900 i 2022.<br>";
    }

    errorAfegirCanco += "</html>";

    if (!errorAfegirCanco.equals("<html></html>")) {
      labelErrorMod.setVisible(true);
      labelErrorMod.setText(errorAfegirCanco);
    } else {
      try {
        DBProducte.insertAlbum(producte_titol, producte_estil, producte_actiu, producte_tipus,
            Integer.parseInt(canco_any_creacio));
        labelErrorMod.setText("");
        tableProductes.clearSelection();
        filtrar();
        JOptionPane.showConfirmDialog(null, "Album Afegit Correctament", "Album afegit!",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE);
        // wait for JOptionPane to close and then netejar() and return;
        netejar();
      } catch (PersistenciaException error) {
        System.out.println(error.getMessage());
        labelErrorMod.setText("");
        tableProductes.clearSelection();
        filtrar();
        netejar();
      }
    }
  }

  public void afegirLlista() {
    // labelTitolModProductes.setVisible(true);
    // textIdHiddenMod.setVisible(false);
    labelTipusMod.setVisible(true);
    cbTipusMod.setVisible(true);
    cbTipusMod.setEnabled(false);

    labelTitolMod.setVisible(true);
    textTitolMod.setVisible(true);
    textTitolMod.setEnabled(true);
    labelEstilMod.setVisible(true);
    cbEstilMod.setVisible(true);
    cbEstilMod.setEnabled(true);
    labelActiuMod.setVisible(true);
    rbSiActiuMod.setVisible(true);
    rbSiActiuMod.setEnabled(true);
    rbNoActiuMod.setVisible(true);
    rbNoActiuMod.setEnabled(true);
    labelDuradaMod.setVisible(false);
    textDuradaMod.setVisible(false);
    textDuradaMod.setEnabled(false);
    labelAnyCreacioMod.setVisible(false);
    textAnyCreacioMod.setVisible(false);
    textAnyCreacioMod.setEnabled(false);
    labelInterpretsMod.setVisible(false);
    cbInterpretsMod.setVisible(false);
    cbInterpretsMod.setEnabled(false);
    buttonCancelarMod.setVisible(true);
    buttonGuardarMod.setVisible(true);
    labelErrorMod.setVisible(true);
  }

  public void afegirLlistaButton() {
    String errorAfegirCanco = "<html>";

    String producte_titol = textTitolMod.getText();
    String producte_estil = cbEstilMod.getSelectedItem().toString();
    char producte_actiu;
    if (rbSiActiuMod.isSelected()) {
      producte_actiu = 'S';
    } else {
      producte_actiu = 'N';
    }
    char producte_tipus = 'L';

    if (producte_titol.equals("") || producte_titol == null || producte_titol.isEmpty()
        || producte_titol.length() > 100) {
      errorAfegirCanco += "El titol no pot ser buit i tenir mes de 100 caracters.<br>";
    }

    errorAfegirCanco += "</html>";

    if (!errorAfegirCanco.equals("<html></html>")) {
      labelErrorMod.setVisible(true);
      labelErrorMod.setText(errorAfegirCanco);
    } else {
      try {
        DBProducte.insertLlista(producte_titol, producte_estil, producte_actiu, producte_tipus);
        labelErrorMod.setText("");
        tableProductes.clearSelection();
        filtrar();
        JOptionPane.showConfirmDialog(null, "Llista Afegida Correctament", "Llista afegida!",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE);
        // wait for JOptionPane to close and then netejar() and return;
        netejar();
      } catch (PersistenciaException error) {
        System.out.println(error.getMessage());
        labelErrorMod.setText("");
        tableProductes.clearSelection();
        filtrar();
        netejar();
      }
    }
  }

  public void modificar(){
    
  }
}
