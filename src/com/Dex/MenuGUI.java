package com.Dex;

import com.Dex.Main;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuGUI {
  private final JFrame frame = new JFrame();
  
  public MenuGUI() {
    JPanel centerPanel = new JPanel(new GridLayout(0, 2));
    JButton button1 = new JButton("Чатик");
    JButton button2 = new JButton("Редактор экономики");
    button1.addActionListener(e -> Main.aquarium.newIteration());
    button2.addActionListener(e -> Main.printinfo());
    centerPanel.add(button1);
    centerPanel.add(button2);
    this.frame.getContentPane().add(centerPanel);
    this.frame.setDefaultCloseOperation(3);
    this.frame.setTitle("Меню");
    this.frame.pack();
    this.frame.setVisible(true);
  }
}
