/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.vistes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import org.milaifontanals.persistencia.Persistencia;
import org.milaifontanals.persistencia.PersistenciaException;

/**
 *
 * @author Ion
 */
public class VistaPrincipal extends JFrame {

  public VistaPrincipal() {
    initComponents();
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        int confirmacio = JOptionPane.showConfirmDialog(null, "Vols fer COMMIT?", "Confirmació",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirmacio == 0) {
          try {
            Persistencia.commit();
          } catch (PersistenciaException e1) {
            System.out.println("Error al fer commit.\n" + e1.getMessage());
          }
        } else if (confirmacio == 1) {
          try {
            Persistencia.rollback();
          } catch (PersistenciaException e1) {
            System.out.println("Error al fer rollback.\n" + e1.getMessage());
          }
        } else {
          try {
            Persistencia.close();
          } catch (PersistenciaException e1) {
            System.out.println("Error al tancar la connexió.\n" + e1.getMessage());
          }
        }
      }
    });
  }

  private void initComponents() {
    setTitle("Mila Spotify");
    setSize(1900, 900);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(null);
    setResizable(false);

    JMenuBar jMenuBar = new JMenuBar();
    JPanel jPanel = new JPanel();

    JMenu jMenu = new JMenu("Productes");
    JMenu jMenu1 = new JMenu("Artistes");
    JMenu jMenu2 = new JMenu("Clients");
    JMenu jMenu3 = new JMenu("Reproduccions");
    JMenu jMenu4 = new JMenu("Ajuda");

    JMenuItem jMenuItem = new JMenuItem("Productes");
    jMenuItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        jPanel.removeAll();
        jPanel.add(new VistaProducte());
        jPanel.repaint();
        jPanel.revalidate();
      }
    });
    JMenuItem jMenuItem1 = new JMenuItem("Estils");
    jMenuItem1.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        jPanel.removeAll();
        jPanel.add(new ToDo());
        jPanel.repaint();
        jPanel.revalidate();
      }
    });

    JMenuItem jMenuItem2 = new JMenuItem("Artistes");
    jMenuItem2.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        jPanel.removeAll();
        jPanel.add(new ToDo());
        jPanel.repaint();
        jPanel.revalidate();
      }
    });
    JMenuItem jMenuItem3 = new JMenuItem("Paisos");
    jMenuItem3.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        jPanel.removeAll();
        jPanel.add(new ToDo());
        jPanel.repaint();
        jPanel.revalidate();
      }
    });

    JMenuItem jMenuItem4 = new JMenuItem("Clients");
    jMenuItem4.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        jPanel.removeAll();
        jPanel.add(new ToDo());
        jPanel.repaint();
        jPanel.revalidate();
      }
    });
    JMenuItem jMenuItem5 = new JMenuItem("Paisos");
    jMenuItem5.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        jPanel.removeAll();
        jPanel.add(new ToDo());
        jPanel.repaint();
        jPanel.revalidate();
      }
    });
    JMenuItem jMenuItem6 = new JMenuItem("Eliminar Client");
    jMenuItem6.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        jPanel.removeAll();
        jPanel.add(new ToDo());
        jPanel.repaint();
        jPanel.revalidate();
      }
    });

    jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        jPanel.removeAll();
        jPanel.add(new ToDo());
        jPanel.repaint();
        jPanel.revalidate();
      }
    });

    jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        jPanel.removeAll();
        jPanel.add(new ToDo());
        jPanel.repaint();
        jPanel.revalidate();
      }
    });

    jMenu.add(jMenuItem);
    jMenu.add(jMenuItem1);

    jMenu1.add(jMenuItem2);
    jMenu1.add(jMenuItem3);

    jMenu2.add(jMenuItem4);
    jMenu2.add(jMenuItem5);
    jMenu2.add(jMenuItem6);

    jMenuBar.add(jMenu);
    jMenuBar.add(jMenu1);
    jMenuBar.add(jMenu2);
    jMenuBar.add(jMenu3);
    jMenuBar.add(jMenu4);

    jPanel.setBounds(0, 0, 1888, 788);
    jPanel.setLayout(null);

    JLabel jLabel = new JLabel("Benvingut a Mila Spotify");
    jLabel.setFont(new java.awt.Font("Tahoma", 1, 56));
    jLabel.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel.setVerticalAlignment(SwingConstants.CENTER);
    jLabel.setBounds(0, 0, 1888, 788);

    jPanel.add(jLabel);

    setJMenuBar(jMenuBar);
    add(jPanel);

  }

  public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new VistaPrincipal().setVisible(true);
      }
    });
  }

}