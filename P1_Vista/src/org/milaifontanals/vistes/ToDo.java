/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.vistes;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
/**
 *
 * @author Ion
 */
public class ToDo extends JPanel {
  public ToDo() {
    initComponents();
    setVisible(true);
  }

  private void initComponents() {
    JLabel jLabel = new JLabel("TO DO");
    jLabel.setBounds(0, 0, 1888, 788);
    jLabel.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel.setVerticalAlignment(SwingConstants.CENTER);
    jLabel.setFont(new java.awt.Font("Tahoma", 1, 56));
    add(jLabel);

    setLayout(null);
    setBounds(0, 0, 1888, 788);
  }
}